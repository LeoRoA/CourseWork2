package Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.Comparator.comparing;

public class Schedule {
    Map<Integer, Task> taskMap = new HashMap<Integer, Task>();

    public Collection<Task> getTaskForDay(LocalDate localDate) {
        Set<Task> taskForDay = new TreeSet<>(
                comparing((Task t) -> t.getTaskDateTime().toLocalTime()));
        for (Task task : taskMap.values()) {
            if (task.appearIn(localDate)) {
                taskForDay.add(task);
            }
        }
        return taskForDay;
    }
    public  void getAmountTaskForDay(LocalDate localDate){
        System.out.println(getTaskForDay(localDate).size());
    }

    public void removeTask (int id) {
        for (Task task : taskMap.values()) {
            if (id == task.getId()) {
                System.out.println("Удалена задача: " + task);
                taskMap.remove(id);
                break;
            }
        }
    }

    public void addTask(Task task) {
        taskMap.put(task.getId(), task);
    }
    public int getSize() {
        return  taskMap.size();
    }
    @Override
    public String toString() {
        for (Map.Entry entry : taskMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        return " " ;
    }
}
