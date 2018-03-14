package ua.amper.kharkov.sf;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.dao.User;
import ua.amper.kharkov.sf.gui.DialogWindows;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

import static ua.amper.kharkov.sf.SFConstants.*;


/**
 * Created by amper on 23.05.2017.
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);


    public static void main(String[] args) throws InvocationTargetException, InterruptedException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException, SQLException {

        ArrayList<User> users = new ArrayList<>();
        User authorizedUser = new User();
        boolean initializationProgram;

        DialogWindows dialogWindows = new DialogWindows(SF_VERSION);
        SF sF = new SF(dialogWindows);

        initializationProgram = sF.initializationOfTheProgram(users);
        System.out.println(initializationProgram);
        if (initializationProgram == true) {
            sF.start(users, authorizedUser);
        }
        sF.stop();
    }

}


