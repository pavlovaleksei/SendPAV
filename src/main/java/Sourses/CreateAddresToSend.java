package Sourses;

import java.io.File;

/**
 * Класс для создания объектов "Адресат"
 *
 * @author Pavlov
 */
public class CreateAddresToSend {

    //Идентификатор (должен соответствовать файлу вложению, если вложения посылаются конкретным адресатам)
    private String id = "";
    //1ый адрес для отправки
    private String addres1 = "";
    //2ой адрес для отправки
    private String addres2 = "";
    //3ий адрес для отправки
    private String addres3 = "";
    //4ый адрес для отправки
    private String addres4 = "";
    //5ый адрес для отправки
    private String addres5 = "";
    //ИНН организации
    private String inn = "";
    //Наименование организации
    private String name = "";
    //Путь к каталогу, в котором находятся файлы для рассылки
    private final String filePath = new File("").getAbsolutePath() + "\\attach\\";
    //Файл вложения (постоянный для всех писем)
    private String file1Attach = new ControlPanel().getFile1attach();
    private final String file1 = filePath + file1Attach;
    //Файл вложения для конкретного адресата
    private String file2 = "";
    //Расширение файла вложений
    private String extension = new ControlPanel().getExtension();

    public CreateAddresToSend() {

    }

    /**
     * Конструктор класса
     *
     * @param id Принимает идентификатор
     * @param addres1 1ый адрес
     * @param addres2 2ой адрес
     * @param addres3 3ий адрес
     * @param addres4 4ый адрес
     * @param addres5 5ый адрес
     * @param inn ИНН организации
     * @param name Наименование организации
     */
    public CreateAddresToSend(String id, String addres1, String addres2, String addres3, String addres4, String addres5, String inn, String name) {
        this.id = id;
        this.addres1 = addres1;
        this.addres2 = addres2;
        this.addres3 = addres3;
        this.addres4 = addres4;
        this.addres5 = addres5;
        this.inn = inn;
        this.name = name;
        file2 = filePath + id + extension;
    }

    /**
     * Получение 1ого вложения
     * @return  вложение (строка)
     */
    public String getFile1() {
        return file1;
    }

    /**
     * Получение ИНН организации
     * @return  ИНН (строка)
     */
    public String getInn() {
        return inn;
    }

    /**
     * Получение наименования организации
     * @return  Наименование организации (строка)
     */
    public String getName() {
        return name;
    }
    
    /**
     * Получение 2ого вложения
     * @return  вложение (строка)
     */
    public String getFile2() {
        return file2;
    }

    /**
     * Получение идентификатора. По этому идентификатору, строятся имена файлов - уникальных вложений
     * @return  идентификатор (строка)
     */
    public String getId() {
        return id;
    }

    /**
     * Получение 1ого адреса для рассылки
     * @return  электронный адрес (строка)
     */
    public String getAddres1() {
        return addres1;
    }

    /**
     * Получение 2ого адреса для рассылки
     * @return  электронный адрес (строка)
     */
    public String getAddres2() {
        return addres2;
    }

    /**
     * Получение 3ого адреса для рассылки
     * @return  электронный адрес (строка)
     */
    public String getAddres3() {
        return addres3;
    }

    /**
     * Получение 4ого адреса для рассылки
     * @return  электронный адрес (строка)
     */
    public String getAddres4() {
        return addres4;
    }

    /**
     * Получение 5ого адреса для рассылки
     * @return  электронный адрес (строка)
     */
    public String getAddres5() {
        return addres5;
    }

    /**
     * Метод получения общего вложения для всех адресатов
     *
     * @return file1Attach Возвращает имя файла вложения для всех получателей
     * (Строка)
     */
    public String getFile1Attach() {
        return file1Attach;
    }

    /**
     * Метод добавления общего вложения для всех адресатов
     *
     * @param file1Attach Принимает имя файла вложения с расширением (Строка)
     */
    public void setFile1Attach(String file1Attach) {
        this.file1Attach = file1Attach;
    }

    /**
     * Метод получения расширения для вложений при отправке уникальных вложений
     * каждому пользователю
     *
     * @return extension Возвращает расширение (Строка)
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Метод установки расширения для вложений при отправке уникальных вложений
     * каждому пользователю
     *
     * @param extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

}
