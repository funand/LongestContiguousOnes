import java.util.ArrayList;
import java.util.List;


public class LongestContiguousOnes {
    static final int[] rowArr = {0, 1, 1, 1};
    static final int[] colArr = {1, -1, 0, 1};
    static boolean[][] visited;

    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(getMatrixLine(0, 1, 1, 0));
        matrix.add(getMatrixLine(1, 0, 1, 0));
        matrix.add(getMatrixLine(1, 0, 0, 1));
        matrix.add(getMatrixLine(0, 1, 0, 0));

        visited = new boolean[matrix.size()][matrix.get(0).size()];
        populateVisited(matrix);
        System.out.println(getMaxLineLength(matrix));   // ANS => 3
    }

    static List<Integer> getMatrixLine(int first, int second, int third, int fourth) {
        List<Integer> line = new ArrayList<>();
        line.add(first);
        line.add(second);
        line.add(third);
        line.add(fourth);

        return line;
    }

    static void populateVisited(List<List<Integer>> matrix) {
        for (int row = 0; row < matrix.size(); row++) {
            for (int col = 0; col < matrix.get(row).size(); col++) {
                visited[row][col] = false;
            }
        }
    }

    static int getMaxLineLength(List<List<Integer>> matrix) {
        int maxLongest = 0;

        for (int row = 0; row < matrix.size(); row++) {
            int currentLongest = 0;
            for (int col = 0; col < matrix.get(row).size(); col++) {
                if (matrix.get(row).get(col) == 1) {
                    for (int k = 0; k < rowArr.length; k++) {
                        int directionRow = rowArr[k];
                        int directionCol = colArr[k];
                        currentLongest = Math.max(getLength(row, col, matrix, directionRow, directionCol), currentLongest);
                    }
                }
            }

            maxLongest = Math.max(maxLongest, currentLongest);
        }

        return maxLongest;
    }

    static int getLength(int row, int col, List<List<Integer>> matrix, int directionRow, int directionCol) {
        int length = 0;

        while (isValid(row, col, matrix) && matrix.get(row).get(col) == 1) {
            row += directionRow;
            col += directionCol;
            length++;
        }

        return length;
    }

    static boolean isValid(int row, int col, List<List<Integer>> matrix) {
        return row >= 0 && row < matrix.size()
                && col >= 0
                && col < matrix.get(row).size()
                && !visited[row][col]
                && matrix.get(row).get(col) == 1;
    }
}































