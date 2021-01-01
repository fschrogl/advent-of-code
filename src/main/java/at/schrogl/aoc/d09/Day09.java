package at.schrogl.aoc.d09;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day09 extends AbstractSolution {

    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(9, "Encoding Error");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(getClass().getResource("input-example12.txt"), 127L);

        List<Long> input = data.getInput().asLines().stream()
            .mapToLong(Long::parseLong)
            .boxed()
            .collect(Collectors.toList());
        int preambleLength = 5;
        for (int i = preambleLength; i < input.size(); i++) {
            List<Long> preamble = input.subList(i - preambleLength, i + preambleLength);
            if (isValidXmasNumber(input.get(i), preamble)) {
                data.setActualResult(input.get(i));
                break;
            }
        }

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(getClass().getResource("input-exercise12.txt"), 3199139634L);

        List<Long> input = data.getInput().asLines().stream()
            .mapToLong(Long::parseLong)
            .boxed()
            .collect(Collectors.toList());
        int preambleLength = 25;
        for (int i = preambleLength; i < input.size(); i++) {
            List<Long> preamble = input.subList(i - preambleLength, i + preambleLength);
            if (isValidXmasNumber(input.get(i), preamble)) {
                data.setActualResult(input.get(i));
                break;
            }
        }

        return data;
    }

    private boolean isValidXmasNumber(Long numberToProbe, List<Long> preamble) {
        Set<Long> sums = new HashSet<>();
        for (int i = 1; i < preamble.size(); i++) {
            Long previousNumber = preamble.get(i - 1);
            preamble.subList(i, preamble.size()).forEach(l -> sums.add(previousNumber + l));
        }
        return !sums.contains(numberToProbe);
    }

    @Override
    protected SolutionData example2() {
        SolutionData data = SolutionData.from(getClass().getResource("input-example12.txt"), 62L);

        long searchedNumber = 127L;
        long[] numbers = data.getInput().asLines().stream()
            .mapToLong(Long::parseLong)
            .filter(l -> l < searchedNumber)
            .toArray();

        List<Long> resultList = findResultList(searchedNumber, numbers);

        Collections.sort(resultList);
        data.setActualResult(resultList.get(0) + resultList.get(resultList.size() - 1));
        data.setActualResultDetails(() ->
            String.format("%s :: %d + %d = %d",
                resultList,
                resultList.get(0),
                resultList.get(resultList.size() - 1),
                data.getActualResult())
        );

        return data;
    }

    @Override
    protected SolutionData exercise2() {
        SolutionData data = SolutionData.from(getClass().getResource("input-exercise12.txt"), 438559930L);

        long searchedNumber = 3199139634L;
        long[] numbers = data.getInput().asLines().stream()
            .mapToLong(Long::parseLong)
            .filter(l -> l < searchedNumber)
            .toArray();

        List<Long> resultList = findResultList(searchedNumber, numbers);

        Collections.sort(resultList);
        data.setActualResult(resultList.get(0) + resultList.get(resultList.size() - 1));
        data.setActualResultDetails(() ->
            String.format("%s :: %d + %d = %d",
                resultList,
                resultList.get(0),
                resultList.get(resultList.size() - 1),
                data.getActualResult())
        );

        return data;
    }

    private List<Long> findResultList(long searchedNumber, long[] numbers) {
        List<Long> resultList = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            int j = i;
            long currentSum = 0L;
            while (currentSum < searchedNumber && j < numbers.length) {
                currentSum += numbers[j];
                resultList.add(numbers[j]);
                j++;
            }
            if (currentSum == searchedNumber) {
                break;
            } else {
                resultList.clear();
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        new Day09().execute();
    }
}
