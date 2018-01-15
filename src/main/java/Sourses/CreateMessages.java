package Sourses;

import Input.ParseXML;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс формирования сообщений для рассылки
 *
 * @author Pavlov
 */
public class CreateMessages {

    private String TOSEND;
    private String DONTSEND;
    private String MESSAGE;
    private String dontSend2 = "";
    private String dontSend3 = "";
    private String dontSend4 = "";
    private String dontSend5 = "";
    private String mail2 = "";
    private String mail3 = "";
    private String mail4 = "";
    private String mail5 = "";

    //Тема сообщения для получателей
    private String themeSends;
    //Сообщение для получателей
    private String txtMessage;
    //Тема сообщения для отправки отчета о рассылке
    private String themeSendsReport;

    /**
     * Метод установки темы сообщения
     *
     * @param themeSends Принимает тему сообщения (Строка)
     */
    public void setThemeSends(String themeSends) {
        this.themeSends = themeSends;
    }

    /**
     * Метод установки текста сообщения
     *
     * @param txtMessage Принимает текст сообщения (Строка)
     */
    public void setTxtMessage(String txtMessage) {
        this.txtMessage = txtMessage;
    }

    /**
     * Метод установеи темы сообщения для отправки отчета
     *
     * @param themeSendsReport Принимает тему сообщения (Строка)
     */
    public void setThemeSendsReport(String themeSendsReport) {
        this.themeSendsReport = themeSendsReport;
    }

    public String getThemeSends() {
        return themeSends;
    }

    public String getTxtMessage() {
        return txtMessage;
    }

    public String getThemeSendsReport() {
        return themeSendsReport;
    }

    //Список адресов для рассылки
    private List<String> sendToAddres;

    //Через сколько итераций спать
    private int count;

