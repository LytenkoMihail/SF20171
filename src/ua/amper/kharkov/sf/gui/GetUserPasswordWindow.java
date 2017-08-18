package ua.amper.kharkov.sf.gui;

import org.apache.log4j.Logger;
import ua.amper.kharkov.sf.SFConstants;
import ua.amper.kharkov.sf.dao.User;
import ua.amper.kharkov.sf.dao.UserSearchPasswordCodeName;
import ua.amper.kharkov.sf.util.LoadImagesFromFile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by amper on 31.05.2017.
 */
public class GetUserPasswordWindow extends JDialog {
    private static final Logger LOGGER = Logger.getLogger(GetUserPasswordWindow.class);
    private String nameUser;

    public String getNameUser() {
        return nameUser;
    }

    private void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    private boolean UserSelected;

    public boolean isUserSelected() {
        return UserSelected;
    }

    public void setUserSelected(boolean userSelected) {
        UserSelected = userSelected;
    }

    private String TitleDialog;


    private String getTitleDialog() {
        return TitleDialog;
    }


    private void setTitleDialog(String titleDialog) {
        TitleDialog = titleDialog;
    }

    public void showDialog() {
        setVisible(true);
    }

    public void hideDialog() {
        dispose();
    }

    private JComboBox combo;
    private JLabel passwordLabel;
    private JLabel loginLabel;
    private JButton ok;
    private JButton cancel;
    private JPasswordField passwordField;
    private UserSearchPasswordCodeName userSearchPasswordCodeName;
    private DialogWindows Dialogwindows;

    public void createDialog(ArrayList<User> users, User user) {
//        String[] elements = new String[]{"Вася", "Петя"};

        combo = new JComboBox();
        if (users.isEmpty() == false) {
            for (int i = 0; i < users.size(); i++) {

                combo.addItem(users.get(i).getName());
            }
        }
        combo.setSelectedIndex(-1);

        // Настраиваем первую горизонтальную панель (для ввода логина)
        Box box1 = Box.createHorizontalBox();
        loginLabel = new JLabel(SFConstants.USER_NAME);
        box1.add(loginLabel);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(combo);
// Настраиваем вторую горизонтальную панель (для ввода пароля)
        Box box2 = Box.createHorizontalBox();
        passwordLabel = new JLabel(SFConstants.USER_PASSWORD);
//        длина поля для ввода пароля количество знаков
        passwordField = new JPasswordField(20);
        passwordField.setEchoChar('*');
        box2.add(passwordLabel);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(passwordField);

// Настраиваем третью горизонтальную панель (с кнопками)
        Box box3 = Box.createHorizontalBox();
        ok = new JButton(SFConstants.BUTTON_YES);
        cancel = new JButton(SFConstants.BUTTON_CANCEL);
        box3.add(Box.createHorizontalGlue());
        box3.add(ok);
        box3.add(Box.createHorizontalStrut(12));
        box3.add(cancel);
// Уточняем размеры компонентов
        passwordLabel.setPreferredSize(loginLabel.getPreferredSize());
//        Размещаем три горизонтальные панели на одной вертикальной
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

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUserSelected(false);
                hideDialog();
            }
        });

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (combo.getSelectedIndex() != -1) {
                    String name = (String) combo.getSelectedItem();
                    String password = new String(passwordField.getPassword());
                    userSearchPasswordCodeName = new UserSearchPasswordCodeName(users, user);
                    userSearchPasswordCodeName.SearchByNameAndPassword(name, password);
                    if (userSearchPasswordCodeName.isSearchPasswordCodeName() == false) {
                        passwordField.setText("");
                        Dialogwindows.DialogMessageError(SFConstants.USER_NAME_PASSWORD_ERROR);
                    } else {
                        setNameUser(name);
                        setUserSelected(true);
                        hideDialog();
                    }
                }
            }
        });
    }

    public GetUserPasswordWindow(OptionsWindow WOGUPW) {
        super((Dialog) null, WOGUPW.getTitle(), true);
        // Устанавливаем поведение формы при закрытии - не закрывать совсем.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setUserSelected(false);
        setTitleDialog(WOGUPW.getTitle());
        LOGGER.info(SFConstants.LOGGER_GETUSERPASSWORDWINDOW);
        Dialogwindows = new DialogWindows(getTitleDialog());
//        загрузка картики
        LoadImagesFromFile liff = new LoadImagesFromFile(WOGUPW.getFileImageIconName());
        if (liff.isUploadedImage()) {
            setIconImage(liff.getUploadedImage());
        }
    }


}
