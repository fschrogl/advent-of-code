package at.schrogl.aoc.d02;

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.common.SolutionData;

import java.util.List;
import java.util.stream.Collectors;

public class Day02 extends AbstractSolution {

    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(2, "Password Philosophy");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day02.class.getResource("input-example12.txt"), 2L);

        List<PasswordChecker> validPasswords = data.getInput().asLines().stream()
            .map(PasswordChecker::new)
            .filter(PasswordChecker::isValidForPart1)
            .collect(Collectors.toList());
        data.setActualResult((long) validPasswords.size());
        data.setActualResultDetails(() ->
            validPasswords.stream()
                .map(pc -> String.format("%d-%d %s: %s", pc.lowerBound, pc.upperBound, pc.letter, pc.password))
                .collect(Collectors.joining(", "))
        );

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(Day02.class.getResource("input-exercise12.txt"), 454L);

        List<PasswordChecker> validPasswords = data.getInput().asLines().stream()
            .map(PasswordChecker::new)
            .filter(PasswordChecker::isValidForPart1)
            .collect(Collectors.toList());
        data.setActualResult((long) validPasswords.size());
//        data.setActualResultDetails(() ->
//            validPasswords.stream()
//                .map(pc -> String.format("%d-%d %s: %s", pc.lowerBound, pc.upperBound, pc.letter, pc.password))
//                .collect(Collectors.joining(", "))
//        );

        return data;
    }

    @Override
    protected SolutionData example2() {
        SolutionData data = SolutionData.from(Day02.class.getResource("input-example12.txt"), 1L);

        List<PasswordChecker> validPasswords = data.getInput().asLines().stream()
            .map(PasswordChecker::new)
            .filter(PasswordChecker::isValidForPart2)
            .collect(Collectors.toList());
        data.setActualResult((long) validPasswords.size());
        data.setActualResultDetails(() ->
            validPasswords.stream()
                .map(pc -> String.format("%d-%d %s: %s", pc.lowerBound, pc.upperBound, pc.letter, pc.password))
                .collect(Collectors.joining(", "))
        );

        return data;
    }

    @Override
    protected SolutionData exercise2() {
        SolutionData data = SolutionData.from(Day02.class.getResource("input-exercise12.txt"), 1L);

        List<PasswordChecker> validPasswords = data.getInput().asLines().stream()
            .map(PasswordChecker::new)
            .filter(PasswordChecker::isValidForPart2)
            .collect(Collectors.toList());
        data.setActualResult((long) validPasswords.size());
//        data.setActualResultDetails(() ->
//            validPasswords.stream()
//                .map(pc -> String.format("%d-%d %s: %s", pc.lowerBound, pc.upperBound, pc.letter, pc.password))
//                .collect(Collectors.joining(", "))
//        );

        return data;
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

    public static void main(String[] args) {
        new Day02().execute();
    }

}
