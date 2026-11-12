import questionLinks from '../data/question-links.json';

const questionLinksByName: Record<string, { title: string; url: string }> = questionLinks;

const questionAliases: Record<string, string> = {
  'backtrack/generate-parentheses': 'generateparenthesis',
  'backtrack/letter-combinations-of-a-phone-number': 'lettercombinationofaphonenumber',
  'binary-search/find-first-and-last-occurrence-of-element-in-array': 'findfirstandlastoccuranceofelementinarray',
  'binary-search/koko-eating-bananas': 'kokoeatingbanana',
  'binary-search/median-of-two-sorted-arrays': 'medianoftwosortedarray',
  'binary-tree/inorder': 'inordertraversalrecursiveiterative',
  'binary-tree/level-order-traversal': 'binarytreelevelordertraversal',
  'binary-tree/postorder': 'postordertraversalrecursiveiterative',
  'binary-tree/populating-next-right-pointers-in-each-node': 'populatingnextrightpointersineachnodei',
  'binary-tree/preorder': 'preordertraversalrecursiveiterative',
  'binary-tree/serialize-and-deserialize-binary-tree': 'seialiseanddeserialisebinarytree',
  'binary-tree/zigzag-level-order-traversal': 'binarytreezigzaglevelordertraversal',
  'graph/alien-dictionary': '269aliendictionary',
  'graph/bellman-ford': 'bellmanfordalgorithm',
  'graph/bfs': 'bfsbreadthfirstsearch',
  'graph/course-schedule': '207courseschedule',
  'graph/course-schedule-ii': '210coursescheduleii',
  'graph/detect-cycle-in-directed-graph': 'detectcycleinadirectedgraphbfskahnsalgorithm',
  'graph/detect-cycle-in-undirected-graph': 'cycledetectioninundirectedgraphbfsanddfs',
  'graph/dfs': 'dfsdepthfirstsearch',
  'graph/dijkstra-algorithm': 'dijkstraalgorithmpriorityqueueminheap',
  'graph/find-eventual-safe-states': '802findeventualsafestates',
  'graph/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance': 'findthecitywiththesmallestnumberofneighbors',
  'graph/floyd-warshall': 'floydwarshallalgorithm',
  'graph/graph-is-tree-or-not': 'graphvalidtree',
  'graph/keys-and-rooms': '841keysandrooms',
  'graph/minimum-genetic-mutation': '433minimumgeneticmutation',
  'graph/number-of-island': 'numberofislands',
  'graph/number-of-ways-to-arrive-at-destination': 'numberofwaystoarriveatdesitination',
  'graph/open-the-lock': '752openthelock',
  'graph/path-with-maximum-probability': 'pathwithmaximumprobablilty',
  'graph/shortest-path-in-directed-acyclic-graph': 'shortestpathindirectedascyclicgraph',
  'graph/shortest-path-in-undirected-graph-having-unit-distance': 'gfgshortestpathinundirectedgraphunitweight',
  'graph/snakes-and-ladders': '909snakesandladders',
  'graph/word-ladder': '127wordladder',
  'graph/word-ladder-ii': '126wordladderii',
  'hashing/group-anagrams': 'groupanagram',
  'hashing/isomorphic-strings': 'isomorphicstring',
  'linked-list/all-oone-data-structure': 'allo1datastructure',
  'linked-list/detect-cycle': 'linkedlistcycle',
  'linked-list/detect-starting-node-of-cycle': 'linkedlistcycleii',
  'linked-list/intersection-of-two-linked-lists': 'intersectionoftwolinkedlist',
  'linked-list/merge-two-sorted-lists': 'mergetwosortedlist',
  'linked-list/reverse-linked-list': 'reverselinkedlistiterativerecursive',
  'linked-list/reverse-sub-list': 'reverselinkedlistii',
  'prefix-sum/contiguous-array': 'contigiousarray',
  'sliding-window/maximum-average-subarray': 'maximumaveragesubarrayi',
  'two-pointers/boats-to-save-people': 'boattosavepeople',
  'two-pointers/four-sum': '4sum',
  'two-pointers/sort-colors': 'sortcolorsdutchnationalflagalgorithm',
  'two-pointers/three-sum': '3sum',
};

function normalizeQuestionName(name: string): string {
  return name.toLowerCase().replace(/&/g, 'and').replace(/[^a-z0-9]/g, '');
}

export function getQuestionLink(folderPath: string): { title: string; url: string } | undefined {
  const folderName = folderPath.split('/').at(-1) ?? folderPath;
  const key = questionAliases[folderPath] ?? normalizeQuestionName(folderName);
  return questionLinksByName[key];
}
