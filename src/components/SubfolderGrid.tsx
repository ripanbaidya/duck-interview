import { countFiles, getDisplayName } from '../lib/catalog';
import type { FolderNode } from '../types';
import { Icon } from './Icon';

interface SubfolderGridProps {
  folders: FolderNode[];
  isSectionRoot: boolean;
  onNavigate: (path: string) => void;
}

export function SubfolderGrid({ folders, isSectionRoot, onNavigate }: SubfolderGridProps) {
  if (!folders.length) return null;

  return (
    <section className="mb-7" aria-label="Subfolders">
      <div className="mb-[11px] flex items-center justify-between gap-3">
        <h2 className="m-0 text-[13px] font-semibold tracking-[-0.01em] text-[#e2e2e7]">{isSectionRoot ? 'Browse topics' : 'Inside this folder'}</h2>
        <span className="text-[10px] text-[#a1a1aa]">{folders.length} folders</span>
      </div>
      <div className="grid grid-cols-[repeat(auto-fill,minmax(min(100%,230px),1fr))] gap-2 max-[760px]:grid-cols-1">
        {folders.map((folder) => {
          const sourceCount = folder.files.filter((file) => file.extension !== '.md').length;
          const total = countFiles(folder);
          return (
            <button className="flex min-h-[62px] min-w-0 items-center gap-[11px] rounded-md border border-[#1c1c20] bg-[#050505] px-3 py-[10px] text-left transition-[border-color,background] duration-150 hover:border-[#292b30] hover:bg-[#08090b] max-[760px]:min-h-[58px]" key={folder.path} onClick={() => onNavigate(folder.path)}>
              <span className="grid size-[31px] shrink-0 place-items-center rounded-[5px] bg-[#0a0a0c] text-[#60a5fa]"><Icon className="size-4" name="folder" /></span>
              <span className="grid min-w-0 flex-1 gap-1">
                <span className="overflow-hidden text-ellipsis whitespace-nowrap text-[11.5px] font-medium text-[#dcdce1]">{getDisplayName(folder.name)}</span>
                <span className="text-[10px] text-[#a1a1aa]">
                  {sourceCount
                    ? `${sourceCount} ${sourceCount === 1 ? 'solution' : 'solutions'}`
                    : `${total} ${total === 1 ? 'item' : 'items'}`}
                  {folder.children.length > 0 && ` · ${folder.children.length} folders`}
                </span>
              </span>
              <Icon className="size-[14px] text-[#676770]" name="chevronRight" />
            </button>
          );
        })}
      </div>
    </section>
  );
}
