package ua.amper.kharkov.sf;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.dao.User;
import ua.amper.kharkov.sf.gui.DialogWindows;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static ua.amper.kharkov.sf.SFConstants.MSG_DB_CONNECTINGDATABASE_ERROR;
import static ua.amper.kharkov.sf.SFConstants.SF_RESOURCES_FILE_SQL_SELECT_FROM_USERS;
import static ua.amper.kharkov.sf.SFConstants.SF_VERSION;


/**
 * Created by amper on 23.05.2017.
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) throws InvocationTargetException, InterruptedException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {

        ArrayList<User> users = new ArrayList<>();
        User authorizedUser = new User();
        String stringSqlExecute;

        SF sF = new SF();
        DialogWindows dialogWindows = new DialogWindows(SF_VERSION);
        if (args.length == 0) {
            sF.connectingToDataBase(users);
            if (sF.isConnectingDataBase() == true) {
                sF.loadSqlExecuteFromFile(SF_RESOURCES_FILE_SQL_SELECT_FROM_USERS);
                if (sF.isLoadSqlExecuteFromFile() == true) {
                    stringSqlExecute = sF.loadStringSqlExecuteFromFile();

                    sF.start(users, authorizedUser);
                    if (sF.isPasswordEnteredCorrectly() == true) {
                        sF.run(authorizedUser);
                    }
                }
            } else {
                dialogWindows.DialogMessageError(MSG_DB_CONNECTINGDATABASE_ERROR);
                LOGGER.error(SFConstants.MSG_DB_CONNECTINGDATABASE_ERROR);

            }
        }

        sF.stop();

    }

}
