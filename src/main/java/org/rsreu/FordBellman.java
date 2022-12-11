package org.rsreu;

import org.rsreu.structure.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FordBellman {

    private static final int INFINITY = Integer.MAX_VALUE;

    public Integer[] run(Graph graph, int start) throws RuntimeException {
        int vertCount = graph.getAdjList().length; //Определяется кол-во вершин
        Integer[][] adjList = graph.getAdjList();//Локальная переменная. Присвоение списка смежности
        int k = 0;//Порядок итерации
        Integer[] s;//Массив переменной S
        Integer[] previusWays = new Integer[vertCount];//Массив пердыдущих значений кратчайших путей
        Integer[] shortestWays = new Integer[vertCount];//Массив кратчайших путей
        for (int i = 0; i < vertCount; i++) {//Заполнение путей бесконечностями
            previusWays[i] = INFINITY;
        }
        previusWays[start] = 0;//Установка кратчайшего пути для начальной точки
        for (int i = 0; i < adjList[start].length; i++) {//Нулевая итерация. Установка первых кратчайших путей из начальной вершины
            previusWays[adjList[start][i]] = graph.getWeight(start, adjList[0][i]);
        }
        s = adjList[start];//формирование множества S
        while (k < vertCount - 1) {//Пока кол-во итераций меньше кол-ва вершин
            shortestWays = previusWays.clone();//Новая строка - копия предыдущей
            for (int i = 0; i < s.length; i++) {//Для каждой вершины в множестве S
                Integer[] gs = adjList[s[i]]; //формирование множества Г(S)
                for (int j = 0; j < gs.length; j++) {//Для каждой дуги, ведущей в вершину множества Г(S)
                    shortestWays[gs[j]] = Integer.min(shortestWays[gs[j]],
                            shortestWays[s[i]] + graph.getWeight(s[i], gs[j]));//Присвоение пометки
                }
            }
            int countOfMatch = 0;//Проверка условия окончания
            for (int i = 0; i < vertCount; i++) {//Подсчет пометок, значение которых после итерации не изменилось
                if(previusWays[i].equals(shortestWays[i])) countOfMatch++;
            }
            if (k <= vertCount - 1 && countOfMatch == vertCount) {
                break;//Получен оптимальный результат
            }
            if (k + 1 == vertCount - 1 && countOfMatch != vertCount) {
                throw new IllegalArgumentException("There is a negative sum cycle");//В графе существует цикл отрицательной длины
            }
            List<Integer> newS = new ArrayList<>();//Подготовка к следующей итерации
            for (int i = 0; i < s.length; i++) {//Для всех элементов из множества S
                newS.addAll(Arrays.stream(adjList[s[i]]).distinct().collect(Collectors.toList()));//К списку S прибавляется строка списка смежности, затем список удаляет все повторяющиеся элементы
            }
            for (int i = 0; i < vertCount; i++) {//Удаление вершин из множества S, пометки которых после итерации не изменились
                if(previusWays[i].equals(shortestWays[i])) newS.remove(Integer.valueOf(i));//Если пометка не изменилась с прошлой итерации, то она удаляется из нового формируемого множества S
            }
            s = newS.toArray(new Integer[0]);//Приведение списка к виду массива
            k++;//Инкремент
            previusWays = shortestWays.clone();//Сохранение результатов итерации в массив предыдущих значений кратчайших путей
        }
        return shortestWays.clone();
    }

}
