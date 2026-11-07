import hljs from 'highlight.js/lib/core';
import type { LanguageFn } from 'highlight.js';

const highlightLanguages: Record<string, string> = {
  c: 'c',
  cc: 'cpp',
  cpp: 'cpp',
  cs: 'csharp',
  go: 'go',
  h: 'c',
  hpp: 'cpp',
  java: 'java',
  js: 'javascript',
  jsx: 'javascript',
  kt: 'kotlin',
  php: 'php',
  py: 'python',
  rb: 'ruby',
  rs: 'rust',
  scala: 'scala',
  sh: 'bash',
  sql: 'sql',
  swift: 'swift',
  ts: 'typescript',
  tsx: 'typescript',
};

const languageLoaders: Record<string, () => Promise<{ default: LanguageFn }>> = {
  c: () => import('highlight.js/lib/languages/c'),
  cpp: () => import('highlight.js/lib/languages/cpp'),
  csharp: () => import('highlight.js/lib/languages/csharp'),
  go: () => import('highlight.js/lib/languages/go'),
  java: () => import('highlight.js/lib/languages/java'),
  javascript: () => import('highlight.js/lib/languages/javascript'),
  kotlin: () => import('highlight.js/lib/languages/kotlin'),
  php: () => import('highlight.js/lib/languages/php'),
  python: () => import('highlight.js/lib/languages/python'),
  ruby: () => import('highlight.js/lib/languages/ruby'),
  rust: () => import('highlight.js/lib/languages/rust'),
  scala: () => import('highlight.js/lib/languages/scala'),
  bash: () => import('highlight.js/lib/languages/bash'),
  sql: () => import('highlight.js/lib/languages/sql'),
  swift: () => import('highlight.js/lib/languages/swift'),
  typescript: () => import('highlight.js/lib/languages/typescript'),
};

export async function highlightCode(code: string, extension: string): Promise<string> {
  const language = highlightLanguages[extension.slice(1)];
  const loadLanguage = language ? languageLoaders[language] : undefined;
  if (!language || !loadLanguage) return escapeHtml(code);
  if (!hljs.getLanguage(language)) {
    const languageModule = await loadLanguage();
    hljs.registerLanguage(language, languageModule.default);
  }
  return hljs.highlight(code, { language }).value;
}

function escapeHtml(text: string): string {
  return text.replace(/[&<>"']/g, (character) => {
    const entities: Record<string, string> = {
      '&': '&amp;',
      '<': '&lt;',
      '>': '&gt;',
      '"': '&quot;',
      "'": '&#x27;',
    };
    return entities[character]!;
  });
}
