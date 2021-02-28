package at.schrogl.aoc;

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
import at.schrogl.aoc.d01.Day01;
import at.schrogl.aoc.d02.Day02;
import at.schrogl.aoc.d03.Day03;
import at.schrogl.aoc.d04.Day04;
import at.schrogl.aoc.d05.Day05;
import at.schrogl.aoc.d06.Day06;
import at.schrogl.aoc.d08.Day08;
import at.schrogl.aoc.d09.Day09;
import at.schrogl.aoc.d11.Day11;
import at.schrogl.aoc.d12.Day12;
import at.schrogl.aoc.d14.Day14;
import at.schrogl.aoc.d15.Day15;
import at.schrogl.aoc.d18.Day18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class AdventOfCodeApplication {

    public static final Class<?>[] solutionClasses = new Class[]{
        Day01.class, Day02.class, Day03.class, Day04.class, Day05.class, Day06.class, Day08.class, Day09.class,
        Day11.class, Day12.class, Day14.class, Day15.class, Day18.class
    };
    private final Map<Integer, AbstractSolution> solutions = new HashMap<>(solutionClasses.length);

    public static void main(String[] args) throws Exception {
        AdventOfCodeApplication adventOfCodeApplication = new AdventOfCodeApplication();

        System.out.println("ADVENT OF CODE 2020\n");
        adventOfCodeApplication.initAndPrintToc();

        int solutionToExecute;
        if (args.length == 0) {
            System.out.print("Select day to execute: ");
            String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
            solutionToExecute = Integer.parseInt(input);
        } else {
            solutionToExecute = Integer.parseInt(args[0]);
        }
        System.out.println();
        adventOfCodeApplication.execute(solutionToExecute);
    }

    private void initAndPrintToc() throws Exception {
        for (Class<?> solutionClass : solutionClasses) {
            final AbstractSolution solution = (AbstractSolution) solutionClass.getDeclaredConstructor().newInstance();
            final DayInfo dayInfo = solution.getDayInfo();
            solutions.put(dayInfo.getDayOfAdvent(), solution);
            System.out.printf("Day %2d: %s\n", dayInfo.getDayOfAdvent(), dayInfo.getTitle());
        }
        System.out.println();
    }

    private void execute(int dayToExecute) {
        final AbstractSolution solution = solutions.get(dayToExecute);
        if (solution == null) {
            System.err.println("Solution for day " + dayToExecute + " doesn't exist!\n");
        } else {
            solution.execute();
        }
    }
}
