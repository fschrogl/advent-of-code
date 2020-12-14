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

        System.out.println("## DAY 04: Part I");
        System.out.printf("Total passports: %d\n", passports.size());
        System.out.printf("Valid passports: %d\n", passports.stream().filter(Passport::isValidPart1).count());

        System.out.println("\n## DAY 04: Part II");
        System.out.printf("Total passports: %d\n", passports.size());
        System.out.printf("Valid passports: %d\n", passports.stream().filter(Passport::isValidPart2).count());
    }

    private static List<String> getInputLines() throws IOException, URISyntaxException {
        try (Stream<String> inputLines = Files.lines(Paths.get(Day04Application.class.getResource("input-exercise12.txt").toURI()))) {
            return inputLines.collect(Collectors.toList());
        }
    }

    private static class Passport {

        private final Map<PassportField, String> dataFields = new HashMap<>();
        private final EnumSet<PassportField> requiredFields = EnumSet.of(PassportField.BIRTH_YEAR, PassportField.ISSUE_YEAR,
            PassportField.EXPIRATION_YEAR, PassportField.HEIGHT, PassportField.HAIR_COLOR, PassportField.EYE_COLOR,
            PassportField.PASSPORT_ID);

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

        private boolean isValidPart1() {
            return dataFields.keySet().containsAll(requiredFields);
        }

        private boolean isValidPart2() {
            return isValidPart1() &&
                dataFields.entrySet().stream()
                    .allMatch(e -> e.getKey().isValid(e.getValue()));
        }

    }

    private interface PassportFieldValidation {
        boolean isValid(String value);
    }

    private enum PassportField implements PassportFieldValidation {
        BIRTH_YEAR("byr") {
            public boolean isValid(String value) {
                int integer = Integer.parseInt(value.trim());
                return integer >= 1920 && integer <= 2002;
            }
        },
        ISSUE_YEAR("iyr") {
            public boolean isValid(String value) {
                int integer = Integer.parseInt(value.trim());
                return integer >= 2010 && integer <= 2020;
            }
        },
        EXPIRATION_YEAR("eyr") {
            public boolean isValid(String value) {
                int integer = Integer.parseInt(value.trim());
                return integer >= 2020 && integer <= 2030;
            }
        },
        HEIGHT("hgt") {
            public boolean isValid(String value) {
                String trimmedValue = value.trim();
                if (trimmedValue.endsWith("cm")) {
                    Integer integer = Integer.parseInt(trimmedValue.substring(0, trimmedValue.length() - 2));
                    return integer >= 150 && integer <= 193;
                } else if (trimmedValue.endsWith("in")) {
                    Integer integer = Integer.parseInt(trimmedValue.substring(0, trimmedValue.length() - 2));
                    return integer >= 59 && integer <= 76;
                } else
                    return false;
            }
        },
        HAIR_COLOR("hcl") {
            public boolean isValid(String value) {
                return value.trim().matches("#[a-f0-9]{6}");
            }
        },
        EYE_COLOR("ecl") {
            public boolean isValid(String value) {
                String[] validColors = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
                return Arrays.stream(validColors).anyMatch(ec -> ec.equalsIgnoreCase(value.trim()));
            }
        },
        PASSPORT_ID("pid") {
            public boolean isValid(String value) {
                return value.trim().matches("[0-9]{9}");
            }
        },
        COUNTRY_ID("cid") {
            public boolean isValid(String value) {
                return true;
            }
        };

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
