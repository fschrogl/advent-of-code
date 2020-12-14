package at.schrogl.aoc.d06;

/*-
 * #%L
 * Advent-Of-Code
 * %%
 * Copyright (C) 2020 Fritz Schrogl
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

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
