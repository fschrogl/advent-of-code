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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Day07Solver {

    public Map<String, Bag> parseBags(List<String> lines) {
        final Map<String, Day07Solver.Bag> colorBagMap = new HashMap<>();

        for (String line : lines) {
            if (!line.contains("no other bags")) {
                final String[] split = line.split(" ");
                Day07Solver.Bag parentBag = null;
                for (int i = 0; i < split.length; i++) {
                    if (split[i].contains("bag")) {
                        String color = String.join(" ", split[i - 2].trim(), split[i - 1].trim());
                        if (parentBag == null) {
                            parentBag = colorBagMap.getOrDefault(color, new Day07Solver.Bag(color));
                            colorBagMap.put(color, parentBag);
                        } else {
                            Day07Solver.Bag childBag = colorBagMap.getOrDefault(color, new Day07Solver.Bag(color));
                            parentBag.add(childBag, Integer.parseInt(split[i - 3]));
                            colorBagMap.put(color, childBag);
                        }
                    }
                }
            }
        }

        return colorBagMap;
    }

    static class Bag {
        private final String color;
        private final Map<Bag, Integer> containedBags = new HashMap<>();

        public Bag(String color) {
            this.color = color;
        }

        public void add(Bag bag, int amount) {
            containedBags.merge(bag, amount, Integer::sum);
        }

        public boolean containsRecursive(Bag searchBag) {
            for (Bag containedBag : containedBags.keySet()) {
                if (containedBag.equals(searchBag) || containedBag.containsRecursive(searchBag)) {
                    return true;
                }
            }
            return false;
        }

        public long countBagsWithin() {
            if (containedBags.isEmpty()) {
                return 0;
            }

            long containedBagsSum = 0;
            for (Map.Entry<Bag, Integer> containedBagTuple : containedBags.entrySet()) {
                long contained = containedBagTuple.getKey().countBagsWithin();
                containedBagsSum += Math.multiplyExact(contained, containedBagTuple.getValue());
            }
            containedBagsSum += containedBags.values().stream().reduce(0, Integer::sum);

            return containedBagsSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bag bag = (Bag) o;
            return color.equals(bag.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(color);
        }

        @Override
        public String toString() {
            return String.format("bag [%s]", color);
        }
    }
}
