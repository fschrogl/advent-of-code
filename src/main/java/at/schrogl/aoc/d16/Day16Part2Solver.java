package at.schrogl.aoc.d16;

import at.schrogl.aoc.common.SolutionData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day16Part2Solver {

    List<FieldDefinition> ticketFields = new ArrayList<>();
    List<Long> myTicketNumbers = new ArrayList<>();
    Map<Integer, List<Long>> validNearbyTicketNumbersByPosition = new HashMap<>();

    public Day16Part2Solver(SolutionData.Data input) {
        parseInput(input);
        getFieldsInCorrectOrder();
        validateMyTicket();
    }

    private void parseInput(SolutionData.Data input) {
        int inputSection = 0;
        for (String line : input.asLines()) {
            if (line.isBlank()) {
                inputSection++;
                continue;
            }
            if (line.startsWith("your ticket:") || line.startsWith("nearby tickets:")) continue;

            switch (inputSection) {
                case 0: // Fields
                    FieldDefinition field = new FieldDefinition(line.substring(0, line.indexOf(':')));
                    for (String rangeValues : line.substring(field.name.length() + 2).split(" or ")) {
                        String[] rangeSplits = rangeValues.split("-");
                        field.addBounds(rangeSplits[0], rangeSplits[1]);
                    }
                    ticketFields.add(field);
                    break;
                case 1: // my ticket
                    for (String value : line.split(",")) {
                        myTicketNumbers.add(Long.parseLong(value));
                    }
                    break;
                case 2: // Nearby tickets
                    List<Long> ticketNumbers = Arrays.stream(line.split(","))
                        .map(Long::parseLong)
                        .collect(Collectors.toList());
                    boolean isValidTicket = ticketNumbers.stream()
                        .allMatch(aLong -> ticketFields.stream()
                            .anyMatch(fieldDefinition -> fieldDefinition.isValid(aLong)));
                    if (isValidTicket) {
                        for (int i = 0; i < ticketNumbers.size(); i++) {
                            List<Long> value = new ArrayList<>();
                            value.add(ticketNumbers.get(i));
                            validNearbyTicketNumbersByPosition.merge(i, value, (v, l) ->
                            {
                                v.addAll(l);
                                return v;
                            });
                        }
                    }
                    break;
            }
        }
    }

    private void getFieldsInCorrectOrder() {
        if (myTicketNumbers.size() != ticketFields.size()) {
            throw new RuntimeException(
                String.format("%d Rules found, but %d numbers on my ticket", ticketFields.size(), myTicketNumbers.size())
            );
        }

        FieldDefinition[] orderedFields = new FieldDefinition[ticketFields.size()];
        while (!ticketFields.isEmpty()) {
            FieldDefinition field = ticketFields.iterator().next();
            List<Integer> possiblePositions = new ArrayList<>();
            for (Map.Entry<Integer, List<Long>> numbersEntry : validNearbyTicketNumbersByPosition.entrySet()) {
                if (numbersEntry.getValue().stream().allMatch(field::isValid)) {
                    possiblePositions.add(numbersEntry.getKey());
                }
            }
            if (possiblePositions.size() == 1) {
                orderedFields[possiblePositions.get(0)] = field;
            } else {
                for (Integer possiblePosition : possiblePositions) {
                    if (orderedFields[possiblePosition] == null) {
                        orderedFields[possiblePosition] = field;
                    }
                }
            }
            ticketFields.remove(field);
        }

        ticketFields = Arrays.asList(orderedFields);
    }

    private void validateMyTicket() {
        for (int i = 0; i < myTicketNumbers.size(); i++) {
            if (!ticketFields.get(i).isValid(myTicketNumbers.get(i))) {
                throw new RuntimeException(
                    String.format("Ticket number %d doesn't match rule %s", myTicketNumbers.get(i), ticketFields.get(i))
                );
            }
        }
    }

    static class FieldDefinition {

        public final String name;
        private final List<long[]> bounds = new ArrayList<>();

        public FieldDefinition(String name) {
            this.name = name;
        }

        public boolean isValid(long value) {
            return bounds.stream().anyMatch(b -> value >= b[0] && value <= b[1]);
        }

        public void addBounds(String lower, String upper) {
            bounds.add(new long[]{Long.parseLong(lower), Long.parseLong(upper)});
        }

        @Override
        public String toString() {
            return String.format("[%s: %s]", name, bounds.stream().map(a -> a[0] + "-" + a[1]).collect(Collectors.joining(",")));
        }
    }

}
