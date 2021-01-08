package at.schrogl.aoc.d16;

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.common.SolutionData;

import java.util.ArrayList;
import java.util.List;

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
        SolutionData data = SolutionData.from(Day16.class.getResource("input-example2.txt"), 1716L);
        Day16Part2Solver day16Part2Solver = new Day16Part2Solver(data.getInput());
        data.setActualResult(day16Part2Solver.myTicketNumbers.stream().reduce(1L, Math::multiplyExact));
        data.setActualResultDetails(() ->
            String.format("my ticket: %s / Rules: %s", day16Part2Solver.myTicketNumbers, day16Part2Solver.ticketFields)
        );
        return data;
    }

    @Override
    protected SolutionData exercise2() {
        SolutionData data = SolutionData.from(Day16.class.getResource("input-exercise12.txt"), null);
        Day16Part2Solver day16Part2Solver = new Day16Part2Solver(data.getInput());

        List<Long> myDepartureNumbers = new ArrayList<>();
        List<Day16Part2Solver.FieldDefinition> departureFields = new ArrayList<>();
        for (int i = 0; i < day16Part2Solver.ticketFields.size(); i++) {
            if (day16Part2Solver.ticketFields.get(i).name.startsWith("departure")) {
                myDepartureNumbers.add(day16Part2Solver.myTicketNumbers.get(i));
                departureFields.add(day16Part2Solver.ticketFields.get(i));
            }
        }

        data.setActualResult(myDepartureNumbers.stream().reduce(1L, Math::multiplyExact));
        data.setActualResultDetails(
            () -> String.format("my 'departure' numbers: %s / Departure rules: %s", myDepartureNumbers, departureFields)
        );

        return data;
    }

    public static void main(String[] args) {
        new Day16().execute();
    }
}
