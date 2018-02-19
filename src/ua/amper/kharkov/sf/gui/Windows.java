package ua.amper.kharkov.sf.gui;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.SFConstants;
import ua.amper.kharkov.sf.util.LoadImagesFromFile;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by amper on 23.05.2017.
 */
public class Windows extends JFrame {

    private static final Logger LOGGER = Logger.getLogger(Windows.class);
    protected void frameInit(String FileImageIconName) {
        LoadImagesFromFile loadImagesFromFile = new LoadImagesFromFile(FileImageIconName);
        if (loadImagesFromFile.isUploadedImage()) {
            setIconImage(loadImagesFromFile.getUploadedImage());
        }

    }
    public Windows(String FileImageIconName, int X, int Y, int Width, int Height) {
        super();
        frameInit(FileImageIconName);
        setBounds(X, Y, Width, Height);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    public Windows(String FileImageIconName) {
        super();
        frameInit(FileImageIconName);
        setBounds(0, 0, 0, 0);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

}
