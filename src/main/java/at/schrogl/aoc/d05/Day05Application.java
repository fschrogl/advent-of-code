package at.schrogl.aoc.d05;

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
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day05Application {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> inputLines = getInputLines();

        System.out.println("## DAY 05: Part I");
        System.out.printf("Total number of seats: %d\n", inputLines.size());
        List<Long> allSeatIds = inputLines.stream()
            .mapToLong(Day05Application::calculateSeatId)
            .sorted()
            .boxed()
            .collect(Collectors.toList());
        System.out.printf("Highest seat id: %d\n\n", allSeatIds.get(allSeatIds.size() - 1));

        System.out.println("## DAY 05: Part II");
        Iterator<Long> iterator = allSeatIds.iterator();
        long mySeatId = 0;
        long previousSeatId = iterator.next();
        while (iterator.hasNext()) {
            Long thisSeatId = iterator.next();
            if ((previousSeatId + 1L) != thisSeatId) {
                mySeatId = previousSeatId + 1L;
                break;
            }
            previousSeatId = thisSeatId;
        }
        System.out.printf("My seat id: %d\n", mySeatId);
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
        try (Stream<String> inputLines = Files.lines(Paths.get(Day05Application.class.getResource("input-exercise12.txt").toURI()))) {
            return inputLines.collect(Collectors.toList());
        }
    }

}
