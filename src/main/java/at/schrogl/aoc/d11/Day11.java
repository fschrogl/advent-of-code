package at.schrogl.aoc.d11;

/*-
 * #%L
 * Advent-Of-Code
 * %%
 * Copyright (C) 2020 - 2021 Fritz Schrogl
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

import java.util.List;
import java.util.function.BiFunction;

public class Day11 extends AbstractSolution {
    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(11, "Seating System");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day11.class.getResource("input-example12.txt"), 37L);

        SeatMap seatMap = new SeatMap(data.getInput().asLines());
        boolean hasChanged;
        do {
            hasChanged = seatMap.simulatePart1();
        } while (hasChanged);

        data.setActualResult(seatMap.getOccupiedSeats());
        data.setActualResultDetails(() -> String.format("Iterations until stabilization %d", seatMap.iterations));

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(Day11.class.getResource("input-exercise12.txt"), 2453L);

        SeatMap seatMap = new SeatMap(data.getInput().asLines());
        boolean hasChanged;
        do {
            hasChanged = seatMap.simulatePart1();
        } while (hasChanged);

        data.setActualResult(seatMap.getOccupiedSeats());
        data.setActualResultDetails(() -> String.format("Iterations until stabilization %d", seatMap.iterations));

        return data;
    }

    @Override
    protected SolutionData example2() {
        SolutionData data = SolutionData.from(Day11.class.getResource("input-example12.txt"), 26L);

        SeatMap seatMap = new SeatMap(data.getInput().asLines());
        boolean hasChanged;
        do {
            hasChanged = seatMap.simulatePart2();
        } while (hasChanged);

        data.setActualResult(seatMap.getOccupiedSeats());
        data.setActualResultDetails(() -> String.format("Iterations until stabilization %d", seatMap.iterations));

        return data;
    }

    @Override
    protected SolutionData exercise2() {
        SolutionData data = SolutionData.from(Day11.class.getResource("input-exercise12.txt"), 2159L);

        SeatMap seatMap = new SeatMap(data.getInput().asLines());
        boolean hasChanged;
        do {
            hasChanged = seatMap.simulatePart2();
        } while (hasChanged);

        data.setActualResult(seatMap.getOccupiedSeats());
        data.setActualResultDetails(() -> String.format("Iterations until stabilization %d", seatMap.iterations));

        return data;
    }

    private static class SeatMap {

        private static final char FLOOR = '.';
        private static final char EMPTY = 'L';
        private static final char OCCUPIED = '#';

        private char[][] seatMap;
        private char[][] seatMapN;
        private long iterations = 0L;

        public SeatMap(List<String> sourceMap) {
            this.seatMap = new char[sourceMap.size()][sourceMap.get(0).trim().length()];
            this.seatMapN = new char[seatMap.length][seatMap[0].length];
            for (int i = 0; i < seatMap.length; i++) {
                seatMap[i] = sourceMap.get(i).trim().toCharArray();
            }
        }

        public boolean simulatePart1() {
            return simulate((y, x) -> {
                switch (seatMap[y][x]) {
                    case EMPTY:
                        return (getAdjacentOccupiedSeats(y, x) == 0) ? OCCUPIED : EMPTY;
                    case OCCUPIED:
                        return (getAdjacentOccupiedSeats(y, x) >= 4) ? EMPTY : OCCUPIED;
                    default:
                        return seatMap[y][x];
                }
            });
        }

        public boolean simulatePart2() {
            return simulate((y, x) -> {
                switch (seatMap[y][x]) {
                    case EMPTY:
                        return (getInSightOccupiedSeats(y, x) == 0) ? OCCUPIED : EMPTY;
                    case OCCUPIED:
                        return (getInSightOccupiedSeats(y, x) >= 5) ? EMPTY : OCCUPIED;
                    default:
                        return seatMap[y][x];
                }
            });
        }

        private boolean simulate(BiFunction<Integer, Integer, Character> rulesFunction) {
            boolean hasChangedHappened = false;

            for (int y = 0; y < seatMap.length; y++) {
                for (int x = 0; x < seatMap[y].length; x++) {
                    seatMapN[y][x] = rulesFunction.apply(y, x);
                    hasChangedHappened |= seatMap[y][x] != seatMapN[y][x];
                }
            }
            char[][] backup = seatMap;
            seatMap = seatMapN;
            seatMapN = backup;
            iterations++;

//            printSeatMap();

            return hasChangedHappened;
        }

        private void printSeatMap() {
            System.out.printf("Iteration %d\n", iterations);
            System.out.printf("Occupied seats %d\n", getOccupiedSeats());
            for (char[] chars : seatMap) {
                System.out.println(chars);
            }
            System.out.println();
        }

        private int getAdjacentOccupiedSeats(int y, int x) {
            int occupiedSeats = 0;

            int y1 = (y - 1 >= 0) ? y - 1 : y;
            int y2 = (y + 1 < seatMap.length) ? y + 1 : y;
            int x1 = (x - 1 >= 0) ? x - 1 : x;
            int x2 = (x + 1 < seatMap[0].length) ? x + 1 : x;

            for (int yi = y1; yi <= y2; yi++) {
                for (int xi = x1; xi <= x2; xi++) {

                    occupiedSeats += ((yi == y && xi == x) || seatMap[yi][xi] != OCCUPIED) ? 0 : 1;
                }
            }

            return occupiedSeats;
        }

        private int getInSightOccupiedSeats(int y, int x) {
            int occupiedSeats = 0;

            occupiedSeats += checkLineSightForIncrement(y, -1, x, 0); // North
            occupiedSeats += checkLineSightForIncrement(y, -1, x, 1); // NorthEast
            occupiedSeats += checkLineSightForIncrement(y, 0, x, 1); // East
            occupiedSeats += checkLineSightForIncrement(y, 1, x, 1); // SouthEast
            occupiedSeats += checkLineSightForIncrement(y, 1, x, 0); // South
            occupiedSeats += checkLineSightForIncrement(y, 1, x, -1); // SouthWest
            occupiedSeats += checkLineSightForIncrement(y, 0, x, -1); // West
            occupiedSeats += checkLineSightForIncrement(y, -1, x, -1); // NorthWest

            return occupiedSeats;
        }

        private int checkLineSightForIncrement(int y, int yStep, int x, int xStep) {
            int newY = y + yStep;
            int newX = x + xStep;

            while (newY >= 0 && newY < seatMap.length && newX >= 0 && newX < seatMap[0].length) {
                if (seatMap[newY][newX] != FLOOR) {
                    return (seatMap[newY][newX] == OCCUPIED) ? 1 : 0;
                }

                newY += yStep;
                newX += xStep;
            }

            return 0;
        }

        private long getOccupiedSeats() {
            long occupiedSeats = 0L;
            for (char[] row : seatMap) {
                for (char c : row) {
                    occupiedSeats += (OCCUPIED == c) ? 1 : 0;
                }
            }
            return occupiedSeats;
        }
    }

    public static void main(String[] args) {
        new Day11().execute();
    }

}
