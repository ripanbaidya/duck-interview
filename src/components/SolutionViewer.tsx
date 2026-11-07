import { getLanguage, getVariantName } from '../lib/catalog';
import type { ContentFile } from '../types';
import { CodeViewer } from './CodeViewer';
import { Icon } from './Icon';

interface SolutionViewerProps {
  allSourceFiles: ContentFile[];
  activeLanguage: string;
  activeSourceFiles: ContentFile[];
  copied: string;
  selectedSource?: ContentFile;
  onCopy: (file: ContentFile) => void;
  onLanguageChange: (language: string) => void;
  onVariantChange: (file: ContentFile) => void;
}

export function SolutionViewer({
  allSourceFiles,
  activeLanguage,
  activeSourceFiles,
  copied,
  selectedSource,
  onCopy,
  onLanguageChange,
  onVariantChange,
}: SolutionViewerProps) {
  const languages = [...new Set(allSourceFiles.map((file) => getLanguage(file.extension)))];

  return (
    <section className="mt-[27px]">
      <div className="mb-[13px] flex items-end justify-between gap-3">
        <div>
          <h2 className="m-0 text-[15px] font-semibold text-[#e2e2e7]">Solutions</h2>
          <p className="mb-0 mt-1 text-[10.5px] text-[#81818b]">Browse implementations by language.</p>
        </div>
      </div>

      <div className="flex max-w-full gap-1 overflow-x-auto [scrollbar-width:none] [&::-webkit-scrollbar]:hidden" role="tablist" aria-label="Programming language">
        {languages.map((language) => (
          <button
            aria-selected={language === activeLanguage}
            className={`flex shrink-0 items-center gap-2 rounded-full border-0 px-3 py-2 text-[11px] transition-colors duration-150 ${language === activeLanguage ? 'bg-[#101a2d] text-[#bfdbfe]' : 'bg-transparent text-[#92929c] hover:bg-white/[0.04] hover:text-[#e4e4e7]'}`}
            key={language}
            onClick={() => onLanguageChange(language)}
            role="tab"
          >
            {language}
            <span className="text-[9px] text-[#85808f]">{allSourceFiles.filter((file) => getLanguage(file.extension) === language).length}</span>
          </button>
        ))}
      </div>

      <div className="mt-3 overflow-hidden rounded-lg border border-[#29292e] bg-[#0c0c0f]">
        <div className="flex min-h-[43px] items-center justify-between gap-3 border-b border-[#25252a] bg-[#111114] px-[11px] max-[420px]:px-[7px]">
          {activeSourceFiles.length > 1 ? (
            <div className="flex min-w-0 items-center gap-[5px] overflow-x-auto [scrollbar-width:none] [&::-webkit-scrollbar]:hidden" aria-label={`${activeLanguage} solution variants`}>
              {activeSourceFiles.map((file) => (
                <button
                  aria-pressed={file.path === selectedSource?.path}
                  className={`shrink-0 rounded-[5px] border px-2 py-[5px] text-[10.5px] ${file.path === selectedSource?.path ? 'border-[#1e3a5f] bg-[#101a2d] text-[#60a5fa]' : 'border-transparent bg-transparent text-[#b4b4bd] hover:bg-[#202024] hover:text-[#e0e0e5]'}`}
                  key={file.path}
                  onClick={() => onVariantChange(file)}
                >
                  {getVariantName(file)}
                </button>
              ))}
            </div>
          ) : (
            <span className="overflow-hidden text-ellipsis whitespace-nowrap font-mono text-[10px] text-[#92929c]">{selectedSource?.name}</span>
          )}
          {selectedSource && (
            <div className="flex shrink-0 items-center gap-3">
              <span className="max-w-[220px] overflow-hidden text-ellipsis whitespace-nowrap font-mono text-[10px] text-[#92929c] max-[760px]:hidden">{selectedSource.name}</span>
              <button className={`flex h-[27px] items-center gap-[6px] rounded-[5px] border px-2 text-[10px] transition-colors ${copied === selectedSource.path ? 'border-[#1e3a5f] bg-[#101a2d] text-[#93c5fd]' : 'border-[#313137] bg-[#19191d] text-[#dedee3] hover:border-[#1e3a5f] hover:text-[#60a5fa]'}`} onClick={() => onCopy(selectedSource)}>
                <Icon className="size-[13px]" name={copied === selectedSource.path ? 'check' : 'copy'} />
                {copied === selectedSource.path ? 'Copied' : copied === 'error' ? 'Copy failed' : 'Copy'}
              </button>
            </div>
          )}
        </div>
        {selectedSource ? (
          <CodeViewer file={selectedSource} />
        ) : (
          <div className="p-[26px] text-center text-xs text-[#85858e]">Choose a language to view its solution.</div>
        )}
      </div>
    </section>
  );
}
