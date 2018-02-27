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

import static javax.management.remote.JMXConnectionNotification.FAILED;
import static ua.amper.kharkov.sf.SFConstants.*;


/**
 * Created by amper on 23.05.2017.
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) throws InvocationTargetException, InterruptedException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException, SQLException {

        ArrayList<User> users = new ArrayList<>();
        User authorizedUser = new User();
        String stringSqlExecute = "";
        boolean resultQueryExecution;
        ResultSet resultSetForUsers;
        String URL = "jdbc:mysql://localhost:3306/sfbase";
        String host = "localhost";
        String database = "sfbase";
        int port = 3306;
        String NAMEUSER = "root";
        String PASSWORD = "root";
        com.mysql.jdbc.Connection connection = null;

        LoadSqlExecuteUpdateFromFile loadSqlExecuteUpdateFromFile = new LoadSqlExecuteUpdateFromFile(SF_RESOURCES_FILE_SQL_SELECT_FROM_USERS);
        DialogWindows dialogWindows = new DialogWindows(SF_VERSION);

        if (loadSqlExecuteUpdateFromFile.isLoadSqlExecuteUpdateFromFile() == true) {
            stringSqlExecute = loadSqlExecuteUpdateFromFile.getStringSqlExecute();
            dialogWindows.DialogMessageError(stringSqlExecute);

        } else ExitingProgramIfErrorOccurs(dialogWindows, SF_SQL_FILE_NOT_OPEN, 0);
        DaoMySql daoMySql = new DaoMySql();
        daoMySql.setURL(host, database, port);
        daoMySql.Connect(NAMEUSER, PASSWORD);
        if (daoMySql.getConnection() != null) {
            connection = daoMySql.getConnection();
            resultQueryExecution = daoMySql.execSQLExecution(stringSqlExecute);
            if (resultQueryExecution == true) {
                resultSetForUsers = daoMySql.getResultSet();
                UserDisassembleResults userDisassembleResults = new UserDisassembleResults(resultSetForUsers, users);
                userDisassembleResults.PrepareListOfUsersFromResults();
                System.out.println(users.toString());
                System.out.println("resultQueryExecution=" + resultQueryExecution);
            } else {
                ExitingProgramIfErrorOccurs(dialogWindows, SF_SQL_EXEC_SQL , 0);
            }

        } else {
            ExitingProgramIfErrorOccurs(dialogWindows, MSG_DB_CONNECTINGDATABASE_ERROR + "0000000000000", 0);
        }

        SF sF = new SF();

//        if(args.length==0){
//                sF.connectingToDataBase(users);
//                if(sF.isConnectingDataBase()==true){
//                sF.loadSqlExecuteFromFile(SF_RESOURCES_FILE_SQL_SELECT_FROM_USERS);
//                if(sF.isLoadSqlExecuteFromFile()==true){
//                stringSqlExecute=sF.loadStringSqlExecuteFromFile();
//
//                sF.start(users,authorizedUser);
//                if(sF.isPasswordEnteredCorrectly()==true){
//                sF.run(authorizedUser);
//                }
//                }
//                }else{
//                dialogWindows.DialogMessageError(MSG_DB_CONNECTINGDATABASE_ERROR);
//                LOGGER.error(SFConstants.MSG_DB_CONNECTINGDATABASE_ERROR);
//
//                }
//                }

        sF.stop();
        // Отключение от сервера БД
        daoMySql.Disconnect(connection);
    }

    private static void ExitingProgramIfErrorOccurs(DialogWindows dialogWindows, String messageError, int status) {
        dialogWindows.DialogMessageError(messageError);
        LOGGER.error(messageError);
        System.exit(status);

    }


}


