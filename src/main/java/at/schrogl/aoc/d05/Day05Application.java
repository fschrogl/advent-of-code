package at.schrogl.aoc.d05;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day05Application {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> inputLines = getInputLines();

        System.out.println("## DAY 05: Part I");
        System.out.printf("Total number of seats: %d\n", inputLines.size());
        Long highestSeatId = inputLines.stream()
            .mapToLong(Day05Application::calculateSeatId)
            .max()
            .getAsLong();
        System.out.printf("Highest seat id: %d\n", highestSeatId);
    }

    private static long calculateSeatId(String searchPattern) {
        int[] rowSteps = {64, 32, 16, 8, 4, 2, 1};
        int low = 0, high = 127;

        for (int i = 0; i < 7; i++) {
            char letter = searchPattern.charAt(i);
            if (letter == 'F')
                high = (high - rowSteps[i]);
            if (letter == 'B')
                low = low + rowSteps[i];
        }
        int row = low;

        int[] columnSteps = {4, 2, 1};
        low = 0;
        high = 7;
        for (int i = 0; i < 3; i++) {
            char letter = searchPattern.substring(7).charAt(i);
            if (letter == 'L')
                high = (high - columnSteps[i]);
            if (letter == 'R')
                low = low + columnSteps[i];
        }
        int column = low;

        return (row * 8) + column;
    }

    private static List<String> getInputLines() throws IOException, URISyntaxException {
        try (Stream<String> inputLines = Files.lines(Paths.get(Day05Application.class.getResource("input.txt").toURI()))) {
            return inputLines.collect(Collectors.toList());
        }
    }

}
