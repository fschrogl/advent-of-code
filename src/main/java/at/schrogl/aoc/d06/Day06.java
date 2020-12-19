package at.schrogl.aoc.d06;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day06 extends AbstractSolution {

    private static Long countYesesPerGroup(String s) {
        long groupSize = 1;
        Map<Character, Long> yesCountsPerAnswer = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (c == ' ') groupSize++;
            else yesCountsPerAnswer.merge(c, 1L, Long::sum);
        }
        final Long finalGroupSize = groupSize;
        return yesCountsPerAnswer.values().stream()
            .filter(v -> v.equals(finalGroupSize))
            .count();
    }

    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(6, "Custom Customs");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day06.class.getResource("input-example12.txt"), 11L);

        List<Long> yesAnswersPerGroup = data.getInput().asJoinedGroupLines().values().stream()
            .map(s -> s.replaceAll(" ", "").chars().distinct().count())
            .collect(Collectors.toList());
        data.setActualResult(yesAnswersPerGroup.stream().reduce(0L, Long::sum));
        data.setActualResultDetails(() ->
            String.format("%s = %d",
                yesAnswersPerGroup.stream().map(String::valueOf).collect(Collectors.joining(" + ")),
                data.getActualResult())
        );

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(Day06.class.getResource("input-exercise12.txt"), 6748L);

        List<Long> yesAnswersPerGroup = data.getInput().asJoinedGroupLines().values().parallelStream()
            .map(s -> s.replaceAll(" ", "").chars().distinct().count())
            .collect(Collectors.toList());
        data.setActualResult(yesAnswersPerGroup.stream().reduce(0L, Long::sum));
//        data.setActualResultDetails(() ->
//            String.format("%s = %d",
//                yesAnswersPerGroup.stream().map(String::valueOf).collect(Collectors.joining(" + ")),
//                data.getActualResult())
//        );

        return data;
    }

    @Override
    protected SolutionData example2() {
        SolutionData data = SolutionData.from(Day06.class.getResource("input-example12.txt"), 6L);

        List<Long> yesCounts = data.getInput().asJoinedGroupLines().values().stream()
            .map(Day06::countYesesPerGroup)
            .collect(Collectors.toList());
        data.setActualResult(yesCounts.stream().reduce(0L, Long::sum));
        data.setActualResultDetails(() ->
            String.format("%s = %d",
                yesCounts.stream().map(String::valueOf).collect(Collectors.joining(" + ")),
                data.getActualResult())
        );

        return data;
    }

    @Override
    protected SolutionData exercise2() {
        SolutionData data = SolutionData.from(Day06.class.getResource("input-exercise12.txt"), 3445L);

        List<Long> yesCounts = data.getInput().asJoinedGroupLines().values().stream()
            .map(Day06::countYesesPerGroup)
            .collect(Collectors.toList());
        data.setActualResult(yesCounts.stream().reduce(0L, Long::sum));
//        data.setActualResultDetails(() ->
//            String.format("%s = %d",
//                yesCounts.stream().map(String::valueOf).collect(Collectors.joining(" + ")),
//                data.getActualResult())
//        );

        return data;
    }

    public static void main(String[] args) {
        new Day06().execute();
    }
}
