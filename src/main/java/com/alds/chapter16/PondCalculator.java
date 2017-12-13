package com.alds.chapter16;

import java.util.*;

import static com.alds.util.Helper.checkNotNull;

public class PondCalculator {


    /**
     * <p>Task 16.19. Time for implementation and testing :<i>120 min</i></p>
     * Finds ponds in a 2D matrix, each element of which represents height above sea level.
     * Zeros corresponds to ponds. A pond contains of zeros connected to each other
     * vertically, horizontally or diagonally.
     * Performance :  <i>O(N1*N2)</i> (N1 - number of rows of heightArr, N2 - number of columns of heightArr)
     * Memory :size of visited elements + number of found ponds <i>O(N1*N2 + K)</i>
     *
     * @param heightArr 2D matrix with heights
     * @return list, size of which equals to number of ponds and each element of the list contains the size of a pond
     */
    public static List<Integer> countPondsSizes(int[][] heightArr) {
        checkNotNull(heightArr);
        List<Integer> result = new ArrayList<>();
        if (!isEmpty(heightArr)) {

            int columnsTotal = heightArr[0].length;
            int rowsTotal = heightArr.length;
            boolean[][] visited = new boolean[rowsTotal][columnsTotal];

            for (int row = 0; row < rowsTotal; row++) {
                for (int column = 0; column < columnsTotal; column++) {
                    if (heightArr[row][column] == 0 && !visited[row][column]) {
                        int count = countPondSize(heightArr, visited, row, column);
                        result.add(count);
                    }
                }
            }
        }
        return result;
    }

    private static int countPondSize(int[][] heightArr, boolean[][] visited, int row, int column) {

        int totalColumns = heightArr[0].length;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(get1DIndexFrom2DIndexes(row, column, totalColumns));
        visited[row][column] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            Integer oneDIndex = queue.remove();
            int[] twoDIndex = get2DRowFrom1DIndex(oneDIndex, totalColumns);
            count++;
            queue.addAll(get1DZeroNeighbours(heightArr, visited, twoDIndex[0], twoDIndex[1]));
        }
        return count;
    }


    private static List<Integer> get1DZeroNeighbours(int[][] heightArr, boolean[][] visited, int row, int column) {
        List<Integer> list = new ArrayList<>();
        for (int r = -1; r < 2; r++)
            for (int c = -1; c < 2; c++) {
                int newRow = row + r;
                int newColumn = column + c;
                if (newRow >= 0 && newRow < heightArr.length &&
                        newColumn >= 0 && newColumn < heightArr[0].length &&
                        heightArr[newRow][newColumn] == 0 &&
                        !visited[newRow][newColumn]) {
                    list.add(get1DIndexFrom2DIndexes(newRow, newColumn, heightArr[0].length));
                    visited[newRow][newColumn] = true;
                }
            }
        return list;

    }

    private static int get1DIndexFrom2DIndexes(int row, int column, int columnMax) {
        return row * columnMax + column;
    }

    private static int[] get2DRowFrom1DIndex(int index, int columnMax) {
        int column = index % columnMax;
        int row = (index - column) / columnMax;
        return new int[]{row, column};
    }

    private static boolean isEmpty(int[][] heightArr) {
        return heightArr.length == 0 || heightArr[0].length == 0;
    }

}
