import { useEffect, useId, useState } from 'react';

let mermaidRenderQueue: Promise<void> = Promise.resolve();

interface MermaidDiagramProps {
  chart: string;
}

export function MermaidDiagram({ chart }: MermaidDiagramProps) {
  const [svg, setSvg] = useState('');
  const [error, setError] = useState('');
  const reactId = useId();
  const renderId = `mermaid-${reactId.replace(/[^a-zA-Z0-9_-]/g, '')}`;

  useEffect(() => {
    let cancelled = false;
    setSvg('');
    setError('');

    const renderTask = mermaidRenderQueue.then(async () => {
      if (cancelled) return '';
      const { default: mermaid } = await import('mermaid');
      mermaid.initialize({
        startOnLoad: false,
        theme: 'dark',
        securityLevel: 'strict',
        themeVariables: {
          darkMode: true,
          background: '#111114',
          primaryColor: '#101a2d',
          primaryTextColor: '#f4f4f5',
          primaryBorderColor: '#60a5fa',
          lineColor: '#93c5fd',
          secondaryColor: '#17171b',
          tertiaryColor: '#202024',
          fontFamily: 'Inter, ui-sans-serif, system-ui, sans-serif',
        },
      });
      const result = await mermaid.render(renderId, chart);
      return result.svg;
    });
    mermaidRenderQueue = renderTask.then(() => undefined, () => undefined);
    renderTask.then((renderedSvg) => {
      if (!cancelled) setSvg(renderedSvg);
    }).catch((renderError: unknown) => {
      if (cancelled) return;
      console.error('Unable to render a Mermaid diagram.', renderError);
      setError('Diagram could not be rendered. The original Mermaid source is shown below.');
    });

    return () => {
      cancelled = true;
    };
  }, [chart, renderId]);

  return (
    <div className="mermaid-container w-full overflow-auto rounded-lg border border-[#303036] bg-[#111114] p-[14px]">
      {error ? (
        <div className="text-[11px] text-[#f0aaa5]">
          <p className="mb-2 mt-0">{error}</p>
          <pre className="m-0 max-w-full overflow-auto rounded-md border border-[#29292e] bg-[#0a0a0d] p-[13px]"><code>{chart}</code></pre>
        </div>
      ) : svg ? (
        <div className="flex min-w-max justify-center [&_svg]:h-auto [&_svg]:max-w-full" role="img" aria-label="Rendered Mermaid diagram" dangerouslySetInnerHTML={{ __html: svg }} />
      ) : (
        <div className="text-[11px] text-[#c4c4cc]" aria-label="Rendering diagram">Rendering diagram…</div>
      )}
    </div>
  );
}
