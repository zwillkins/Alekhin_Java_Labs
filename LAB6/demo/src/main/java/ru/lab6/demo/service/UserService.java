package ru.lab6.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import ru.lab6.demo.model.User;

@Service
public class UserService {
    private final Map<Long, User> userStorage = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public UserService() {
    createUser(new User("Влад", "gg@ex.com"));
    createUser(new User("Альберт", "wp@exxx.com"));
}

    public List<User> getAllUsers() {
        return new ArrayList<>(userStorage.values());
    }

    public User getUserById(Long id) {
        return userStorage.get(id);
    }

    public User createUser(User user) {
        Long newId = idCounter.incrementAndGet();
        user.setId(newId);
        userStorage.put(newId, user);
        return user;
    }

    public User updateUser(Long id, User userDetails) {
        if (userStorage.containsKey(id)) {
            userDetails.setId(id);
            userStorage.put(id, userDetails);
            return userDetails;
        }
        return null;
    }

    public boolean deleteUser(Long id) {
        return userStorage.remove(id) != null;
    }
}