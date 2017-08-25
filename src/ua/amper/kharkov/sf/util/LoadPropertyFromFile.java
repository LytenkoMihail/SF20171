package ua.amper.kharkov.sf.util;

import com.sun.deploy.util.SyncFileAccess;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static ua.amper.kharkov.sf.SFConstants.LOGGER_ERROR_NOT_OPEN_FILE;

public class LoadPropertyFromFile {
    boolean PropertyFile;

    public boolean isPropertyFile() {
        return PropertyFile;
    }

    private void setPropertyFile(boolean propertyFile) {
        PropertyFile = propertyFile;
    }

    private Properties properties;
    private FileInputStream fileInputStream;

    public LoadPropertyFromFile(String fileNamePropetries) {
        setPropertyFile(false);
        Properties properties = new Properties();
        try {
            //обращаемся к файлу и получаем данные
            InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream(fileNamePropetries);
            if (fileInputStream != null) {
                properties.load(fileInputStream);
                System.out.println(fileInputStream.toString()+"=========="+properties.getProperty("USER_NAME"));
            }


        } catch (FileNotFoundException e) {
            System.out.println(LOGGER_ERROR_NOT_OPEN_FILE + ":" + fileNamePropetries);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(LOGGER_ERROR_NOT_OPEN_FILE + ":" + fileNamePropetries);
            e.printStackTrace();
        }
    }

}
