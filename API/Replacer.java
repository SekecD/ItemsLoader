package ru.mastaa.qub.API;

import java.util.HashMap;
import java.util.Map;

public class Replacer {
    public int[] arrayChange(int[] nums, int[][] operations) {

        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            indexMap.put(nums[i], i);
        }

        for (int[] operation : operations) {
            int oldValue = operation[0];
            int newValue = operation[1];

            int index = indexMap.get(oldValue);
            nums[index] = newValue;

            indexMap.put(newValue, index);

            indexMap.remove(oldValue);
        }
        return nums;
    }
}