package ua.amper.kharkov.sf;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.dao.User;
import ua.amper.kharkov.sf.gui.DialogWindows;
import ua.amper.kharkov.sf.gui.MainWindow;
import ua.amper.kharkov.sf.gui.OptionsWindow;
import ua.amper.kharkov.sf.util.UtilScreen;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static ua.amper.kharkov.sf.SFConstants.LOGGER_START;
import static ua.amper.kharkov.sf.SFConstants.SF_RESOURCES_FILE_ICON_PROGRAMM;
import static ua.amper.kharkov.sf.SFConstants.SF_VERSION;

public class SF {
    private static final Logger LOGGER = Logger.getLogger(SF.class);
    private static DialogWindows dialogWindows;
    private static UtilScreen utilScreen = new UtilScreen();
    private static SFStart mainStart;
    private static SFStop mainStop;

    public SF() throws InvocationTargetException, InterruptedException {
        LOGGER.info(SF_VERSION);
        LOGGER.info(LOGGER_START);

        ArrayList<User> users = new ArrayList<>();
        User authorizedUser = new User();
        users.ensureCapacity(1_000_000);
        for (Integer i = 0; i < 1_00; i++) {
            users.add(new User(i, "Петя()" + i, Integer.toString(i)));
        }
        SFStart mainStart = new SFStart(this);

        mainStart.UserInputAndPassword(users,authorizedUser);
//        mainStart.isPasswordEnteredCorrectly();
        if (mainStart.isPasswordEnteredCorrectly()) {

            System.out.println(authorizedUser.toString());
            OptionsWindow WOMainWindow = new OptionsWindow();
            WOMainWindow.setX(0);
            WOMainWindow.setY(0);
            WOMainWindow.setWidth(utilScreen.getWidth());
            WOMainWindow.setHeight(utilScreen.getHeight());
            WOMainWindow.setTitle(SF_VERSION + " [" + authorizedUser.getName() + "]");
            WOMainWindow.setFileImageIconName(SF_RESOURCES_FILE_ICON_PROGRAMM);

            SwingUtilities.invokeAndWait(() ->
            {
                MainWindow mainwin = new MainWindow(WOMainWindow);
                mainwin.setVisible(true);
            });


        }

    }
}
