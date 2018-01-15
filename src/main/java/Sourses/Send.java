package Sourses;

import Input.ParamManager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс для формирования и последующей отправки писем
 *
 * @author Pavlov
 */
public class Send {

    private SendEmail se;

    /**
     * Метод отправки письма
     *
     * @param toAddres 1ый параметр - адрес на который следует отправить письмо
     * @param thema_mail 2ой параметр - тема письма
     * @param txt_mail 3ий параметр - сообщение письма
     * @param file1 4ый параметр - файл общего вложения
     * @param file2 5ый параметро - файл уникального вложения
     */
    public void send(String toAddres, String thema_mail, String txt_mail, String file1, String file2) {
        //Получаем настройки почты из файла с настройками
        try {
            ParamManager pm = new ParamManager("./src/main/resources/settings");
            SendEmail.SMTP_SERVER = pm.readString("mail", "server", "0");
            SendEmail.SMTP_Port = pm.readString("mail", "port", "0");
            SendEmail.EMAIL_FROM = pm.readString("mail", "mail", "0");
            SendEmail.SMTP_AUTH_USER = pm.readString("mail", "mail", "0");
            SendEmail.SMTP_AUTH_PWD = pm.readString("mail", "password", "0");
            SendEmail.REPLY_TO = pm.readString("mail", "mail", "0");

            SendEmail.FILE_PATH = file1;
            SendEmail.FILE_PATH2 = file2;

        } catch (Exception ex) {
            Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Отправка сообщения
        se = new SendEmail(toAddres, thema_mail);
        se.sendMessage(txt_mail);
    }
}
