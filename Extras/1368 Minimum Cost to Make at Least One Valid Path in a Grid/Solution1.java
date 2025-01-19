// Solution 1

import java.util.*;

class Solution {
    public int minCost(int[][] grid) {
        int[] direction = {1, 2, 3, 4};
        int ROWS = grid.length;
        int COLS = grid[0].length;
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});
        Map<Pair, Integer> minCost = new HashMap<>();
        minCost.put(new Pair(0, 0), 0);

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1], cost = curr[2];
            if (r == ROWS - 1 && c == COLS - 1) {
                return cost;
            }
            for (int d : direction) {
                int nr = r, nc = c;
                switch (d) {
                    case 1: nr = r; nc = c + 1; break;
                    case 2: nr = r; nc = c - 1; break;
                    case 3: nr = r + 1; nc = c; break;
                    case 4: nr = r - 1; nc = c; break;
                }
                int nCost = d == grid[r][c] ? cost : cost + 1;
                if (nr < 0 || nc < 0 || nr >= ROWS || nc >= COLS || nCost >= minCost.getOrDefault(new Pair(nr, nc), Integer.MAX_VALUE)) {
                    continue;
                }
                minCost.put(new Pair(nr, nc), nCost);
                if (d == grid[r][c]) {
                    q.offerFirst(new int[]{nr, nc, nCost});
                } else {
                    q.offerLast(new int[]{nr, nc, nCost});
                }
            }
        }
        return -1; // just in case, should never reach here if input is valid
    }

    class Pair {
        int r, c;
        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return r == pair.r && c == pair.c;
        }
        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}

// Solution 1 End