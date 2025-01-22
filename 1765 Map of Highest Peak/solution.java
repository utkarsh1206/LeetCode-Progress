class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        int m = isWater.length;
        int n = isWater[0].length;
        int[][] result = new int[m][n];
        boolean[][] visited = new boolean[m][n];

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    result[i][j] = 0;
                    q.offer(new int[] { 0, i, j });
                    visited[i][j] = true;
                } else {
                    result[i][j] = 1;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] currentCell = q.poll();
            int currentHeight = currentCell[0];
            int currentRow = currentCell[1];
            int currentCol = currentCell[2];

            for (int i = 0; i < direction.length; i++) {
                int newRow = currentRow + direction[i][0];
                int newCol = currentCol + direction[i][1];

                if (newRow >= 0 && newCol >= 0 && newRow < m && newCol < n && !visited[newRow][newCol]) {
                    int neighborHeight = result[newRow][newCol];
                    if (neighborHeight <= currentHeight) {
                        result[newRow][newCol] = neighborHeight + currentHeight;
                    }
                    q.offer(new int[] { result[newRow][newCol], newRow, newCol });
                    visited[newRow][newCol] = true;
                }
            }
        }
        return result;
    }
}