    /**
     * Метод формирования и отправки собщения
     */
    public void SendMessages() {

        ParseXML parse = new ParseXML();
        parse.read();
        Send send = new Send();

        sendToAddres = new ArrayList<>();

        ControlPanel cpanel = new ControlPanel();
        themeSends = cpanel.getThemeMess();
        txtMessage = cpanel.getTxtMess();
        themeSendsReport = cpanel.getThemeReport();

        System.out.println("******** " + themeSends + " " + new Timestamp(System.currentTimeMillis()).toString() + " ********");
        System.out.println("");
        System.err.println("******** " + themeSends + " " + new Timestamp(System.currentTimeMillis()).toString() + " ********");
        System.err.println("");

        count = cpanel.getCountToSleep();

        for (int i = 0; i < parse.getCreateAddr().size(); i++) {
            System.out.println("Отправляется сообщение № " + (i + 1)
                    + " Организации: " + parse.getCreateAddr().get(i).getName()
                    + " ИНН: " + parse.getCreateAddr().get(i).getInn()
            );

            System.err.println("Отправляется сообщение № " + (i + 1)
                    + " Организации: " + parse.getCreateAddr().get(i).getName()
                    + " ИНН: " + parse.getCreateAddr().get(i).getInn()
            );

            try {
                send.send(parse.getCreateAddr().get(i).getAddres1(),
                        themeSends,
                        txtMessage,
                        parse.getCreateAddr().get(i).getFile1(),
                        parse.getCreateAddr().get(i).getFile2()
                );
                sendToAddres.add(parse.getCreateAddr().get(i).getAddres1());
            } catch (Exception e) {
                System.out.println("ОШИБКА В АДРЕСЕ ЭЛЕКТРОННОЙ ПОЧТЫ: " + parse.getCreateAddr().get(i).getAddres1());
                System.err.println("ОШИБКА В АДРЕСЕ ЭЛЕКТРОННОЙ ПОЧТЫ: " + parse.getCreateAddr().get(i).getAddres1());
            }

            if (!parse.getCreateAddr().get(i).getAddres2().equals("0")) {
                try {
                    send.send(parse.getCreateAddr().get(i).getAddres2(),
                            themeSends,
                            txtMessage,
                            parse.getCreateAddr().get(i).getFile1(),
                            parse.getCreateAddr().get(i).getFile2()
                    );
                    sendToAddres.add(parse.getCreateAddr().get(i).getAddres2());
                    mail2 = sendToAddres.get(1);
                    dontSend2 = SendEmail.toAddres;
                } catch (Exception e) {
                    System.out.println("ОШИБКА В АДРЕСЕ ЭЛЕКТРОННОЙ ПОЧТЫ: " + parse.getCreateAddr().get(i).getAddres2());
                    System.err.println("ОШИБКА В АДРЕСЕ ЭЛЕКТРОННОЙ ПОЧТЫ: " + parse.getCreateAddr().get(i).getAddres2());
                }
            }

            if (!parse.getCreateAddr().get(i).getAddres3().equals("0")) {
                try {
                    send.send(parse.getCreateAddr().get(i).getAddres3(),
                            themeSends,
                            txtMessage,
                            parse.getCreateAddr().get(i).getFile1(),
                            parse.getCreateAddr().get(i).getFile2()
                    );
                    sendToAddres.add(parse.getCreateAddr().get(i).getAddres3());
                    mail3 = sendToAddres.get(2);
                    dontSend3 = SendEmail.toAddres;
                } catch (Exception e) {
                    System.out.println("ОШИБКА В АДРЕСЕ ЭЛЕКТРОННОЙ ПОЧТЫ: " + parse.getCreateAddr().get(i).getAddres3());
                    System.err.println("ОШИБКА В АДРЕСЕ ЭЛЕКТРОННОЙ ПОЧТЫ: " + parse.getCreateAddr().get(i).getAddres3());
                }
            }

            if (!parse.getCreateAddr().get(i).getAddres4().equals("0")) {
                try {
                    send.send(parse.getCreateAddr().get(i).getAddres4(),
                            themeSends,
                            txtMessage,
                            parse.getCreateAddr().get(i).getFile1(),
                            parse.getCreateAddr().get(i).getFile2()
                    );
                    sendToAddres.add(parse.getCreateAddr().get(i).getAddres4());
                    mail4 = sendToAddres.get(3);
                    dontSend4 = SendEmail.toAddres;
                } catch (Exception e) {
                    System.out.println("ОШИБКА В АДРЕСЕ ЭЛЕКТРОННОЙ ПОЧТЫ: " + parse.getCreateAddr().get(i).getAddres4());
                    System.err.println("ОШИБКА В АДРЕСЕ ЭЛЕКТРОННОЙ ПОЧТЫ: " + parse.getCreateAddr().get(i).getAddres4());
                }
            }

            if (!parse.getCreateAddr().get(i).getAddres5().equals("0")) {
                try {
                    send.send(parse.getCreateAddr().get(i).getAddres5(),
                            themeSends,
                            txtMessage,
                            parse.getCreateAddr().get(i).getFile1(),
                            parse.getCreateAddr().get(i).getFile2()
                    );
                    sendToAddres.add(parse.getCreateAddr().get(i).getAddres5());
                    mail5 = sendToAddres.get(4);
                    dontSend5 = SendEmail.toAddres;
                } catch (Exception e) {
                    System.out.println("ОШИБКА В АДРЕСЕ ЭЛЕКТРОННОЙ ПОЧТЫ: " + parse.getCreateAddr().get(i).getAddres5());
                    System.err.println("ОШИБКА В АДРЕСЕ ЭЛЕКТРОННОЙ ПОЧТЫ: " + parse.getCreateAddr().get(i).getAddres5());
                }
            }

            MESSAGE = "Сообщение отправлено организации: " + parse.getCreateAddr().get(i).getName()
                    + "\nИНН: " + parse.getCreateAddr().get(i).getInn()
                    + "\nВремя отправления: " + new Timestamp(System.currentTimeMillis()).toString();

            try {
                TOSEND = "Сообщение отправлено по адресам: \n" + sendToAddres.get(0) + "\n"
                        + mail2 + "\n"
                        + mail3 + "\n"
                        + mail4 + "\n"
                        + mail5;
            } catch (Exception e) {
                TOSEND = "Ошибка при отправлении письма организации: "
                        + parse.getCreateAddr().get(i).getName()
                        + " ИНН: "
                        + parse.getCreateAddr().get(i).getInn();
            }

            DONTSEND = "Сообщение не доставлено по адресам: \n" + dontSend2 + "\n"
                    + dontSend3 + "\n"
                    + dontSend4 + "\n"
                    + dontSend5;

            //Формирование и отправки письма для отчета
            send.send(cpanel.getReplyMail(),
                    themeSendsReport + " ИНН: " + parse.getCreateAddr().get(i).getInn(),
                    MESSAGE + "\n\n" + TOSEND + "\n\n" + DONTSEND,
                    parse.getCreateAddr().get(i).getFile1(),
                    parse.getCreateAddr().get(i).getFile2()
            );

            System.out.println("Сообщение успешно отправлено!");
            System.out.println("**********************************************************");

            System.err.println("Сообщение успешно отправлено!");
            System.err.println("**********************************************************");

            sendToAddres.clear();

            if (i == count) {
                System.out.println("Спим - " + cpanel.getTimeToSleep() + "сек. \nВремя начала сна: " + new Timestamp(System.currentTimeMillis()).toString());
                System.err.println("Спим - " + cpanel.getTimeToSleep() + "сек. \nВремя начала сна: " + new Timestamp(System.currentTimeMillis()).toString());

                try {
                    Thread.sleep(cpanel.getTimeToSleep() * 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CreateMessages.class.getName()).log(Level.SEVERE, null, ex);
                }
                count = count + cpanel.getCountToSleep();
                System.out.println("Продолжение рассылки: " + new Timestamp(System.currentTimeMillis()).toString());
                System.err.println("Продолжение рассылки: " + new Timestamp(System.currentTimeMillis()).toString());
                System.out.println("**********************************************************");
                System.err.println("**********************************************************");
            }

        }
        System.out.println("Все сообщения отправлены!");
        System.err.println("Все сообщения отправлены!");
        System.out.println("Рассылка " + themeSends + " завершена в " + new Timestamp(System.currentTimeMillis()).toString());
        System.out.println("");
        System.err.println("Рассылка " + themeSends + " завершена в " + new Timestamp(System.currentTimeMillis()).toString());
        System.err.println("");
        System.out.println("Файл с отчетом о рассылке сформирован по адресу: " + new File("").getAbsolutePath() + "\\" + cpanel.getFileReport());
        System.err.println("Файл с отчетом о рассылке сформирован по адресу: " + new File("").getAbsolutePath() + "\\" + cpanel.getFileReport());
    }

}
