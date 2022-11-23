package org.rsreu;

import org.rsreu.structure.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FordBellman {

    private static final int INFINITY = Integer.MAX_VALUE;

    public int[] run(Graph graph, int start) throws RuntimeException {
        int vertCount = graph.getAdjList().length; //Определяется кол-во вершин
        int[][] adjList = graph.getAdjList();
        int k = 0;//Порядок итерации
        List<Integer> src;
        List<Integer>[] shortestWays = new ArrayList[vertCount];
        shortestWays[k] = new ArrayList<>();
        for (int i = 0; i < vertCount; i++) {//Заполнение путей бесконечностями
            shortestWays[k].add(INFINITY);
        }
        shortestWays[k].set(start, 0);//Установка кратчайшего пути для начальной точки
        for (int i = 0; i < adjList[start].length; i++) {//Нулевая итерация. Установка первых кратчайших путей из начальной вершины
            shortestWays[k].set(adjList[start][i], graph.getWeight(start, adjList[0][i]));
        }
        src = Arrays.stream(adjList[start]).boxed().collect(Collectors.toList());//формирование множества S
        while (k < vertCount - 1) {//Пока кол-во итераций меньше кол-ва вершин
            shortestWays[k + 1] = new ArrayList<>(shortestWays[k]);
            for (Integer s : src) {//Для каждой вершины в множестве S
                for (int j = 0; j < adjList[s].length; j++) {//Для каждой дуги из вершины множества S
                    shortestWays[k + 1].set(adjList[s][j], Integer.min(shortestWays[k + 1].get(adjList[s][j]),
                            shortestWays[k + 1].get(s) + graph.getWeight(s, adjList[s][j])));//Присвоение минимального пути
                }
            }
            int countOfMatch = 0;//Проверка условия окончания
            for (int i = 0; i < vertCount; i++) {//Подсчет минимальных путей, значение которых после итерации не изменилось
                if(shortestWays[k].get(i).equals(shortestWays[k + 1].get(i))) countOfMatch++;
            }
            if (k <= vertCount - 1 && countOfMatch == vertCount) {
                break;//Получен оптимальный результат
            }
            if (k + 1 == vertCount - 1 && countOfMatch != vertCount) {
                throw new IllegalArgumentException("There is a negative sum cycle");//В графе существует цикл отрицательной длины
            }
            List<Integer> newSrc = new ArrayList<>();//Подготовка к слудующей итерации
            for (Integer s : src) {
                newSrc.addAll(Arrays.stream(adjList[s]).boxed().collect(Collectors.toList()));
            }
            src = newSrc.stream().distinct().collect(Collectors.toList());
            k++;//Инкремент
        }
        int[] result = new int[shortestWays[k].size()];//Перевод списка в массив integer
        for (int i = 0; i < vertCount; i++) result[i] = shortestWays[k].get(i);
        return result;
    }

}
