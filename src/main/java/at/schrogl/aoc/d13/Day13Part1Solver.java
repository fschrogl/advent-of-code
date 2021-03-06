package at.schrogl.aoc.d13;

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

    public BusSchedule getEarliestBusForStartingTime(int earliestDeparture) {
        BusSchedule earliestBus = busSchedules.get(0);
        for (BusSchedule currentBus : busSchedules) {
            long earliestCurrentBus = earliestBus.getNextDepartureFor(earliestDeparture);
            long earliestNewBus = currentBus.getNextDepartureFor(earliestDeparture);
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
