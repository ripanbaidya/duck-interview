import type { IconType } from 'react-icons';
import {
  FiCheck,
  FiChevronDown,
  FiChevronRight,
  FiCode,
  FiCopy,
  FiFileText,
  FiFolder,
  FiExternalLink,
  FiGithub,
  FiInfo,
  FiMenu,
  FiSearch,
  FiMoon,
  FiSun,
  FiX,
} from 'react-icons/fi';

export type IconName =
  | 'search'
  | 'folder'
  | 'document'
  | 'code'
  | 'chevronRight'
  | 'chevronDown'
  | 'copy'
  | 'check'
  | 'menu'
  | 'close'
  | 'sun'
  | 'moon'
  | 'info'
  | 'github'
  | 'externalLink';

const icons: Record<IconName, IconType> = {
  search: FiSearch,
  folder: FiFolder,
  document: FiFileText,
  code: FiCode,
  chevronRight: FiChevronRight,
  chevronDown: FiChevronDown,
  copy: FiCopy,
  check: FiCheck,
  menu: FiMenu,
  close: FiX,
  sun: FiSun,
  moon: FiMoon,
  info: FiInfo,
  github: FiGithub,
  externalLink: FiExternalLink,
};

interface IconProps {
  name: IconName;
  className?: string;
}

export function Icon({ name, className = '' }: IconProps) {
  const IconComponent = icons[name];
  return <IconComponent aria-hidden="true" className={`size-4 shrink-0 ${className}`.trim()} />;
}
