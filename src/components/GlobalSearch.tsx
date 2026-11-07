import { useEffect, useMemo, useRef, useState } from 'react';
import type { KeyboardEvent, RefObject } from 'react';
import { getFolderTitle } from '../lib/catalog';
import { findSearchResults } from '../lib/search';
import type { ContentFile } from '../types';
import type { SearchResult } from '../types/search';
import { Icon } from './Icon';

interface GlobalSearchProps {
  files: ContentFile[];
  onNavigate: (path: string, filePath?: string) => void;
  open: boolean;
  query: string;
  searchInputRef: RefObject<HTMLInputElement | null>;
  setOpen: (open: boolean) => void;
  setQuery: (query: string) => void;
}

export function GlobalSearch({
  files,
  onNavigate,
  open,
  query,
  searchInputRef,
  setOpen,
  setQuery,
}: GlobalSearchProps) {
  const results = useMemo(() => findSearchResults(files, query), [files, query]);
  const [activeIndex, setActiveIndex] = useState(-1);
  const resultsListRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    setActiveIndex(-1);
  }, [query]);

  useEffect(() => {
    if (activeIndex < 0) return;
    resultsListRef.current
      ?.querySelector<HTMLElement>(`[data-result-index="${activeIndex}"]`)
      ?.scrollIntoView({ block: 'nearest' });
  }, [activeIndex]);

  const chooseResult = (result: SearchResult) => {
    onNavigate(result.folderPath, result.file.path);
    searchInputRef.current?.blur();
  };

  const handleKeyDown = (event: KeyboardEvent<HTMLInputElement>) => {
    if (!open || !query.trim() || !results.length) return;

    if (event.key === 'ArrowDown') {
      event.preventDefault();
      setActiveIndex((index) => (index + 1) % results.length);
    } else if (event.key === 'ArrowUp') {
      event.preventDefault();
      setActiveIndex((index) => (index <= 0 ? results.length - 1 : index - 1));
    } else if (event.key === 'Enter') {
      event.preventDefault();
      chooseResult(results[activeIndex] ?? results[0]!);
    }
  };

  return (
    <div className="relative z-30 col-start-2 w-full max-[760px]:col-span-3 max-[760px]:col-start-1 max-[760px]:row-start-2">
      <div className={`flex h-[38px] items-center gap-[10px] rounded-full border border-transparent bg-[#131316] px-[14px] text-[#92929c] transition-[background-color,box-shadow] duration-150 max-[760px]:h-9 max-[760px]:gap-2 max-[760px]:px-[12px] ${open ? 'bg-[#18181b] shadow-[0_0_0_1px_rgba(56,189,248,0.18),0_4px_18px_rgba(0,0,0,0.2)]' : 'hover:bg-[#17171a]'}`}>
        <Icon className="size-[15px] shrink-0 text-[#8b8b95]" name="search" />
        <input
          aria-label="Search all code and notes"
          onChange={(event) => {
            setQuery(event.target.value);
            setActiveIndex(-1);
            setOpen(true);
          }}
          onFocus={() => setOpen(true)}
          onKeyDown={handleKeyDown}
          className="w-full min-w-0 border-0 bg-transparent text-[12.5px] text-[#f4f4f5] outline-none placeholder:text-[#a1a1aa] max-[760px]:text-xs"
          placeholder="Search solutions, topics, notes…"
          ref={searchInputRef}
          value={query}
        />
        {query ? (
          <button className="grid size-[22px] shrink-0 place-items-center rounded-[5px] border-0 bg-transparent text-lg text-[#a1a1aa] hover:bg-[#27272c]" onClick={() => setQuery('')} aria-label="Clear search">×</button>
        ) : (
          <kbd className="inline-flex shrink-0 items-center gap-[3px] border-0 bg-transparent p-0 font-[inherit] text-[10px] text-[#888891] max-[760px]:hidden"><span>⌘</span> K</kbd>
        )}
      </div>
      {open && query.trim() && (
        <>
          <button className="fixed inset-0 z-[-1] w-full border-0 bg-black/35" onClick={() => setOpen(false)} aria-label="Close search results" />
          <div ref={resultsListRef} className="absolute left-0 top-[calc(100%+10px)] z-30 max-h-[min(520px,calc(100vh-100px))] w-full overflow-auto rounded-2xl border border-white/[0.07] bg-[#111114] p-2 shadow-[0_18px_48px_rgba(0,0,0,0.55)]" role="listbox" aria-label="Search results">
            <div className="sticky top-0 flex items-center justify-between bg-[#111114] px-3 py-[9px] text-[9px] font-bold tracking-[0.1em] text-[#8e8e98]">
              <span>{results.length ? 'RESULTS' : 'NO MATCHES'}</span>
              {results.length > 0 && <span className="font-medium tracking-normal">{results.length} shown</span>}
            </div>
            {results.map((result, index) => (
              <button
                className={`flex w-full items-start gap-[11px] rounded-xl border-0 px-3 py-[10px] text-left transition-colors ${index === activeIndex ? 'bg-white/[0.07]' : 'bg-transparent hover:bg-white/[0.045]'}`}
                data-result-index={index}
                key={result.file.path}
                onClick={() => chooseResult(result)}
                onMouseEnter={() => setActiveIndex(index)}
                role="option"
                aria-selected={index === activeIndex}
              >
                <span className="grid size-[26px] shrink-0 place-items-center rounded-lg bg-[#101a2d] text-[#60a5fa]"><Icon className="size-[14px]" name={result.file.extension === '.md' ? 'document' : 'code'} /></span>
                <span className="grid min-w-0 flex-1 gap-[3px]">
                  <span className="text-xs font-semibold text-[#e8e8eb]">{getFolderTitle(result.folderPath)}</span>
                  <span className="overflow-hidden text-ellipsis whitespace-nowrap text-[10px] text-[#a1a1aa]">{result.file.path}</span>
                  {result.excerpt && <span className="overflow-hidden text-ellipsis whitespace-nowrap text-[10px] text-[#a1a1aa]">{result.excerpt}</span>}
                </span>
                <span className="pt-0.5 text-[9px] text-[#a1a1aa]">{result.matchesIn}</span>
              </button>
            ))}
            {!results.length && <p className="px-[14px] pb-[14px] pt-[10px] text-xs text-[#92929c]">Try a problem name, topic, language, or phrase in the code.</p>}
            <div className="px-3 pb-1 pt-3 text-[10px] text-[#777780]">Searches filenames, folders, README files, and source code</div>
          </div>
        </>
      )}
    </div>
  );
}
