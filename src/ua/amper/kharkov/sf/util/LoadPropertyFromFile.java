package ua.amper.kharkov.sf.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static ua.amper.kharkov.sf.SFConstants.SF_PROPERTIES_FILE_NOT_OPEN_PROPERTIES;
import static ua.amper.kharkov.sf.SFConstants.SF_PROPERTIES_NOT__FIND;

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
        String returnString = SF_PROPERTIES_FILE_NOT_OPEN_PROPERTIES;
        if (properties!=null) {
            if (isLoadPropertyFile()) {
                returnString=properties.getProperty(nameProperty,nameProperty+"  "+ SF_PROPERTIES_NOT__FIND );
//                return  properties.getProperty(nameProperty, SF_PROPERTIES_FILE_NOT_OPEN_PROPERTIES);
            }
        }
        return returnString;
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
                LOGGER.error(SF_PROPERTIES_FILE_NOT_OPEN_PROPERTIES + fileNamePropetries);
            }
        } else {
            LOGGER.error(SF_PROPERTIES_FILE_NOT_OPEN_PROPERTIES + fileNamePropetries);
        }
    }

}
