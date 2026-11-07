interface LoadingStateProps {
  error?: string;
}

export function LoadingState({ error }: LoadingStateProps) {
  if (error) {
    return (
      <div className="flex min-h-screen flex-col items-center justify-center p-[25px] text-center text-[#888891]">
        <div className="max-w-[430px] rounded-[10px] border border-[#30282d] bg-[#111114] p-7 text-left">
          <span className="text-[9px] font-bold tracking-[0.12em] text-[#a1a1aa]">CONTENT UNAVAILABLE</span>
          <h1 className="my-1 text-[25px] font-semibold text-[#f4f4f5]">Could not load the library</h1>
          <p className="text-xs text-[#8d8d96]">{error}</p>
          <p className="mt-5 text-[10px] text-[#777780]">
            Run <code className="text-[#60a5fa]">npm run dev</code> so the content catalog is generated automatically.
          </p>
        </div>
      </div>
    );
  }

  return (
    <div className="flex min-h-screen flex-col items-center justify-center gap-3 p-[25px] text-center text-[#888891]">
      <span className="grid size-[38px] place-items-center rounded-[9px] border border-[#1e3a5f] bg-[#101a2d] text-lg font-bold text-[#60a5fa]">C</span>
      <p>Loading your code library…</p>
    </div>
  );
}
