package ru.mastaa.qub.ServerBuilder;

import java.util.Arrays;

public class ServerSelector {
    public int minOperations(int[] nums, int x) {
        int targetSum = Arrays.stream(nums).sum() -x;
        if (targetSum == 0) {
            return nums.length;
        }

        int maxSubArrLen = -1;
        int currentSum = 0;
        int left = 0;
        int right = 0;

        while (right < nums.length) {
            currentSum += nums[right];

            while (currentSum > targetSum && left <= right) {
                currentSum -= nums[left];
                left++;
            }

            if (currentSum == targetSum) {
                maxSubArrLen = Math.max(maxSubArrLen, right - left + 1);

            }

            right++;

        }

        return maxSubArrLen != -1 ? nums.length - maxSubArrLen : -1;
    }
}
