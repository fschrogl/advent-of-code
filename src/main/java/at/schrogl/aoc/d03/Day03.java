package at.schrogl.aoc.d03;

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

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.common.SolutionData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day03 extends AbstractSolution {

    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(3, "Toboggan Trajectory");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day03.class.getResource("input-example12.txt"), 7L);

        FlightMap flightMap = new FlightMap(data.getInput().asLines(), 3, 1);
        flightMap.traverseFlightMap();
        data.setActualResult(flightMap.encounteredTrees());

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(Day03.class.getResource("input-exercise12.txt"), 205L);

        FlightMap flightMap = new FlightMap(data.getInput().asLines(), 3, 1);
        flightMap.traverseFlightMap();
        data.setActualResult(flightMap.encounteredTrees());

        return data;
    }

    @Override
    protected SolutionData example2() {
        SolutionData data = SolutionData.from(Day03.class.getResource("input-example12.txt"), 336L);

        List<String> input = data.getInput().asLines();
        List<Long> encounteredTreesPerSlope = new ArrayList<>();
        int[][] slopes = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
        for (int[] slope : slopes) {
            FlightMap flightMap = new FlightMap(input, slope[0], slope[1]);
            flightMap.traverseFlightMap();
            encounteredTreesPerSlope.add(flightMap.encounteredTrees());
        }
        data.setActualResult(encounteredTreesPerSlope.stream().reduce(Math::multiplyExact).orElse(-1L));
        data.setActualResultDetails(() ->
            String.format("%s = %d",
                encounteredTreesPerSlope.stream().map(String::valueOf).collect(Collectors.joining(" * ")),
                data.getActualResult())
        );

        return data;
    }

    @Override
    protected SolutionData exercise2() {
        SolutionData data = SolutionData.from(Day03.class.getResource("input-exercise12.txt"), 3952146825L);

        List<String> input = data.getInput().asLines();
        List<Long> encounteredTreesPerSlope = new ArrayList<>();
        int[][] slopes = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
        for (int[] slope : slopes) {
            FlightMap flightMap = new FlightMap(input, slope[0], slope[1]);
            flightMap.traverseFlightMap();
            encounteredTreesPerSlope.add(flightMap.encounteredTrees());
        }
        data.setActualResult(encounteredTreesPerSlope.stream().reduce(Math::multiplyExact).orElse(-1L));
        data.setActualResultDetails(() ->
            String.format("%s = %d",
                encounteredTreesPerSlope.stream().map(String::valueOf).collect(Collectors.joining(" * ")),
                data.getActualResult())
        );

        return data;
    }

    private static class FlightMap {


        private static final char TREE = '#';
        private final char[][] map;
        private final int posXincrement;
        private final int posYincrement;

        private long treeCount;
        private long encounteredTrees;

        public FlightMap(List<String> inputLines, int posXincrement, int posYinrement) {
            this.map = new char[inputLines.size()][inputLines.get(0).trim().length()];
            for (int i = 0; i < inputLines.size(); i++) {
                String line = inputLines.get(i).trim();
                map[i] = line.toCharArray();
                treeCount += line.chars().filter(c -> (c == TREE)).count();
            }
            this.posXincrement = posXincrement;
            this.posYincrement = posYinrement;
        }

        public int width() {
            return map[0].length;
        }

        public int height() {
            return map.length;
        }

        public long treeCount() {
            return treeCount;
        }

        public long encounteredTrees() {
            return encounteredTrees;
        }

        public void traverseFlightMap() {
            int posX = 0;
            int posY = posYincrement;

            do {
                posX += posXincrement;
                posX = (posX >= map[0].length) ? (posX - map[0].length) : posX;
                encounteredTrees += (map[posY][posX] == TREE) ? 1 : 0;
                posY += posYincrement;
            } while (posY < map.length);
        }
    }

    public static void main(String[] args) {
        new Day03().execute();
    }
}
