package ru.mastaa.qub.ServerBuilder;

import static java.lang.Integer.MIN_VALUE;

public class ServerSorter {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if (m > n) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;

            int tempLength = m;
            m = n;
            n = tempLength;
        }

        int left = 0;
        int right = m;
        int halfLength  = (m + n + 1) / 2;

        while (left <= right) {
            int partNum1= (left + right) / 2;
            int partNum2 = halfLength - partNum1;

            int maxLeftNums1 = (partNum1 == 0) ? MIN_VALUE : nums1[partNum1 - 1];
            int minRightNums1 = (partNum1 == m) ? MIN_VALUE : nums1[partNum1];
            int maxLeftNums2 = (partNum2 == 0) ? MIN_VALUE : nums2[partNum2 - 1];
            int minRightNums2 = (partNum2 == m) ? MIN_VALUE : nums2[partNum2];

            if (maxLeftNums1 <= minRightNums2 && maxLeftNums2 <= minRightNums1) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeftNums1, maxLeftNums2) + Math.min(minRightNums1, minRightNums2)) / 2.0;
                } else {
                    return Math.max(maxLeftNums1, maxLeftNums2);
                }
            } else if (maxLeftNums1 > minRightNums2) {
                right = partNum1 - 1;
            } else {
                left = partNum1 + 1;
            }
        }
        throw new IllegalArgumentException("Invalid");
    }
}
