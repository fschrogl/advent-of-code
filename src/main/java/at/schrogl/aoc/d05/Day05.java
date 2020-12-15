package at.schrogl.aoc.d05;

/*-
 * #%L
 * Advent-Of-Code
 * %%
 * Copyright (C) 2020 Fritz Schrogl
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

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.common.SolutionData;

import java.util.List;
import java.util.OptionalLong;
import java.util.stream.Collectors;

public class Day05 extends AbstractSolution {

    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(5, "Binary Boarding");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day05.class.getResource("input-example12.txt"), 820L);

        OptionalLong highestSeatId = data.getInput().asLines().stream()
            .mapToLong(this::calculateSeatId)
            .max();
        data.setActualResult(highestSeatId.isPresent() ? highestSeatId.getAsLong() : null);

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(Day05.class.getResource("input-exercise12.txt"), 989L);

        OptionalLong highestSeatId = data.getInput().asLines().stream()
            .mapToLong(this::calculateSeatId)
            .max();
        data.setActualResult(highestSeatId.isPresent() ? highestSeatId.getAsLong() : null);

        return data;
    }

    @Override
    protected SolutionData example2() {
        return null;
    }

    @Override
    protected SolutionData exercise2() {
        SolutionData data = SolutionData.from(Day05.class.getResource("input-exercise12.txt"), 548L);

        List<Long> allSeatIds = data.getInput().asLines().parallelStream()
            .mapToLong(this::calculateSeatId)
            .sorted()
            .boxed()
            .collect(Collectors.toList());

        List<Long> seatIdsMinusFirstAndLast = allSeatIds.subList(1, allSeatIds.size() - 1);
        for (int i = seatIdsMinusFirstAndLast.size() - 1; i > 0; i--) {
            long baseId = seatIdsMinusFirstAndLast.get(i);
            if (baseId - 2 == seatIdsMinusFirstAndLast.get(i - 1)) {
                data.setActualResult(baseId - 1);
                break;
            }
        }

        return data;
    }

    private long calculateSeatId(String searchPattern) {
        int[] rowSteps = {64, 32, 16, 8, 4, 2, 1};
        int low = 0, high = 127;

        for (int i = 0; i < 7; i++) {
            char letter = searchPattern.charAt(i);
            if (letter == 'F')
                high = (high - rowSteps[i]);
            if (letter == 'B')
                low = low + rowSteps[i];
        }
        int row = low;

        int[] columnSteps = {4, 2, 1};
        low = 0;
        high = 7;
        for (int i = 0; i < 3; i++) {
            char letter = searchPattern.substring(7).charAt(i);
            if (letter == 'L')
                high = (high - columnSteps[i]);
            if (letter == 'R')
                low = low + columnSteps[i];
        }
        int column = low;

        return (row * 8) + column;
    }

    public static void main(String[] args) {
        new Day05().execute();
    }
}
