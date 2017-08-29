package ua.amper.kharkov.sf.util;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.Main;
import ua.amper.kharkov.sf.SFConstants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static ua.amper.kharkov.sf.SFConstants.LOGGER_ERROR_NOT_OPEN_FILE;

public class LoadPropertyFromFile {
    private static final Logger LOGGER = Logger.getLogger(LoadPropertyFromFile.class);
    private boolean isLoadPropertyFile;

    public boolean isLoadPropertyFile() {
        return isLoadPropertyFile;
    }

    private void setLoadPropertyFile(boolean loadPropertyFile) {
        isLoadPropertyFile = loadPropertyFile;
    }

    private Properties properties;
    private InputStream fileInputStream;

    public LoadPropertyFromFile(String fileNamePropetries) {
        LOGGER.info(SFConstants.LOGGER_LOADPROPERTYFROMFILE);

        setLoadPropertyFile(false);
        Properties properties = new Properties();

        //обращаемся к файлу и получаем данные
        fileInputStream = getClass().getClassLoader().getResourceAsStream(fileNamePropetries);
        if (fileInputStream != null) {
            setLoadPropertyFile(true);
        } else {
            LOGGER.error(SFConstants.LOGGER_ERROR_NOT_OPEN_FILE + fileNamePropetries);
        }
    }

}
