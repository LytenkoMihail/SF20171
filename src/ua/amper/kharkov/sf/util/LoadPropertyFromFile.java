package ua.amper.kharkov.sf.util;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.SFConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadPropertyFromFile {
    private static final Logger LOGGER = Logger.getLogger(LoadPropertyFromFile.class);
    private boolean isLoadPropertyFile;

    public boolean isLoadPropertyFile() {
        return isLoadPropertyFile;
    }

    private void setLoadPropertyFile(boolean loadPropertyFile) {
        isLoadPropertyFile = loadPropertyFile;
    }

    private Properties properties = new Properties();
    private InputStream fileInputStream;


    public String getProperty(String nameProperty) {

        if (properties!=null) {
            if (isLoadPropertyFile()) {
//                System.out.printf("777777777777"+nameProperty);
//                String S = properties.getProperty((String)nameProperty,SFConstants.SF_PROPERTIES_FILE_NOT_OPEN_PROPERTIES);
//                System.out.printf(S);
                return  properties.getProperty(nameProperty,SFConstants.SF_PROPERTIES_FILE_NOT_OPEN_PROPERTIES);
//                return properties.getProperty(nameProperty).toString();
            } else {
                return SFConstants.SF_PROPERTIES_FILE_NOT_OPEN_PROPERTIES;
            }
        }
        return SFConstants.SF_PROPERTIES_FILE_NOT_OPEN_PROPERTIES+"----------------------";
    }

    public LoadPropertyFromFile(String fileNamePropetries) {
        LOGGER.info(fileNamePropetries);
        setLoadPropertyFile(false);
//        Properties properties = new Properties();
        //обращаемся к файлу и получаем данные
        fileInputStream = getClass().getClassLoader().getResourceAsStream(fileNamePropetries);
        if (fileInputStream != null) {
            setLoadPropertyFile(true);
            try {
                properties.load(fileInputStream);
                fileInputStream.close();
            } catch (IOException e) {
                LOGGER.error(SFConstants.LOGGER_ERROR_NOT_OPEN_FILE + fileNamePropetries);
            }
        } else {
            LOGGER.error(SFConstants.LOGGER_ERROR_NOT_OPEN_FILE + fileNamePropetries);
        }
    }

}
