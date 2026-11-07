import { getDisplayName } from '../lib/catalog';
import { Icon } from './Icon';

interface BreadcrumbsProps {
  path: string;
  onNavigate: (path: string) => void;
}

export function Breadcrumbs({ path, onNavigate }: BreadcrumbsProps) {
  const segments = path.split('/');

  return (
    <div className="mb-[27px] flex flex-wrap items-center gap-[5px] text-[#72727c] max-[760px]:mb-[21px]" aria-label="Breadcrumb">
      {segments.map((segment, index) => {
        const segmentPath = segments.slice(0, index + 1).join('/');
        return (
          <span className="inline-flex items-center gap-[5px]" key={segmentPath}>
            {index > 0 && <Icon className="size-[13px] text-[#9a9aa4]" name="chevronRight" />}
            <button
              className="border-0 bg-transparent p-0 text-[10.5px] text-[#60a5fa] transition-colors hover:text-[#93c5fd]"
              onClick={() => onNavigate(segmentPath)}
            >
              {getDisplayName(segment)}
            </button>
          </span>
        );
      })}
    </div>
  );
}
