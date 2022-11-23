package org.rsreu;

import org.junit.Assert;
import org.junit.Test;
import org.rsreu.structure.Graph;

public class AppTest {
    private static final int INF = Integer.MAX_VALUE;

    @Test
    public void shouldGetCorrectWays() {
        Graph graph;
        FordBellman alg = new FordBellman();
        int[][] adjMatrix = new int[][]{
                {INF, 5, INF, 1, INF},
                {INF, INF, 7, INF, 1},
                {INF, INF, INF, INF, 42},
                {INF, INF, 3, INF, INF},
                {INF, INF, INF, INF, INF}
        };
        graph = new Graph(adjMatrix);
        int[] expected = new int[]{0, 5, 4, 1, 6};
        int[] result = alg.run(graph, 0);
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], result[i]);
        }

        adjMatrix = new int[][]{
                {INF, 1, INF, INF, 3},
                {INF, INF, 3, 3, -5},
                {INF, INF, INF, 1, -5},
                {INF, INF, 2, INF, INF},
                {INF, -5, INF, 4, INF}
        };
        graph = new Graph(adjMatrix);
        try {
            alg.run(graph, 0);
            Assert.fail();
        } catch (IllegalArgumentException exception) {
            Assert.assertTrue(true);
        }

        adjMatrix = new int[][]{
                {INF, 1, INF, INF, 3},
                {INF, INF, 3, 3, 8},
                {INF, INF, INF, 1, -5},
                {INF, INF, 2, INF, INF},
                {INF, 8, INF, 4, INF}
        };
        graph = new Graph(adjMatrix);
        expected = new int[]{0, 1, 4, 3, -1};
        result = alg.run(graph, 0);
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], result[i]);
        }

        adjMatrix = new int[][]{
                {INF, -3, INF, INF, 2, INF, INF, INF, INF},
                {INF, INF, -5, 15, 12, INF, INF, INF, INF},
                {INF, INF, INF, 8, INF, INF, INF, 24, INF},
                {6, INF, 8, INF, INF, 18, 4, INF, 11},
                {INF, 12, INF, -7, INF, 20, INF, INF, INF},
                {INF, INF, INF, INF, 20, INF, 9, INF, INF},
                {INF, INF, -10, 4, INF, 9, INF, 16, INF},
                {INF, INF, 24, INF, INF, INF, 16, INF, 22},
                {INF, INF, INF, 11, INF, -13, INF, INF, INF},
        };
        graph = new Graph(adjMatrix);
        expected = new int[]{0, -3, -11, -5, 2, -7, -1, 13, 6};
        result = alg.run(graph, 0);
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], result[i]);
        }
    }
}
