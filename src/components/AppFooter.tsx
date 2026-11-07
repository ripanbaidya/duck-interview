interface AppFooterProps {
  onHome: () => void;
}

export function AppFooter({ onHome }: AppFooterProps) {
  return (
    <footer className="relative grid grid-cols-[1fr_auto_1fr] items-center gap-4 px-7 py-5 text-xs text-[#9999a3] max-[760px]:grid-cols-1 max-[760px]:justify-items-center max-[760px]:px-4 max-[760px]:py-4">
      <button
        aria-label="Go to home"
        className="absolute inset-0 z-0 border-0 bg-transparent"
        onClick={onHome}
      />
      <span aria-hidden="true" />
      <p className="pointer-events-none col-start-2 m-0 text-center max-[760px]:col-start-1">
        © {new Date().getFullYear()} Duck Interview. All rights reserved.
      </p>
      <span aria-hidden="true" />
    </footer>
  );
}
