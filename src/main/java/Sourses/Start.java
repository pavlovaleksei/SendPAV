package Sourses;

import Input.ParseXML;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Главный класс программы
 *
 * @author Pavlov
 */
public class Start {

    public static void main(String[] args) {
        ControlPanel cpanel = new ControlPanel();
         //Направляем вывод sout в файл
        try {
            System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(cpanel.getFileReport())), true));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Читаем параметры рассылки и отправляем письма
        cpanel.sendMessages();

        //Проверка чтения файла с рассылкой
//        testReadXML();
    }
    
    /**
     * Метод проверки чтения файла с рассылкой
     */
    private static void testReadXML(){
        ParseXML p = new ParseXML();
        p.read();
        p.listenXML();
    }
}
