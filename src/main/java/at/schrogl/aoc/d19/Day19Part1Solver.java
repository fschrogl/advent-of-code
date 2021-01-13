package at.schrogl.aoc.d19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day19Part1Solver {

    private List<Integer> ruleZeroMatchingLineNumbers = Collections.emptyList();

    public long countDataLinesMatchingRuleZero() {
        return ruleZeroMatchingLineNumbers.size();
    }

    public List<Integer> getLineNumbersMatchingRuleZero() {
        return ruleZeroMatchingLineNumbers;
    }

    public void parse(List<String> lines) {
        List<Rule> rules = new ArrayList<>();
        Map<Integer, String> signalData = new HashMap<>();
        boolean hasReachedSignalData = false;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isBlank()) {
                hasReachedSignalData = true;
                continue;
            }

            if (!hasReachedSignalData) {
                int ruleNumber = Integer.parseInt(line.substring(0, line.indexOf(":")));
                String rawPattern = line.substring(line.indexOf(":") + 1).trim();
                rules.add(new Rule(ruleNumber, rawPattern));
            } else {
                signalData.put(i, line.trim());
            }
        }

        Rule ruleZero = resolveRuleZero(rules);

        ruleZeroMatchingLineNumbers = signalData.entrySet().stream()
            .filter(e -> ruleZero.matches(e.getValue()))
            .map(l -> l.getKey() + 1)
            .collect(Collectors.toList());
    }

    private Rule resolveRuleZero(List<Rule> unresolvedRules) {
        Map<Integer, Rule> resolvedRulesMap = unresolvedRules.stream()
            .filter(Rule::isResolved)
            .collect(Collectors.toMap(Rule::getNumber, Function.identity()));
        while (!unresolvedRules.isEmpty()) {
            for (Rule unresolvedRule : unresolvedRules) {
                unresolvedRule.resolveRules(resolvedRulesMap);
                if (unresolvedRule.isResolved()) {
                    resolvedRulesMap.put(unresolvedRule.getNumber(), unresolvedRule);
                }
            }
            unresolvedRules.removeAll(resolvedRulesMap.values());
        }
        return resolvedRulesMap.get(0);
    }

    private static class Rule {
        public int number;
        List<List<Integer>> unresolvedRuleNumbers = new ArrayList<>();
        List<String> resolvedPatterns = new ArrayList<>();

        public Rule(int number, String rawPattern) {
            this.number = number;
            if (rawPattern.contains("\"")) {
                resolvedPatterns.add(rawPattern.replaceAll("\"", "").trim());
            } else {
                String[] unresolvedRuleBatches = rawPattern.split("\\|");
                for (String unresolvedRuleBatch : unresolvedRuleBatches) {
                    unresolvedRuleNumbers.add(Arrays.stream(unresolvedRuleBatch.trim().split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
                    );
                }
            }
        }

        public int getNumber() {
            return number;
        }

        void resolveRules(Map<Integer, Rule> resolvedRules) {
            List<List<Integer>> newlyResolvedRules = new ArrayList<>();
            for (List<Integer> unresolvedRuleNumber : unresolvedRuleNumbers) {
                if (resolvedRules.keySet().containsAll(unresolvedRuleNumber)) {
                    List<String> newPatterns = new ArrayList<>();
                    for (Integer ruleNumber : unresolvedRuleNumber) {
                        Rule rule = resolvedRules.get(ruleNumber);
                        newPatterns = rule.prependPatterns(newPatterns);
                    }
                    resolvedPatterns.addAll(newPatterns);
                    newlyResolvedRules.addAll(unresolvedRuleNumbers);
                }
            }
            unresolvedRuleNumbers.removeAll(newlyResolvedRules);
        }

        private List<String> prependPatterns(List<String> newPatterns) {
            List<String> computedPatterns = new ArrayList<>();
            if (newPatterns.isEmpty()) {
                computedPatterns = new ArrayList<>(resolvedPatterns);
            } else {
                for (String newPattern : newPatterns) {
                    for (String resolvedPattern : resolvedPatterns) {
                        computedPatterns.add(newPattern + resolvedPattern);
                    }
                }
            }
            return computedPatterns;
        }

        boolean isResolved() {
            return unresolvedRuleNumbers.isEmpty();
        }

        boolean matches(String signal) {
            return isResolved() && resolvedPatterns.stream().anyMatch(signal::equals);
        }
    }

}
