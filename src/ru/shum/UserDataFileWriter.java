package ru.shum;
import java.io.IOException;

/**
 * UserDataFileWriter - интерфейс, определяющий контракт для записи данных пользователя. Он содержит
 * метод writeUserData(), который должен быть реализован в конкретных классах-писателях. Метод может
 * выбрасывать исключение IOException в случае ошибок ввода-вывода.
 */
public interface UserDataFileWriter {

    void writeUserData(UserData userData) throws IOException;

}