package ua.amper.kharkov.sf;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.dao.User;
import ua.amper.kharkov.sf.gui.*;
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

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        /* Пусть наш GUI будет в стиле ОС */
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        if (args.length == 0) {

            SF sf = new SF();
        } else {

        }

        SFStop mainStop = new SFStop();
    }

}
