package at.schrogl.aoc.d16;

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.common.SolutionData;

public class Day16 extends AbstractSolution {
    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(16, "Ticket Translation");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day16.class.getResource("input-example1.txt"), 71L);
        Day16Part1Solver day16Part1Solver = new Day16Part1Solver(data.getInput());
        data.setActualResult(day16Part1Solver.getResult());
        data.setActualResultDetails(day16Part1Solver::getResultDetails);
        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(Day16.class.getResource("input-exercise12.txt"), 23925L);
        Day16Part1Solver day16Part1Solver = new Day16Part1Solver(data.getInput());
        data.setActualResult(day16Part1Solver.getResult());
        data.setActualResultDetails(day16Part1Solver::getResultDetails);
        return data;
    }


    @Override
    protected SolutionData example2() {
        SolutionData data = SolutionData.from(Day16.class.getResource("input-example2.txt"), null);


        return data;
    }

    @Override
    protected SolutionData exercise2() {
        return null;
    }

    public static void main(String[] args) {
        new Day16().execute();
    }
}
