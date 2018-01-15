package Sourses;

/**
 * Класс управления рассылками
 *
 * @author Pavlov
 */
public class ControlPanel {
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //*****     ВЛОЖЕНИЯ       *******
    //Настройка вложений
    //Расширение для уникальных вложений (Если поставить null, то 1ое вложение отправлено не будет)
    private final String extension = null;
    //Общее вложение для всех адресатов (Если поставить null, то 2ое вложение отправлено не будет)
    private final String file1attach = "vipmop.pdf";
    //Имя для первого вложения, указывать с расширением (Как будет отображаться у получателей)
    private final String nameForFile1 = "Обоснования.pdf";
    //Имя для второго вложения, указывать с расширением (Как будет отображаться у получателей)
    private final String nameForFile2 = "Обоснования.pdf";  //Уникальное вложение
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //*****     СООБЩЕНИЯ       *******
    //Настройки сообщения для отправки
    //Тема сообщения
    private final String themeMess = "Для бухгалтерии. Обоснования доплаты в КФ ОДО";
    //Текст письма
    private final String txtMess = "С уважением, НП МОП(СРО)";
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   
    //*****     ОТЧЕТЫ       *******
    //Адрес для отправки отчетов
    private final String replyMail = "reply@mail.ru";
    //Тема письма для отчета
    private final String themeReport = "Отчет по отправке обоснований МОП 06-07-2017 ";
    //Имя файла, в который производится запись с отчетом о рассылке
    private final String nameFileReport = "obosn_06-07-2017";
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //*****     НАСТРОЙКИ ПАУЗЫ       *******
    //Через сколько писем после отправки спать
    private final int countToSleep = 20;
    //Сколько спать в секундах
    private final int timeToSleep = 120;
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    

//************************************************************************************************************************************    
    //Формирование имени файла с отчетом о рассылке
    private final String SOFTNAME = "SendPAV_";
    private final String EXTFORFILEREPORT = ".doc";
    private final String fileReport = SOFTNAME + nameFileReport + EXTFORFILEREPORT;

    /**
     * Метод начала рассылки сообщений
     */
    public void sendMessages() {
        CreateMessages cmessage = new CreateMessages();
        cmessage.SendMessages();
    }

    /**
     * Получение расширения файла для уникальных вложений
     * @return  расширение (строка)
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Получить вложение, общее для всех адресатов
     * @return  имя файла вложения (строка)
     */
    public String getFile1attach() {
        return file1attach;
    }

    /**
     * Получить тему сообщения
     * @return  тема сообщения (строка)
     */
    public String getThemeMess() {
        return themeMess;
    }

    /**
     * Получить сообщение письма
     * @return  сообщение (строка)
     */
    public String getTxtMess() {
        return txtMess;
    }

    /**
     * Получить тему отчета
     * @return  тема отчета (строка)
     */
    public String getThemeReport() {
        return themeReport;
    }

    /**
     * Получить имя файла для формирования файла с отчетом о рассылке
     * @return  Имя файла (строка)
     */
    public String getFileReport() {
        return fileReport;
    }

    /**
     * Получить время паузы во время рассылки
     * @return  время в сек. (целочисленное значение)
     */
    public int getTimeToSleep() {
        return timeToSleep;
    }

    /**
     * Получить колличество отправленных писем, после которых будет пауза
     * @return  колличество писем (целочисленное значение)
     */
    public int getCountToSleep() {
        return countToSleep;
    }

    /**
     * Получить имя для первого вложения (как видно у получателей письма)
     * @return  Имя (строка)
     */
    public String getNameForFile1() {
        return nameForFile1;
    }

    /**
     * Получить имя для второго вложения (как видно у получателей письма)
     * @return  Имя (строка)
     */
    public String getNameForFile2() {
        return nameForFile2;
    }

    /**
     * Получить адрес почты, на которую будут выставленны отчеты
     * @return  электронный адрес (строка)
     */
    public String getReplyMail() {
        return replyMail;
    }

}
