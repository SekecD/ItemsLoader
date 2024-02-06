package ru.mastaa.qub.API;

import java.util.Arrays;

public class PairsFinder {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);

        int[] pairs = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {
            int spell = spells[i];

            int left = 0;
            int right = potions.length - 1;
            int index = -1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if ((long) spell * potions[mid] >= success) {
                    index = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            pairs[i] = index == -1 ? 0 : potions.length - index;
        }

        return pairs;
    }
}
