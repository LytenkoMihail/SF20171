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
    private boolean passwordEnteredCorrectly;
    public boolean isPasswordEnteredCorrectly() {
        return passwordEnteredCorrectly;
    }

    public void UserInputAndPassword (ArrayList<User> users, User authorizedUser) {
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


    public SFStart(SF caller) {
        parrent = caller;

        passwordEnteredCorrectly = false;
        System.out.println(LOGGER_START);
    }
}
