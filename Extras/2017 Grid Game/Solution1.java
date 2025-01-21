class Solution {

    // Exceeds TIME LIMIT
    public long gridGame(int[][] grid) {
        // Get the number of columns in the grid
        int n = grid[0].length;

        // Initialize minimum to a very large value to find the minimum later
        long minimum = Long.MAX_VALUE;

        // Iterate over each column index 'i' as the possible split point
        for (int i = 0; i < n; i++) {
            // Variables to store the prefix sums for the two rows
            long prefix1 = 0; // Prefix sum for the first row (row 0)
            long prefix2 = 0; // Prefix sum for the second row (row 1)

            // Calculate prefix1 for the first row from column i+1 to n-1
            // (i.e., the sum of elements on the right side of the current split in row 0)
            for (int j = i + 1; j < n; j++) {
                prefix1 += grid[0][j];
            }

            // Calculate prefix2 for the second row from column 0 to i-1
            // (i.e., the sum of elements on the left side of the current split in row 1)
            for (int j = 0; j < i; j++) {
                prefix2 += grid[1][j];
            }

            // Get the maximum value between prefix1 and prefix2, as we want the largest sum
            // for the player who will be left with the remaining cells to pick
            long maximum = Math.max(prefix1, prefix2);

            // Update the minimum value by comparing with the previously stored minimum
            minimum = Math.min(minimum, maximum);
        }

        // Return the minimum value found across all possible split points
        return minimum;
    }
}
