package at.schrogl.aoc.common;

public class DayInfo {

    private final int dayOfAdvent;
    private final String title;

    public DayInfo(int dayOfAdvent, String title) {
        this.dayOfAdvent = dayOfAdvent;
        this.title = title;
    }

    public int getDayOfAdvent() {
        return dayOfAdvent;
    }

    public String getTitle() {
        return title;
    }
}
