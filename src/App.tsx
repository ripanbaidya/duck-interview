import { useCallback, useEffect, useMemo, useRef, useState } from 'react';
import { AppHeader } from './components/AppHeader';
import { AppFooter } from './components/AppFooter';
import { AboutPage } from './components/AboutPage';
import { FolderPage } from './components/FolderPage';
import { Icon } from './components/Icon';
import { LoadingState } from './components/LoadingState';
import { Sidebar } from './components/Sidebar';
import { buildFolderTree, findFolder } from './lib/catalog';
import { useContentCatalog } from './hooks/useContentCatalog';
import { useSolutionSelection } from './hooks/useSolutionSelection';
import type { ContentFile } from './types';

const ABOUT_PATH = 'about';

function App() {
  const { files, error, isLoading } = useContentCatalog();
  const [theme, setTheme] = useState<'dark' | 'light'>(() => {
    try {
      return window.localStorage.getItem('duck-lc-theme') === 'light' ? 'light' : 'dark';
    } catch (cause: unknown) {
      console.error('Unable to read the saved appearance preference.', cause);
      return 'dark';
    }
  });
  const [selectedPath, setSelectedPath] = useState('');
  const [aboutPage, setAboutPage] = useState(false);
  const [selectedFilePath, setSelectedFilePath] = useState('');
  const [searchQuery, setSearchQuery] = useState('');
  const [searchOpen, setSearchOpen] = useState(false);
  const [mobileNavOpen, setMobileNavOpen] = useState(false);
  const [sidebarVisible, setSidebarVisible] = useState(true);
  const [copied, setCopied] = useState('');
  const searchInputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    document.documentElement.dataset.theme = theme;
    document.documentElement.style.colorScheme = theme;
    document.querySelector('meta[name="theme-color"]')?.setAttribute(
      'content',
      theme === 'light' ? '#f7f8fc' : '#040508',
    );
    try {
      window.localStorage.setItem('duck-lc-theme', theme);
    } catch (cause: unknown) {
      console.error('Unable to save the appearance preference.', cause);
    }
  }, [theme]);

  const sections = useMemo(() => buildFolderTree(files), [files]);
  const currentFolder = useMemo(
    () => findFolder(sections, selectedPath),
    [sections, selectedPath],
  );
  const {
    activeLanguage,
    activeSourceFiles,
    readmes,
    selectedSource,
    setActiveLanguage,
    setSelectedFilePath: selectSolutionFile,
  } = useSolutionSelection(currentFolder, selectedFilePath);

  useEffect(() => {
    if (!sections.length) return;
    const requestedPath = getHashPath();
    setAboutPage(requestedPath === ABOUT_PATH);
    const initialPath = requestedPath && requestedPath !== ABOUT_PATH && findFolder(sections, requestedPath)
      ? requestedPath
      : sections[0]!.path;
    setSelectedPath(initialPath);
  }, [sections]);

  useEffect(() => {
    const handleHistory = () => {
      const path = getHashPath();
      setAboutPage(path === ABOUT_PATH);
      const nextPath = path && path !== ABOUT_PATH && findFolder(sections, path)
        ? path
        : sections[0]?.path ?? '';
      setSelectedPath(nextPath);
      setSelectedFilePath('');
    };
    window.addEventListener('popstate', handleHistory);
    window.addEventListener('hashchange', handleHistory);
    return () => {
      window.removeEventListener('popstate', handleHistory);
      window.removeEventListener('hashchange', handleHistory);
    };
  }, [sections]);

  const navigateTo = useCallback((path: string, filePath = '') => {
    if (path === ABOUT_PATH) {
      setAboutPage(true);
      setMobileNavOpen(false);
      setSearchOpen(false);
      setSearchQuery('');
      window.history.pushState(null, '', `#${ABOUT_PATH}`);
      return;
    }

    setAboutPage(false);
    setSelectedPath(path);
    setSelectedFilePath(filePath);
    setMobileNavOpen(false);
    setSearchOpen(false);
    setSearchQuery('');
    window.history.pushState(null, '', `#${encodeURIComponent(path)}`);
  }, []);

  const goHome = useCallback(() => {
    const homePath = sections[0]?.path;
    if (homePath) navigateTo(homePath);
  }, [navigateTo, sections]);

  const copyCode = useCallback(async (file: ContentFile) => {
    try {
      await navigator.clipboard.writeText(file.content);
      setCopied(file.path);
      window.setTimeout(() => setCopied(''), 1800);
    } catch (cause: unknown) {
      console.error('Unable to copy the selected source file.', cause);
      setCopied('error');
      window.setTimeout(() => setCopied(''), 2400);
    }
  }, []);

  if (isLoading || error) return <LoadingState error={error} />;

  const sourceFileCount = files.filter((file) => file.extension !== '.md').length;

  return (
    <div className="flex min-h-screen flex-col" data-theme={theme}>
      <AppHeader
        files={files}
        mobileNavOpen={mobileNavOpen}
        onHome={goHome}
        onNavigate={navigateTo}
        onToggleTheme={() => setTheme((current) => current === 'dark' ? 'light' : 'dark')}
        searchInputRef={searchInputRef}
        searchOpen={searchOpen}
        searchQuery={searchQuery}
        setMobileNavOpen={setMobileNavOpen}
        setSearchOpen={setSearchOpen}
        setSearchQuery={setSearchQuery}
        theme={theme}
      />

      <div className="flex min-h-[calc(100vh-66px)] flex-1 max-[760px]:min-h-[calc(100vh-96px)]">
        <div
          id="content-sidebar"
          className={`sticky top-[66px] h-[calc(100vh-66px)] shrink-0 overflow-hidden bg-black transition-[width] duration-200 ${sidebarVisible ? 'w-[280px] min-[1500px]:w-[300px]' : 'w-0'} max-[760px]:fixed max-[760px]:bottom-0 max-[760px]:left-0 max-[760px]:top-[96px] max-[760px]:z-30 max-[760px]:h-auto max-[760px]:w-[min(310px,86vw)] max-[760px]:transition-transform max-[760px]:duration-200 max-[760px]:shadow-[12px_0_35px_rgba(0,0,0,0.45)] ${mobileNavOpen ? 'max-[760px]:translate-x-0' : 'max-[760px]:-translate-x-[105%]'}`}
        >
          <Sidebar
            aboutPage={aboutPage}
            filesCount={sourceFileCount}
            sections={sections}
            selectedPath={selectedPath}
            onNavigate={navigateTo}
            onToggle={() => setSidebarVisible(false)}
          />
        </div>
        {!sidebarVisible && (
          <button
            aria-controls="content-sidebar"
            aria-expanded={false}
            aria-label="Show sidebar"
            className="fixed left-3 top-[78px] z-20 hidden size-8 place-items-center rounded-full border-0 bg-[#111114] text-[#a1a1aa] shadow-[0_2px_12px_rgba(0,0,0,0.35)] transition-colors hover:bg-[#1b1b20] hover:text-[#e4e4e7] min-[761px]:grid"
            onClick={() => setSidebarVisible(true)}
            title="Show sidebar"
          >
            <Icon className="size-[17px]" name="chevronRight" />
          </button>
        )}
        {mobileNavOpen && (
          <button
            aria-label="Close navigation"
            className="fixed inset-x-0 bottom-0 top-[96px] z-20 hidden w-full border-0 bg-black/55 max-[760px]:block"
            onClick={() => setMobileNavOpen(false)}
          />
        )}
        <main className="flex min-w-0 flex-1 flex-col max-[760px]:w-full">
          <div className="flex-1">
            {aboutPage ? (
              <AboutPage />
            ) : currentFolder ? (
              <FolderPage
                activeLanguage={activeLanguage}
                activeSourceFiles={activeSourceFiles}
                copied={copied}
                folder={currentFolder}
                onCopy={copyCode}
                onLanguageChange={(language) => {
                  setActiveLanguage(language);
                  selectSolutionFile('');
                }}
                onNavigate={navigateTo}
                onVariantChange={(file) => selectSolutionFile(file.path)}
                readmes={readmes}
                sections={sections}
                selectedPath={selectedPath}
                selectedSource={selectedSource}
              />
            ) : (
              <div className="flex min-h-[calc(100vh-66px)] flex-col items-center justify-center p-[25px] text-center text-[#888891]">
                <span className="text-[9px] font-bold tracking-[0.12em] text-[#60a5fa]">YOUR LIBRARY</span>
                <h1 className="my-1 text-[25px] font-semibold text-[#f4f4f5]">Nothing here yet</h1>
                <p className="text-xs">Add a folder with source code or Markdown to get started.</p>
              </div>
            )}
          </div>
          <AppFooter onHome={goHome} />
        </main>
      </div>
    </div>
  );
}

function getHashPath(): string {
  try {
    return decodeURIComponent(window.location.hash.slice(1));
  } catch {
    return '';
  }
}

export default App;
