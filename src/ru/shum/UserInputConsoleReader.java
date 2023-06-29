package ru.shum;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * `UserInputConsoleReader` - класс, реализующий интерфейс `UserInputReader`. Он использует
 * `Scanner` для чтения пользовательского ввода с консоли. Класс содержит метод `readUserData()`,
 * который предлагает пользователю ввести данные в определенном порядке, разделяя их пробелом. Затем
 * метод разбивает введенные данные на составляющие и выполняет их парсинг. Если формат данных
 * неверный, выбрасывается исключение `ParseException`. Если введено неверное количество данных,
 * выбрасывается `UserDataException`. Если все данные корректны, создается и возвращается объект
 * `UserData`.
 */
public class UserInputConsoleReader implements UserInputReader {
    private Scanner scanner;

    public UserInputConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public UserData readUserData() throws UserDataException {
        while (true) {
            System.out.println("Введите данные в следующем порядке, разделяя их пробелом: Фамилия Имя Отчество Дата рождения (dd.mm.yyyy) Номер телефона Пол (f или m)");

            try {
                String input = scanner.nextLine();
                String[] data = input.split(" ");

                if (data.length != 6) {
                    throw new UserDataException("Введено неверное количество данных");
                }

                LocalDate dateOfBirth = parseDateOfBirth(data[3]);
                long phoneNumber = parsePhoneNumber(data[4]);
                Gender gender = parseGender(data[5]);

                return new UserData(data[0], data[1], data[2], dateOfBirth, phoneNumber, gender);
            } catch (ParseException | NumberFormatException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private LocalDate parseDateOfBirth(String dateOfBirthString) throws ParseException {
        try {
            return LocalDate.parse(dateOfBirthString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (Exception e) {
            throw new ParseException("Неверный формат даты рождения");
        }
    }

    private long parsePhoneNumber(String phoneNumberString) throws ParseException {
        try {
            return Long.parseLong(phoneNumberString);
        } catch (NumberFormatException e) {
            throw new ParseException("Неверный формат номера телефона");
        }
    }

    private Gender parseGender(String genderString) throws ParseException {
        if (genderString.equalsIgnoreCase("m")) {
            return Gender.MALE;
        } else if (genderString.equalsIgnoreCase("f")) {
            return Gender.FEMALE;
        } else {
            throw new ParseException("Неверный формат пола");
        }
    }
}