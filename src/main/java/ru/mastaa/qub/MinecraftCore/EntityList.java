package ru.mastaa.qub.MinecraftCore;

public class EntityList {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] glasses = new double[query_row + 1][query_row + 1];
        glasses[0][0] = poured;

        for (int i = 0; i < query_row; i++) {
            for (int j = 0; j <= i; j++) {
                double overflow = (glasses[i][j] - 1.0) / 2.0;
                if (overflow > 0) {
                    glasses[i + 1][j] += overflow;
                    glasses[i + 1][j + 1] += overflow;
                }
            }
        }
            return Math.min(1.0, glasses[query_row][query_glass]);
    }
}
