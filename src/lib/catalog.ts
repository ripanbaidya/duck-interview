import type { ContentFile, FolderNode } from '../types';

const languageNames: Record<string, string> = {
  '.c': 'C',
  '.cc': 'C++',
  '.cpp': 'C++',
  '.cs': 'C#',
  '.go': 'Go',
  '.h': 'C/C++',
  '.hpp': 'C++',
  '.java': 'Java',
  '.js': 'JavaScript',
  '.jsx': 'JavaScript',
  '.kt': 'Kotlin',
  '.php': 'PHP',
  '.py': 'Python',
  '.rb': 'Ruby',
  '.rs': 'Rust',
  '.scala': 'Scala',
  '.sh': 'Shell',
  '.sql': 'SQL',
  '.swift': 'Swift',
  '.ts': 'TypeScript',
  '.tsx': 'TypeScript',
};

export function getLanguage(extension: string): string {
  return languageNames[extension] ?? extension.slice(1).toUpperCase();
}

export function getLanguageKey(extension: string): string {
  if (['.cc', '.cpp', '.h', '.hpp'].includes(extension)) return 'cpp';
  if (['.js', '.jsx'].includes(extension)) return 'javascript';
  if (['.ts', '.tsx'].includes(extension)) return 'typescript';
  return extension.slice(1);
}

export function getParentPath(path: string): string {
  const separator = path.lastIndexOf('/');
  return separator === -1 ? '' : path.slice(0, separator);
}

export function getDisplayName(name: string): string {
  const formatted = name
    .replace(/([a-z])([A-Z0-9])/g, '$1 $2')
    .replace(/[-_]+/g, ' ')
    .replace(/\b\w/g, (character) => character.toUpperCase());
  return formatted.replace(/\b(dp|bst|bfs|dfs|mst|sql|api|ii|iii)\b/gi, (word) => word.toUpperCase());
}

export function getVariantName(file: ContentFile): string {
  return file.name.replace(/\.[^.]+$/, '').replace(/([a-zA-Z])(\d+)/g, '$1 $2');
}

export function buildFolderTree(files: ContentFile[]): FolderNode[] {
  const sections = new Map<string, FolderNode>();

  for (const file of files) {
    const segments = file.path.split('/');
    let node: FolderNode;
    const existingSection = sections.get(file.section);

    if (existingSection) {
      node = existingSection;
    } else {
      node = { name: file.section, path: file.section, children: [], files: [] };
      sections.set(file.section, node);
    }

    let currentPath = file.section;
    for (const segment of segments.slice(1, -1)) {
      currentPath = `${currentPath}/${segment}`;
      let child: FolderNode | undefined = node.children.find((candidate) => candidate.name === segment);

      if (!child) {
        child = { name: segment, path: currentPath, children: [], files: [] };
        node.children.push(child);
      }

      node = child;
    }

    node.files.push(file);
  }

  const sortNodes = (nodes: FolderNode[]) => {
    nodes.sort((a, b) => a.name.localeCompare(b.name));
    for (const node of nodes) sortNodes(node.children);
  };
  const result = [...sections.values()];
  sortNodes(result);
  return result;
}

export function countFiles(folder: FolderNode): number {
  return folder.files.length + folder.children.reduce((sum, child) => sum + countFiles(child), 0);
}

export function findFolder(nodes: FolderNode[], path: string): FolderNode | undefined {
  for (const node of nodes) {
    if (node.path === path) return node;
    const childMatch = findFolder(node.children, path);
    if (childMatch) return childMatch;
  }
  return undefined;
}

export function getFolderTitle(path: string): string {
  return getDisplayName(path.split('/').at(-1) ?? path);
}
