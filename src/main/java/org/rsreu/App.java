package org.rsreu;

import org.rsreu.structure.Graph;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);//Создание объекта scanner класса Scanner, который отвечает за чтение данных с консоли
        System.out.println("Insert vertex count");//Вывод сообщения о вводе количества вершин
        int vertexCount = scanner.nextInt();//Ввод количества вершин
        System.out.println("Insert arc count");//Вывод сообщения о вводе количества дуг
        int arcCount = scanner.nextInt();//Ввод количества дуг
        System.out.println("Insert start vertex");
        int start = scanner.nextInt() - 1;//Ввод вершины начала
        System.out.println("Insert finish vertex");
        int finish = scanner.nextInt() - 1;//Ввод вершины конца
        scanner.nextLine();//Чтение пустой строчки, без неё следующий вызов метода scanner прочтёт символ enter
        Integer[][] matrix = new Integer[vertexCount][vertexCount];//Создание квадратной матрицы смежности, заполненной нулями, размером, равным количеству вершин
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Integer.MAX_VALUE;//Каждый элемент, равный нулю в матрице, приравниваем к "бесконечности" (Integer.MAX_VALUE = 2^31 - 1)
            }
        }
        for (int i = 0; i < arcCount; i++) {//Цикл от нуля до количества дуг
            System.out.printf("Insert %d-th arc in format: start,final,weight\n", i + 1);//Вывод сообщения о вводе дуги в формате начальная вершина, конечная вершина и вес
            String[] arc = scanner.nextLine().split(",");//Ввод строки дуги, которая разбивается на 3 по разделителям (,)
            matrix[Integer.parseInt(arc[0]) - 1][Integer.parseInt(arc[1]) - 1] = Integer.parseInt(arc[2]);//Ввод дуги в матрицу смежности, где arc[0] - начало, arc[1] - конец, arc[2] - вес дуги
        }

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
