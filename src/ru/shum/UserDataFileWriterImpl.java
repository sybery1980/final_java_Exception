package ru.shum;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * `UserDataFileWriterImpl` - класс, реализующий интерфейс `UserDataFileWriter`. Он используется для
 * записи данных пользователя в файл. Класс содержит метод `writeUserData()`, который принимает
 * объект `UserData`. Пользователю предлагается ввести имя файла, в который будут записаны данные.
 * Затем создаётся объект `FileWriter` для указанного файла и записываются данные в форматированном
 * виде. После записи данных файл закрывается. В случае возникновения ошибок ввода-вывода
 * выбрасывается исключение `IOException`. После успешной записи данных выводится сообщение об
 * успешной операции.
 */
public class UserDataFileWriterImpl implements UserDataFileWriter {

    @Override
    public void writeUserData(UserData userData) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя файла для записи данных: ");
        String fileName = scanner.nextLine();
        FileWriter writer = new FileWriter(fileName, true);
        String userDataString = String.join(" ",
                userData.getLastName(),
                userData.getFirstName(),
                userData.getMiddleName(),
                userData.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                String.valueOf(userData.getPhoneNumber()),
                userData.getGender().toString());
        writer.write(userDataString + "\n");
        writer.close();
        System.out.println("Данные успешно записаны в файл: " +
                fileName);
    }
}