package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoHibernateImpl();

        // 1. Создание таблицы User(ов)
        userDao.createUsersTable();

        // 2. Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в
        // консоль ( User с именем – name добавлен в базу данных )
        userDao.saveUser("John", "Malkovich", (byte) 68);
        userDao.saveUser("Josh", "Brolin", (byte) 54);
        userDao.saveUser("Zooye", "Deshanel", (byte) 39);
        userDao.saveUser("Peter", "Peterson", (byte) 28);

        // 3.  Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        userDao.getAllUsers().stream().forEach(System.out::println);


//        // 4.  Очистка таблицы User(ов)
        userDao.cleanUsersTable();
//
//        // 5.  Удаление таблицы
        userDao.dropUsersTable();
    }
}
