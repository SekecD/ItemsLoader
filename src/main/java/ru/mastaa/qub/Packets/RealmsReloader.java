package ru.mastaa.qub.Packets;

public class RealmsReloader {
    public int maxSubArray(int[] nums) {
        return mxaSubArrayHelper(nums, 0, nums.length - 1);
    }

    private int mxaSubArrayHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;
        int leftSum = mxaSubArrayHelper(nums, left, mid);
        int rightSum = mxaSubArrayHelper(nums, mid + 1, right);
        int crossingSum = maxCrossingSum(nums, left, mid, right);

        return Math.max(Math.max(leftSum, rightSum), crossingSum);
    }

    private int maxCrossingSum(int[] nums, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        leftSum = Math.max(leftSum, sum);

        for (int i = mid; i >= left; i --) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;

        for (int i = mid + 1; i <= right; i++) {
            rightSum = Math.max(rightSum, sum);
        }

        return leftSum + rightSum;
    }
}
