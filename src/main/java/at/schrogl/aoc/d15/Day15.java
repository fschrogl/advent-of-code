package at.schrogl.aoc.d15;

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.common.SolutionData;

public class Day15 extends AbstractSolution {
    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(15, "Rambunctious Recitation");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day15.class.getResource("input-example1.txt"), 2826L);

        long result = 0L;
        StringBuilder detailResult = new StringBuilder();
        for (String line : data.getInput().asLines()) {
            String formula = line.substring(0, line.indexOf("=")).trim();
            long expectedResult = Long.parseLong(line.substring(line.lastIndexOf("=") + 1).trim());
            long actualResult = Day15Part1Solver.solve(formula);
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
    protected SolutionData example2() {
        SolutionData data = SolutionData.from(Day15.class.getResource("input-exercise12.txt"), 1085L);
        long result = Day15Part1Solver.solve(data.getInput().asLines().get(0));
        data.setActualResult(result);

        return data;
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
        new Day15().execute();
    }
}
