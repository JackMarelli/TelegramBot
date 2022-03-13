/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OSM;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import mytelegramapi.fileManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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

    public File getXMLAsFile(String s) {
        try {
            fileManager fm = new fileManager("return.xml", false);
            fm.add(getXML(s), false);
            return fm.getFile();
        } catch (IOException ex) {
            Logger.getLogger(OSMManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String getXML(String s) throws FileNotFoundException, IOException {
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

        doc = builder.parse(getXMLAsFile(s));
        root = doc.getDocumentElement();

        //N.B: essendo una procedura automatica, considero il primo risultato come quello più attendibile
        nodelist = root.getElementsByTagName("place");
        if (nodelist != null && nodelist.getLength() > 0) {
            element = (Element) nodelist.item(0);
            return new Place(element.getAttribute("place_id").toString(), Double.parseDouble(element.getAttribute("lat")), Double.parseDouble(element.getAttribute("lon")));
        } else {
            return null;
        }
    }

    public String getEncodedString(String toEncode) throws UnsupportedEncodingException {
        return URLEncoder.encode(toEncode, StandardCharsets.UTF_8.toString());
    }
}
