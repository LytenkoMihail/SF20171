package ua.amper.kharkov.sf.util;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public LoadPropertyFromFile(String filePropetries) {
        setPropertyFile(false);
        Properties properties = new Properties();
        try {
            //обращаемся к файлу и получаем данные
//            fileInputStream = new FileInputStream(getClass().getClassLoader().getResource(filePropetries).getPath());
            fileInputStream = new FileInputStream(getClass().getClassLoader().getResource(filePropetries).getFile());
            String s = getClass().getClassLoader().getResource(filePropetries).getFile();

//            fileInputStream = new FileInputStream(filePropetries);
            properties.load(fileInputStream);
            System.out.println(s+"=========="+properties.getProperty("USER_NAME"));
        } catch (FileNotFoundException e) {
            System.out.println(LOGGER_ERROR_NOT_OPEN_FILE + ":" + filePropetries);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(LOGGER_ERROR_NOT_OPEN_FILE + ":" + filePropetries);
            e.printStackTrace();
        }
    }

}
