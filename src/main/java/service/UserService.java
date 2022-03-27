package service;

import entity.User;
import lombok.NonNull;
import storage.EntityStorage;

import java.util.List;

public class UserService {
    private static final EntityStorage<User> userStorage = new EntityStorage<>();

    private static UserService instance;
    private static Integer nextId = 0;

    public static UserService getInstance() {
        if (instance == null) instance = new UserService();
        return instance;
    }

    public static Integer getNextId() {
        return ++nextId;
    }

    private UserService() {
    }

    public List<User> getAll() {
        return userStorage.getAll();
    }

    public User getById(@NonNull Integer id) {
        return userStorage.getById(id);
    }

    public User getByLogin(@NonNull String login) {
        List<User> users = getAll();
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    public boolean save(@NonNull User user) {
        return userStorage.save(user);
    }

    public User removeById(@NonNull Integer id) {
        return userStorage.removeById(id);
    }

    public boolean removeAll() {
        return userStorage.removeAll();
    }

    public boolean removeAll(@NonNull List<User> entities) {
        return userStorage.removeAll(entities);
    }

    public boolean removeAllCommonUsers() {
        List<User> users = getAll();
        return removeAll(users);
    }

    public boolean isUserExist(@NonNull User user) {
        return userStorage.isContains(user);
    }

    public boolean isLoginExist(@NonNull String login) {
        List<User> users = getAll();
        return users.stream()
                .map(User::getLogin)
                .anyMatch(login::equals);
    }

    public boolean isIdExist(@NonNull Integer id) {
        List<User> users = getAll();
        return users.stream()
                .map(User::getId)
                .anyMatch(id::equals);
    }
}