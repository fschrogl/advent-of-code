package at.schrogl.aoc.d18;

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

public class Day18 extends AbstractSolution {
    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(18, "Operation Order");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day18.class.getResource("input-example1.txt"), 26457L);

        long result = 0L;
        StringBuilder detailResult = new StringBuilder();
        for (String line : data.getInput().asLines()) {
            String formula = line.substring(0, line.indexOf("=")).trim();
            long expectedResult = Long.parseLong(line.substring(line.lastIndexOf("=") + 1).trim());
            long actualResult = Day18Part1Solver.solve(formula);
            if (expectedResult == actualResult) {
                result += actualResult;
                detailResult.append(actualResult).append(", ");
            } else {
                throw new RuntimeException(String.format("Formula %s evaluated to %d", line, actualResult));
            }
        }

        data.setActualResult(result);
        data.setActualResultDetails(detailResult::toString);

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(Day18.class.getResource("input-exercise12.txt"), 202553439706L);

        long sumOfAllFormulas = data.getInput().asLines().parallelStream()
            .mapToLong(Day18Part1Solver::solve)
            .sum();

        data.setActualResult(sumOfAllFormulas);

        return data;
    }

    @Override
    protected SolutionData example2() {
        SolutionData data = SolutionData.from(Day18.class.getResource("input-example2.txt"), 694173L);

        long result = 0L;
        StringBuilder detailResult = new StringBuilder();
        for (String line : data.getInput().asLines()) {
            String formula = line.substring(0, line.indexOf("=")).trim();
            long expectedResult = Long.parseLong(line.substring(line.lastIndexOf("=") + 1).trim());
            long actualResult = Day18Part2Solver.solve(formula);
            if (expectedResult == actualResult) {
                result += actualResult;
                detailResult.append(actualResult).append(", ");
            } else {
                throw new RuntimeException(String.format("Formula %s evaluated to %d", line, actualResult));
            }
        }

        data.setActualResult(result);
        data.setActualResultDetails(detailResult::toString);

        return data;
    }

    @Override
    protected SolutionData exercise2() {
        SolutionData data = SolutionData.from(Day18.class.getResource("input-exercise12.txt"), 88534268715686L);

        long sumOfAllFormulas = data.getInput().asLines().parallelStream()
            .mapToLong(Day18Part2Solver::solve)
            .sum();

        data.setActualResult(sumOfAllFormulas);

        return data;
    }

    public static void main(String[] args) {
        new Day18().execute();
    }
}
