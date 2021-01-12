package at.schrogl.aoc.d19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day19Part1Solver {

    private List<Integer> ruleZeroMatchingLineNumbers;

    public void parse(List<String> lines) {
        Map<Integer, String> ruleStringMap = new HashMap<>();
        Map<Integer, String> signalData = new HashMap<>();
        boolean hasReachedSignalData = false;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isBlank()) {
                hasReachedSignalData = true;
                continue;
            }

            if (!hasReachedSignalData) {
                Integer ruleNumber = Integer.valueOf(line.substring(0, line.indexOf(":")));
                String rawRule = line.substring(line.indexOf(":")).trim();
                ruleStringMap.put(ruleNumber, rawRule);
            } else {
                signalData.put(i, line.trim());
            }
        }

        Rule ruleZero = resolveRules(ruleStringMap);

        ruleZeroMatchingLineNumbers = signalData.entrySet().stream()
            .filter(e -> ruleZero.validateSignal(e.getValue()))
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
    }

    private Rule resolveRules(Map<Integer, String> ruleStringMap) {

        return null;
    }

    public long countDataLinesMatchingRuleZero() {
        return ruleZeroMatchingLineNumbers.size();
    }

    public List<Integer> getLineNumbersMatchingRuleZero() {
        return ruleZeroMatchingLineNumbers;
    }

    private class Rule {
        List<String> orStrings = new ArrayList<>();

        public boolean validateSignal(String signalString) {
            return orStrings.stream().anyMatch(signalString::equals);
        }
    }
}
