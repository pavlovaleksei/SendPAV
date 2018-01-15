package Input;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Класс для работы с параметрами
 *
 * @author Pavlov A.V.
 */
public final class ParamManager {

    private String fileName;
    private Document doc;

    public ParamManager(String fileName) throws Exception {
        this.fileName = fileName;
        // пробуем открыть
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        }
    }

    public ParamManager(String fileName, boolean keepExisting) throws Exception {
        this.fileName = fileName;
        if (keepExisting) {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } else {
            // пробуем открыть,
            try {
                doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileName);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            }
        }
    }

    /**
     * Сохраняет параметры в файл
     *
     * @return
     * @throws Exception
     */
    public ParamManager save() throws Exception {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        Transformer t = TransformerFactory.newInstance().newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        t.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty(OutputKeys.METHOD, "xml");
        t.transform(source, result);
        return null;
    }

    private Node getNodeByName(Node parent, String childName) {
        Node r = null;
        if (parent == null) {
            return null;
        }
        NodeList list = parent.getChildNodes();
        if (list == null) {
            return null;
        }
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals(childName)) {
                r = list.item(i);
                break;
            }
        }
        return r;
    }

    /**
     * Возвращает логический параметр
     *
     * @param group Группа
     * @param param Параметр
     * @param def Значение по умолчанию
     * @return Значение параметра
     */
    public boolean readBoolean(String group, String param, boolean def) {
        return readString(group, param, def ? "true" : "false").equals("true");
    }

    /**
     * Возвращает целочисленный параметр
     *
     * @param group Группа
     * @param param Параметр
     * @param def Значение по умолчанию
     * @return Значение параметра
     */
    public int readInteger(String group, String param, int def) {
        int result = def;
        try {
            result = Integer.parseInt(readString(group, param, Integer.toString(def)));
        } catch (NumberFormatException e) {
        }
        return result;
    }

    /**
     * Возвращает параметр цисло с плавающей точкой
     *
     * @param group Группа
     * @param param Параметр
     * @param def Значение по умолчанию
     * @return Значение параметра
     */
    public double readDouble(String group, String param, double def) {
        double result = def;
        try {
            result = Double.parseDouble(readString(group, param, Double.toString(def)));
        } catch (NumberFormatException e) {
        }
        return result;
    }

    /**
     * Возвращает строковый параметр
     *
     * @param group Группа
     * @param param Параметр
     * @param def Значение по умолчанию
     * @return Значение параметра
     */
    public String readString(String group, String param, String def) {
        Node gr = getNodeByName(doc.getFirstChild(), group);
        Node p = getNodeByName(gr, param);
        return ((p != null) && (((Element) p).hasAttribute("value"))) ? ((Element) p).getAttribute("value") : def;
    }

    /**
     * Записывает логический параметр
     *
     * @param group Группа
     * @param param Параметр
     * @param value Значение
     * @throws Exception
     */
    public void writeBoolean(String group, String param, boolean value) throws Exception {
        String v = value ? "true" : "false";
        writeString(group, param, v);
    }

    /**
     * Записывает целочисленный параметр
     *
     * @param group Группа
     * @param param Параметр
     * @param value Значение
     * @throws Exception
     */
    public void writeInteger(String group, String param, int value) throws Exception {
        writeString(group, param, Integer.toString(value));
    }

    /**
     * Записывает параметр число с плавающей точкой
     *
     * @param group Группа
     * @param param Параметр
     * @param value Значение
     * @throws Exception
     */
    public void writeDouble(String group, String param, double value) throws Exception {
        writeString(group, param, Double.toString(value));
    }

    /**
     * Записывает строковый параметр
     *
     * @param group Группа
     * @param param Параметр
     * @param value Значение
     * @throws Exception
     */
    public void writeString(String group, String param, String value) throws Exception {
        // корень
        Node root = doc.getFirstChild();
        if (root == null) {
            root = doc.createElement("root");
            doc.appendChild(root);
        }
        Node gr = getNodeByName(root, group);
        if (gr == null) {
            gr = doc.createElement(group);
            root.appendChild(gr);
        }
        // параметр
        Node p = getNodeByName(gr, param);
        if (p == null) {
            p = doc.createElement(param);
            gr.appendChild(p);
        }
        // значение
        ((Element) p).setAttribute("value", value);
    }

}