package ua.amper.kharkov.sf.gui;

import javax.swing.*;

public class DialogWindows {
    private String title;

    private String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public void DialogMessage(String Message) {
        JOptionPane.showMessageDialog(null,
                Message, getTitle(), JOptionPane.PLAIN_MESSAGE);
    }

    public DialogWindows(String Title) {
        setTitle(Title);
    }

    public void DialogMessageWarning(String Message) {
        JOptionPane.showMessageDialog(null,
                Message, getTitle(), JOptionPane.WARNING_MESSAGE);
    }

    public void DialogMessageError(String Message) {
        JOptionPane.showMessageDialog(null,
                Message, getTitle(), JOptionPane.ERROR_MESSAGE);
    }
}
