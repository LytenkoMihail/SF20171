package ua.amper.kharkov.sf;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.dao.User;
import ua.amper.kharkov.sf.gui.*;
import ua.amper.kharkov.sf.util.UtilScreen;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static ua.amper.kharkov.sf.SFConstants.*;


/**
 * Created by amper on 23.05.2017.
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) throws InvocationTargetException, InterruptedException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        ArrayList<User> users = new ArrayList<>();
        User authorizedUser = new User();

        users.ensureCapacity(1_00);
        for (Integer i = 0; i < 1_00; i++) {
            users.add(new User(i, "Петя()" + i, Integer.toString(i)));
        }


/* Пусть наш GUI будет в стиле ОС */
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());


        final UtilScreen utilScreen = new UtilScreen();
        LOGGER.info(SFConstants.SF_VERSION);
        LOGGER.info(SFConstants.LOGGER_START);
        System.out.println(SFConstants.LOGGER_START);


        OptionsWindow OWGetUserPasswordWindow = new OptionsWindow();
        OWGetUserPasswordWindow.setX(0);
        OWGetUserPasswordWindow.setY(0);
        OWGetUserPasswordWindow.setWidth(0);
        OWGetUserPasswordWindow.setHeight(0);
        OWGetUserPasswordWindow.setTitle(SFConstants.SF_VERSION);
        OWGetUserPasswordWindow.setFileImageIconName(SFConstants.SF_ICON_FILE);
        GetUserPasswordWindow gupw = new GetUserPasswordWindow(OWGetUserPasswordWindow);
        gupw.createDialog(users, authorizedUser);
        gupw.showDialog();


        if (gupw.isUserSelected()) {
            System.out.println(authorizedUser.toString());
            OptionsWindow WOMainWindow = new OptionsWindow();
            WOMainWindow.setX(0);
            WOMainWindow.setY(0);
            WOMainWindow.setWidth(utilScreen.getWidth());
            WOMainWindow.setHeight(utilScreen.getHeight());
            WOMainWindow.setTitle(SFConstants.SF_VERSION+" ["+authorizedUser.getName()+"]");
            WOMainWindow.setFileImageIconName(SFConstants.SF_ICON_FILE);

            SwingUtilities.invokeAndWait(() ->
            {
                MainWindow mainwin = new MainWindow(WOMainWindow);
                mainwin.setVisible(true);
            });
        }
        LOGGER.info(SFConstants.LOGGER_END);
        System.out.println(SFConstants.LOGGER_END);
//        System.exit(0);
    }
}
