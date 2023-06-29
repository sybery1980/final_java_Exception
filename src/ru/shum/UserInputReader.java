package ru.shum;
public interface UserInputReader {
    UserData readUserData() throws UserDataException;
}