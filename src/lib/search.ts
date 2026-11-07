import { getParentPath } from './catalog';
import type { ContentFile } from '../types';
import type { SearchResult } from '../types/search';

export function findSearchResults(files: ContentFile[], query: string): SearchResult[] {
  const terms = query.toLowerCase().trim().split(/\s+/).filter(Boolean);
  if (!terms.length) return [];

  return files
    .map((file) => {
      const path = file.path.toLowerCase();
      const content = file.content.toLowerCase();
      const haystack = `${path} ${content}`;
      if (!terms.every((term) => haystack.includes(term))) return null;

      const pathMatch = terms.every((term) => path.includes(term));
      const matchTerm = terms.find((term) => !path.includes(term)) ?? terms[0]!;
      const index = content.indexOf(matchTerm);
      const excerpt = index < 0
        ? ''
        : file.content.slice(Math.max(0, index - 45), Math.min(file.content.length, index + 100))
          .replace(/\s+/g, ' ')
          .trim();

      return {
        file,
        folderPath: getParentPath(file.path),
        excerpt,
        matchesIn: pathMatch ? 'PATH' : file.extension === '.md' ? 'NOTES' : 'CODE',
        score: (pathMatch ? 0 : 1) + (path.includes(terms[0]!) ? 0 : 1),
      };
    })
    .filter((result): result is SearchResult => result !== null)
    .sort((a, b) => a.score - b.score || a.file.path.localeCompare(b.file.path))
    .slice(0, 12);
}
