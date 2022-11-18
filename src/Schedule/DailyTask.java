package Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task{
    public DailyTask(String title, String description, boolean personalityType, LocalDateTime taskDateTime) {
        super(title, description, personalityType, taskDateTime, Periodicity.DAILY);
    }



    @Override
    public boolean appearIn(LocalDate date) {
        LocalDate taskCreationDate = getTaskDateTime().toLocalDate();
        return taskCreationDate.equals(date) || taskCreationDate.isBefore(date);
    }
}
