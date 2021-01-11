package at.schrogl.aoc.d21;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Day21Part1Solver {

    Map<String, Ingredient> allIngredients = new HashMap<>();
    Map<String, Allergen> allAllergenes = new HashMap<>();

    /*
        Ingredients contain 0 to 1 allergenes
        Allergenes are in exactly one ingredient
     */
    public void parseFoods(List<String> lines) {

        for (String line : lines) {
            int idxAllergenStart = line.indexOf("(contains");
            Set<Ingredient> ingredients = Arrays
                .stream(line.substring(0, idxAllergenStart).trim().split(" "))
                .map(this::mapToIngredient)
                .collect(Collectors.toSet());
            Set<Allergen> allergenes = Arrays
                .stream(line.substring(idxAllergenStart, line.length() - 1).split(", "))
                .map(this::mapToAllergen)
                .collect(Collectors.toSet());

            allergenes.forEach(a -> a.computePossibleIngredients(ingredients));

        }
    }

    private Ingredient mapToIngredient(final String ingredientName) {
        String trimmedIngredientName = ingredientName.trim();
        Ingredient ingredient = allIngredients.merge(trimmedIngredientName, new Ingredient(trimmedIngredientName), (oldValue, newValue) -> oldValue);
        ingredient.incrementAppearance();
        return ingredient;
    }

    private Allergen mapToAllergen(final String allergenName) {
        String trimmedAllergenName = allergenName.trim();
        return allAllergenes.merge(trimmedAllergenName, new Allergen(trimmedAllergenName), (oldValue, newValue) -> oldValue);
    }

    private static class Allergen {
        Ingredient ingredient;
        Set<Ingredient> possibleIngredients = new HashSet<>();

        final String name;

        public Allergen(String name) {
            this.name = Objects.requireNonNull(name);
        }

        void computePossibleIngredients(Collection<Ingredient> ingredients) {
            if (possibleIngredients.isEmpty()) {
                possibleIngredients.addAll(ingredients);
            } else {

            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Allergen allergen = (Allergen) o;
            return name.equals(allergen.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    private static class Ingredient {
        Allergen allergen;

        final String name;
        int appearances = 0;

        public Ingredient(String name) {
            this.name = Objects.requireNonNull(name);
        }

        void incrementAppearance() {
            appearances++;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Ingredient that = (Ingredient) o;
            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
