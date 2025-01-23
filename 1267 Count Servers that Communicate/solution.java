class Solution {
    // Method to count servers that communicate
    public int countServers(int[][] grid) {
        // Get the number of rows and columns in the grid
        int row = grid.length;
        int col = grid[0].length;
        int totalCount = 0; // To store the total count of servers that can communicate
        int[] rowCount = new int[row]; // Array to keep track of the number of servers in each row
        int[] colCount = new int[col]; // Array to keep track of the number of servers in each column

        // First pass: Count the number of servers in each row and column
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) { // If there's a server at grid[i][j]
                    rowCount[i]++; // Increment the count for the respective row
                    colCount[j]++; // Increment the count for the respective column
                }
            }
        }

        // Second pass: Identify servers that can communicate with others
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 && (rowCount[i] > 1 || colCount[j] > 1)) {
                    // If there's a server at grid[i][j] and it has at least one other server in its
                    // row or column
                    totalCount++; // Increment the total count of communicable servers
                }
            }
        }
        return totalCount; // Return the total count of communicable servers
    }
}
