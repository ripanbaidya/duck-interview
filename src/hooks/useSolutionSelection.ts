import { useEffect, useMemo, useState } from 'react';
import { getLanguage } from '../lib/catalog';
import type { ContentFile, FolderNode } from '../types';

interface SolutionSelection {
  activeLanguage: string;
  activeSourceFiles: ContentFile[];
  readmes: ContentFile[];
  selectedSource?: ContentFile;
  setActiveLanguage: (language: string) => void;
  setSelectedFilePath: (path: string) => void;
}

export function useSolutionSelection(
  folder: FolderNode | undefined,
  preferredFilePath: string,
): SolutionSelection {
  const [activeLanguage, setActiveLanguage] = useState('');
  const [selectedFilePath, setSelectedFilePath] = useState('');

  useEffect(() => {
    if (!folder) {
      setActiveLanguage('');
      setSelectedFilePath('');
      return;
    }

    const sourceFiles = folder.files.filter((file) => file.extension !== '.md');
    const languages = [...new Set(sourceFiles.map((file) => getLanguage(file.extension)))];
    const preferredFile = sourceFiles.find((file) => file.path === preferredFilePath);
    const nextLanguage = preferredFile ? getLanguage(preferredFile.extension) : languages[0] ?? '';

    setActiveLanguage((current) => languages.includes(current) ? current : nextLanguage);
    setSelectedFilePath((current) => {
      if (preferredFile) return preferredFile.path;
      return sourceFiles.some((file) => file.path === current) ? current : '';
    });
  }, [folder, preferredFilePath]);

  const activeSourceFiles = useMemo(
    () => folder?.files
      .filter((file) => file.extension !== '.md' && getLanguage(file.extension) === activeLanguage)
      .sort((a, b) => a.name.localeCompare(b.name, undefined, { numeric: true })) ?? [],
    [activeLanguage, folder],
  );
  const selectedSource = activeSourceFiles.find((file) => file.path === selectedFilePath) ?? activeSourceFiles[0];
  const readmes = folder?.files.filter((file) => file.extension === '.md') ?? [];

  return {
    activeLanguage,
    activeSourceFiles,
    readmes,
    selectedSource,
    setActiveLanguage,
    setSelectedFilePath,
  };
}
