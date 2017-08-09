package ua.amper.kharkov.sf.dao;

import java.util.ArrayList;

public class UserSearchPasswordCodeName {
    public boolean isSearchPasswordCodeName() {
        return SearchPasswordCodeName;
    }

    public void setSearchPasswordCodeName(boolean searchPasswordCodeName) {
        SearchPasswordCodeName = searchPasswordCodeName;
    }

    private boolean SearchPasswordCodeName;

    private void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    private ArrayList<User> users;

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }

    private User user;

    private void setUserNamePasswordId(User userOne) {

        user.setId(userOne.getId());
        user.setPassword(userOne.getPassword());
        user.setName(userOne.getName());
    }

    public void SearchByNameAndPassword(String searchName, String password) {
//        setSearchPasswordCodeName(false);
        if (users.isEmpty() == false) {
            for (int i = 0; i < users.size(); i++) {

                if (searchName.equals(users.get(i).getName()) && password.equals(users.get(i).getPassword())) {
                    setSearchPasswordCodeName(true);
                    setUserNamePasswordId(users.get(i));
                    return;
                }
            }
        }
        return;
    }

    public void SearchByNameAndPasswordAndId(String searchName, String password, Integer id) {
//        setSearchPasswordCodeName(false);
        if (users.isEmpty() == false) {
            for (int i = 0; i < users.size(); i++) {
                if (searchName.equals(users.get(i).getName()) && password.equals(users.get(i).getPassword()) && id.equals(users.get(i).getId())) {
                    setSearchPasswordCodeName(true);
                    setUserNamePasswordId(users.get(i));
                    return;
                }
            }
        }
        return;
    }

    public UserSearchPasswordCodeName(ArrayList<User> users, User user) {
        setSearchPasswordCodeName(false);
        setUsers(users);
        setUser(user);
    }
}
