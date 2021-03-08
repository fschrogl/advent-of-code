package at.schrogl.aoc.d13;

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

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.common.SolutionData;

import java.util.List;

public class Day13 extends AbstractSolution {
    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(13, "Shuttle Search");
    }

    @Override
    protected SolutionData example1() {
        final SolutionData data = SolutionData.from(Day13.class.getResource("input-example1.txt"), 295L);

        Day13Part1Solver day13Part1Solver = new Day13Part1Solver(data.getInput().asLines());
        Day13Part1Solver.BusSchedule bus = day13Part1Solver.getEarliestBusForStartingTime(0);

        data.setActualResult(bus.getTimeToNextDeparture(0) * bus.getId());
        data.setActualResultDetails(() ->
            String.format("BusId: %d, Next Departure: %d",
                bus.getId(),
                bus.getNextDepartureFor(0))
        );

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        final SolutionData data = SolutionData.from(Day13.class.getResource("input-exercise12.txt"), 2935L);

        Day13Part1Solver day13Part1Solver = new Day13Part1Solver(data.getInput().asLines());
        Day13Part1Solver.BusSchedule bus = day13Part1Solver.getEarliestBusForStartingTime(0);

        data.setActualResult(bus.getTimeToNextDeparture(0) * bus.getId());
        data.setActualResultDetails(() ->
            String.format("BusId: %d, Next Departure: %d",
                bus.getId(),
                bus.getNextDepartureFor(0))
        );

        return data;
    }

    @Override
    protected SolutionData example2() {
        SolutionData data = SolutionData.from(Day13.class.getResource("input-example2.txt"), 0L);

        long[] results = new long[]{1068788L, 3417L, 754018L, 779210L, 1261476L, 1202161486L};
        List<String> input = data.getInput().asLines();
        if (results.length != input.size()) {
            throw new RuntimeException(String.format("Amount of examples and results don't match (%d != %d)", results.length, input.size()));
        }

        for (int i = 0; i < input.size(); i++) {
            Day13Part2Solver day13Part2Solver = new Day13Part2Solver().parse(input.get(i));
            long actualResult = day13Part2Solver.getAlignedDepartureTime();
            long expectedResult = results[i];

            boolean expectedValid = day13Part2Solver.isValidResult(expectedResult);
            boolean actualValid = day13Part2Solver.isValidResult(actualResult);
            boolean equalResults = (expectedResult == actualResult);

            if (!expectedValid || !actualValid || !equalResults) {
                String message = String.format(
                    "Error for input line %d. Expected: %d (%s) Actual: %d (%s)",
                    i + 1,
                    expectedResult, expectedValid ? "OK" : "NOK",
                    actualResult, actualValid ? "OK" : "NOK"
                );
                throw new RuntimeException(message);
            }
        }

        data.setActualResult(data.getExpectedResult());
        return data;
    }

    @Override
    protected SolutionData exercise2() {
        return null;
    }

    public static void main(String[] args) {
        new Day13().execute();
    }
}
