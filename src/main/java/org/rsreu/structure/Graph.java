package org.rsreu.structure;

public class Graph {

    private static final int INFINITY = Integer.MAX_VALUE;

    private final int[][] adjList;
    private final int[][] adjMatrix;

    public Graph(int[][] adjMatrix) {
        adjList = new int[adjMatrix.length][];//Количество строк списка смежности равно количеству вершин графа
        this.adjMatrix = adjMatrix;
        for (int i = 0; i < adjMatrix.length; i++) {
            int arcCount = 0;
            for (int j = 0; j < adjMatrix.length; j++) if (adjMatrix[i][j] != INFINITY) arcCount++;
            adjList[i] = new int[arcCount];//Количество столбцов данной строки равно количеству дуг, выходящих из данной вершины
            arcCount = 0;
            for (int j = 0; j < adjMatrix[i].length; j++) {
                if (adjMatrix[i][j] != INFINITY) {
                    adjList[i][arcCount] = j;
                    arcCount++;
                }
            }
        }
    }

    public int[][] getAdjList() {
        return adjList;
    }

    public int getWeight(int start, int finish) {
        return adjMatrix[start][finish];
    }
}
