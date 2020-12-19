package at.schrogl.aoc.d04;

/*-
 * #%L
 * Advent-Of-Code
 * %%
 * Copyright (C) 2020 Fritz Schrogl
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

import java.util.*;
import java.util.stream.Collectors;

public class Day04 extends AbstractSolution {

    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(4, "Passport Processing");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day04.class.getResource("input-example12.txt"), 2L);

        Map<Integer, String> passportLines = data.getInput().asJoinedGroupLines();
        List<Passport> validPassports = passportLines.entrySet().stream()
            .map(e -> new Passport(e.getKey(), e.getValue()))
            .filter(Passport::isValidPart1)
            .collect(Collectors.toList());
        data.setActualResult((long) validPassports.size());
        data.setActualResultDetails(() ->
            String.format("Valid passports starting at lines %s",
                validPassports.stream().map(Passport::getLineNumber).collect(Collectors.toList())
            )
        );

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(Day04.class.getResource("input-exercise12.txt"), 233L);

        Map<Integer, String> passportLines = data.getInput().asJoinedGroupLines();
        List<Passport> validPassports = passportLines.entrySet().parallelStream()
            .map(e -> new Passport(e.getKey(), e.getValue()))
            .filter(Passport::isValidPart1)
            .collect(Collectors.toList());
        data.setActualResult((long) validPassports.size());
        data.setActualResultDetails(() ->
            String.format("Valid passports starting at lines %s",
                validPassports.stream().map(Passport::getLineNumber).collect(Collectors.toList())
            )
        );

        return data;
    }

    @Override
    protected SolutionData example2() {
        SolutionData data = SolutionData.from(Day04.class.getResource("input-example12.txt"), 2L);

        Map<Integer, String> passportLines = data.getInput().asJoinedGroupLines();
        List<Passport> validPassports = passportLines.entrySet().stream()
            .map(e -> new Passport(e.getKey(), e.getValue()))
            .filter(Passport::isValidPart2)
            .collect(Collectors.toList());
        data.setActualResult((long) validPassports.size());
        data.setActualResultDetails(() ->
            String.format("Valid passports starting at lines %s",
                validPassports.stream().map(Passport::getLineNumber).collect(Collectors.toList())
            )
        );

        return data;
    }

    @Override
    protected SolutionData exercise2() {
        SolutionData data = SolutionData.from(Day04.class.getResource("input-exercise12.txt"), 111L);

        Map<Integer, String> passportLines = data.getInput().asJoinedGroupLines();
        List<Passport> validPassports = passportLines.entrySet().parallelStream()
            .map(e -> new Passport(e.getKey(), e.getValue()))
            .filter(Passport::isValidPart2)
            .collect(Collectors.toList());
        data.setActualResult((long) validPassports.size());
        data.setActualResultDetails(() ->
            String.format("Valid passports starting at lines %s",
                validPassports.stream().map(Passport::getLineNumber).collect(Collectors.toList())
            )
        );

        return data;
    }

    private static class Passport {

        private final int lineNumber;
        private final Map<PassportField, String> dataFields = new HashMap<>();
        private final EnumSet<PassportField> requiredFields = EnumSet.of(PassportField.BIRTH_YEAR, PassportField.ISSUE_YEAR,
            PassportField.EXPIRATION_YEAR, PassportField.HEIGHT, PassportField.HAIR_COLOR, PassportField.EYE_COLOR,
            PassportField.PASSPORT_ID);

        public Passport(int lineNumber, String data) {
            this.lineNumber = lineNumber;

            for (String dataChunk : data.split(" ")) {
                String[] dataSplit = dataChunk.trim().split(":");
                PassportField passportField = PassportField.from(dataSplit[0].trim().toLowerCase());
                String value = dataSplit[1].trim();
                if (passportField != null) {
                    dataFields.put(passportField, value);
                } else {
                    System.out.println("WARN: Passport-Key '" + dataSplit[0].trim().toLowerCase() + "' unknown!");
                }
            }
        }

        public int getLineNumber() {
            return lineNumber;
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

    public static void main(String[] args) {
        new Day04().execute();
    }

}
