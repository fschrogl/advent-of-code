package at.schrogl.aoc.d13;

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.common.SolutionData;

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
        return null;
    }

    @Override
    protected SolutionData exercise2() {
        return null;
    }

    public static void main(String[] args) {
        new Day13().execute();
    }
}
