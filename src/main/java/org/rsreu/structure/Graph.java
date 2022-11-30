package org.rsreu.structure;

public class Graph {

    private static final int INFINITY = Integer.MAX_VALUE;

    private final Integer[][] adjList; //список смежности
    private final Integer[][] adjMatrix; //матрица смежности

    public Graph(Integer[][] adjMatrix) {
        adjList = new Integer[adjMatrix.length][];//Количество строк списка смежности равно количеству вершин графа
        this.adjMatrix = adjMatrix;//Сохранение матрицы смежности
        for (int i = 0; i < adjMatrix.length; i++) {
            int arcCount = 0;//Инициализация переменной количества дуг
            for (int j = 0; j < adjMatrix.length; j++) if (adjMatrix[i][j] != INFINITY) arcCount++;//Подсчёт количества дуг с весом, не равным бесконечности
            adjList[i] = new Integer[arcCount];//Количество столбцов i-й строки равно количеству дуг, выходящих из i-й вершины
            arcCount = 0;//Обнуление счетчика
            for (int j = 0; j < adjMatrix[i].length; j++) {
                if (adjMatrix[i][j] != INFINITY) {
                    adjList[i][arcCount] = j;//Присвоение элементу массива в строке i значения вершины, в которую ведёт дуга
                    arcCount++;//Инкремент счётчика
                }
            }
        }
    }

    public Integer[][] getAdjList() {
        return adjList;
    }

    public int getWeight(int start, int finish) {
        return adjMatrix[start][finish];
    }
}
