package at.schrogl.aoc.common;

public enum SolutionStatus {
    OK(" OK"),
    NOK("NOK"),
    UNKNOWN("---");

    private final String text;

    SolutionStatus(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
