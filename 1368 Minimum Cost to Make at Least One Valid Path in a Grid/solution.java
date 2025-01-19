class Solution {
    public int minCost(int[][] grid) {
        int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        int ROWS = grid.length;
        int COLS = grid[0].length;
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] { 0, 0, 0 });
        int[][] minCost = new int[ROWS][COLS];
        for (int[] row : minCost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        minCost[0][0] = 0;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1], cost = curr[2];
            if (r == ROWS - 1 && c == COLS - 1) {
                return cost;
            }
            for (int i = 0; i < directions.length; i++) {
                int nr = r + directions[i][0];
                int nc = c + directions[i][1];
                int nCost = (i + 1) == grid[r][c] ? cost : cost + 1;
                if (nr < 0 || nc < 0 || nr >= ROWS || nc >= COLS || nCost >= minCost[nr][nc]) {
                    continue;
                }
                minCost[nr][nc] = nCost;
                if ((i + 1) == grid[r][c]) {
                    q.offerFirst(new int[] { nr, nc, nCost });
                } else {
                    q.offerLast(new int[] { nr, nc, nCost });
                }
            }
        }
        return -1;
    }
}