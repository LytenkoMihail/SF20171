package ua.amper.kharkov.sf;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.dao.User;
import ua.amper.kharkov.sf.gui.GetUserPasswordWindow;
import ua.amper.kharkov.sf.gui.OptionsWindow;

import java.util.ArrayList;

import static ua.amper.kharkov.sf.SFConstants.LOGGER_START;
import static ua.amper.kharkov.sf.SFConstants.SF_RESOURCES_FILE_ICON_PROGRAMM;
import static ua.amper.kharkov.sf.SFConstants.SF_VERSION;

public class SFStart extends Thread {
    private static final Logger LOGGER = Logger.getLogger(SFStart.class);
    private SF parrent = null;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public User getAuthorizedUser() {
        return authorizedUser;
    }

    public void setAuthorizedUser(User authorizedUser) {
        this.authorizedUser = authorizedUser;
    }

    private ArrayList<User> users;
    private User authorizedUser;
    private boolean passwordEnteredCorrectly;

    public boolean isPasswordEnteredCorrectly() {
        return passwordEnteredCorrectly;
    }

    private void UserInputAndPassword(ArrayList<User> users, User authorizedUser) {
        OptionsWindow optionsWindowGetUserPasswordWindow = new OptionsWindow();
        optionsWindowGetUserPasswordWindow.setX(0);
        optionsWindowGetUserPasswordWindow.setY(0);
        optionsWindowGetUserPasswordWindow.setWidth(0);
        optionsWindowGetUserPasswordWindow.setHeight(0);
        optionsWindowGetUserPasswordWindow.setTitle(SF_VERSION);
        optionsWindowGetUserPasswordWindow.setFileImageIconName(SF_RESOURCES_FILE_ICON_PROGRAMM);
        GetUserPasswordWindow getUserPasswordWindow = new GetUserPasswordWindow(optionsWindowGetUserPasswordWindow);
        getUserPasswordWindow.createDialog(users, authorizedUser);
        getUserPasswordWindow.showDialog();
        if (getUserPasswordWindow.isUserSelected()) {
            passwordEnteredCorrectly = true;
        }

    }

    public SFStart(SF caller,ArrayList<User> users, User authorizedUser) {
        setUsers(users);
        setAuthorizedUser(authorizedUser);
        parrent = caller;
        passwordEnteredCorrectly = false;

    }

    @Override
    public void run() {
        UserInputAndPassword(getUsers(), getAuthorizedUser());
        synchronized (parrent) {

            parrent.notify();
        }
    }
}
