package at.schrogl.aoc.common;

public abstract class AbstractSolution {

    public abstract DayInfo getDayInfo();

    protected abstract SolutionData example1();

    protected abstract SolutionData example2();

    protected abstract SolutionData exercise1();

    protected abstract SolutionData exercise2();

    public void execute() {
        DayInfo dayInfo = getDayInfo();
        System.out.printf("# Day %2d: %s\n", dayInfo.getDayOfAdvent(), dayInfo.getTitle());
        printSolution("Example  1", example1());
        printSolution("Example  2", example2());
        printSolution("Exercise 1", exercise1());
        printSolution("Exercise 2", exercise2());
    }

    private void printSolution(String prefix, SolutionData solutionData) {
        System.out.printf(
            "%s:  %s (%.2fms)\n", prefix, solutionData.getSolutionStatus(),
            solutionData.getComputationDuration().toNanos() / 1_000_000f
        );
        System.out.printf("\tinput:    %s\n", solutionData.getInput().getFilename());
        System.out.printf("\tactual:   %d\n", solutionData.getActualResult());
        System.out.printf("\texpected: %d\n", solutionData.getExpectedResult());
        System.out.printf("\tDetails:  %s\n", solutionData.getActualResultDetails());
    }

}
