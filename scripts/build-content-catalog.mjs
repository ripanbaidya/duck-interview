import { readdir, readFile, mkdir, writeFile } from 'node:fs/promises';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const repositoryRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const ignoredRootDirectories = new Set([
  '.git',
  '.github',
  '.idea',
  '.vscode',
  'build',
  'coverage',
  'dist',
  'node_modules',
  'out',
  'public',
  'scripts',
  'src',
  'target',
  'vendor',
]);
const sourceExtensions = new Set([
  '.c',
  '.cc',
  '.cpp',
  '.cs',
  '.go',
  '.h',
  '.hpp',
  '.java',
  '.js',
  '.jsx',
  '.kt',
  '.php',
  '.py',
  '.rb',
  '.rs',
  '.scala',
  '.sh',
  '.sql',
  '.swift',
  '.ts',
  '.tsx',
]);

async function collectFiles(directory, relativeDirectory = '') {
  const entries = await readdir(directory, { withFileTypes: true });
  const files = [];

  for (const entry of entries) {
    if (entry.name.startsWith('.')) continue;

    const relativePath = path.posix.join(relativeDirectory, entry.name);
    const absolutePath = path.join(directory, entry.name);

    if (entry.isDirectory()) {
      files.push(...(await collectFiles(absolutePath, relativePath)));
      continue;
    }

    const extension = path.extname(entry.name).toLowerCase();
    if (extension === '.md' || sourceExtensions.has(extension)) {
      files.push({ absolutePath, relativePath });
    }
  }

  return files;
}

const rootEntries = await readdir(repositoryRoot, { withFileTypes: true });
const catalog = [];

for (const entry of rootEntries) {
  if (!entry.isDirectory() || ignoredRootDirectories.has(entry.name)) continue;

  const files = await collectFiles(path.join(repositoryRoot, entry.name), entry.name);
  for (const file of files) {
    catalog.push({
      path: file.relativePath,
      section: entry.name,
      name: path.posix.basename(file.relativePath),
      extension: path.extname(file.relativePath).toLowerCase(),
      content: await readFile(file.absolutePath, 'utf8'),
    });
  }
}

catalog.sort((a, b) => a.path.localeCompare(b.path));
const publicDirectory = path.join(repositoryRoot, 'public');
await mkdir(publicDirectory, { recursive: true });
await writeFile(
  path.join(publicDirectory, 'content-catalog.json'),
  JSON.stringify(catalog),
);

console.log(`Indexed ${catalog.length} content files across ${new Set(catalog.map((file) => file.section)).size} sections.`);
