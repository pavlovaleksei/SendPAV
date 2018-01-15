package Sourses;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Класс отправки сообщения
 *
 * @author Pavlov
 */
public class SendEmail {

    private Message message = null;
    static String SMTP_AUTH_USER = null;
    static String SMTP_AUTH_PWD = null;
    static String EMAIL_FROM = null;
    static String SMTP_SERVER = null;
    static String SMTP_Port = null;
    static String REPLY_TO = null;
    static String FILE_PATH = null;
    static String FILE_PATH2 = null;

    static String toAddres;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public String getToAddres() {
        return toAddres;
    }

    /**
     * Метод отправки письма
     *
     * @param emailTo адрес для отправки сообщения
     * @param thema тема сообщения
     */
    public SendEmail(final String emailTo, final String thema) {
        toAddres = emailTo;
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_SERVER);
        properties.put("mail.smtp.port", SMTP_Port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        try {
            Authenticator auth = new EmailAuthenticator(SMTP_AUTH_USER, SMTP_AUTH_PWD);
            Session session = Session.getDefaultInstance(properties, auth);
            session.setDebug(false);

            InternetAddress email_from = new InternetAddress(EMAIL_FROM);
            InternetAddress email_to = new InternetAddress(emailTo);
            InternetAddress reply_to = (REPLY_TO != null)
                    ? new InternetAddress(REPLY_TO) : null;
            message = new MimeMessage(session);

            message.setFrom(email_from);
            message.setRecipient(Message.RecipientType.TO, email_to);
            message.setSubject(thema);
            if (reply_to != null) {
                message.setReplyTo(new Address[]{reply_to});
            }
        } catch (AddressException e) {
            System.err.println(e.getMessage());
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * Метод добавдения текста письма, файлов вложений
     *
     * @param text Принимает текст письма
     * @return Возвращает логическое значение
     */
    public boolean sendMessage(final String text) {
        ControlPanel cpanel = new ControlPanel();

        boolean result = false;
        try {
            // Содержимое сообщения
            Multipart mmp = new MimeMultipart();

            // Вложение 1 файла в сообщение
            MimeBodyPart mbr_attach1 = new MimeBodyPart();
            DataSource source = new FileDataSource(FILE_PATH);

            //Если первое вложение есть - отправляем
            if (cpanel.getFile1attach() != null) {
                mbr_attach1.setDataHandler(new DataHandler(source));
                mbr_attach1.setFileName(cpanel.getNameForFile1());
                mmp.addBodyPart(mbr_attach1);
            }

            // Вложение 2 файла в сообщение
            MimeBodyPart mbr_attach2 = new MimeBodyPart();
            source = new FileDataSource(FILE_PATH2);

            //Если второе вложение есть - отправляем
            if (cpanel.getExtension() != null) {
                mbr_attach2.setDataHandler(new DataHandler(source));
                mbr_attach2.setFileName(cpanel.getNameForFile2());
                mmp.addBodyPart(mbr_attach2);
            }

            //Текст сообщения
            MimeBodyPart mbr_text_of_message = new MimeBodyPart();
            mbr_text_of_message.setContent(text, "text/plain; charset=utf-8");
            mmp.addBodyPart(mbr_text_of_message);

            // Определение контента сообщения
            message.setContent(mmp);
            // Отправка сообщения 
            try {
                Transport.send(message);

                System.out.println("Сообщение отправлено по адресу - " + toAddres);

                System.err.println("Сообщение отправлено по адресу - " + toAddres);

            } catch (MessagingException e) {

                System.out.println("!   Сообщение НЕ ОТПРАВЛЕНО по адресу - " + toAddres);

                System.err.println("!   Сообщение НЕ ОТПРАВЛЕНО по адресу - " + toAddres);
            }

            result = true;
        } catch (MessagingException e) {
            // Ошибка отправки сообщения
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
