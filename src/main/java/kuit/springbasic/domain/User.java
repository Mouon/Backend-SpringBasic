package kuit.springbasic.domain;

import lombok.*;

//어노테이션으로 대체
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"userId", "password"})
public class User {
    private String userId;
    private String password;
    private String name;
    private String email;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void update(User updateUser) {
        this.password = updateUser.password;
        this.name = updateUser.name;
        this.email = updateUser.email;
    }

    public boolean matchPassword(String password) {
        if (password == null) {
            return false;
        }

        return this.password.equals(password);
    }

    public boolean isSameUser(User user) {
        return isSameUser(user.getUserId(),user.getPassword());
    }

    public boolean isSameUser(String userId, String password) {
        return userId.equals(this.userId) && matchPassword(password);
    }


}
