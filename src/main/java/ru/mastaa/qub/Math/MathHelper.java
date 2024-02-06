package ru.mastaa.qub.Math;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MathHelper {

    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        Map<Character, Set<Character>> replacementMap = new HashMap<>();
        for (char[] mapping : mappings) {
            char oldChar = mapping[0];
            char newChar = mapping[1];
            replacementMap.putIfAbsent(oldChar, new HashSet<>());
            replacementMap.get(oldChar).add(newChar);
        }
        return backtrack(s, sub, 0, 0, replacementMap);
    }

    private boolean backtrack(String s, String sub, int sIndex, int subIndex, Map<Character, Set<Character>> replacementMap) {
        if (subIndex == sub.length()) {
            return true;
        }
        if (sIndex == s.length()) {
            return false;
        }
        char sChar = s.charAt(sIndex);
        char subChar = sub.charAt(subIndex);

        if (sChar == subChar) {
            return backtrack(s, sub, sIndex + 1, subIndex + 1, replacementMap);
        }

        if (replacementMap.containsKey(sChar)) {
            Set<Character> possibleReplacements = replacementMap.get(sChar);
            for (char replacement : possibleReplacements) {
                if (replacement == subChar) {
                    if (backtrack(s, sub, sIndex + 1, subIndex + 1, replacementMap)) {
                        return true;
                    }
                }
            }
        }

        return backtrack(s, sub, sIndex + 1, subIndex, replacementMap);
    }
}
