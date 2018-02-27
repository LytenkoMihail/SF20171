package ua.amper.kharkov.sf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDisassembleResults {
    private ArrayList<User> users;
    private ResultSet resultSetForUsers;

    private ArrayList<User> getUsers() {
        return users;
    }

    private void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    private ResultSet getResultSetForUsers() {
        return resultSetForUsers;
    }

    private void setResultSetForUsers(ResultSet resultSetForUsers) {
        this.resultSetForUsers = resultSetForUsers;
    }

    public UserDisassembleResults(ResultSet resultSetForUsers, ArrayList<User> users) {
        setResultSetForUsers(resultSetForUsers);
        setUsers(users);

    }

    public void PrepareListOfUsersFromResults() throws SQLException {
        while (resultSetForUsers.next()) {
            users.add(new User(resultSetForUsers.getInt("id"), resultSetForUsers.getString("name"), resultSetForUsers.getString("password")));
        }
    }
}
