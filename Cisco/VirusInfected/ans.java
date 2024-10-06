package com.example.sort.DFS;

import java.util.LinkedList;
import java.util.Queue;

public class VirusInfected {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static int spreadVirus(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return -1;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int totalTime = 0;
        int totalInfectable = 0;
        int infected = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
                if (matrix[i][j] == 1) {
                    totalInfectable++;
                }
            }
        }
        if (totalInfectable == 0) {
            return 0;
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean spread = false;
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];
                for (int[] direction : DIRECTIONS) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && matrix[newRow][newCol] == 1) {
                        matrix[newRow][newCol] = 2;
                        queue.offer(new int[]{newRow, newCol});
                        infected++;
                        spread = true;
                    }
                }
            }
            if (spread) {
                totalTime++;
            }
        }
        return infected == totalInfectable ? totalTime : -1;


    }
    public static void main(String[] args) {
        int[][] matrix = {
                {2, 1, 0, 2, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 2, 1}
        };

        int result = spreadVirus(matrix);
        System.out.println("Total time to infect all: " + result);
    }

}
