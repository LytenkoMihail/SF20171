package ua.amper.kharkov.sf.dao;

/**
 * Created by amper on 08.06.2017.
 */
public class User extends UserAbstract {
    public User() {
        setId(0);
        setName("");
        setPassword("");
    }

    public User(Integer id, String name, String password) {
        setId(id);
        setName(name);
        setPassword(password);
    }
}
