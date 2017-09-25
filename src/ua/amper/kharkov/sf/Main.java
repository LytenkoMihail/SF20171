package ua.amper.kharkov.sf;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.dao.User;
import ua.amper.kharkov.sf.gui.*;
import ua.amper.kharkov.sf.util.LoadPropertyFromFile;
import ua.amper.kharkov.sf.util.UtilScreen;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static ua.amper.kharkov.sf.SFConstants.*;


/**
 * Created by amper on 23.05.2017.
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static DialogWindows dialogWindows;
    private static UtilScreen utilScreen = new UtilScreen();

    private static void starProgramma() {
//        final UtilScreen utilScreen = new UtilScreen();
        LOGGER.info(SF_VERSION);
        LOGGER.info(LOGGER_START);
        System.out.println(LOGGER_START);

    }

    private static void exitProgramma() {

    }

    private static void runProgramma() {

    }

    public static void main(String[] args) throws InvocationTargetException, InterruptedException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {


        ArrayList<User> users = new ArrayList<>();
        User authorizedUser = new User();
        users.ensureCapacity(1_00);
        for (Integer i = 0; i < 1_00; i++) {
            users.add(new User(i, "Петя()" + i, Integer.toString(i)));
        }

        starProgramma();
        LoadPropertyFromFile globalProperty = new LoadPropertyFromFile(SF_RESOURCES_FILE_PROPERTIES_SF_PROPERTIES);
        System.out.println(globalProperty.isLoadPropertyFile());
        if (!(globalProperty.isLoadPropertyFile())) {
            dialogWindows = new DialogWindows(SF_VERSION);
            dialogWindows.DialogMessageError(SF_PROPERTIES_FILE_NOT_OPEN_MESSAGE);
            System.exit(1);
        } else {

            System.out.println(globalProperty.getProperty("USER_NAME"));
        }

/* Пусть наш GUI будет в стиле ОС */
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());


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
        LOGGER.info(LOGGER_END);
        System.out.println(LOGGER_END);
//        System.exit(0);
    }



}
