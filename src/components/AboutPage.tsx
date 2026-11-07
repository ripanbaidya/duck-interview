import { Icon } from './Icon';

export function AboutPage() {
  return (
    <section className="flex min-h-[calc(100vh-155px)] items-center justify-center bg-[var(--app-background)] px-6 py-12 max-[760px]:min-h-[calc(100vh-185px)] max-[760px]:px-4 max-[760px]:py-8">
      <div className="w-full max-w-[680px] text-center">
        <img
          alt="Duck Interview duck mascot"
          className="mx-auto mb-6 size-[68px] object-contain max-[420px]:size-[60px]"
          src="/favicon.png"
        />
        <p className="mb-2 text-[10px] font-semibold tracking-[0.16em] text-[#60a5fa]">ABOUT THE PROJECT</p>
        <h1 className="mb-5 mt-0 text-[clamp(30px,5vw,46px)] font-semibold tracking-[-0.045em] text-[#f4f4f5]">
          Duck Interview
        </h1>
        <p className="mx-auto mb-4 max-w-[570px] text-[14px] leading-7 text-[#b4b4bd] max-[420px]:text-[13px] max-[420px]:leading-6">
          Duck Interview is a free, open-source web app for focused coding interview preparation. It brings quality practice questions and solutions together—without roadmaps, study sheets, or distractions.
        </p>
        <p className="mx-auto mb-8 max-w-[570px] text-[13px] leading-6 text-[#92929c]">
          I built this project for personal use, and it is not actively maintained. I may add more content and features, including system design, in the future.
        </p>
        <a
          className="inline-flex min-h-11 items-center justify-center gap-2 rounded-full border border-[#1e3a5f] bg-[#101a2d] px-5 text-[13px] font-medium text-[#dbeafe] transition-colors hover:bg-[#172744]"
          href="https://github.com/ripanbaidya"
          rel="noreferrer"
          target="_blank"
        >
          <Icon className="size-[16px]" name="github" />
          Visit on GitHub
          <Icon className="size-[14px]" name="chevronRight" />
        </a>
      </div>
    </section>
  );
}
