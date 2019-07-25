package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            test();
        }
    }

    public static void test(){
        int countOfLine = (int)(Math.random() * 40);
        int countOfColumn = (int)(Math.random() * 40);
        int[][] matrix = new int[countOfLine][countOfColumn];
        for (int i = 0; i < countOfLine; i++) {
            for (int j = 0; j < countOfColumn; j++) {
                matrix[i][j] = (int)(Math.random() * 2);
            }
        }
        System.out.println("size of array:\n" + countOfLine + " " + countOfColumn);
        System.out.println("source array:");
        for (int i = 0; i < countOfLine; i++) {
            for (int j = 0; j < countOfColumn; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
        long startTime = System.currentTimeMillis();
        System.out.println("square is: " + maxSquare(matrix));
        for (int i = 0; i < 3; i++) {
            System.out.println("");
        }
        System.out.println("operation's time is " + (System.currentTimeMillis() - startTime));
    }

    public static int maxSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int maxSquareSize = 0;
        for (int column = 0; column < matrix[0].length; column++) {
            for (int line = 0; line < matrix.length; line++) {
                if (matrix[line][column] == 1) {
                    int result = foundSquare(column, line, matrix);
                    if (result > maxSquareSize)
                        maxSquareSize = result;
                }
            }
        }
        return maxSquareSize;
    }

    private static int foundSquare(int line, int column, int[][] matrix) {
        int maxSize = 1;
        int availSize = matrix[0].length - column < matrix.length - line ?
                        matrix[0].length - column : matrix.length - line;
        for (int size = 1; size < availSize; size++) {
            //проходим все доступные ячейки по строке
            for (int col = 0; col <= size; col++) {
                if (matrix[line + size][column + col] == 0)
                    return maxSize*maxSize;
            }
            //проходим все доступные по столбцу
            for (int lin = 0; lin < size; lin++) {
                if (matrix[line + lin][column + size] == 0)
                    return maxSize*maxSize;
            }
            maxSize++;
        }
        return maxSize*maxSize;
    }
}

