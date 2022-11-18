package Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task{
    public WeeklyTask(String title, String description, boolean personalityType, LocalDateTime taskDateTime) {
        super(title, description, personalityType, taskDateTime, Periodicity.WEEKLY);
    }

    @Override
    public boolean appearIn(LocalDate date) {
        LocalDate taskCreationDate = getTaskDateTime().toLocalDate();
        return taskCreationDate.equals(date) ||
                taskCreationDate.isBefore(date) &&
                taskCreationDate.getDayOfWeek().equals(date.getDayOfWeek());
    }
}
