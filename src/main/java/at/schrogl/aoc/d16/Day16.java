package at.schrogl.aoc.d16;

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.common.SolutionData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day16 extends AbstractSolution {
    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(16, "Ticket Translation");
    }

    @Override
    protected SolutionData example1() {
        return solvePart1(SolutionData.from(Day16.class.getResource("input-example12.txt"), 71L));
    }

    @Override
    protected SolutionData exercise1() {
        return solvePart1(SolutionData.from(Day16.class.getResource("input-exercise12.txt"), 23925L));
    }

    private SolutionData solvePart1(SolutionData data) {
        List<Long> nearbyTicketNumbers = new ArrayList<>();
        List<Range> validRanges = new ArrayList<>();

        int inputSection = 0;
        for (String line : data.getInput().asLines()) {
            if (line.isBlank()) {
                inputSection++;
                continue;
            }
            if (line.startsWith("your ticket:") || line.startsWith("nearby tickets:")) continue;

            switch (inputSection) {
                case 0: // Rules
                    for (String rangeValues : line.substring(line.indexOf(':') + 2).split(" or ")) {
                        String[] rangeSplits = rangeValues.split("-");
                        validRanges.add(new Range(Long.parseLong(rangeSplits[0]), Long.parseLong(rangeSplits[1])));
                    }
                    break;
                case 2: // Nearby tickets
                    for (String value : line.split(",")) {
                        nearbyTicketNumbers.add(Long.parseLong(value));
                    }
                    break;
            }
        }

        List<Long> ticketScanningErrorVales = nearbyTicketNumbers.stream()
            .filter(aLong -> validRanges.stream().noneMatch(range -> range.isValid(aLong)))
            .collect(Collectors.toList());
        data.setActualResult(ticketScanningErrorVales.stream().mapToLong(Long::longValue).sum());
        data.setActualResultDetails(ticketScanningErrorVales::toString);

        return data;
    }

    private static class Range {

        private long lowerBound;
        private long upperBound;

        public Range(long lowerBound, long upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        public boolean isValid(long value) {
            return value >= lowerBound && value <= upperBound;
        }

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
        new Day16().execute();
    }
}
