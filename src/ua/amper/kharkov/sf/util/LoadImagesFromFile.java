package ua.amper.kharkov.sf.util;

import ua.amper.kharkov.sf.SFConstants;

import javax.swing.*;
import java.awt.*;

//загрузка картинки из файла в Image
public class LoadImagesFromFile {
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
        try {
            setUploadedImage(new ImageIcon(getClass().getClassLoader().getResource(FileImageIconName)).getImage());
        } catch (Exception e) {
            setUploadedImage(null);
        }
    }
}
