import type { ContentFile } from '../types';

export interface SearchResult {
  file: ContentFile;
  folderPath: string;
  excerpt: string;
  matchesIn: string;
  score: number;
}
