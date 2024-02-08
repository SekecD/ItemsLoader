package ru.mastaa.qub.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIConnector {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> groups = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < groupSizes.length; i++) {
            int groupSize = groupSizes[i];
            if (!groups.containsKey(groupSize)) {
                groups.put(groupSize, new ArrayList<>());
            }

            List<Integer> group = groups.get(groupSize);
            group.add(i);
            if (group.size() == groupSize) {
                result.add(group);
                groups.put(groupSize, new ArrayList<>());
            }
        }
            return result;
    }
}
