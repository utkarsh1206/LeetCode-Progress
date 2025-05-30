class Solution {
    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }

    public static int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        UnionFind uf = new UnionFind(n);

        // Unite indices with nums[i] and nums[j] satisfying |nums[i] - nums[j]| <=
        // limit
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) <= limit) {
                    uf.union(i, j);
                }
            }
        }

        // Group elements by their root and sort each group
        Map<Integer, List<Integer>> groupMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            groupMap.computeIfAbsent(root, k -> new ArrayList<>()).add(nums[i]);
        }

        for (List<Integer> group : groupMap.values()) {
            Collections.sort(group);
        }

        // Reconstruct the result array with sorted groups
        int[] result = new int[n];
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            List<Integer> group = groupMap.get(root);
            result[i] = group.get(indexMap.getOrDefault(root, 0));
            indexMap.put(root, indexMap.getOrDefault(root, 0) + 1);
        }

        return result;
    }
}