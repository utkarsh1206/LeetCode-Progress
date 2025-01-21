class Solution {
    public long gridGame(int[][] grid) {
        // Get the number of columns (n) in the grid
        int n = grid[0].length;

        // Initialize the 'minimum' variable to store the result, starting with the
        // largest possible value
        long minimum = Long.MAX_VALUE;

        // Calculate the total sum of the first row (grid[0])
        long totalRow1 = 0;
        for (int i = 0; i < n; i++) {
            totalRow1 += grid[0][i]; // Add each element of the first row to totalRow1
        }

        // Initialize variables to keep track of the cumulative points for each row
        // while iterating
        long pointsRow1 = 0;
        long pointsRow2 = 0;

        // Iterate over each column in the grid
        for (int i = 0; i < n; i++) {
            // These variables will hold the score calculations for the first and second
            // rows for the current iteration
            long scoreRow1 = 0;
            long scoreRow2 = 0;

            // Update the cumulative points for row 1 and row 2 based on the current column
            pointsRow1 += grid[0][i];
            pointsRow2 += grid[1][i];

            // Calculate the remaining points for row 1 (i.e., total points in row 1 minus
            // the points already accumulated)
            scoreRow1 = totalRow1 - pointsRow1;

            // Calculate the score for row 2 (i.e., cumulative points from row 2 minus the
            // current column's points from row 2)
            scoreRow2 = pointsRow2 - grid[1][i];

            // Find the maximum score between the two rows (since the game chooses the
            // optimal score for each row)
            long maximum = Math.max(scoreRow1, scoreRow2);

            // Keep track of the minimum value of the maximum scores seen so far (this gives
            // the optimal result)
            minimum = Math.min(minimum, maximum);
        }

        // Return the minimum score which represents the optimal outcome of the game
        return minimum;
    }
}
