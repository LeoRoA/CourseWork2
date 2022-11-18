package Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task {
    public MonthlyTask(String title, String description, boolean personalityType, LocalDateTime taskDateTime) {
        super(title, description, personalityType, taskDateTime, Periodicity.MONTHLY);
    }

    @Override
    public boolean appearIn(LocalDate date) {
        LocalDate taskCreationDate = getTaskDateTime().toLocalDate();
        return (taskCreationDate.equals(date) ||
                taskCreationDate.isBefore(date)) &&
                (taskCreationDate.getDayOfMonth() == date.getDayOfMonth());
    }
}
