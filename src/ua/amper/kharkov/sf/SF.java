package ua.amper.kharkov.sf;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.dao.DaoMySql;
import ua.amper.kharkov.sf.dao.User;
import ua.amper.kharkov.sf.dao.UserDisassembleResults;
import ua.amper.kharkov.sf.gui.DialogWindows;
import ua.amper.kharkov.sf.util.LoadSqlExecuteUpdateFromFile;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static ua.amper.kharkov.sf.SFConstants.*;


public class SF {
    private static final Logger LOGGER = Logger.getLogger(SF.class);
    private boolean connectingDataBase;
    private DialogWindows dialogWindows;
    private String stringSqlExecute = "";
    private com.mysql.jdbc.Connection connection = null;
    private boolean resultQueryExecution;
    private ResultSet resultSetForUsers;
    private DaoMySql daoMySql = new DaoMySql();


    public boolean isLoadSqlExecuteFromFile() {
        return loadSqlExecuteFromFile;
    }

    private void setLoadSqlExecuteFromFile(boolean loadSqlExecuteFromFile) {
        this.loadSqlExecuteFromFile = loadSqlExecuteFromFile;
    }

    private boolean loadSqlExecuteFromFile;
    private String stringSqlExecuteFromFile;

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

    public String loadStringSqlExecuteFromFile() {
        return stringSqlExecuteFromFile;
    }

    public void loadSqlExecuteFromFile(String fileNameSqlExecuteUpdate) throws IOException {
        LoadSqlExecuteUpdateFromFile loadSqlExecuteUpdateFromFile = new LoadSqlExecuteUpdateFromFile(fileNameSqlExecuteUpdate);
        if (loadSqlExecuteUpdateFromFile.isLoadSqlExecuteUpdateFromFile() == true) {
            stringSqlExecuteFromFile = loadSqlExecuteUpdateFromFile.getStringSqlExecute();
            System.out.println(stringSqlExecuteFromFile);
            setLoadSqlExecuteFromFile(true);
            return;
        } else {
            System.out.println(SF_SQL_FILE_NOT_OPEN);
            setLoadSqlExecuteFromFile(false);
            return;

        }

    }

    public void run(User authorizedUser) throws InterruptedException, InvocationTargetException {
        SFRun sfRun = new SFRun(this, authorizedUser);
        sfRun.start();
        synchronized (this) {
            wait();
        }

    }
    private void ExitingProgramIfErrorOccurs(DialogWindows dialogWindows, String messageError, int status) {
        dialogWindows.DialogMessageError(messageError);
        LOGGER.error(messageError);
//        System.exit(status);

    }


    public boolean inisilizasia(ArrayList<User> users)throws IOException ,SQLException {
        boolean result = false;
        LoadSqlExecuteUpdateFromFile loadSqlExecuteUpdateFromFile = new LoadSqlExecuteUpdateFromFile(SF_RESOURCES_FILE_SQL_SELECT_FROM_USERS);
        if (loadSqlExecuteUpdateFromFile.isLoadSqlExecuteUpdateFromFile() == true) {
            stringSqlExecute = loadSqlExecuteUpdateFromFile.getStringSqlExecute();
            result = true;
        } else{
            ExitingProgramIfErrorOccurs(dialogWindows, SF_SQL_FILE_NOT_OPEN, 0);
            result =false;
            return result;
        }


        daoMySql.setURL(MY_SQL_LOCAL_HOST, MY_SQL_DATA_BASE, MY_SQL_PORT);
        daoMySql.Connect(MY_SQL_NAME_USER, MY_SQL_PASS_WORD);
        if (daoMySql.getConnection() != null) {
            connection = daoMySql.getConnection();
            resultQueryExecution = daoMySql.execSQLExecution(stringSqlExecute);
            if (resultQueryExecution == true) {
                resultSetForUsers = daoMySql.getResultSet();
                UserDisassembleResults userDisassembleResults = new UserDisassembleResults(resultSetForUsers, users);
                userDisassembleResults.PrepareListOfUsersFromResults();
                System.out.println(users.toString());
                System.out.println("resultQueryExecution=" + resultQueryExecution);
                result =true;
                return result;
            } else {
                ExitingProgramIfErrorOccurs(dialogWindows, SF_SQL_EXEC_NOT_RECORD_TABLE, 0);
                result =false;
                return result;
            }

        } else {
            ExitingProgramIfErrorOccurs(dialogWindows, MSG_DB_CONNECTINGDATABASE_ERROR, 0);
            result =false;
            return result;

        }
    }

    public void start(ArrayList<User> users, User authorizedUser) throws InterruptedException, InvocationTargetException {
        SFStart sfStart = new SFStart(this, users, authorizedUser);
        sfStart.start();
        synchronized (this) {
//            System.out.println("wait();");
            wait();
        }
        if (sfStart.isPasswordEnteredCorrectly()) setPasswordEnteredCorrectly(true);
    }

    public void stop() {
        SFStop mainStop = new SFStop();
        mainStop.stop();
        // Отключение от сервера БД
        daoMySql.Disconnect(connection);

    }

//    public void connectingToDataBase(ArrayList<User> users){
//        final int minCapacity = 1_000_00;
//        users.ensureCapacity(minCapacity);
//        for (Integer i = 0; i < minCapacity; i++) {
//            users.add(new User(i, "Петя()" + i, Integer.toString(i)));
//        }
//
//    }

    public SF(DialogWindows dialogWindows) throws InvocationTargetException, InterruptedException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        /* Пусть наш GUI будет в стиле ОС */
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        LOGGER.info(SF_VERSION);
        LOGGER.info(LOGGER_START);
        connectingDataBase = false;
        PasswordEnteredCorrectly = false;
        loadSqlExecuteFromFile = false;
        stringSqlExecuteFromFile = "";
        this.dialogWindows = dialogWindows;
        System.out.println(LOGGER_START);
    }
}
