package at.schrogl.aoc.d06;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day06Application {

    public static void main(String[] args) throws IOException, URISyntaxException {
        final List<String> inputLines = getInputLines();

        System.out.println("## DAY 06: Part I");
        long uniqueYesAnwsersOverAllGroups = 0;
        Set<Integer> uniqueYesForGroup = new HashSet<>();
        for (String inputLine : inputLines) {
            if (inputLine.isBlank()) {
                uniqueYesAnwsersOverAllGroups += uniqueYesForGroup.size();
                uniqueYesForGroup.clear();
            } else {
                uniqueYesForGroup.addAll(inputLine.chars()
                    .boxed()
                    .collect(Collectors.toSet()));
            }
        }
        uniqueYesAnwsersOverAllGroups += uniqueYesForGroup.size();

        System.out.printf("All unique 'yes' answers per group: %d\n", uniqueYesAnwsersOverAllGroups);
    }

    private static List<String> getInputLines() throws IOException, URISyntaxException {
        try (Stream<String> inputLines = Files.lines(Paths.get(Day06Application.class.getResource("input.txt").toURI()))) {
            return inputLines.collect(Collectors.toList());
        }
    }

}
