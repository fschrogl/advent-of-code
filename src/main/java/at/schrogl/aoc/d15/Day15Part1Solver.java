package at.schrogl.aoc.d15;

import java.util.HashMap;
import java.util.Map;

public class Day15Part1Solver {

    public static long solve(String formula) {
        Map<Long, Integer> numberOccurrenceIndex = new HashMap<>();
        String[] startingNumbers = formula.split(",");
        Long lastNumberSpoken = null;

        for (int i = 0; i < startingNumbers.length; i++) {
            lastNumberSpoken = Long.parseLong(startingNumbers[i]);
            numberOccurrenceIndex.put(lastNumberSpoken, i);
        }

        for (int i = startingNumbers.length; i < 10; ) {
            Integer indexLastNumberSpokenOccurrence = numberOccurrenceIndex.get(lastNumberSpoken);
            lastNumberSpoken = (indexLastNumberSpokenOccurrence <= 2) ? 0L : (long) (i - indexLastNumberSpokenOccurrence);
            numberOccurrenceIndex.put(lastNumberSpoken, i);
            i++;
        }

        return lastNumberSpoken;
    }
}
