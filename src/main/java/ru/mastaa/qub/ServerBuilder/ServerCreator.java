package ru.mastaa.qub.ServerBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ServerCreator {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        Map<String, Integer> dp = new HashMap<>();

        int maxLength = 1;

        for (String word : words) {
            int currentLength = 1;

            for (int i = 0; i < word.length(); i++) {
                StringBuilder predecessor = new StringBuilder(word);
                predecessor.deleteCharAt(i);

                int predecessorLength = dp.getOrDefault(predecessor.toString(), 0);


                currentLength = Math.max(currentLength, predecessorLength + 1);


            }

            dp.put(word, currentLength);


            maxLength = Math.max(maxLength, currentLength);
        }


        return maxLength;
    }
}
