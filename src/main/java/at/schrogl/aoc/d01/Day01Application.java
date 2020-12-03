package at.schrogl.aoc.d01;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day01Application {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Day01Application day01Application = new Day01Application();

        List<Integer> inputList = Collections.unmodifiableList(day01Application.getInputLines());

        System.out.println("## DAY 01: Part I");
        day01Application.solvePart1(inputList);

        System.out.println("## DAY 01: Part II");
        day01Application.solvePart2(inputList);
    }

    private List<Integer> getInputLines() throws IOException, URISyntaxException {
        return Files.lines(Paths.get(Day01Application.class.getResource("input.txt").toURI()))
            .map(Integer::valueOf)
            .collect(Collectors.toList());
    }

    private void solvePart1(List<Integer> inputList) {
        for (int i = 0; i < inputList.size(); i++) {
            int base = inputList.get(i);
            for (Integer next : inputList.subList(i + 1, inputList.size())) {
                if (base + next == 2020) {
                    System.out.println("Sum: " + base + " + " + next + " = " + (base + next));
                    System.out.println("Multiply: " + base + " * " + next + " = " + (base * next));
                    System.out.println();
                    return;
                }
            }
        }
    }

    private void solvePart2(List<Integer> inputList) {
        // TODO
    }

}
