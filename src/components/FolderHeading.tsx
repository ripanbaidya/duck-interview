import { countFiles, getFolderTitle } from '../lib/catalog';
import type { FolderNode } from '../types';

interface FolderHeadingProps {
  folder: FolderNode;
  hasDocumentation: boolean;
}

export function FolderHeading({ folder, hasDocumentation }: FolderHeadingProps) {
  const sourceFiles = folder.files.filter((file) => file.extension !== '.md');
  const itemCount = countFiles(folder);

  return (
    <div className="mb-[29px] flex items-center justify-between gap-[18px] max-[760px]:mb-[23px]">
      <div>
        <span className="text-[9px] font-bold tracking-[0.12em] text-[#a1a1aa]">{folder.path.includes('/') ? 'FOLDER' : 'COLLECTION'}</span>
        <h1 className="my-1 text-[clamp(25px,3vw,34px)] font-semibold tracking-[-0.045em] text-[#f4f4f5] max-[760px]:text-[26px]">{getFolderTitle(folder.path)}</h1>
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
