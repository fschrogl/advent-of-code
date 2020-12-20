package at.schrogl.aoc.d10;

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.common.SolutionData;

import java.util.Arrays;

public class Day10 extends AbstractSolution {
    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(10, "Adapter Array");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(getClass().getResource("input-example12.txt"), 220L);

        long[] adapterJolts = data.getInput().asLines().stream()
            .mapToLong(Long::parseLong)
            .sorted()
            .toArray();
        long[] joltDifferences = new long[]{0L, 0L, 0L};
        long currentJolt = 0L;
        for (int i = 0; i < adapterJolts.length; i++) {
            long adapterJolt = adapterJolts[i];
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
        SolutionData data = SolutionData.from(getClass().getResource("input-exercise12.txt"), 1914L);

        long[] adapterJolts = data.getInput().asLines().stream()
            .mapToLong(Long::parseLong)
            .sorted()
            .toArray();
        long[] joltDifferences = new long[]{0L, 0L, 0L};
        long currentJolt = 0L;
        for (int i = 0; i < adapterJolts.length; i++) {
            long adapterJolt = adapterJolts[i];
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
        SolutionData data = SolutionData.from(getClass().getResource("input-example12.txt"), 19208L);

        // TODO

        return null;
    }

    @Override
    protected SolutionData exercise2() {
        return null;
    }

    public static void main(String[] args) {
        new Day10().execute();
    }
}
