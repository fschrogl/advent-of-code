package at.schrogl.aoc.d12;

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

import java.util.Arrays;

public class Day12 extends AbstractSolution {
    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(12, "Rain Risk");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(getClass().getResource("input-example12.txt"), 25L);

        NavigatableShip ship = new NavigatableShip();
        for (String command : data.getInput().asLines()) {
            ship.processCommand(command);
        }
        data.setActualResult(ship.getManhattanDistance());
        data.setActualResultDetails(() ->
            String.format("Current direction: %s, east/west: %d, north/south: %d",
                ship.facingDirection.name(),
                ship.east_west,
                ship.north_south)
        );

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(getClass().getResource("input-exercise12.txt"), 1838L);

        NavigatableShip ship = new NavigatableShip();
        for (String command : data.getInput().asLines()) {
            ship.processCommand(command);
        }
        data.setActualResult(ship.getManhattanDistance());
        data.setActualResultDetails(() ->
            String.format("Current direction: %s, east/west: %d, north/south: %d",
                ship.facingDirection.name(),
                ship.east_west,
                ship.north_south)
        );

        return data;
    }

    private static class NavigatableShip {

        private FacingDirection facingDirection = FacingDirection.EAST;
        private long east_west = 0L;
        private long north_south = 0L;

        public void processCommand(String cmd) {
            int steps = Integer.parseInt(cmd.substring(1));

            switch (cmd.charAt(0)) {
                case 'N':
                    north_south += steps;
                    break;
                case 'S':
                    north_south -= steps;
                    break;
                case 'E':
                    east_west += steps;
                    break;
                case 'W':
                    east_west -= steps;
                    break;
                case 'L':
                case 'R':
                    facingDirection = facingDirection.getNewFacingDirection(cmd);
                    break;
                case 'F':
                    if (facingDirection.equals(FacingDirection.EAST) || facingDirection.equals(FacingDirection.WEST)) {
                        east_west = east_west + (steps * facingDirection.sign);
                    } else {
                        north_south = north_south + (steps * facingDirection.sign);
                    }
                    break;
                default:
                    throw new RuntimeException();
            }
        }

        public long getManhattanDistance() {
            return Math.abs(east_west) + Math.abs(north_south);
        }

        private enum FacingDirection {
            EAST(0, +1),
            SOUTH(90, -1),
            WEST(180, -1),
            NORTH(270, +1);

            private final int degree;
            private final int sign;

            FacingDirection(int degree, int sign) {
                this.degree = degree;
                this.sign = sign;
            }

            public FacingDirection getNewFacingDirection(String directionCmd) {
                int degrees = Integer.parseInt(directionCmd.substring(1));
                int newDegree = degree + (degrees * (directionCmd.startsWith("R") ? 1 : -1));
                while (newDegree < 0 || newDegree > 270) {
                    newDegree += (newDegree > 270) ? -360 : 360;
                }
                int finalNewDegree = newDegree;
                return Arrays.stream(values())
                    .filter(fd -> fd.degree == finalNewDegree)
                    .findFirst()
                    .orElseThrow(() -> new NullPointerException(String.format("current: %s, cmd: %s", name(), directionCmd)));
            }

        }
    }

    @Override
    protected SolutionData example2() {
        SolutionData data = SolutionData.from(Day12.class.getResource("input-example12.txt"), 286L);

        NavigationPart2.Coordinate longitutde = new NavigationPart2.Coordinate('E', 10);
        NavigationPart2.Coordinate latitude = new NavigationPart2.Coordinate('N', 1);
        NavigationPart2.Waypoint waypoint = new NavigationPart2.Waypoint(longitutde, latitude);
        NavigationPart2.Ship ship = new NavigationPart2.Ship();

        for (String command : data.getInput().asLines()) {
            char cmd = command.charAt(0);
            long cmdValue = Long.parseLong(command.substring(1));

            switch (cmd) {
                case 'N':
                case 'E':
                case 'S':
                case 'W':
                    waypoint.move(cmd, cmdValue);
                    break;
                case 'L':
                case 'R':
                    waypoint.rotate(cmd, cmdValue);
                    break;
                case 'F':
                    ship.move(cmdValue, waypoint);
                    break;
                default:
                    throw new RuntimeException("Unknown command: " + command);
            }
        }

        data.setActualResult(ship.getManhattanDistance());
        data.setActualResultDetails(() ->
            String.format("Current waypoint: %d%c/%d%c  Current ship: %d%c/%d%c",
                waypoint.longitude.value, waypoint.longitude.direction,
                waypoint.latitude.value, waypoint.latitude.direction,
                ship.longitude.value, ship.longitude.direction,
                ship.latitude.value, ship.latitude.direction)
        );

        return data;
    }

    @Override
    protected SolutionData exercise2() {
        SolutionData data = SolutionData.from(Day12.class.getResource("input-exercise12.txt"), 89936L);

        NavigationPart2.Coordinate longitutde = new NavigationPart2.Coordinate('E', 10);
        NavigationPart2.Coordinate latitude = new NavigationPart2.Coordinate('N', 1);
        NavigationPart2.Waypoint waypoint = new NavigationPart2.Waypoint(longitutde, latitude);
        NavigationPart2.Ship ship = new NavigationPart2.Ship();

        for (String command : data.getInput().asLines()) {
            char cmd = command.charAt(0);
            long cmdValue = Long.parseLong(command.substring(1));

            switch (cmd) {
                case 'N':
                case 'E':
                case 'S':
                case 'W':
                    waypoint.move(cmd, cmdValue);
                    break;
                case 'L':
                case 'R':
                    waypoint.rotate(cmd, cmdValue);
                    break;
                case 'F':
                    ship.move(cmdValue, waypoint);
                    break;
                default:
                    throw new RuntimeException("Unknown command: " + command);
            }
        }

        data.setActualResult(ship.getManhattanDistance());
        data.setActualResultDetails(() ->
            String.format("Current waypoint: %d%c/%d%c  Current ship: %d%c/%d%c",
                waypoint.longitude.value, waypoint.longitude.direction,
                waypoint.latitude.value, waypoint.latitude.direction,
                ship.longitude.value, ship.longitude.direction,
                ship.latitude.value, ship.latitude.direction)
        );

        return data;
    }

    private static class NavigationPart2 {

        private static class Coordinate {
            private char direction;
            private long value;

            public Coordinate(char direction, long value) {
                this.direction = direction;
                this.value = value;
            }

            private void move(char direction, long steps) {
                value += (this.direction == direction) ? steps : steps * -1;
                if (value < 0) {
                    value = Math.abs(value);
                    this.direction = direction;
                }
            }
        }

        private static class Waypoint {

            private enum Quadrant {
                NW("NW", "SW", "NE"), NE("NE", "NW", "SE"),
                SE("SE", "NE", "SW"), SW("SW", "SE", "NW");

                private final String id;
                private final String left;
                private final String right;

                Quadrant(String id, String left, String right) {
                    this.id = id;
                    this.left = left;
                    this.right = right;
                }

                public static Quadrant valueFrom(String id) {
                    for (Quadrant quadrant : values()) {
                        if (id.equals(quadrant.id))
                            return quadrant;
                    }
                    throw new RuntimeException("Unknown quadrant id: " + id);
                }
            }

            private final Coordinate longitude; /* Longitude == East-West */
            private final Coordinate latitude; /* Latitude == North-South */

            public Waypoint(Coordinate longitude, Coordinate latitude) {
                this.longitude = longitude;
                this.latitude = latitude;

            }

            private void move(char direction, long steps) {
                switch (direction) {
                    case 'N':
                    case 'S':
                        latitude.move(direction, steps);
                        break;
                    case 'E':
                    case 'W':
                        longitude.move(direction, steps);
                        break;
                    default:
                        throw new RuntimeException("Unknown direction: " + direction);
                }
            }

            private void rotate(char direction, long degrees) {
                while (degrees > 0) {
                    long backup = latitude.value;
                    latitude.value = longitude.value;
                    longitude.value = backup;

                    Quadrant currentQuadrant = Quadrant.valueFrom(String.format("%c%c", latitude.direction, longitude.direction));
                    String newQuadrant = (direction == 'L') ? currentQuadrant.left : currentQuadrant.right;
                    latitude.direction = newQuadrant.charAt(0);
                    longitude.direction = newQuadrant.charAt(1);

                    degrees -= 90;
                }

            }
        }

        private static class Ship {

            private final Coordinate longitude = new Coordinate('E', 0); /* Longitude == East-West */
            private final Coordinate latitude = new Coordinate('N', 0); /* Latitude == North-South */

            public void move(long cmdValue, Waypoint waypoint) {
                longitude.move(waypoint.longitude.direction, waypoint.longitude.value * cmdValue);
                latitude.move(waypoint.latitude.direction, waypoint.latitude.value * cmdValue);
            }

            public Long getManhattanDistance() {
                return longitude.value + latitude.value;
            }
        }

    }


    public static void main(String[] args) {
        new Day12().execute();
    }
}
