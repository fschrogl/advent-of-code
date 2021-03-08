package at.schrogl.aoc.d14;

/*-
 * #%L
 * Advent-Of-Code
 * %%
 * Copyright (C) 2020 - 2021 Fritz Schrogl
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day14Part1Solver {

    private Map<Long, Long> memory = new HashMap<>();
    private char[] mask;

    public void computeInput(List<String> lines) {
        for (String line : lines) {
            if (line.startsWith("mask"))
                mask = line.split("=")[1].trim().toCharArray();
            else
                computeMemory(line);
        }
    }

    private void computeMemory(String line) {
        String[] valueSplit = line.split("=");
        Long address = Long.valueOf(valueSplit[0].trim().substring(4, valueSplit[0].trim().length() - 1));
        String binaryValue = toBinary(valueSplit[1]);
        String binaryResult = applyBitmask(binaryValue);
        Long result = fromBinary(binaryResult);
        memory.put(address, result);
    }

    private String applyBitmask(String binaryValue) {
        char[] input = binaryValue.toCharArray();
        char[] result = new char[36];

        for (int i = 0, j = (result.length - input.length); i < result.length; i++) {
            result[i] = (i < j) ? '0' : input[i - j];
        }
        for (int i = 0; i < mask.length; i++) {
            if (mask[i] != 'X') {
                result[i] = mask[i];
            }
        }
        return String.valueOf(result);
    }

    public long getResultSum() {
        return memory.values().stream().mapToLong(Long::valueOf).sum();
    }

    public String getMemoryContents() {
        return memory.entrySet().stream()
            .map(e -> String.format("%d=%d", e.getKey(), e.getValue()))
            .collect(Collectors.joining(", "));
    }

    private String toBinary(String value) {
        return Long.toBinaryString(Long.parseLong(value.trim()));
    }

    private Long fromBinary(String binaryString) {
        return Long.parseLong(binaryString, 2);
    }
}
