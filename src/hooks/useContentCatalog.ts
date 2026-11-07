import { useEffect, useState } from 'react';
import type { ContentFile } from '../types';

interface ContentCatalog {
  files: ContentFile[];
  error: string;
  isLoading: boolean;
}

export function useContentCatalog(): ContentCatalog {
  const [files, setFiles] = useState<ContentFile[]>([]);
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    fetch(`${import.meta.env.BASE_URL}content-catalog.json`)
      .then((response) => {
        if (!response.ok) throw new Error(`Catalog request failed (${response.status})`);
        return response.json() as Promise<ContentFile[]>;
      })
      .then(setFiles)
      .catch((cause: unknown) => {
        setError(cause instanceof Error ? cause.message : 'Unable to load the content catalog.');
      })
      .finally(() => setIsLoading(false));
  }, []);

  return { files, error, isLoading };
}
