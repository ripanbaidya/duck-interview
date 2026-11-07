import type { ContentFile, FolderNode } from '../types';
import { Breadcrumbs } from './Breadcrumbs';
import { FolderHeading } from './FolderHeading';
import { MarkdownDocument } from './MarkdownDocument';
import { SolutionViewer } from './SolutionViewer';
import { SubfolderGrid } from './SubfolderGrid';

interface FolderPageProps {
  activeLanguage: string;
  activeSourceFiles: ContentFile[];
  copied: string;
  folder: FolderNode;
  onCopy: (file: ContentFile) => void;
  onLanguageChange: (language: string) => void;
  onNavigate: (path: string, filePath?: string) => void;
  onVariantChange: (file: ContentFile) => void;
  readmes: ContentFile[];
  sections: FolderNode[];
  selectedPath: string;
  selectedSource?: ContentFile;
}

export function FolderPage({
  activeLanguage,
  activeSourceFiles,
  copied,
  folder,
  onCopy,
  onLanguageChange,
  onNavigate,
  onVariantChange,
  readmes,
  sections,
  selectedPath,
  selectedSource,
}: FolderPageProps) {
  const sourceFiles = folder.files.filter((file) => file.extension !== '.md');
  const primaryReadme = readmes.find((file) => file.name.toLowerCase() === 'readme.md');
  const otherReadmes = readmes.filter((file) => file !== primaryReadme);
  const isSectionRoot = sections.some((section) => section.path === folder.path);

  return (
    <div className="mx-auto w-full max-w-[1120px] px-[clamp(22px,4vw,58px)] pb-[72px] pt-[27px] max-[760px]:px-4 max-[760px]:pb-[50px] max-[760px]:pt-5 max-[420px]:px-3">
      <Breadcrumbs path={selectedPath} onNavigate={onNavigate} />
      <FolderHeading folder={folder} hasDocumentation={readmes.length > 0} />
      <SubfolderGrid folders={folder.children} isSectionRoot={isSectionRoot} onNavigate={onNavigate} />

      {primaryReadme && (
        <section className="my-4 mb-6">
          <div className="mb-[9px] text-[9px] font-bold tracking-[0.1em] text-[#85858f]">OVERVIEW</div>
          <MarkdownDocument content={primaryReadme.content} />
        </section>
      )}
      {otherReadmes.map((file) => (
        <section className="my-4 mb-6" key={file.path}>
          <div className="mb-[9px] text-[9px] font-bold tracking-[0.1em] text-[#85858f]">{file.name.toUpperCase()}</div>
          <MarkdownDocument content={file.content} />
        </section>
      ))}

      {sourceFiles.length > 0 && (
        <SolutionViewer
          allSourceFiles={sourceFiles}
          activeLanguage={activeLanguage}
          activeSourceFiles={activeSourceFiles}
          copied={copied}
          selectedSource={selectedSource}
          onCopy={onCopy}
          onLanguageChange={onLanguageChange}
          onVariantChange={onVariantChange}
        />
      )}

      {!sourceFiles.length && !readmes.length && folder.children.length === 0 && (
        <div className="mt-5 rounded-lg border border-dashed border-[#303036] p-5 text-center text-[11px] text-[#85858f]">No supported files in this folder yet.</div>
      )}
    </div>
  );
}
