package ru.mastaa.qub.MinecraftCore;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityLivingBase {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[] parent = new int[n];
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; i++) {
                int cost = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new Edge(i, j, cost));
            }
        }

        Collections.sort(edges, (a, b) -> a.cost - b.cost);

        int minCost = 0;
        int numEdges = 0;

        for (Edge edge : edges) {
            int u = edge.u;
            int v = edge.v;
            int cost = edge.cost;

            if (union(parent, rank, u, v)) {
                minCost += cost;
                numEdges++;

                if (numEdges == n - 1) {
                    break;
                }
            }
        }

        return minCost;

    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }

        return parent[x];
    }

    private boolean union(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rootX == rootY) {
            return false;
        }

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
            rank[rootY]++;
        }

        return true;
    }

    private static class Edge {
        int u;
        int v;
        int cost;

        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }

}
