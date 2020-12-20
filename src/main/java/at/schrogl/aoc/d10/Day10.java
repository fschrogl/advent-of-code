package at.schrogl.aoc.d10;

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.common.SolutionData;

public class Day10 extends AbstractSolution {
    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(10, "Adapter Array");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(getClass().getResource("input-example12.txt"), 0L);


        return data;
    }

    @Override
    protected SolutionData example2() {
        return null;
    }

    @Override
    protected SolutionData exercise1() {
        return null;
    }

    @Override
    protected SolutionData exercise2() {
        return null;
    }

    public static void main(String[] args) {
        new Day10().execute();
    }
}
