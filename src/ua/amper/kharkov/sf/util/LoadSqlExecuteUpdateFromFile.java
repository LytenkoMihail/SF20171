package ua.amper.kharkov.sf.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

import static ua.amper.kharkov.sf.SFConstants.SF_SQL_FILE_NOT_OPEN;

public class LoadSqlExecuteUpdateFromFile {
    private static final Logger LOGGER = Logger.getLogger(LoadSqlExecuteUpdateFromFile.class);
    private boolean isLoadSqlExecuteUpdateFromFile;
    private InputStream fileInputStream;
    private String stringSqlExecute;

    public boolean isLoadSqlExecuteUpdateFromFile() {
        return isLoadSqlExecuteUpdateFromFile;
    }

    private void setLoadSqlExecuteUpdateFromFile(boolean loadSqlExecuteUpdateFromFile) {
        isLoadSqlExecuteUpdateFromFile = loadSqlExecuteUpdateFromFile;
    }

    public String getStringSqlExecute() {
        return stringSqlExecute;
    }

    private void setStringSqlExecute(String stringSqlExecute) {
        this.stringSqlExecute = stringSqlExecute;
    }


    public LoadSqlExecuteUpdateFromFile(String fileNameSqlExecuteUpdate) throws IOException {
        setLoadSqlExecuteUpdateFromFile(false);
        LOGGER.info(fileNameSqlExecuteUpdate);
        fileInputStream = getClass().getClassLoader().getResourceAsStream(fileNameSqlExecuteUpdate);
        if (fileInputStream != null)
        {
            loadSqlExecuteToString(fileInputStream, fileNameSqlExecuteUpdate);
        } else {
            LOGGER.error(SF_SQL_FILE_NOT_OPEN + fileNameSqlExecuteUpdate);
        }
    }

    private void loadSqlExecuteToString(InputStream fileInputStream, String fileNameSqlExecuteUpdate) throws IOException {
        try {
            byte[] str = new byte[fileInputStream.available()];
            fileInputStream.read(str);
            String text = new String(str);
            setStringSqlExecute(text);
            setLoadSqlExecuteUpdateFromFile(true);
        } catch (IOException e) {
            LOGGER.error(SF_SQL_FILE_NOT_OPEN + fileNameSqlExecuteUpdate);

        }

    }
}
