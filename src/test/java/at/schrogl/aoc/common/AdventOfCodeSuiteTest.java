package at.schrogl.aoc.common;

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

import at.schrogl.aoc.AdventOfCodeApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Advent of Code 2020")
public class AdventOfCodeSuiteTest {

    static class ExerciseArgumentProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Arrays.stream(AdventOfCodeApplication.solutionClasses)
                .map(this::mapToArgument);
        }

        Arguments mapToArgument(Class<?> clazz) {
            try {
                return Arguments.of(clazz.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @DisplayName("Part 1")
    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(ExerciseArgumentProvider.class)
    void executePart1ForDay(AbstractSolution dayTask) {
        SolutionData solution1 = dayTask.exercise1();
        assertEquals(solution1.getExpectedResult(), solution1.getActualResult());
    }

    @DisplayName("Part 2")
    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(ExerciseArgumentProvider.class)
    void executePart2ForDay(AbstractSolution dayTask) {
        SolutionData solution2 = dayTask.exercise2();
        assertEquals(solution2.getExpectedResult(), solution2.getActualResult());
    }

}
