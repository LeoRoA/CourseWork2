import Schedule.*;

import javax.lang.model.element.VariableElement;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import static Schedule.Periodicity.*;

public class Main {

    public static void main(String[] args) {
        Schedule schedule = new Schedule();
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
//                            Task task1 = new SingleTask("first",
//                                    "sfdghdsf",
//                                    false,
//                                    LocalDateTime.of(2022, 11, 29, 15, 15));
//                            Task task2 = new DailyTask("second",
//                                    "dsfhgdhyfj fgj",
//                                    true,
//                                    LocalDateTime.of(2022, 11, 29, 16, 15));
//                            Task task3 = new WeeklyTask("third",
//                                    "khjkl sa",
//                                    false,
//                                    LocalDateTime.of(2022, 11, 29, 17, 15));
//                            Task task4 = new WeeklyTask("forth",
//                                    "vcnn trey",
//                                    true,
//                                    LocalDateTime.of(2022, 11, 29, 18, 15));
//                            Task task5 = new MonthlyTask("fifth",
//                                    "qasfdh zxc",
//                                    false,
//                                    LocalDateTime.of(2022, 11, 29, 19, 15));
//                            Task task6 = new YearlyTask("sixth",
//                                    "popjb hse",
//                                    true,
//                                    LocalDateTime.of(2022, 11, 18, 20, 15));
//                            System.out.println((task6.getTaskDateTime().toLocalDate().equals(LocalDate.of(2022, 11, 18)) ||
//                                    task6.getTaskDateTime().toLocalDate().isBefore(LocalDate.of(2022, 11, 18))) &&
//                                    (task6.getTaskDateTime().getDayOfYear() == LocalDate.of(2022, 11, 18).getDayOfYear()));
//                            schedule.addTask(task1);
//                            schedule.addTask(task2);
//                            schedule.addTask(task3);
//                            schedule.addTask(task4);
//                            schedule.addTask(task5);
//                            schedule.addTask(task6);
                            inputTask(scanner, schedule);

                            System.out.println("Добавлено задач: " + schedule.getSize());
                            break;
                        case 2:
                            System.out.print("Введите ID задачи для удаления: ");
                            removeTask(schedule,scanner);
                            break;
                        case 3:
                            System.out.print("Введите дату: ");
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                            String strCheckDate = scanner.next();
                            LocalDate checkDate = LocalDate.parse(strCheckDate, formatter);
                            if (checkDate.isBefore(LocalDate.now())) {
                                System.err.println("Указанная дата уже прошла, введите корректную дату");
                            } else if (schedule.getTaskForDay(checkDate).isEmpty()) {
                                System.out.println("Нет задач на указанную дату");
                            } else {
                                for (Task taskForDay : schedule.getTaskForDay(checkDate)) {
                                    System.out.println(taskForDay);
                                }

                            }
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner, Schedule schedule) {

        System.out.print("Введите название задачи: ");
        String taskName = inputName(scanner);

        System.out.print("Введите описание задачи задачи: ");
        String taskDescription = inputDescription(scanner);

        System.out.println("Выберите тип задачи: ");
        System.out.print(
                """
                                1. Личная
                                2. Рабочая
                        """
        );
        boolean taskType = inputPersonalityType(scanner);

        System.out.print("Введите срок выполнения задачи: ");
        LocalDateTime taskDate = inputDate(scanner);

        System.out.println("Выберите переодичность задач: ");
        System.out.println(
                """
                        1. Однократная
                        2. Ежедневная
                        3. Еженедельная
                        4. Ежемесячная
                        5. Ежегодная
                        """
        );

        boolean isCorrectPeriodicity = false;
        Periodicity taskPeriodicity;
        do {
            try {
                if (scanner.hasNext()) {
                    String period = scanner.next();
                    isCorrectPeriodicity = false;
                    switch (Integer.parseInt(period)) {
                        case 1 -> {
                            taskPeriodicity = SINGLE;
                            Task newSingleTask = new SingleTask(taskName, taskDescription, taskType, taskDate);
                            schedule.addTask(newSingleTask);
                            System.out.printf("Однократная задача %s успешно добавлена\n", taskName);
                        }
                        case 2 -> {
                            taskPeriodicity = DAILY;
                            Task newDailyTask = new DailyTask(taskName, taskDescription, taskType, taskDate);
                            schedule.addTask(newDailyTask);
                            System.out.printf("Ежедневная задача %s успешно добавлена\n", taskName);
                        }
                        case 3 -> {
                            taskPeriodicity = WEEKLY;
                            Task newWeeklyTask = new WeeklyTask(taskName, taskDescription, taskType, taskDate);
                            schedule.addTask(newWeeklyTask);
                            System.out.printf("Еженедельная задача %s успешно добавлена\n", taskName);
                        }
                        case 4 -> {
                            taskPeriodicity = MONTHLY;
                            Task newMonthlyTask = new MonthlyTask(taskName, taskDescription, taskType, taskDate);
                            schedule.addTask(newMonthlyTask);
                            System.out.printf("Ежемесячная задача %s успешно добавлена\n", taskName);
                        }
                        case 5 -> {
                            taskPeriodicity = YEARLY;
                            Task newYearlyTask = new YearlyTask(taskName, taskDescription, taskType, taskDate);
                            schedule.addTask(newYearlyTask);
                            System.out.printf("Ежегодная задача %s успешно добавлена\n", taskName);
                        }
                        default -> {
                            System.out.println("Некорректно выбрана периодичность");
                            System.out.println("Выберите переодичность задач: ");
                            isCorrectPeriodicity = true;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("Неправильный формат строки!");
                isCorrectPeriodicity = true;
            }
        } while (isCorrectPeriodicity);
    }

    private static String inputName(Scanner scanner) {
        boolean isCorrectName = false;
        String name = "";
        do {
            name = scanner.useDelimiter("\n").next();
            if (name == null || name.isEmpty() || name.isBlank()) {
                System.err.println("Ошибка: Не введено название задачи");
            } else {
                System.out.println("Название задачи:" + name);
                isCorrectName = true;
            }
        }
        while (!isCorrectName);
        return name;
    }

    private static String inputDescription(Scanner scanner) {
        String description = null;
        while (true) {
            description = scanner.useDelimiter("\n").next();
            if (description == null || description.isEmpty() || description.isBlank()) {
                System.err.println("Ошибка: Не введено описание задачи");
            } else {
                System.out.println("Описание задачи:" + description);
                break;
            }
        }
        return description;
    }

    private static boolean inputPersonalityType(Scanner scanner) {
        boolean type = false;
        System.out.print("Введите пункт: ");
        try {
            label:
            while (true) {
                if (scanner.hasNext()) {
                    String personalityType = scanner.next();
                    switch (Integer.parseInt(personalityType)) {
                        case 1 -> {
                            type = false;
                            break label;
                        }
                        case 2 -> {
                            type = true;
                            break label;
                        }
                        default -> {
                            System.err.println("Выбрано некорректное значение типа задачи");
                            System.out.print("Ввведите пункт: ");
                        }
                    }
                }

            }
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
            System.out.print("Ввведите пункт: ");
        }

        return type;
    }

    private static LocalDateTime inputDate(Scanner scanner) {
        LocalDateTime inputDate;
        while (true) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                StringBuilder strDate = new StringBuilder();
                if (scanner.hasNext()) {
                    strDate.append(scanner.useDelimiter("\n").next());
                } else {
                    scanner.next();
                    System.out.println("Введите дату в формате dd.MM.yyyy HH:mm");
                }
                inputDate = LocalDateTime.parse(strDate.toString(), formatter);
                if (inputDate.isAfter(LocalDateTime.now())) {
                    System.out.println("Cрок выполения задачи: " + inputDate.format(formatter) + "\n");
                    break;
                } else {
                    System.err.println("Введенная дата уже прошла");
                    System.out.print("Введите актуальную дату в формате dd.MM.yyyy HH:mm:");
                }
            } catch (DateTimeException e) {
                System.err.println("Ошибка: Введите дату в формате dd.MM.yyyy HH:mm");
            }
        }
        return inputDate;
    }

    public static void removeTask(Schedule schedule, Scanner scanner) {
        try  {
            while (true) {
                System.out.println("Введите ID:");

//                System.out.println("спс");
                if (scanner.hasNextInt()) {
                    int id = scanner.nextInt();
                    if (id <= schedule.getSize()) {
                        schedule.removeTask(id);
                        break;
                    } else {
                        System.out.println("Некорректно введен Id");
                    }
                } else {
                    System.out.println("Введите id: ");
                    scanner.next();
                }
//                scanner.nextInt();
            }
        } catch (InputMismatchException e) {
            System.err.println("Неправильный формат строки!");
            System.out.print("Ввведите пункт: ");

        }
    }

    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачу на указанный день
                        0. Выход
                        """
        );
    }
}
