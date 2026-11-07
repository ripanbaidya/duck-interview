import { useEffect, useState } from 'react';
import { getLanguageKey } from '../lib/catalog';
import { highlightCode } from '../lib/highlight';
import type { ContentFile } from '../types';

interface CodeViewerProps {
  file: ContentFile;
}

export function CodeViewer({ file }: CodeViewerProps) {
  const [html, setHtml] = useState('');
  const [highlightError, setHighlightError] = useState('');
  const lineCount = file.content.split('\n').length;

  useEffect(() => {
    let cancelled = false;
    setHtml('');
    setHighlightError('');
    highlightCode(file.content, file.extension)
      .then((highlighted) => {
        if (!cancelled) setHtml(highlighted);
      })
      .catch((error: unknown) => {
        if (cancelled) return;
        console.error('Unable to load syntax highlighting for this file.', error);
        setHighlightError('Syntax highlighting is unavailable; showing plain text.');
      });

    return () => {
      cancelled = true;
    };
  }, [file]);

  return (
    <div className="max-h-[72vh] overflow-auto [scrollbar-color:#35353b_#0c0c0f] max-[760px]:max-h-[66vh]" aria-label={`${file.name} source code`}>
      {highlightError && <p className="m-0 border-b border-[#392e2d] bg-[#211818] px-[14px] py-2 text-[10px] text-[#d6a8a2]">{highlightError}</p>}
      <div className="flex min-w-max items-stretch">
        <div className="flex min-w-12 select-none flex-col border-r border-[#222227] bg-[#0c0c0f] pb-[15px] pl-[6px] pr-[11px] pt-[15px] text-right text-[#5e5e68]" aria-hidden="true">
          {Array.from({ length: lineCount }, (_, index) => <span className="h-5 font-mono text-[10px] leading-5" key={index}>{index + 1}</span>)}
        </div>
        <pre className="m-0 bg-transparent px-[18px] py-[15px]">
          {html ? (
            <code
              className={`hljs language-${getLanguageKey(file.extension)} !bg-transparent !p-0 font-mono text-[11.5px] leading-5 whitespace-pre text-[#d4d4d8] [tab-size:2]`}
              dangerouslySetInnerHTML={{ __html: html }}
            />
          ) : (
            <code className={`hljs language-${getLanguageKey(file.extension)} !bg-transparent !p-0 font-mono text-[11.5px] leading-5 whitespace-pre text-[#d4d4d8] [tab-size:2]`}>{file.content}</code>
          )}
        </pre>
      </div>
    </div>
  );
}
