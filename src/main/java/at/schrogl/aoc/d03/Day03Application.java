package at.schrogl.aoc.d03;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day03Application {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> inputLines = getInputLines();
        FlightMap flightMap = new FlightMap(inputLines);
        flightMap.traverseFlightMap();

        System.out.println("## DAY 03: Part I");
        System.out.printf("Original flight map dimensions: %d x %d\n", flightMap.width(), flightMap.height());
        System.out.printf("Original flight map trees: %d\n", flightMap.treeCount());
        System.out.printf("Original flight map none-trees: %d\n", (flightMap.width() * flightMap.height()) - flightMap.treeCount());
        System.out.printf("Original flight map encountered trees: %d\n", flightMap.encounteredTrees());
    }

    private static List<String> getInputLines() throws IOException, URISyntaxException {
        try (Stream<String> inputLines = Files.lines(Paths.get(Day03Application.class.getResource("input.txt").toURI()))) {
            return inputLines.collect(Collectors.toList());
        }
    }

    private static class FlightMap {

        private static final char TREE = '#';
        private final char[][] map;

        private long treeCount;
        private long encounteredTrees;

        public FlightMap(List<String> inputLines) {
            this.map = new char[inputLines.size()][inputLines.get(0).trim().length()];
            for (int i = 0; i < inputLines.size(); i++) {
                String line = inputLines.get(i).trim();
                map[i] = line.toCharArray();
                treeCount += line.chars().filter(c -> (c == TREE)).count();
            }
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
            int posY = 1;

            do {
                posX += 3;
                posX = (posX >= map[0].length) ? (posX - map[0].length) : posX;
                encounteredTrees += (map[posY][posX] == TREE) ? 1 : 0;
                posY++;
            } while (posY < map.length);
        }
    }
}
