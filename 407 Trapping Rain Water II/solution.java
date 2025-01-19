class Solution {
    public int trapRainWater(int[][] heightMap) {

        int[] dRow = { 0, 0, -1, 1 };
        int[] dCol = { -1, 1, 0, 0 };

        int numOfRows = heightMap.length;
        int numOfCols = heightMap[0].length;

        boolean[][] visited = new boolean[numOfRows][numOfCols];

        PriorityQueue<int[]> boundary = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < numOfRows; i++) {
            boundary.offer(new int[] { heightMap[i][0], i, 0 });
            boundary.offer(new int[] { heightMap[i][numOfCols - 1], i, numOfCols - 1 });
            visited[i][0] = visited[i][numOfCols - 1] = true;
        }

        for (int i = 0; i < numOfCols; i++) {
            boundary.offer(new int[] { heightMap[0][i], 0, i });
            boundary.offer(new int[] { heightMap[numOfRows - 1][i], numOfRows - 1, i });
            visited[0][i] = visited[numOfRows - 1][i] = true;
        }
        int totalWaterVolume = 0;

        while (!boundary.isEmpty()) {
            int[] currentCell = boundary.poll();
            int currentRow = currentCell[1];
            int currentCol = currentCell[2];
            int currentHeight = currentCell[0];

            for (int direction = 0; direction < 4; direction++) {
                int neighborRow = currentRow + dRow[direction];
                int neighborCol = currentCol + dCol[direction];

                if (neighborRow >= 0 && neighborCol >= 0 && neighborRow < numOfRows && neighborCol < numOfCols
                        && !visited[neighborRow][neighborCol]) {
                    int neighborHeight = heightMap[neighborRow][neighborCol];
                    if (neighborHeight < currentHeight) {
                        totalWaterVolume += currentHeight - neighborHeight;
                    }

                    boundary.offer(new int[] { Math.max(neighborHeight, currentHeight), neighborRow, neighborCol });
                    visited[neighborRow][neighborCol] = true;
                }
            }
        }
        return totalWaterVolume;
    }
}