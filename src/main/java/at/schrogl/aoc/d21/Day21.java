package at.schrogl.aoc.d21;

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.common.SolutionData;

public class Day21 extends AbstractSolution {
    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(21, "Allergen Assessment");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day21.class.getResource("input-example1.txt"), null);

        Day21Part1Solver day21Part1Solver = new Day21Part1Solver();
        day21Part1Solver.parseFoods(data.getInput().asLines());

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        return null;
    }

    @Override
    protected SolutionData example2() {
        return null;
    }

    @Override
    protected SolutionData exercise2() {
        return null;
    }

    public static void main(String[] args) {
        new Day21().execute();
    }
}
