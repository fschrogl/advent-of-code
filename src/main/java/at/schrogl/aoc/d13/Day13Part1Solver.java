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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day13Part1Solver {

    private final List<BusSchedule> busSchedules;

    public Day13Part1Solver(List<String> inputLines) {
        long earliestDepartureTime = Long.parseLong(inputLines.get(0).trim());
        busSchedules = Arrays.stream(inputLines.get(1).trim().split(","))
            .filter(id -> !"X".equalsIgnoreCase(id))
            .map(id -> new BusSchedule(earliestDepartureTime, Long.parseLong(id)))
            .collect(Collectors.toList());
    }

    public BusSchedule getEarliestBusForStartingTime(int startingTime) {
        BusSchedule earliestBus = busSchedules.get(0);
        for (BusSchedule currentBus : busSchedules) {
            long earliestCurrentBus = earliestBus.getNextDepartureFor(startingTime);
            long earliestNewBus = currentBus.getNextDepartureFor(startingTime);
            if (earliestNewBus < earliestCurrentBus) {
                earliestBus = currentBus;
            }
        }
        return earliestBus;
    }

    static class BusSchedule {

        private final long earliestStartingTime;
        private final long interval;

        public BusSchedule(long earliestStartingTime, long interval) {
            this.earliestStartingTime = earliestStartingTime;
            this.interval = interval;
        }

        public long getNextDepartureFor(long startingTime) {
            long minutesInBetween = (earliestStartingTime - startingTime);
            return (earliestStartingTime - (minutesInBetween % interval) + interval);
        }

        public long getTimeToNextDeparture(long startingTime) {
            return getNextDepartureFor(startingTime) - earliestStartingTime;
        }

        public long getId() {
            return interval;
        }
    }
}
