package at.schrogl.aoc.d15;

/*-
 * #%L
 * Advent-Of-Code
 * %%
 * Copyright (C) 2020 - 2021 Fritz Schrogl
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

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
