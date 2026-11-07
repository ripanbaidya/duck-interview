import { useEffect, useState } from 'react';
import { countFiles, getDisplayName } from '../lib/catalog';
import type { FolderNode } from '../types';
import { Icon } from './Icon';

interface FolderTreeItemProps {
  node: FolderNode;
  selectedPath: string;
  onSelect: (path: string) => void;
}

export function FolderTreeItem({ node, selectedPath, onSelect }: FolderTreeItemProps) {
  const isSelected = selectedPath === node.path;
  const isAncestor = selectedPath.startsWith(`${node.path}/`);
  const [expanded, setExpanded] = useState(isAncestor || isSelected);
  const total = countFiles(node);

  useEffect(() => {
    if (isAncestor) setExpanded(true);
  }, [isAncestor]);

  return (
    <div>
      <div className={`flex h-[31px] items-center rounded-[4px] py-0 pr-[7px] ${isSelected ? 'bg-[#0b1422]' : 'hover:bg-[#151519]'}`}>
        {node.children.length > 0 ? (
          <button
            aria-label={`${expanded ? 'Collapse' : 'Expand'} ${getDisplayName(node.name)}`}
            className="grid size-[28px] w-[25px] shrink-0 place-items-center border-0 bg-transparent text-[#a1a1aa]"
            onClick={() => setExpanded((open) => !open)}
          >
            <Icon className="size-[13px]" name={expanded ? 'chevronDown' : 'chevronRight'} />
          </button>
        ) : <span className="grid h-7 w-[25px] shrink-0 place-items-center" />}
        <button
          aria-expanded={node.children.length > 0 ? expanded : undefined}
          aria-current={isSelected ? 'page' : undefined}
          className={`flex min-w-0 flex-1 items-center justify-between gap-[6px] border-0 bg-transparent p-0 text-left ${isSelected ? 'text-[#bfdbfe]' : 'text-[#c4c4cc]'}`}
          onClick={() => {
            if (node.children.length > 0) setExpanded((open) => !open);
            onSelect(node.path);
          }}
          title={getDisplayName(node.name)}
        >
          <span className="overflow-hidden text-ellipsis whitespace-nowrap text-[11.5px]">{getDisplayName(node.name)}</span>
          <span className={`text-[10px] ${isSelected ? 'text-[#93c5fd]' : 'text-[#a1a1aa]'}`}>{total}</span>
        </button>
      </div>
      {expanded && node.children.length > 0 && (
        <div className="ml-3 border-l border-[#27272d] pl-[5px]">
          {node.children.map((child) => (
            <FolderTreeItem key={child.path} node={child} onSelect={onSelect} selectedPath={selectedPath} />
          ))}
        </div>
      )}
    </div>
  );
}
