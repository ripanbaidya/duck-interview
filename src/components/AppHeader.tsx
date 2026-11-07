import { useEffect } from 'react';
import type { RefObject } from 'react';
import type { ContentFile } from '../types';
import { GlobalSearch } from './GlobalSearch';
import { Icon } from './Icon';

interface AppHeaderProps {
  files: ContentFile[];
  mobileNavOpen: boolean;
  onHome: () => void;
  onNavigate: (path: string, filePath?: string) => void;
  onToggleTheme: () => void;
  searchOpen: boolean;
  searchQuery: string;
  searchInputRef: RefObject<HTMLInputElement | null>;
  setMobileNavOpen: (open: boolean) => void;
  setSearchOpen: (open: boolean) => void;
  setSearchQuery: (query: string) => void;
  theme: 'dark' | 'light';
}

export function AppHeader({
  files,
  mobileNavOpen,
  onHome,
  onNavigate,
  onToggleTheme,
  searchOpen,
  searchQuery,
  searchInputRef,
  setMobileNavOpen,
  setSearchOpen,
  setSearchQuery,
  theme,
}: AppHeaderProps) {
  useEffect(() => {
    const handleShortcut = (event: globalThis.KeyboardEvent) => {
      if ((event.metaKey || event.ctrlKey) && event.key.toLowerCase() === 'k') {
        event.preventDefault();
        searchInputRef.current?.focus();
        setSearchOpen(true);
      }
      if (event.key === 'Escape') setSearchOpen(false);
    };
    window.addEventListener('keydown', handleShortcut);
    return () => window.removeEventListener('keydown', handleShortcut);
  }, [searchInputRef, setSearchOpen]);

  return (
    <header className="sticky top-0 z-50 grid h-[66px] grid-cols-[minmax(0,1fr)_minmax(0,540px)_minmax(0,1fr)] items-center bg-black px-7 max-[760px]:h-[96px] max-[760px]:grid-cols-[minmax(0,1fr)_36px_36px] max-[760px]:grid-rows-[36px_36px] max-[760px]:gap-x-3 max-[760px]:gap-y-2 max-[760px]:px-3 max-[760px]:py-2">
      <div className="col-start-1 row-start-1 flex items-center gap-2 justify-self-start">
        <button
          aria-label="Duck Interview home"
          className="flex items-center gap-2 rounded-full border-0 bg-transparent p-1 pr-3 whitespace-nowrap transition-opacity hover:opacity-80"
          onClick={onHome}
        >
          <img alt="" aria-hidden="true" className="size-[26px] object-contain max-[760px]:size-[23px]" src="/favicon.png" />
          <span className="text-[15px] font-semibold tracking-[-0.035em] text-[#e4e4e7] max-[760px]:text-[13px]">Duck Interview</span>
        </button>
      </div>
      <GlobalSearch
        files={files}
        onNavigate={onNavigate}
        open={searchOpen}
        query={searchQuery}
        searchInputRef={searchInputRef}
        setOpen={setSearchOpen}
        setQuery={setSearchQuery}
      />
      <button
        aria-label={theme === 'dark' ? 'Switch to light mode' : 'Switch to dark mode'}
        aria-pressed={theme === 'light'}
        className="col-start-3 row-start-1 grid size-9 place-items-center justify-self-end rounded-full border-0 bg-transparent text-[#a1a1aa] transition-colors hover:bg-white/[0.06] hover:text-[#e4e4e7] max-[760px]:col-start-2"
        onClick={onToggleTheme}
        title={theme === 'dark' ? 'Switch to light mode' : 'Switch to dark mode'}
      >
        <Icon className="size-[17px]" name={theme === 'dark' ? 'sun' : 'moon'} />
      </button>
      <button
        aria-expanded={mobileNavOpen}
        aria-label={mobileNavOpen ? 'Close navigation' : 'Open navigation'}
        className="col-start-2 row-start-1 hidden size-9 place-items-center justify-self-end rounded-full border-0 bg-transparent text-[#a1a1aa] transition-colors hover:bg-white/[0.06] hover:text-[#e4e4e7] max-[760px]:col-start-3 max-[760px]:grid"
        onClick={() => setMobileNavOpen(!mobileNavOpen)}
      >
        <Icon className="size-[17px]" name={mobileNavOpen ? 'close' : 'menu'} />
      </button>
    </header>
  );
}
