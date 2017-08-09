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

    public Windows(String FileImageIconName, int X, int Y, int Width, int Height) {
        super();
        setBounds(X, Y, Width, Height);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        LoadImagesFromFile liff = new LoadImagesFromFile(FileImageIconName);
        if (liff.isUploadedImage()) {
            setIconImage(liff.getUploadedImage());
        }
    }

}
