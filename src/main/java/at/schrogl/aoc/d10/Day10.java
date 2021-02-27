package at.schrogl.aoc.d10;

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

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.common.SolutionData;

import java.util.Arrays;
import java.util.List;

public class Day10 extends AbstractSolution {
    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(10, "Adapter Array");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day10.class.getResource("input-example1.txt"), 220L);

        long[] adapterJolts = data.getInput().asLines().stream()
            .mapToLong(Long::parseLong)
            .sorted()
            .toArray();
        long[] joltDifferences = new long[]{0L, 0L, 0L};
        long currentJolt = 0L;
        for (long adapterJolt : adapterJolts) {
            int differenceIndex = Math.toIntExact(adapterJolt - currentJolt) - 1;
            joltDifferences[differenceIndex]++;
            currentJolt = adapterJolt;
        }
        joltDifferences[2]++; // Laptop has always a difference of 3

        data.setActualResult(Math.multiplyExact(joltDifferences[0], joltDifferences[2]));
        data.setActualResultDetails(() ->
            String.format("%s :: %d * %d = %d", Arrays.toString(joltDifferences),
                joltDifferences[0],
                joltDifferences[2],
                data.getActualResult())
        );

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(Day10.class.getResource("input-exercise12.txt"), 1914L);

        long[] adapterJolts = data.getInput().asLines().stream()
            .mapToLong(Long::parseLong)
            .sorted()
            .toArray();
        long[] joltDifferences = new long[]{0L, 0L, 0L};
        long currentJolt = 0L;
        for (long adapterJolt : adapterJolts) {
            int differenceIndex = Math.toIntExact(adapterJolt - currentJolt) - 1;
            joltDifferences[differenceIndex]++;
            currentJolt = adapterJolt;
        }
        joltDifferences[2]++; // Laptop has always a difference of 3

        data.setActualResult(Math.multiplyExact(joltDifferences[0], joltDifferences[2]));
        data.setActualResultDetails(() ->
            String.format("%s :: %d * %d = %d", Arrays.toString(joltDifferences),
                joltDifferences[0],
                joltDifferences[2],
                data.getActualResult())
        );

        return data;
    }

    @Override
    protected SolutionData example2() {
        SolutionData solutionData2a = example2a();
        SolutionData solutionData2b = example2b();

        long expectedSolutionSum = Long.sum(solutionData2a.getExpectedResult(), solutionData2b.getExpectedResult());
        SolutionData data = SolutionData.from(Day10.class.getResource("input-example2a.txt"), expectedSolutionSum);
        data.setActualResult(Long.sum(solutionData2a.getActualResult(), solutionData2b.getActualResult()));
        data.setActualResultDetails(() ->
            String.format("e2a: %d/%d, e2b: %d/%d",
                solutionData2a.getActualResult(), solutionData2a.getExpectedResult(),
                solutionData2b.getActualResult(), solutionData2b.getExpectedResult()
            )
        );

        return data;
    }

    private SolutionData example2a() {
        SolutionData data = SolutionData.from(Day10.class.getResource("input-example2a.txt"), 8L);

        long[] sortedJolts = data.getInput().asLines().stream()
            .mapToLong(Long::parseLong)
            .sorted()
            .toArray();

        long permutations = 1;
        for (int i = 0, j = 0; j < sortedJolts.length; j++) {
            long joltDifference = sortedJolts[j] - sortedJolts[i];
            if (joltDifference > 3) {
                int joltsInBetween = (j - i);
                if (joltsInBetween == 3) {
                    permutations *= 2;
                } else if (joltsInBetween == 4) {
                    permutations *= 4;
                }
                j--;
                i = j;
            }
        }
        data.setActualResult(permutations);

        return data;
    }

    private long factorial(long n) {
        // Optimization. Input is valid and we wont get any n > 2
        return (n < 2) ? 1 : 2;
    }

    private SolutionData example2b() {
        SolutionData data = SolutionData.from(Day10.class.getResource("input-example2b.txt"), 19208L);

        List<String> lines = data.getInput().asLines();
        lines.add("0");
        lines.add(String.valueOf(Integer.MAX_VALUE));
        long[] sortedJolts = lines.stream()
            .mapToLong(Long::parseLong)
            .sorted()
            .toArray();
        sortedJolts[sortedJolts.length - 1] = sortedJolts[sortedJolts.length - 2] + 3;

        long permutations = 1;
        for (int i = 0, j = 0; j < sortedJolts.length; j++) {
            long joltDifference = sortedJolts[j] - sortedJolts[i];
            if (joltDifference > 3) {
                int joltsInBetween = (j - i);
                if (joltsInBetween == 3) {
                    permutations *= 2;
                } else if (joltsInBetween == 4) {
                    permutations *= 4;
                }
                j--;
                i = j;
            }
        }
        data.setActualResult(permutations);

        return data;
    }

    @Override
    protected SolutionData exercise2() {
        return null;
    }

    public static void main(String[] args) {
        new Day10().execute();
    }
}
