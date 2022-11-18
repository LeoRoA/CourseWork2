package Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task{
    public YearlyTask(String title, String description, boolean personalityType, LocalDateTime taskDateTime) {
        super(title, description, personalityType, taskDateTime, Periodicity.YEARLY);
    }

    @Override
    public boolean appearIn(LocalDate date) {
        LocalDate taskCreationDate = getTaskDateTime().toLocalDate();
        return (taskCreationDate.equals(date) ||
                taskCreationDate.isBefore(date)) &&
                        (taskCreationDate.getDayOfYear() == date.getDayOfYear());
    }
}
