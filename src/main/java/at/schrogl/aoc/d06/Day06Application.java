package at.schrogl.aoc.d06;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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

        System.out.printf("All unique 'yes' answers per group: %d\n\n", uniqueYesAnwsersOverAllGroups);

        System.out.println("## DAY 06: Part II");

        long sumOfEveryoneYes = 0;
        Map<Integer, Integer> yesPerGroup = new HashMap<>();
        int groupSize = 0;
        for (String inputLine : inputLines) {
            if (inputLine.isBlank()) {
                Integer finalGroupSize = groupSize;
                final long count = yesPerGroup.values().stream()
                    .filter(i -> i.equals(finalGroupSize))
                    .count();
                sumOfEveryoneYes += count;
                groupSize = 0;
                yesPerGroup.clear();
            } else {
                groupSize++;
                inputLine.chars().forEach(c -> yesPerGroup.merge(c, 1, Integer::sum));
            }
        }
        Integer finalGroupSize = groupSize;
        final long count = yesPerGroup.values().stream()
            .filter(i -> i.equals(finalGroupSize))
            .count();
        sumOfEveryoneYes += count;

        System.out.printf("All unique 'yes' answers overall: %d\n\n", sumOfEveryoneYes);
    }

    private static List<String> getInputLines() throws IOException, URISyntaxException {
        try (Stream<String> inputLines = Files.lines(Paths.get(Day06Application.class.getResource("input-exercise12.txt").toURI()))) {
            return inputLines.collect(Collectors.toList());
        }
    }

}
