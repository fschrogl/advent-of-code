package at.schrogl.aoc.d18;

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

public class Day18Part1Solver {

    public static long solve(String formula) {
        int lastIndexOfOpening = formula.lastIndexOf("(");
        if (lastIndexOfOpening > -1) {
            int lastIndexOfClosing = formula.indexOf(")", lastIndexOfOpening);
            String subformula = formula.substring(lastIndexOfOpening + 1, lastIndexOfClosing);
            long intermediateResult = solve(subformula);
            String updatedFormula =
                String.format("%s%d%s",
                    formula.substring(0, lastIndexOfOpening), intermediateResult, formula.substring(lastIndexOfClosing + 1)
                );
            return solve(updatedFormula);
        } else {
            String[] formulaSplit = formula.split(" ");
            int i = 2;
            long result = Long.parseLong(formulaSplit[0].trim());
            do {
                result = compute(result, formulaSplit[i - 1], formulaSplit[i]);
                i += 2;
            } while (i < formulaSplit.length);
            return result;
        }
    }

    private static long compute(long left, String operation, String rightString) {
        long right = Long.parseLong(rightString.trim());

        switch (operation.trim()) {
            case "+":
                return Math.addExact(left, right);
            case "*":
                return Math.multiplyExact(left, right);
            default:
                throw new RuntimeException("Unknown operation " + operation);
        }
    }
}
