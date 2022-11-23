package org.rsreu;

import org.rsreu.structure.Graph;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert vertex count");
        int vertexCount = scanner.nextInt();
        System.out.println("Insert arc count");
        int arcCount = scanner.nextInt();
        scanner.nextLine();
        int[][] matrix = new int[vertexCount][vertexCount];
        for (int i = 0; i < arcCount; i++) {
            System.out.printf("Insert %d-th arc if format: start,final,weight\n", i + 1);
            String[] arc = scanner.nextLine().split(",");
            matrix[Integer.parseInt(arc[0]) - 1][Integer.parseInt(arc[1]) - 1] = Integer.parseInt(arc[2]);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        Graph graph = new Graph(matrix);
        FordBellman algorithm = new FordBellman();
        System.out.println("Insert start vertex");
        int start = scanner.nextInt() - 1;
        int[] shortestWays = new int[0];
        try {
            shortestWays = algorithm.run(graph, start);
        } catch (IllegalArgumentException exception) {
            System.err.println(exception.getMessage());
        }
        for (int i = 0; i < shortestWays.length; i++) {
            System.out.printf("Shortest way from %d to %d-th vertex is equals %d\n", start + 1, i + 1, shortestWays[i]);
        }
    }
}
