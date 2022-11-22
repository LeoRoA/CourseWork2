package Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SingleTask extends Task{
    public SingleTask(String title, String description, boolean personalityType,LocalDateTime taskDateTime) {
        super(title, description, personalityType,  taskDateTime, Periodicity.SINGLE);
    }

    @Override
    public boolean appearIn(LocalDate date) {
        return getTaskDateTime().toLocalDate().equals(date);
    }
}
