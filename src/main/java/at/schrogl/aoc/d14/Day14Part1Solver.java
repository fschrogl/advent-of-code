package at.schrogl.aoc.d14;

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
