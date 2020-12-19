package at.schrogl.aoc.d08;

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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day08 extends AbstractSolution {

    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(8, "Handheld Halting");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(getClass().getResource("input-example12.txt"), 5L);

        List<String> source = data.getInput().asLines();
        long[] executionResult = getAccumulatorValueBeforeRepeat(source);
        data.setActualResult(executionResult[0]);

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(getClass().getResource("input-exercise12.txt"), 1859L);

        List<String> source = data.getInput().asLines();
        long accumulator = getAccumulatorValueBeforeRepeat(source)[0];
        data.setActualResult(accumulator);

        return data;
    }

    private long[] getAccumulatorValueBeforeRepeat(List<String> source) {
        long[] result = new long[]{0L, 0L};
        Set<Integer> alreadyExecuted = new HashSet<>(source.size());
        for (int i = 0; i < source.size(); ) {
            if (alreadyExecuted.contains(i)) {
                result[1] = 1L;
                break;
            } else {
                alreadyExecuted.add(i);
                String[] cmdLine = source.get(i).split(" ");
                switch (cmdLine[0]) {
                    case "acc":
                        result[0] += Long.parseLong(cmdLine[1]);
                        break;
                    case "jmp":
                        i += Integer.parseInt(cmdLine[1]);
                        continue;
                    default:
                }
                i++;
            }
        }
        return result;
    }

    @Override
    protected SolutionData example2() {
        SolutionData data = SolutionData.from(getClass().getResource("input-example12.txt"), 8L);

        searchCmdToReplace(data, data.getInput().asLines());

        return data;
    }

    @Override
    protected SolutionData exercise2() {
        SolutionData data = SolutionData.from(getClass().getResource("input-exercise12.txt"), 1235L);

        searchCmdToReplace(data, data.getInput().asLines());

        return data;
    }

    private void searchCmdToReplace(SolutionData data, List<String> source) {
        for (int i = 0; i < source.size(); i++) {
            final int lineNumber = i;
            String cmdLine = source.get(lineNumber);
            if (cmdLine.startsWith("nop")) {
                source.set(lineNumber, cmdLine.replaceFirst("nop", "jmp"));
            } else if (cmdLine.startsWith("jmp")) {
                source.set(lineNumber, cmdLine.replaceFirst("jmp", "nop"));
            }

            long[] executionResult = getAccumulatorValueBeforeRepeat(source);
            if (executionResult[1] == 0) {
                data.setActualResult(executionResult[0]);
                data.setActualResultDetails(() -> String.format("Replaced nop -> jmp on line %d", lineNumber));
                break;
            }

            source.set(lineNumber, cmdLine);
        }
    }

    public static void main(String[] args) {
        new Day08().execute();
    }
}
