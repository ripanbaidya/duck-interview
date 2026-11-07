export interface ContentFile {
  path: string;
  section: string;
  name: string;
  extension: string;
  content: string;
}

export interface FolderNode {
  name: string;
  path: string;
  children: FolderNode[];
  files: ContentFile[];
}
