class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        // Map to store the position of each number in the matrix
        HashMap<Integer, int[]> valuePositions = new HashMap<>();

        // Populate the valuePositions map with the positions of each number in the
        // matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                valuePositions.put(mat[i][j], new int[] { i, j });
            }
        }

        // Maps to count the number of marked numbers in each row and column
        HashMap<Integer, Integer> rowCount = new HashMap<>();
        HashMap<Integer, Integer> colCount = new HashMap<>();

        // Iterate through the arr to find the first complete index
        for (int i = 0; i < arr.length; i++) {
            int[] position = valuePositions.get(arr[i]);
            int row = position[0];
            int col = position[1];

            // Increment the count for the corresponding row and column
            rowCount.put(row, rowCount.getOrDefault(row, 0) + 1);
            colCount.put(col, colCount.getOrDefault(col, 0) + 1);

            // Check if the current row or column is complete
            if (rowCount.get(row) == cols || colCount.get(col) == rows) {
                return i; // Return the index of the first complete row or column
            }
        }

        return -1; // Return -1 if no complete row or column is found
    }
}