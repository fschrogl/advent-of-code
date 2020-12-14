package at.schrogl.aoc;

import at.schrogl.aoc.common.AbstractSolution;
import at.schrogl.aoc.common.DayInfo;
import at.schrogl.aoc.d01.Day01;

import java.util.HashMap;
import java.util.Map;

public class AdventOfCodeApplication {

    private final Class<? extends AbstractSolution>[] solutionClasses = new Class[]{
        Day01.class
    };
    private final Map<Integer, AbstractSolution> solutions = new HashMap<>(solutionClasses.length);

    public static void main(String[] args) throws Exception {
        AdventOfCodeApplication adventOfCodeApplication = new AdventOfCodeApplication();

        System.out.println("ADVENT OF CODE 2020\n");
        adventOfCodeApplication.initAndPrintToc();

        int solutionToExecute = -1;
        if (args == null) {
            System.out.print("Select day to execute: ");
            solutionToExecute = System.in.read() - 48;
        } else {
            solutionToExecute = Integer.parseInt(args[0]);
        }
        System.out.println();
        adventOfCodeApplication.execute(solutionToExecute);
    }

    private void initAndPrintToc() throws Exception {
        for (Class<? extends AbstractSolution> solutionClass : solutionClasses) {
            final AbstractSolution solution = solutionClass.getDeclaredConstructor().newInstance();
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
