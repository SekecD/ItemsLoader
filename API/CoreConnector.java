package ru.mastaa.qub.API;

public class CoreConnector {
    public String minWindow(String s, String t) {
        int[] charFreq = new int[128];
        for (char c : t.toCharArray()) {
            charFreq[c]++;
        }

        int left = 0;
        int right = 0;
        int count = t.length();

        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        while (right < s.length()) {
            if (charFreq[s.charAt(right)] > 0) {
                count--;
            }
            charFreq[s.charAt(right)]--;

            while (count == 0) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                charFreq[s.charAt(left)]++;

                if (charFreq[s.charAt(left)] > 0) {
                    count++;
                }

                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}
