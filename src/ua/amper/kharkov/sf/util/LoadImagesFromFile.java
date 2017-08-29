package ua.amper.kharkov.sf.util;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.SFConstants;
import ua.amper.kharkov.sf.gui.GetUserPasswordWindow;

import javax.swing.*;
import java.awt.*;

//загрузка картинки из файла в Image
public class LoadImagesFromFile {
    private static final Logger LOGGER = Logger.getLogger(LoadImagesFromFile.class);
    private Image UploadedImage;

    public Image getUploadedImage() {
        return UploadedImage;
    }

    private void setUploadedImage(Image uploadedImage) {
        UploadedImage = uploadedImage;
    }

    //    проверка загружен ли файл с картинкой
    public boolean isUploadedImage() {
        if ((getUploadedImage() == null)) {
            return false;
        } else {
            return true;
        }
    }

    public LoadImagesFromFile(String FileImageIconName) {
        LOGGER.info(SFConstants.LOGGER_LOADIMAGESFROMFILE);
        try {
            setUploadedImage(new ImageIcon(getClass().getClassLoader().getResource(FileImageIconName)).getImage());

        } catch (Exception e) {
            setUploadedImage(null);
            LOGGER.error(SFConstants.LOGGER_ERROR_NOT_OPEN_FILE+FileImageIconName);
        }
    }
}
