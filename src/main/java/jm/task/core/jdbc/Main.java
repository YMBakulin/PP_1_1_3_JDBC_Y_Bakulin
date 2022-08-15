package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        

        // 1. Создание таблицы User(ов)
        userDaoJDBC.createUsersTable();

        // 2. Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в
        // консоль ( User с именем – name добавлен в базу данных )
        userDaoJDBC.saveUser("John", "Malkovich", (byte) 68);
        userDaoJDBC.saveUser("Josh", "Brolin", (byte) 54);
        userDaoJDBC.saveUser("Zooye", "Deshanel", (byte) 39);
        userDaoJDBC.saveUser("Peter", "Peterson", (byte) 28);

        // 3.  Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        userDaoJDBC.getAllUsers().stream().forEach(System.out::println);

        // 4.  Очистка таблицы User(ов)
        userDaoJDBC.cleanUsersTable();

        // 5.  Удаление таблицы
        userDaoJDBC.dropUsersTable();

    }
}
