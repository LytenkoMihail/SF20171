package ua.amper.kharkov.sf.gui;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.SFConstants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Created by amper on 23.05.2017.
 */
public class GetUserPasswordWindowFrame extends Windows {
    private static final Logger LOGGER = Logger.getLogger(GetUserPasswordWindowFrame.class);


    public GetUserPasswordWindowFrame(OptionsWindow WOGUPW) {

        super(WOGUPW.getFileImageIconName(), WOGUPW.getX(), WOGUPW.getX(), WOGUPW.getWidth(), WOGUPW.getHeight());
        LOGGER.info(SFConstants.LOGGER_GETUSERPASSWORDWINDOW);
        setTitle(WOGUPW.getTitle());


        String[] elements = new String[]{"Вася", "Петя"};
        JTextField loginField;
        JPasswordField passwordField;
        JComboBox combo = new JComboBox(elements);
        combo.setSelectedIndex(-1);
// Настраиваем первую горизонтальную панель (для ввода логина)
        Box box1 = Box.createHorizontalBox();
        JLabel loginLabel = new JLabel(SFConstants.MSG_USER_NAME);
        box1.add(loginLabel);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(combo);
// Настраиваем вторую горизонтальную панель (для ввода пароля)
        Box box2 = Box.createHorizontalBox();
        JLabel passwordLabel = new JLabel(SFConstants.MSG_USER_PASSWORD);
//        длина поля для ввода пароля количество знаков
        passwordField = new JPasswordField(20);
        box2.add(passwordLabel);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(passwordField);
// Настраиваем третью горизонтальную панель (с кнопками)
        Box box3 = Box.createHorizontalBox();
        JButton ok = new JButton(SFConstants.MSG_BUTTON_YES);
        JButton cancel = new JButton(SFConstants.MSG_BUTTON_CANCEL);
        box3.add(Box.createHorizontalGlue());
        box3.add(ok);
        box3.add(Box.createHorizontalStrut(12));
        box3.add(cancel);
// Уточняем размеры компонентов
        passwordLabel.setPreferredSize(loginLabel.getPreferredSize());
// Размещаем три горизонтальные панели на одной вертикальной
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
        mainBox.add(box1);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(box2);
        mainBox.add(Box.createVerticalStrut(17));
        mainBox.add(box3);
        setContentPane(mainBox);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        //без рамки и заголовка
//        setUndecorated(true);

    }
}