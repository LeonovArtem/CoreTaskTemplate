package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Agent", "Smith", (byte) 35);
        userService.saveUser("Thomas", "Anderson", (byte) 25);
        userService.saveUser("Clone", "Smith", (byte) 36);
        userService.saveUser("Neo", "User", (byte) 99);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
