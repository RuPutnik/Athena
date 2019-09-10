package ru.putnik.athena.ciphers;

import javafx.scene.control.Alert;
import ru.putnik.athena.AthenaAlerts;

import java.util.Random;

public class TrytCipher extends Cipher {
    public TrytCipher(int numberCipher){
        super(numberCipher);
        nameCipher="Шифр Тритемиуса";
        nameFileInfo="tritemiusCipher.txt";
    }
    @Override
    public String crypt(String text, String key, int direction) {
        StringBuilder resultText= new StringBuilder();
        int digitKey=Integer.parseInt(key);
        int b=0;
        int c=0;

        for (int a=0;a<text.length();a++){
            if(String.valueOf(text.charAt(a)).equals("\n")){
                resultText.append("\n");
            }else {
                if (c > 30) b = 0;
                b = (int) Math.sqrt(c);
                resultText.append(cryptSymbol(text.charAt(a), digitKey + b, direction));
                c++;
            }
        }

        return resultText.toString();
    }

    @Override
    public boolean checkKey(String key) {
        if(key==null||key.equals("")){
            AthenaAlerts.callAlert(Alert.AlertType.WARNING,"Ошибка","Ошибка шифрования","Поля ключа пусто!",false);
            return false;
        }
        try {
            Integer.parseInt(key);
            return true;
        }catch (NumberFormatException e){
            e.printStackTrace();
            AthenaAlerts.callAlert(Alert.AlertType.WARNING,"Ошибка","Ошибка шифрования","Используемый ключ содержит недопустимые символы для данного шифра!",false);
            return false;
        }
    }

    @Override
    public String generateKey() {
        int randomKey=new Random().nextInt(20);
        return String.valueOf(randomKey);
    }
}
