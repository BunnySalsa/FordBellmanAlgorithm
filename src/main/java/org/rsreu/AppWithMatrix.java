package org.rsreu;

import org.rsreu.structure.Graph;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class AppWithMatrix {
    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;
        int start = 0;//Ввод вершины начала
        int finish = 3;//Ввод вершины конца
        Integer[][] matrix = new Integer[][]{
                {INF, 1, INF, INF, 3},
                {INF, INF, 3, 3, 8},
                {INF, INF, INF, 1, -5},
                {INF, INF, 2, INF, INF},
                {INF, 8, INF, 4, INF}
        };

        FordBellman algorithm = new FordBellman();//Создание объекта класса FordBellman для вызова метода run()

        try {//Следующий код может выкинуть исключение, поэтому мы заключаем его в тело блока try
            //Вызов метода run(), который возвращает массив кратчайших путей из начальной вершины.
            Integer[] shortestWays = algorithm.run(new Graph(matrix), start);// В параметр метода передаётся новый объект графа, в который в свою очередь передаётся матрица смежности, созданная ранее
            System.out.printf("Shortest way from %d to %d-th vertex is equals %d\n", start + 1, finish + 1, shortestWays[finish]);//Вывод кратчайшего пути из начальной вершины в конечную
            System.out.println(Arrays.toString(shortestWays));
        } catch (IllegalArgumentException exception) {//Часть кода, которая выполняется в случае исключения
            System.err.println(exception.getMessage());//Вывод сообщения об исключительной ситуации
        }
    }
}
