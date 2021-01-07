package at.schrogl.aoc.d16;

import at.schrogl.aoc.common.SolutionData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day16Part1Solver {

    List<Long> nearbyTicketNumbers = new ArrayList<>();
    List<Day16Part1Solver.Range> validRanges = new ArrayList<>();
    List<Long> ticketsScanningErrorValues;

    public Day16Part1Solver(SolutionData.Data input) {
        int inputSection = 0;
        for (String line : input.asLines()) {
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

        ticketsScanningErrorValues = nearbyTicketNumbers.stream()
            .filter(aLong -> validRanges.stream().noneMatch(range -> range.isValid(aLong)))
            .collect(Collectors.toList());
    }

    public long getResult() {
        return ticketsScanningErrorValues.stream().mapToLong(Long::longValue).sum();
    }

    public String getResultDetails() {
        return ticketsScanningErrorValues.toString();
    }

    private static class Range {

        private final long lowerBound;
        private final long upperBound;

        public Range(long lowerBound, long upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        public boolean isValid(long value) {
            return value >= lowerBound && value <= upperBound;
        }

    }
}