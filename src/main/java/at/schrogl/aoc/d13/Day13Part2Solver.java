package at.schrogl.aoc.d13;

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

import java.util.ArrayList;
import java.util.List;

public class Day13Part2Solver {

    private List<Bus> buses = new ArrayList<>();

    public Day13Part2Solver parse(String input) {
        String[] inputSplits = input.trim().split(",");
        for (int i = 0; i < inputSplits.length; i++) {
            if (!"X".equalsIgnoreCase(inputSplits[i])) {
                buses.add(new Bus(Integer.parseInt(inputSplits[i]), i));
            }
        }
        return this;
    }

    public long getAlignedDepartureTime() {
        long[] normalizedIntervals = buses.stream().mapToLong(Bus::getNormalizedDepartureTime).toArray();

        long leastCommonMultiple = 0L;

        // TODO

        return leastCommonMultiple;
    }

    public boolean isValidResult(long departureTime) {
        return buses.stream().allMatch(b -> b.matchesDepartureTime(departureTime));
    }

    static class Bus {

        private final int busId;
        private final int position;

        public Bus(int busId, int position) {
            this.busId = busId;
            this.position = position;
        }

        public int getNormalizedDepartureTime() {
            return (busId - position);
        }

        public boolean matchesDepartureTime(long departureTime) {
            return departureTime % getNormalizedDepartureTime() == 0;
        }
    }
}
