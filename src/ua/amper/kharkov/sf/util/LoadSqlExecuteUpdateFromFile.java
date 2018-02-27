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
    private String fileNameSqlExecuteUpdate;

    private String getFileNameSqlExecuteUpdate() {
        return fileNameSqlExecuteUpdate;
    }

    private void setFileNameSqlExecuteUpdate(String fileNameSqlExecuteUpdate) {
        this.fileNameSqlExecuteUpdate = fileNameSqlExecuteUpdate;
    }


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

    private void openAndLoadSqlExecuteUpdateFromFil(String fileNameSql) throws IOException {
        setLoadSqlExecuteUpdateFromFile(false);
        LOGGER.info(getFileNameSqlExecuteUpdate());
        fileInputStream = getClass().getClassLoader().getResourceAsStream(getFileNameSqlExecuteUpdate());
        if (fileInputStream != null) {
            loadSqlExecuteToString(fileInputStream, getFileNameSqlExecuteUpdate());
            setLoadSqlExecuteUpdateFromFile(true);
        } else {
            LOGGER.error(SF_SQL_FILE_NOT_OPEN + getFileNameSqlExecuteUpdate());
        }

    }

    public LoadSqlExecuteUpdateFromFile(String fileNameSql) throws IOException {

        setFileNameSqlExecuteUpdate(fileNameSql);
        openAndLoadSqlExecuteUpdateFromFil(fileNameSql);
    }

    public LoadSqlExecuteUpdateFromFile() throws IOException {
        openAndLoadSqlExecuteUpdateFromFil(getFileNameSqlExecuteUpdate());

    }

    private void loadSqlExecuteToString(InputStream fileInputStream, String fileNameSqlExecuteUpdate) throws IOException {
        try {
            byte[] str = new byte[fileInputStream.available()];
            fileInputStream.read(str);
            String text = new String(str);
            setStringSqlExecute(text);

        } catch (IOException e) {
            LOGGER.error(SF_SQL_FILE_NOT_OPEN + fileNameSqlExecuteUpdate);

        }

    }
}
