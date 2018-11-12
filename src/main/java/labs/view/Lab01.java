package labs.view;

import labs.model.Person;
import labs.repository.Repository;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.util.Scanner;

public class Lab01 {

    private static final String SIZE = "--size";
    private static final String IS_EMPTY = "--isEmpty";
    private static final String ADD = "--add";
    private static final String CLEAR = "--clear";
    private static final String REMOVE = "--remove";
    private static final String PRINT = "--print";
    private static final String TERMINATE = "-t";

    private static boolean appRunning = true;

    private static final String HELP = "Для взаимодействия с приложением используйте следующие ключи:\n"
            + SIZE + " для вывода длины списка\n"
            + IS_EMPTY + " чтобы узнать есть ли записи в списке\n"
            + ADD + " для добавления новой записи\n"
            + CLEAR + " для очистки списка\n"
            + REMOVE + " для удаления элемента из списка\n"
            + PRINT + " для вывода списка\n"
            + TERMINATE + " для выхода из программы";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(HELP);
        Repository<Person> personRepository = new Repository<>();

        while (appRunning) {
            String command = scanner.next();
            switch (command) {
                case TERMINATE:
                    appRunning = false;
                    break;
                case SIZE:
                    System.out.println(personRepository.size());
                    break;
                case IS_EMPTY:
                    System.out.println(personRepository.isEmpty());
                    break;
                case ADD:
                    System.out.println("Введите данные новой записи через пробел -> ID Имя ДатаРождения Пол\n" +
                            "например\n" +
                            "1 Иван 01-02-1993 Мужской");
                    if (personRepository.add(new Person(
                            Integer.parseInt(scanner.next()),
                            scanner.next(),
                            LocalDate.parse(scanner.next(), DateTimeFormat.forPattern("dd-MM-yyyy"))
                    ))) {
                        System.out.println("Запись добавлена");
                    } else {
                        System.out.println("Ошибка добавления записи");
                    }
                    break;
                case CLEAR:
                    System.out.println(personRepository.clear());
                    break;
                case REMOVE:
                    System.out.println("введите номер удаляемой записи");
                    System.out.println(personRepository.remove(Integer.parseInt(scanner.next())));
                    break;
                case PRINT:
                    System.out.println(personRepository.toString());
                    break;
                default:
                    System.out.println(HELP);
                    break;
            }
        }


//        Repository<String> myStringArrayList = new Repository<>();
//
//        System.out.println("size = " + myStringArrayList.size() + "\n"
//                + "isEmpty " + myStringArrayList.isEmpty() + "\n");
//
//        for (int i = 0; i < 11; i++) {
//            myStringArrayList.add(String.valueOf(i));
//        }
//        System.out.println("size = " + myStringArrayList.size() + "\n"
//                + "elementToRemove = " + myStringArrayList.get(0) + "\n"
//                + "elementRemoved = " + myStringArrayList.remove(0) + "\n"
//                + "newSize = " + myStringArrayList.size() + "\n"
//                + "isEmpty " + myStringArrayList.isEmpty() + "\n");
//        System.out.println("size = " + myStringArrayList.size() + "\n"
//                + "elementToRemove = " + myStringArrayList.get(3) + "\n"
//                + "elementRemoved = " + myStringArrayList.remove("3") + "\n"
//                + "newSize = " + myStringArrayList.size() + "\n");
//        System.out.println("size = " + myStringArrayList.size() + "\n"
//                + "clear " + myStringArrayList.clear() + "\n"
//                + "newSize = " + myStringArrayList.size() + "\n"
//                + "isEmpty " + myStringArrayList.isEmpty() + "\n");
//
//
//        Repository<Person> personList = new Repository<>();
//        personList.add(new Person(1, "Ruslan", new LocalDate(1990, 8, 5 ), Gender.male));
//        personList.add(new Person(2, "Ruslan", LocalDate.now(), Gender.male));
//
//        System.out.println(personList);
    }
}
