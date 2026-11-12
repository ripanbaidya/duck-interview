import { countFiles, getFolderTitle } from '../lib/catalog';
import type { FolderNode } from '../types';
import { Icon } from './Icon';

interface FolderHeadingProps {
  folder: FolderNode;
  hasDocumentation: boolean;
  questionLink?: { title: string; url: string };
}

export function FolderHeading({ folder, hasDocumentation, questionLink }: FolderHeadingProps) {
  const sourceFiles = folder.files.filter((file) => file.extension !== '.md');
  const itemCount = countFiles(folder);

  return (
    <div className="mb-[29px] flex items-center justify-between gap-[18px] max-[760px]:mb-[23px]">
      <div>
        <span className="text-[9px] font-bold tracking-[0.12em] text-[#a1a1aa]">{folder.path.includes('/') ? 'FOLDER' : 'COLLECTION'}</span>
        <div className="flex flex-wrap items-center gap-x-3 gap-y-2">
          <h1 className="my-1 text-[clamp(25px,3vw,34px)] font-semibold tracking-[-0.045em] text-[#f4f4f5] max-[760px]:text-[26px]">{getFolderTitle(folder.path)}</h1>
          {questionLink && (
            <a
              aria-label={`Open ${questionLink.title} question in a new tab`}
              className="inline-flex h-[30px] items-center gap-[6px] rounded-[5px] border border-[#313137] bg-[#19191d] px-2.5 text-[10px] font-medium text-[#dedee3] transition-colors hover:border-[#1e3a5f] hover:text-[#60a5fa]"
              href={questionLink.url}
              rel="noopener noreferrer"
              target="_blank"
            >
              Open question
              <Icon className="size-[13px]" name="externalLink" />
            </a>
          )}
        </div>
        <p className="m-0 text-[11.5px] text-[#b4b4bd]">
          {sourceFiles.length > 0
            ? `${sourceFiles.length} ${sourceFiles.length === 1 ? 'solution file' : 'solution files'}`
            : `${itemCount} ${itemCount === 1 ? 'item' : 'items'}`}
          {folder.children.length > 0 && ` · ${folder.children.length} ${folder.children.length === 1 ? 'subfolder' : 'subfolders'}`}
          {hasDocumentation && ' · documentation'}
        </p>
      </div>
    </div>
  );
}
