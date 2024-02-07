package ru.mastaa.qub.ServerBuilder;

public class ServerFinder {
    public char findTheDifference(String s, String t) {
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            count[c - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] == -1) {
                return (char) (i + 'a');
            }
        }

        throw new IllegalArgumentException("invalid strings");
    }
}
