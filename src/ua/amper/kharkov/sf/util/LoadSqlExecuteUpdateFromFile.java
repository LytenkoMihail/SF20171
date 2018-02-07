package ua.amper.kharkov.sf.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static ua.amper.kharkov.sf.SFConstants.SF_PROPERTIES_FILE_NOT_OPEN;
import static ua.amper.kharkov.sf.SFConstants.SF_SQL_FILE_NOT_OPEN;

public class LoadSqlExecuteUpdateFromFile {
    private static final Logger LOGGER = Logger.getLogger(LoadSqlExecuteUpdateFromFile.class);
    private boolean isLoadSqlExecuteUpdateFromFil;
    private InputStream fileInputStream;
    private String stringSqlExecute;

    public boolean isLoadSqlExecuteUpdateFromFil() {
        return isLoadSqlExecuteUpdateFromFil;
    }

    private void setLoadSqlExecuteUpdateFromFil(boolean loadSqlExecuteUpdateFromFil) {
        isLoadSqlExecuteUpdateFromFil = loadSqlExecuteUpdateFromFil;
    }

    public String getStringSqlExecute() {
        return stringSqlExecute;
    }

    private void setStringSqlExecute(String stringSqlExecute) {
        this.stringSqlExecute = stringSqlExecute;
    }



    public LoadSqlExecuteUpdateFromFile (String fileNameSqlExecuteUpdate) throws IOException   {
        setLoadSqlExecuteUpdateFromFil(false);
        LOGGER.info(fileNameSqlExecuteUpdate);
        fileInputStream = getClass().getClassLoader().getResourceAsStream(fileNameSqlExecuteUpdate);
        if (fileInputStream != null)
//            try
            {
            loadSqlExecuteToString(fileInputStream,fileNameSqlExecuteUpdate);

            //            } catch (IOException e) {
            //                LOGGER.error(SF_SQL_FILE_NOT_OPEN + fileNameSqlExecuteUpdate);
        }
        else {
            LOGGER.error(SF_SQL_FILE_NOT_OPEN + fileNameSqlExecuteUpdate);
        }
    }

    private void loadSqlExecuteToString(InputStream fileInputStream,String fileNameSqlExecuteUpdate) throws IOException {
        try {
            byte[] str = new byte[fileInputStream.available()];
            fileInputStream.read(str);
            String text = new String(str);
            setStringSqlExecute(text);
            setLoadSqlExecuteUpdateFromFil(true);
        } catch (IOException e) {
            LOGGER.error(SF_SQL_FILE_NOT_OPEN + fileNameSqlExecuteUpdate);

        }

    }
}
