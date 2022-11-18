package Schedule;

public enum Periodicity {
    SINGLE("однократная"),
    DAILY("ежедневная"),
    WEEKLY("еженедельная"),
    MONTHLY("ежемесячная"),
    YEARLY("ежегодная");

    private String periodicity;

    Periodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    @Override
    public String toString() {
        return periodicity;
    }
}
