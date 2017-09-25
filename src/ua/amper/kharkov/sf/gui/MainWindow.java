package ua.amper.kharkov.sf.gui;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.SFConstants;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static ua.amper.kharkov.sf.SFConstants.SF_EXIT_THE_PROGRAM;

/**
 * Created by amper on 23.05.2017.
 */
public class MainWindow extends Windows {
    private static final Logger LOGGER = Logger.getLogger(MainWindow.class);
private void exitFromProgram() {
    addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            //потверждение выхода из программы
            int res = JOptionPane.showConfirmDialog(e.getWindow(), SF_EXIT_THE_PROGRAM, SFConstants.SF_VERSION, JOptionPane.YES_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                e.getWindow().setVisible(false);
                e.getWindow().dispose();
                return;
            }
        }
    });
}
    public MainWindow(OptionsWindow WO) {
        super(WO.getFileImageIconName(), WO.getX(), WO.getY(), WO.getWidth(), WO.getHeight());
        LOGGER.info(SFConstants.LOGGER_MAINWINDOW);
        setTitle(WO.getTitle());
        setExtendedState(Windows.MAXIMIZED_BOTH);
        exitFromProgram();


    }
}
