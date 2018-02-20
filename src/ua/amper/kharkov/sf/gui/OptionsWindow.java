package ua.amper.kharkov.sf.gui;

/**
 * Created by amper on 24.05.2017.
 */
public class OptionsWindow {
    private int X;
    private int Y;
    private int Width;
    private int Height;
    private String Title;
    private String FileImageIconName;

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getFileImageIconName() {
        return FileImageIconName;
    }

    public void setFileImageIconName(String fileImageIconName) {
        FileImageIconName = fileImageIconName;
    }

    public OptionsWindow() {
        this.X = 0;
        this.Y = 0;
        this.Height = 0;
        this.Width = 0;
        this.Title = "";
        this.FileImageIconName = "";
    }

    public OptionsWindow(int X, int Y, int Width, int Height, String Title, String FileImageIconName) {
        this.X = X;
        this.Y = Y;
        this.Width = Width;
        this.Height = Height;
        this.Title = Title;
        this.FileImageIconName = FileImageIconName;

    }
}
