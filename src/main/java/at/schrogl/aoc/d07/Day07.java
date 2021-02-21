package at.schrogl.aoc.d07;

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

import java.util.Map;

public class Day07 extends AbstractSolution {

    @Override
    public DayInfo getDayInfo() {
        return new DayInfo(7, "Handy Haversacks");
    }

    @Override
    protected SolutionData example1() {
        SolutionData data = SolutionData.from(Day07.class.getResource("input-example1.txt"), 4L);

        Map<String, Day07Solver.Bag> parseBags = new Day07Solver().parseBags(data.getInput().asLines());
        final Day07Solver.Bag shiny_gold_bag = parseBags.get("shiny gold");
        final long bagCount = parseBags.values().stream()
            .filter(b -> b.containsRecursive(shiny_gold_bag))
            .count();
        data.setActualResult(bagCount);

        return data;
    }

    @Override
    protected SolutionData exercise1() {
        SolutionData data = SolutionData.from(Day07.class.getResource("input-exercise12.txt"), 169L);

        Map<String, Day07Solver.Bag> parseBags = new Day07Solver().parseBags(data.getInput().asLines());
        final Day07Solver.Bag shiny_gold_bag = parseBags.get("shiny gold");
        final long bagCount = parseBags.values().stream()
            .filter(b -> b.containsRecursive(shiny_gold_bag))
            .count();
        data.setActualResult(bagCount);

        return data;
    }

    @Override
    protected SolutionData example2() {
        SolutionData example2a = example2a();
        SolutionData example2b = example2b();

        long expectedResultSum = Long.sum(example2a.getExpectedResult(), example2b.getExpectedResult());
        SolutionData data = SolutionData.from(Day07.class.getResource("input-example2.txt"), expectedResultSum);
        data.setActualResult(Long.sum(example2a.getActualResult(), example2b.getActualResult()));
        data.setActualResultDetails(() -> String.format(
            "e2a: %d/%d, e2b: %d/%d",
            example2a.getExpectedResult(), example2a.getActualResult(),
            example2b.getExpectedResult(), example2b.getActualResult()
        ));
        return data;
    }

    private SolutionData example2a() {
        SolutionData data2a = SolutionData.from(Day07.class.getResource("input-example1.txt"), 32L);
        Map<String, Day07Solver.Bag> parseBags = new Day07Solver().parseBags(data2a.getInput().asLines());
        long containedBagsCount = parseBags.get("shiny gold").countBagsWithin();
        data2a.setActualResult(containedBagsCount);
        return data2a;
    }

    private SolutionData example2b() {
        SolutionData data2b = SolutionData.from(Day07.class.getResource("input-example2.txt"), 126L);
        Map<String, Day07Solver.Bag> parseBags = new Day07Solver().parseBags(data2b.getInput().asLines());
        long containedBagsCount = parseBags.get("shiny gold").countBagsWithin();
        data2b.setActualResult(containedBagsCount);
        return data2b;
    }

    @Override
    protected SolutionData exercise2() {
        SolutionData data = SolutionData.from(Day07.class.getResource("input-exercise12.txt"), 82372L);
        Map<String, Day07Solver.Bag> parseBags = new Day07Solver().parseBags(data.getInput().asLines());
        long containedBagsCount = parseBags.get("shiny gold").countBagsWithin();
        data.setActualResult(containedBagsCount);
        return data;
    }

    public static void main(String[] args) {
        new Day07().execute();
    }
}
