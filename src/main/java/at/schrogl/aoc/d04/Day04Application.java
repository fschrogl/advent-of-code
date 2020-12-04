package at.schrogl.aoc.d04;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day04Application {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Set<Passport> passports = new HashSet<>();

        StringBuilder batchDataBuilder = new StringBuilder();
        for (String inputLine : getInputLines()) {
            if (!inputLine.isBlank()) {
                batchDataBuilder.append(inputLine.trim()).append(" ");
            } else {
                passports.add(Passport.newInstance(batchDataBuilder.toString()));
                batchDataBuilder = new StringBuilder();
            }
        }
        if (batchDataBuilder.length() > 0) {
            passports.add(Passport.newInstance(batchDataBuilder.toString()));
        }

        long validPassports = passports.stream()
            .filter(Passport::isValid)
            .count();

        System.out.println("## DAY 04: Part I");
        System.out.printf("Total passports: %d\n", passports.size());
        System.out.printf("Valid passports: %d\n", validPassports);
    }

    private static List<String> getInputLines() throws IOException, URISyntaxException {
        try (Stream<String> inputLines = Files.lines(Paths.get(Day04Application.class.getResource("input.txt").toURI()))) {
            return inputLines.collect(Collectors.toList());
        }
    }

    private static class Passport {

        private final Map<PassportField, String> dataFields = new HashMap<>();

        private Passport() {
            // intentionally private
        }

        private static Passport newInstance(String batchData) {
            Passport passport = new Passport();

            for (String dataChunk : batchData.trim().split(" ")) {
                String[] dataSplit = dataChunk.trim().split(":");
                PassportField passportField = PassportField.from(dataSplit[0].trim().toLowerCase());
                String value = dataSplit[1].trim();
                if (passportField != null) {
                    passport.dataFields.put(passportField, value);
                } else {
                    System.out.println("WARN: Passport-Key '" + dataSplit[0].trim().toLowerCase() + "' unknown!");
                }
            }

            return passport;
        }

        private boolean isValid() {
            EnumSet<PassportField> requiredFields = EnumSet.of(PassportField.BIRTH_YEAR, PassportField.ISSUE_YEAR,
                PassportField.EXPIRATION_YEAR, PassportField.HEIGHT, PassportField.HAIR_COLOR, PassportField.EYE_COLOR,
                PassportField.PASSPORT_ID);
            Set<PassportField> passportFields = dataFields.keySet();
            return passportFields.containsAll(requiredFields);
        }

    }

    private enum PassportField {
        BIRTH_YEAR("byr"),
        ISSUE_YEAR("iyr"),
        EXPIRATION_YEAR("eyr"),
        HEIGHT("hgt"),
        HAIR_COLOR("hcl"),
        EYE_COLOR("ecl"),
        PASSPORT_ID("pid"),
        COUNTRY_ID("cid");

        private final String key;

        PassportField(String key) {
            this.key = key;
        }

        private static PassportField from(String key) {
            return Arrays.stream(values())
                .filter(pf -> pf.key.equalsIgnoreCase(key))
                .findFirst()
                .orElse(null);
        }
    }

}
