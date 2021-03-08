package at.schrogl.aoc.d14;

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

public class Day14 extends AbstractSolution {
    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(14, "Docking Data");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day14.class.getResource("input-example1.txt"), 165L);

        Day14Part1Solver day14Part1Solver = new Day14Part1Solver();
        day14Part1Solver.computeInput(data.getInput().asLines());
        data.setActualResult(day14Part1Solver.getResultSum());
        data.setActualResultDetails(day14Part1Solver::getMemoryContents);

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(Day14.class.getResource("input-exercise12.txt"), 7477696999511L);

        Day14Part1Solver day14Part1Solver = new Day14Part1Solver();
        day14Part1Solver.computeInput(data.getInput().asLines());
        data.setActualResult(day14Part1Solver.getResultSum());
//        data.setActualResultDetails(day14Part1Solver::getMemoryContents);

        return data;
    }

    @Override
    protected SolutionData example2() {
        SolutionData data = SolutionData.from(Day14.class.getResource("input-example2.txt"), 208L);

        Day14Part2Solver day14Part2Solver = new Day14Part2Solver();
        day14Part2Solver.computeInput(data.getInput().asLines());
        data.setActualResult(day14Part2Solver.getResultSum());
        data.setActualResultDetails(day14Part2Solver::getMemoryContents);

        return data;
    }

    @Override
    protected SolutionData exercise2() {
        SolutionData data = SolutionData.from(Day14.class.getResource("input-exercise12.txt"), 3687727854171L);

        Day14Part2Solver day14Part2Solver = new Day14Part2Solver();
        day14Part2Solver.computeInput(data.getInput().asLines());
        data.setActualResult(day14Part2Solver.getResultSum());
//        data.setActualResultDetails(day14Part2Solver::getMemoryContents);

        return data;
    }

    public static void main(String[] args) {
        new Day14().execute();
    }
}
