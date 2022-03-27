package entity;


import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class User extends Entity {
    private String login;
    private String password;
    private String name;
    private String surname;

    public User(@NonNull Integer id, @NonNull String login, @NonNull String password,
                @NonNull String name, @NonNull String surname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public String getFullName() {
        return String.format("%s %s", name, surname);
    }
}