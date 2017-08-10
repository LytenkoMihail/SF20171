package ua.amper.kharkov.sf.util;

public class LoadPropertyFromFile {
    boolean PropertyFile;

    public boolean isPropertyFile() {
        return PropertyFile;
    }

    private void setPropertyFile(boolean propertyFile) {
        PropertyFile = propertyFile;
    }

    public LoadPropertyFromFile(String FileName) {
        setPropertyFile(false);
    }
}
