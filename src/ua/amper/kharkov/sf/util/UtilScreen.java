package ua.amper.kharkov.sf.util;

import javax.swing.*;
import java.awt.*;

import static ua.amper.kharkov.sf.SFConstants.*;

/**
 * Created by amper on 23.05.2017.
 */
public final class UtilScreen {
    private int Width;//ширина
    private int Height;//высота


    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        this.Width = width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        this.Height = height;
    }


    public UtilScreen() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setHeight(screenSize.height);
        setWidth(screenSize.width);
        //        Для быстрой локализации диалогов
        UIManager.put("OptionPane.yesButtonText", BUTTON_YES);
        UIManager.put("OptionPane.noButtonText", BUTTON_NO);
        UIManager.put("OptionPane.cancelButtonText", BUTTON_CANCEL);
        UIManager.put("OptionPane.okButtonText", BUTTON_OK);

    }
}
