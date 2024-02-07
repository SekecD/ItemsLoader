package ru.mastaa.qub.MinecraftCore;

import java.util.Stack;

public class EntityTracker {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int max2 = Integer.MIN_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < max2) {
                return true;
            }

            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                max2 = stack.pop();
            }

            stack.push(nums[i]);
        }

        return false;

    }
}
