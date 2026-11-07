import { countFiles, getDisplayName } from '../lib/catalog';
import type { FolderNode } from '../types';
import { FolderTreeItem } from './FolderTreeItem';
import { Icon } from './Icon';

interface SidebarProps {
  aboutPage: boolean;
  filesCount: number;
  sections: FolderNode[];
  selectedPath: string;
  onNavigate: (path: string) => void;
  onToggle: () => void;
}

export function Sidebar({ aboutPage, filesCount, sections, selectedPath, onNavigate, onToggle }: SidebarProps) {
  const sectionName = selectedPath.split('/')[0] ?? '';
  const currentSection = sections.find((section) => section.name === sectionName);

  return (
    <aside className="flex h-full flex-col px-[14px] pb-3 pt-[23px] max-[760px]:pt-[19px]">
      <div className="flex items-center gap-2 px-[10px]">
        <span className="flex-1 text-[9px] font-bold tracking-[0.12em] text-[#a1a1aa]">LIBRARY</span>
        <span className="text-[10px] text-[#a1a1aa]">{filesCount} files</span>
        <button
          aria-expanded="true"
          aria-label="Hide sidebar"
          className="grid size-7 place-items-center rounded-full border-0 bg-transparent text-[#85858f] transition-colors hover:bg-white/[0.06] hover:text-[#e4e4e7] max-[760px]:hidden"
          onClick={onToggle}
          title="Hide sidebar"
        >
          <Icon className="size-[15px] -rotate-180" name="chevronRight" />
        </button>
      </div>
      <nav className="mb-6 mt-[13px] grid gap-1" aria-label="Content sections">
        {sections.map((section) => (
          <button
            aria-current={section.name === sectionName ? 'page' : undefined}
            className={`flex h-[35px] items-center gap-[9px] rounded-md border border-transparent px-[10px] text-left ${section.name === sectionName ? 'border-[#1e3a5f] bg-[#101a2d] text-[#dbeafe]' : 'bg-transparent text-[#c4c4cc] hover:bg-[#151519]'}`}
            key={section.path}
            onClick={() => onNavigate(section.path)}
          >
            <Icon className={`size-[15px] ${section.name === sectionName ? 'text-[#60a5fa]' : 'text-[#a1a1aa]'}`} name="folder" />
            <span className="flex-1 text-xs">{getDisplayName(section.name)}</span>
            <span className="text-[10px] text-[#a1a1aa]">{countFiles(section)}</span>
          </button>
        ))}
        <button
          aria-current={aboutPage ? 'page' : undefined}
          className={`flex h-[35px] items-center gap-[9px] rounded-md border border-transparent px-[10px] text-left ${aboutPage ? 'border-[#1e3a5f] bg-[#101a2d] text-[#dbeafe]' : 'bg-transparent text-[#c4c4cc] hover:bg-[#151519]'}`}
          onClick={() => onNavigate('about')}
        >
          <Icon className={`size-[15px] ${aboutPage ? 'text-[#60a5fa]' : 'text-[#a1a1aa]'}`} name="info" />
          <span className="flex-1 text-xs">About</span>
        </button>
      </nav>

      {!aboutPage && (
        <>
          <div className="mb-2 flex items-center justify-between px-[10px]">
            <span className="text-[9px] font-bold tracking-[0.12em] text-[#a1a1aa]">{sectionName ? getDisplayName(sectionName) : 'TOPICS'}</span>
          </div>
          <nav className="min-h-0 flex-1 overflow-auto [scrollbar-color:#303036_transparent] [scrollbar-width:thin]" aria-label="Folders">
            {currentSection?.children.map((node) => (
              <FolderTreeItem
                key={node.path}
                node={node}
                selectedPath={selectedPath}
                onSelect={onNavigate}
              />
            ))}
            {!sections.length && <p className="px-[10px] py-[6px] text-[11px] text-[#a1a1aa]">No content folders found.</p>}
          </nav>
        </>
      )}
    </aside>
  );
}
