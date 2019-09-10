package ru.putnik.athena.pojo;

/**
 * Создано 10.09.2019 в 19:11
 */
public class Settings {
    private String pathToDataFile;
    private boolean cryptoSpacing;
    private int numberCipher;

    public String getPathToDataFile() {
        return pathToDataFile;
    }

    public void setPathToDataFile(String pathToDataFile) {
        this.pathToDataFile = pathToDataFile;
    }

    public boolean isCryptoSpacing() {
        return cryptoSpacing;
    }

    public void setCryptoSpacing(boolean cryptoSpacing) {
        this.cryptoSpacing = cryptoSpacing;
    }

    public int getNumberCipher() {
        return numberCipher;
    }

    public void setNumberCipher(int numberCipher) {
        this.numberCipher = numberCipher;
    }
}
