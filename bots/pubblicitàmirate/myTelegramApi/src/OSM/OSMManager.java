/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OSM;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utils.myFileManager;

/**
 *
 * @author Giacomo
 */
public class OSMManager {

    private String defaultUrl;
    private String parametersUrl;
    private String testUrl;
    private Document doc;
    private File xmlFile;

    public OSMManager() {
        defaultUrl = "https://nominatim.openstreetmap.org/search?q=";
        parametersUrl = "&format=xml&polygon_geojson=1&addressdetails=1";
        testUrl = "https://nominatim.openstreetmap.org/search?q=mariano+comense,+monnet&format=xml&polygon_geojson=1&addressdetails=1";
    }

    public File getXMLAsFile(String s) throws IOException {
        File f = new File("results.xml");
        FileWriter fw = new FileWriter(f);
        fw.write(getXMLAsString(s));
        return f;
    }

    private String getXMLAsString(String s) throws FileNotFoundException, IOException {
        URL url = new URL(defaultUrl + getEncodedString(s) + parametersUrl);
        Scanner scanner = new Scanner(url.openStream());
        scanner.useDelimiter("\u001a");
        String file = scanner.next();
        //System.out.println(file);
        return file;
    }

    public Place getPlace(String s) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Element root, element;
        NodeList nodelist;
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();

        File f = new File("results.xml");
        myFileManager fm = new myFileManager(f);
        fm.write(getXMLAsString(s));

        doc = builder.parse(fm.getFile());
        root = doc.getDocumentElement();

        //N.B: essendo una procedura automatica, considero il primo risultato come quello più attendibile
        nodelist = root.getElementsByTagName("place");
        if (nodelist != null && nodelist.getLength() > 0) {
            element = (Element) nodelist.item(0);
            return new Place(element.getAttribute("place_id"), Double.valueOf(element.getAttribute("lat")), Double.valueOf(element.getAttribute("lon")));
        } else {
            System.out.println("NESSUN PLACE TROVATO");
            return null;
        }
    }

    public String getEncodedString(String toEncode) throws UnsupportedEncodingException {
        return URLEncoder.encode(toEncode, StandardCharsets.UTF_8.toString());
    }
    
    //restituisce il valore testuale dell’elemento figlio specificato
    private String getTextValue(Element element, String tag) {
        String value = null;
        NodeList nodelist;
        nodelist = element.getElementsByTagName(tag);
        if (nodelist != null && nodelist.getLength() > 0) {
            value = nodelist.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }

    // restituisce il valore intero dell’elemento figlio specificato
    private int getIntValue(Element element, String tag) {
        return Integer.parseInt(getTextValue(element, tag));
    }

    // restituisce il valore numerico dell’elemento figlio specificato
    private float getFloatValue(Element element, String tag) {
        return Float.parseFloat(getTextValue(element, tag));
    }

     // restituisce il valore numerico dell’elemento figlio specificato
    private double getDoubleValue(Element element, String tag) {
        return Double.parseDouble(getTextValue(element, tag));
    }
}
