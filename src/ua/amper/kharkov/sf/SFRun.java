package ua.amper.kharkov.sf;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.dao.User;
import ua.amper.kharkov.sf.gui.MainWindow;
import ua.amper.kharkov.sf.gui.OptionsWindow;
import ua.amper.kharkov.sf.util.UtilScreen;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

import static ua.amper.kharkov.sf.SFConstants.SF_RESOURCES_FILE_ICON_PROGRAMM;
import static ua.amper.kharkov.sf.SFConstants.SF_VERSION;

public class SFRun extends Thread {
    private static final Logger LOGGER = Logger.getLogger(SFRun.class);
    private static UtilScreen utilScreen = new UtilScreen();
    private SF parrent = null;
    private User authorizedUser;
    private MainWindow mainwin;
    public User getAuthorizedUser() {
        return authorizedUser;
    }

    public void setAuthorizedUser(User authorizedUser) {
        this.authorizedUser = authorizedUser;
    }

    public SFRun(SF caller, User authorizedUser) {
        this.parrent = caller;
        setAuthorizedUser(authorizedUser);
    }

    private void mainWindow()  {
        System.out.println(authorizedUser.toString());
//        String titleString = SF_VERSION + " [" + authorizedUser.getName() + "]";
        OptionsWindow optionsMainWindow = new OptionsWindow(0,0,utilScreen.getWidth(),utilScreen.getHeight(),SF_VERSION + " [" + authorizedUser.getName() + "]",SF_RESOURCES_FILE_ICON_PROGRAMM);
//        optionsMainWindow.setX(0);
//        optionsMainWindow.setY(0);
//        optionsMainWindow.setWidth(utilScreen.getWidth());
//        optionsMainWindow.setHeight(utilScreen.getHeight());
//        optionsMainWindow.setTitle(SF_VERSION + " [" + authorizedUser.getName() + "]");
//        optionsMainWindow.setFileImageIconName(SF_RESOURCES_FILE_ICON_PROGRAMM);

        try {
            SwingUtilities.invokeAndWait(() ->
            {
                mainwin = new MainWindow(optionsMainWindow);
                mainwin.setVisible(true);
            });
        } catch (InterruptedException e) {
            LOGGER.error(e) ;
        } catch (InvocationTargetException e) {
            LOGGER.error(e) ;
        }

    }

    @Override
    public void run() {
        mainWindow();
        while(mainwin.isRunningTheProgram()){
            // Код
        }
        synchronized (parrent) {
            System.out.println("synchronized");
            parrent.notify();
        }
    }
}
