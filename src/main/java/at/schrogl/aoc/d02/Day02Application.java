package at.schrogl.aoc.d02;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day02Application {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Day02Application day02Application = new Day02Application();

        System.out.println("## Day 2: Password Philosophy");
        List<String> inputLines = getInputLines();
        long validPasswords = inputLines.stream()
            .map(PasswordChecker::new)
            .filter(PasswordChecker::isValid)
            .count();
        System.out.printf("Total passwords/Correct passwords: %d/%d", inputLines.size(), validPasswords);
    }

    private static List<String> getInputLines() throws IOException, URISyntaxException {
        return Files.lines(Paths.get(Day02Application.class.getResource("input.txt").toURI()))
            .collect(Collectors.toList());
    }

    private static class PasswordChecker {

        private final String password;
        private final char letter;
        private final int lowerBound;
        private final int upperBound;

        public PasswordChecker(String line) {
            this.password = line.split(":")[1].trim();
            String validationChunk = line.split(":")[0];
            this.letter = validationChunk.charAt(validationChunk.length() - 1);
            this.lowerBound = Integer.parseInt(validationChunk.split("-")[0]);
            this.upperBound = Integer.parseInt(validationChunk.substring(0, validationChunk.length() - 2).split("-")[1]);
        }

        public boolean isValid() {
            long letterCount = password.chars()
                .filter(c -> (c == letter))
                .count();
            return letterCount >= lowerBound && letterCount <= upperBound;
        }
    }

}
