import ReactMarkdown from 'react-markdown';
import rehypeKatex from 'rehype-katex';
import remarkGfm from 'remark-gfm';
import remarkMath from 'remark-math';
import 'katex/dist/katex.min.css';
import { MermaidDiagram } from './MermaidDiagram';

interface MarkdownDocumentProps {
  content: string;
}

export function MarkdownDocument({ content }: MarkdownDocumentProps) {
  return (
    <div className="wrap-anywhere rounded-[9px] border border-[#252529] bg-[#0e0e11] p-[clamp(15px,2.4vw,23px)] text-xs leading-[1.75] text-[#d0d0d6] max-[420px]:p-[13px] [&>*:first-child]:mt-0 [&>*:last-child]:mb-0 [&_p]:my-[10px] [&_ul]:pl-[22px] [&_ol]:pl-[22px] [&_li]:my-1 [&_strong]:font-semibold [&_strong]:text-[#e2e2e7] [&_a]:text-[#60a5fa] [&_a]:no-underline [&_a:hover]:underline [&_blockquote]:my-[13px] [&_blockquote]:border-l-2 [&_blockquote]:border-[#1e3a5f] [&_blockquote]:py-[2px] [&_blockquote]:pl-[13px] [&_blockquote]:text-[#9b9ba5] [&_pre]:max-w-full [&_pre]:overflow-auto [&_pre]:rounded-md [&_pre]:border [&_pre]:border-[#29292e] [&_pre]:bg-[#0a0a0d] [&_pre]:p-[13px] [&_pre:has(.mermaid-container)]:overflow-visible [&_pre:has(.mermaid-container)]:border-0 [&_pre:has(.mermaid-container)]:bg-transparent [&_pre:has(.mermaid-container)]:p-0 [&_pre_code]:border-0 [&_pre_code]:bg-transparent [&_pre_code]:p-0 [&_pre_code]:text-[11px] [&_table]:block [&_table]:w-full [&_table]:overflow-auto [&_table]:border-collapse [&_th]:border [&_th]:border-[#303036] [&_th]:bg-[#151519] [&_th]:px-[10px] [&_th]:py-[7px] [&_th]:text-left [&_th]:text-[#e1e1e6] [&_td]:border [&_td]:border-[#303036] [&_td]:px-[10px] [&_td]:py-[7px] [&_td]:text-left [&_img]:max-w-full [&_hr]:border-0 [&_hr]:border-t [&_hr]:border-[#29292e] [&_.katex-display]:my-4 [&_.katex-display]:overflow-x-auto [&_.hljs]:bg-transparent [&_.hljs]:text-[#c9d1d9] [&_h1]:my-[1.5em] [&_h1]:mb-[0.55em] [&_h1]:text-[22px] [&_h1]:font-semibold [&_h1]:leading-[1.3] [&_h1]:tracking-[-0.02em] [&_h1]:text-[#e9e9ed] [&_h2]:my-[1.5em] [&_h2]:mb-[0.55em] [&_h2]:border-b [&_h2]:border-[#252529] [&_h2]:pb-[7px] [&_h2]:text-[17px] [&_h2]:font-semibold [&_h2]:leading-[1.3] [&_h2]:tracking-[-0.02em] [&_h2]:text-[#e9e9ed] [&_h3]:my-[1.5em] [&_h3]:mb-[0.55em] [&_h3]:text-sm [&_h3]:font-semibold [&_h3]:leading-[1.3] [&_h3]:tracking-[-0.02em] [&_h3]:text-[#e9e9ed] [&_h4]:my-[1.5em] [&_h4]:mb-[0.55em] [&_h4]:text-xs [&_h4]:font-semibold [&_h4]:leading-[1.3] [&_h4]:tracking-[-0.02em] [&_h4]:text-[#e9e9ed]">
      <ReactMarkdown
        remarkPlugins={[remarkGfm, remarkMath]}
        rehypePlugins={[rehypeKatex]}
        components={{
          code: ({ className, children, ...props }) => {
            if (className?.split(/\s+/).includes('language-mermaid')) {
              return <MermaidDiagram chart={String(children).replace(/\n$/, '')} />;
            }
            const inline = !className;
            return (
              <code
                className={`${className ?? ''} ${inline ? 'rounded border border-[#2b2b30] bg-[#17171b] px-[5px] py-[2px] font-mono text-[0.9em] text-[#93c5fd]' : ''}`}
                {...props}
              >
                {children}
              </code>
            );
          },
          h1: ({ children, ...props }) => <h1 className="my-[1.5em] mb-[0.55em] text-[22px] font-semibold leading-[1.3] tracking-[-0.02em] text-[#e9e9ed]" {...props}>{children}</h1>,
          h2: ({ children, ...props }) => <h2 className="my-[1.5em] mb-[0.55em] border-b border-[#252529] pb-[7px] text-[17px] font-semibold leading-[1.3] tracking-[-0.02em] text-[#e9e9ed]" {...props}>{children}</h2>,
          h3: ({ children, ...props }) => <h3 className="my-[1.5em] mb-[0.55em] text-sm font-semibold leading-[1.3] tracking-[-0.02em] text-[#e9e9ed]" {...props}>{children}</h3>,
          h4: ({ children, ...props }) => <h4 className="my-[1.5em] mb-[0.55em] text-xs font-semibold leading-[1.3] tracking-[-0.02em] text-[#e9e9ed]" {...props}>{children}</h4>,
          a: ({ href, children, ...props }) => (
            <a href={href} target="_blank" rel="noreferrer" {...props}>{children}</a>
          ),
        }}
      >
        {content}
      </ReactMarkdown>
    </div>
  );
}
