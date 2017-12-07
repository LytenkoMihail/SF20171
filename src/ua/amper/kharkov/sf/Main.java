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
    private static MainStart mainStart;
    private static MainStop mainStop;

    public static void main(String[] args) throws InvocationTargetException, InterruptedException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
/* Пусть наш GUI будет в стиле ОС */
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        LOGGER.info(SF_VERSION);


        ArrayList<User> users = new ArrayList<>();
        User authorizedUser = new User();
        users.ensureCapacity(1_000_000);
        for (Integer i = 0; i < 1_00; i++) {
            users.add(new User(i, "Петя()" + i, Integer.toString(i)));
        }

        MainStart mainStart = new MainStart();
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
        MainStop mainStop = new MainStop();
    }



}
