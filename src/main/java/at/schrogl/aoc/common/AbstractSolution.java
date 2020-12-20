package at.schrogl.aoc.common;

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

import java.time.Duration;

public abstract class AbstractSolution {

    public abstract DayInfo getDayInfo();

    protected abstract SolutionData example1();

    protected abstract SolutionData exercise1();

    protected abstract SolutionData example2();

    protected abstract SolutionData exercise2();

    public void execute() {
        DayInfo dayInfo = getDayInfo();
        System.out.printf("# Day %2d: %s\n", dayInfo.getDayOfAdvent(), dayInfo.getTitle());
        printSolution("Example  1", example1());
        printSolution("Exercise 1", exercise1());
        printSolution("Example  2", example2());
        printSolution("Exercise 2", exercise2());
    }

    private void printSolution(String prefix, SolutionData solutionData) {
        if (solutionData == null) {
            System.out.printf("%s:  Not implemented!\n", prefix);
        } else {
            Duration computationDuration = solutionData.getComputationDuration();
            System.out.printf(
                "%s:  %s (%.2fms)\n", prefix, solutionData.getSolutionStatus(),
                (computationDuration == null) ? -1 : computationDuration.toNanos() / 1_000_000f
            );
            System.out.printf("\tinput:    %s\n", solutionData.getInput().getFilename());
            System.out.printf("\tactual:   %d\n", solutionData.getActualResult());
            System.out.printf("\texpected: %d\n", solutionData.getExpectedResult());
            System.out.printf("\tDetails:  %s\n", solutionData.getActualResultDetails());
        }
    }

}
