package ru.putnik.athena.ciphers;


import ru.putnik.athena.AthenaConstants;
import ru.putnik.athena.Serializator;
import ru.putnik.athena.pojo.Settings;

public abstract class Cipher{
    private Settings settings=Serializator.deserializationSettings(AthenaConstants.PATH_TO_FILE_SETTINGS);

    String nameCipher;
    String nameFileInfo;
    private int numberCipher;

    Cipher(int numberCipher){
        this.numberCipher=numberCipher;
    }

    Cipher() {}
    //шифрование отдельного символа
    public char cryptSymbol(char symbol, int keyOffset, int direction){
            if (symbol == ' ' && !settings.isCryptoSpacing()) {
                return symbol;
            } else {
                int iv=(int) symbol + keyOffset * direction;//integer value
                return (char) (iv);
            }

    }
    public abstract String crypt(String text, String key, int direction);
    public abstract boolean checkKey(String key);
    public abstract String generateKey();

    public String getNameCipher() {
        return nameCipher;
    }
    public String getNameFileInfo() {
        return nameFileInfo;
    }
    public int getNumberCipher() {
        return numberCipher;
    }
    public void setNumberCipher(int numberCipher){
        this.numberCipher=numberCipher;
    }

    @Override
    public String toString() {
        return nameCipher;
    }
}
