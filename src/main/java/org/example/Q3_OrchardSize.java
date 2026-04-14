package org.example;

// Q3_OrchardSize.java

public class Q3_OrchardSize {

    static int ROW = 4;
    static int COL = 4;

    // 8 directions (horizontal, vertical, diagonal)
    static int[] rowDir = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] colDir = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) {

        char[][] matrix = {
                {'O','T','O','O'},
                {'O','T','O','T'},
                {'T','T','O','T'},
                {'O','T','O','T'}
        };

        boolean[][] visited = new boolean[ROW][COL];

        System.out.print("Orchard sizes: ");

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {

                // If tree and not visited → DFS
                if (matrix[i][j] == 'T' && !visited[i][j]) {
                    int size = dfs(matrix, visited, i, j);
                    System.out.print(size + " ");
                }
            }
        }
    }

    // DFS to count connected trees
    static int dfs(char[][] matrix, boolean[][] visited, int row, int col) {

        visited[row][col] = true;
        int count = 1;

        for (int i = 0; i < 8; i++) {
            int newRow = row + rowDir[i];
            int newCol = col + colDir[i];

            if (isSafe(matrix, visited, newRow, newCol)) {
                count += dfs(matrix, visited, newRow, newCol);
            }
        }

        return count;
    }

    // Check valid cell
    static boolean isSafe(char[][] matrix, boolean[][] visited, int row, int col) {
        return (row >= 0 && row < ROW &&
                col >= 0 && col < COL &&
                matrix[row][col] == 'T' &&
                !visited[row][col]);
    }
}
