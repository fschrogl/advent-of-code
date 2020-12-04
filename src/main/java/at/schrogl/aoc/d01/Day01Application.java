package at.schrogl.aoc.d01;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        try (Stream<String> inputLines = Files.lines(Paths.get(Day01Application.class.getResource("input.txt").toURI()))) {
            return inputLines
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        }
    }

    private void solvePart1(List<Integer> inputList) {
        for (int i = 0; i < (inputList.size() - 1); i++) {
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
        System.out.println("No solution was found!");
    }

    private void solvePart2(List<Integer> inputList) {
        for (int i = 0; i < (inputList.size() - 1); i++) {
            int base = inputList.get(i);
            List<Integer> inputSublist2 = inputList.subList(i + 1, inputList.size());
            for (int i1 = 0; i1 < inputSublist2.size(); i1++) {
                int base2 = inputSublist2.get(i1);
                if (base + base2 < 2020) {
                    for (Integer next : inputList.subList(i1 + 1, inputSublist2.size())) {
                        if (base + base2 + next == 2020) {
                            System.out.println("Sum: " + base + " + " + base2 + " + " + next + " = " + (base + base2 + next));
                            System.out.println("Multiply: " + base + " * " + base2 + " * " + next + " = " + (base * base2 * next));
                            System.out.println();
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("No solution was found!");
    }

}
