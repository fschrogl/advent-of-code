package at.schrogl.aoc.d15;

import java.util.HashMap;
import java.util.Map;

public class Day15Solver {

    public static long solve(String formula, long iterations) {
        Map<Long, Integer> numberOccurrenceIndex = new HashMap<>();
        String[] startingNumbers = formula.split(",");
        Long lastNumberSpoken = null;
        Long thisNumberSpoken = null;

        for (int i = 0; i < startingNumbers.length - 1; i++) {
            lastNumberSpoken = Long.parseLong(startingNumbers[i]);
            numberOccurrenceIndex.put(lastNumberSpoken, i);
        }
        lastNumberSpoken = Long.parseLong(startingNumbers[startingNumbers.length - 1]);

        for (int i = startingNumbers.length; i < iterations; ) {
            Integer indexLastNumberSpokenOccurrence = numberOccurrenceIndex.get(lastNumberSpoken);
            thisNumberSpoken = (indexLastNumberSpokenOccurrence == null) ? 0 : (long) i - 1 - indexLastNumberSpokenOccurrence;
            numberOccurrenceIndex.put(lastNumberSpoken, i - 1);
            lastNumberSpoken = thisNumberSpoken;
            i++;
        }

        return thisNumberSpoken;
    }
}
