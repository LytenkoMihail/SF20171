package ua.amper.kharkov.sf;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.dao.User;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static ua.amper.kharkov.sf.SFConstants.LOGGER_START;
import static ua.amper.kharkov.sf.SFConstants.SF_VERSION;

public class SF {
    private static final Logger LOGGER = Logger.getLogger(SF.class);
    private boolean connectingDataBase;

    public boolean isConnectingDataBase() {
        return connectingDataBase;
    }

    public void setPasswordEnteredCorrectly(boolean passwordEnteredCorrectly) {
        PasswordEnteredCorrectly = passwordEnteredCorrectly;
    }

    private boolean PasswordEnteredCorrectly;

    public boolean isPasswordEnteredCorrectly() {
        return PasswordEnteredCorrectly;
    }

    public void run(User authorizedUser) throws InterruptedException, InvocationTargetException {
        SFRun sfRun = new SFRun(this,authorizedUser);
        sfRun.start();
        synchronized (this) {
            wait();
        }

    }
    public void start(ArrayList<User> users, User user) throws InterruptedException, InvocationTargetException {
        SFStart sfStart = new SFStart(this,users, user);
        sfStart.start();
        synchronized (this) {
            System.out.println("wait();");
            wait();
        }
        if (sfStart.isPasswordEnteredCorrectly()) setPasswordEnteredCorrectly(true);
    }
public  void  stop () {
    SFStop mainStop = new SFStop();
    mainStop.stop();
}
    public void connectingToDataBase(ArrayList<User> users) {
        users.ensureCapacity(1_000_000);
        for (Integer i = 0; i < 1_00; i++) {
            users.add(new User(i, "Петя()" + i, Integer.toString(i)));
        }
        connectingDataBase = true;
    }

    public SF() throws InvocationTargetException, InterruptedException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        /* Пусть наш GUI будет в стиле ОС */
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        LOGGER.info(SF_VERSION);
        LOGGER.info(LOGGER_START);
        connectingDataBase = false;
        PasswordEnteredCorrectly= false;
        System.out.println(LOGGER_START);
    }
}
