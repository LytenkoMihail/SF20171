package ua.amper.kharkov.sf;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.dao.User;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


/**
 * Created by amper on 23.05.2017.
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {

        ArrayList<User> users = new ArrayList<>();
        User authorizedUser = new User();

        if (args.length == 0) {

            SF sF = new SF();
            sF.connectingToDataBase(users);
            if (sF.isConnectingDataBase() == true) {
                sF.start(users,authorizedUser);
            }
        }
        SFStop mainStop = new SFStop();
    }

}
