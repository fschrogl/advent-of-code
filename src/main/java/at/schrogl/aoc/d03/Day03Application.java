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
        FlightMap flightMap = new FlightMap(inputLines, 0, 0);

        System.out.println("## DAY 03: Part I + II\n");
        System.out.printf("Original flight map dimensions: %d x %d\n", flightMap.width(), flightMap.height());
        System.out.printf("Original flight map trees: %d\n", flightMap.treeCount());
        System.out.printf("Original flight map none-trees: %d\n\n", (flightMap.width() * flightMap.height()) - flightMap.treeCount());

        long totalEncounteredTrees = 1;
        int[][] slopes = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
        for (int[] slope : slopes) {
            long encounteredTrees = getEncounteredTrees(inputLines, slope[0], slope[1]);
            System.out.printf("Original flight map encountered trees (right=%d, down=%d): %d\n", slope[0], slope[1], encounteredTrees);
            totalEncounteredTrees *= encounteredTrees;
        }
        System.out.printf("\nTotal encountered trees: %d", totalEncounteredTrees);
    }

    private static List<String> getInputLines() throws IOException, URISyntaxException {
        try (Stream<String> inputLines = Files.lines(Paths.get(Day03Application.class.getResource("input.txt").toURI()))) {
            return inputLines.collect(Collectors.toList());
        }
    }

    private static long getEncounteredTrees(List<String> inputLines, int xIncrement, int yIncrement) {
        FlightMap flightMap = new FlightMap(inputLines, xIncrement, yIncrement);
        flightMap.traverseFlightMap();
        return flightMap.encounteredTrees();
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
}
