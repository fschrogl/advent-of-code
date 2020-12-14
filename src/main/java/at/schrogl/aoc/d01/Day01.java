package at.schrogl.aoc.d01;

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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day01 extends AbstractSolution {

    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(1, "Report Repair");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day01.class.getResource("input-example12.txt"), 514579L);

        List<String> input = data.getInput().asLines();
        List<Long> numbers = get2NumberResult(input);
        data.setActualResult(numbers.stream().reduce(Math::multiplyExact).orElse(null));
        data.setActualResultDetails(() -> String.format("%d * %d = %d", numbers.get(0), numbers.get(1), data.getActualResult()));

        return data;
    }

    @Override
    protected SolutionData example2() {
        SolutionData data = SolutionData.from(Day01.class.getResource("input-example12.txt"), 241861950L);

        List<String> input = data.getInput().asLines();
        List<Long> numbers = get3NumberResult(input);
        data.setActualResult(numbers.stream().reduce(Math::multiplyExact).orElse(null));
        data.setActualResultDetails(() -> String.format("%d * %d * %d = %d", numbers.get(0), numbers.get(1), numbers.get(2), data.getActualResult()));

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(Day01.class.getResource("input-exercise12.txt"), 436404L);

        List<String> input = data.getInput().asLines();
        List<Long> numbers = get2NumberResult(input);
        data.setActualResult(numbers.stream().reduce(Math::multiplyExact).orElse(null));
        data.setActualResultDetails(() -> String.format("%d * %d = %d", numbers.get(0), numbers.get(1), data.getActualResult()));

        return data;
    }

    @Override
    protected SolutionData exercise2() {
        SolutionData data = SolutionData.from(Day01.class.getResource("input-exercise12.txt"), 274879808L);

        List<String> input = data.getInput().asLines();
        List<Long> numbers = get3NumberResult(input);
        data.setActualResult(numbers.stream().reduce(Math::multiplyExact).orElse(null));
        data.setActualResultDetails(() -> String.format("%d * %d * %d = %d", numbers.get(0), numbers.get(1), numbers.get(2), data.getActualResult()));

        return data;
    }

    private List<Long> get2NumberResult(List<String> input) {
        for (int i = 0; i < input.size() - 1; i++) {
            long base = Long.parseLong(input.get(i));
            List<String> subInput = input.subList(i + 1, input.size());
            for (String s : subInput) {
                long add = Long.parseLong(s);
                if (base + add == 2020) {
                    return Arrays.asList(base, add);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Long> get3NumberResult(List<String> input) {
        for (int i = 0; i < input.size() - 2; i++) {
            long base = Long.parseLong(input.get(i));
            List<String> subList1 = input.subList(i + 1, input.size() - 1);
            for (int i1 = 0; i1 < subList1.size(); i1++) {
                long base1 = Long.parseLong(subList1.get(i1));
                if (base + base1 < 2020) {
                    List<String> subList2 = subList1.subList(i1 + 1, subList1.size());
                    for (String s : subList2) {
                        long add = Long.parseLong(s);
                        if (base + base1 + add == 2020) {
                            return Arrays.asList(base, base1, add);
                        }
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        new Day01().execute();
    }
}
