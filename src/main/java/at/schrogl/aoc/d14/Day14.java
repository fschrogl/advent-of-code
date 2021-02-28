package at.schrogl.aoc.d14;

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
