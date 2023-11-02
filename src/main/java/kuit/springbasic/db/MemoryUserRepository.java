package kuit.springbasic.db;


import kuit.springbasic.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryUserRepository {
    private Map<String, User> users = new HashMap<>();
    private static MemoryUserRepository memoryUserRepository;

    public MemoryUserRepository() {
        insert(new User("kuit","kuit","쿠잇","kuit@kuit.com"));
    }
    
    public void insert(User user) {
        users.put(user.getUserId(), user);
    }

    public User findByUserId(String userId) {
        return users.get(userId);
    }

    public Collection<User> findAll() {
        return users.values();
    }

    public void changeUserInfo(User user) {
        if (users.get(user.getUserId()) != null) {
            users.put(user.getUserId(), user);
        }
    }

    public void update(User user) {
        User repoUser = users.get(user.getUserId());
        repoUser.update(user);
    }
}