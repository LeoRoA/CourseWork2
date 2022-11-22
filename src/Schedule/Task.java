package Schedule;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private static  int counter;
    private String title;

    private String description;

    private final int id;

    private final boolean personalityType;
    private final Periodicity periodicity;
private final LocalDateTime taskDateTime;

    public Task( String title, String description, boolean personalityType, LocalDateTime taskDateTime, Periodicity periodicity) {
        setTitle(title);
        setDescription(description);
        this.id = counter++;
        this.taskDateTime = taskDateTime;
        this.personalityType = personalityType;
        this.periodicity = periodicity;
    }


    public LocalDateTime getTaskDateTime() {
        return taskDateTime;
    }

    public static int getCounter() {
        return counter;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract boolean appearIn(LocalDate date);

    @Override
    public String toString() {
        String personality = (personalityType)?" лична€":" рабоча€";
        LocalDate date = taskDateTime.toLocalDate();
        return "id=" + id +", «адача " + title + ", описание: " + description +
                personality +" задача " +
                periodicity.toString() +", срок"+ date;
    }
}
