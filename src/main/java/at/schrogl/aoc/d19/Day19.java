package at.schrogl.aoc.d19;

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.common.SolutionData;

public class Day19 extends AbstractSolution {
    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(19, "Monster Messages");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day19.class.getResource("input-example1.txt"), 2L);

        Day19Part1Solver day19Part1Solver = new Day19Part1Solver();
        day19Part1Solver.parse(data.getInput().asLines());

        data.setActualResult(day19Part1Solver.countDataLinesMatchingRuleZero());
        data.setActualResultDetails(
            () -> String.format("Matching line numbers: %s", day19Part1Solver.getLineNumbersMatchingRuleZero())
        );
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
        new Day19().execute();
    }
}
