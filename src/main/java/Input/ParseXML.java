package Input;

import Sourses.CreateAddresToSend;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с xml файлом
 *
 * @author Pavlov
 */
public class ParseXML {

    private List<CreateAddresToSend> createAddr;

    /**
     * Метод чтения данных из файла xml
     */
    public void read() {
        createAddr = new ArrayList<>();

        try {
            //Указываем какой файл необходимо распарсить
            File file = new File("./src/main/resources/employers.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            //Указываем тег xml
            NodeList nodeList = document.getElementsByTagName("employ");
            for (int tmp = 0; tmp < nodeList.getLength(); tmp++) {
                Node node = nodeList.item(tmp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    createAddr.add(new CreateAddresToSend(
                            element.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue().trim(),
                            element.getElementsByTagName("mail1").item(0).getChildNodes().item(0).getNodeValue().trim(),
                            element.getElementsByTagName("mail2").item(0).getChildNodes().item(0).getNodeValue().trim(),
                            element.getElementsByTagName("mail3").item(0).getChildNodes().item(0).getNodeValue().trim(),
                            element.getElementsByTagName("mail4").item(0).getChildNodes().item(0).getNodeValue().trim(),
                            element.getElementsByTagName("mail5").item(0).getChildNodes().item(0).getNodeValue().trim(),
                            element.getElementsByTagName("inn").item(0).getChildNodes().item(0).getNodeValue().trim(),
                            element.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue().trim()
                    ));
                }
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Чтение содержимого файла XML
     */
    public void listenXML() {
        //Вывод содержимого xml файла
        for (int i = 0; i < createAddr.size(); i++) {
            System.err.println("id - " + createAddr.get(i).getId());
            System.err.println("Организация - " + createAddr.get(i).getName() + " ИНН - " + createAddr.get(i).getInn());
            System.err.println("Почта 1 - " + createAddr.get(i).getAddres1());
            if (!createAddr.get(i).getAddres2().equals("0")) {
                System.err.println("Почта 2 - " + createAddr.get(i).getAddres2());
            }
            if (!createAddr.get(i).getAddres3().equals("0")) {
                System.err.println("Почта 3 - " + createAddr.get(i).getAddres3());
            }
            if (!createAddr.get(i).getAddres4().equals("0")) {
                System.err.println("Почта 4 - " + createAddr.get(i).getAddres4());
            }
            if (!createAddr.get(i).getAddres5().equals("0")) {
                System.err.println("Почта 5 - " + createAddr.get(i).getAddres5());
            }
            System.err.println("Файл 1 - " + createAddr.get(i).getFile1());
            System.err.println("Файл 2 - " + createAddr.get(i).getFile2());
            System.err.println("");
        }
    }

    public List<CreateAddresToSend> getCreateAddr() {
        return createAddr;
    }
}