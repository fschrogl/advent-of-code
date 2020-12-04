package at.schrogl.aoc.d02;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day02Application {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<String> inputLines = getInputLines();

        System.out.println("## DAY 02: Part I");
        long validPasswords1 = inputLines.stream()
            .map(PasswordChecker::new)
            .filter(PasswordChecker::isValidForPart1)
            .count();
        System.out.printf("Total passwords/Correct passwords: %d/%d\n", inputLines.size(), validPasswords1);

        System.out.println("\n## DAY 02: Part II");
        long validPasswords2 = inputLines.stream()
            .map(PasswordChecker::new)
            .filter(PasswordChecker::isValidForPart2)
            .count();
        System.out.printf("Total passwords/Correct passwords: %d/%d\n", inputLines.size(), validPasswords2);
    }

    private static List<String> getInputLines() throws IOException, URISyntaxException {
        try (Stream<String> inputLines = Files.lines(Paths.get(Day02Application.class.getResource("input.txt").toURI()))) {
            return inputLines.collect(Collectors.toList());
        }
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

        public boolean isValidForPart1() {
            long letterCount = password.chars()
                .filter(c -> (c == letter))
                .count();
            return letterCount >= lowerBound && letterCount <= upperBound;
        }

        public boolean isValidForPart2() {
            char charAtPos1 = password.charAt(lowerBound - 1);
            char charAtPos2 = password.charAt(upperBound - 1);
            return Boolean.logicalXor(charAtPos1 == letter, charAtPos2 == letter);
        }
    }

}
