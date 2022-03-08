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

    String defaultUrl;
    String parametersUrl;
    String testUrl;

    public OSMManager() {
        defaultUrl = "https://nominatim.openstreetmap.org/search?q=";
        parametersUrl = "&format=xml&polygon_geojson=1&addressdetails=1";
        testUrl = "https://nominatim.openstreetmap.org/search?q=mariano+comense,+monnet&format=xml&polygon_geojson=1&addressdetails=1";
    }

    //dato un posto da cercare come stringa, ritorna xml come stringa
    public String getXMLAsString(String researchString) {
        try {
            URL url = new URL(defaultUrl + getEncodedString(researchString) + parametersUrl);

            Scanner scs = new Scanner(url.openStream());
            scs.useDelimiter("\u001a");
            String fileString = scs.next();
            return fileString;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(OSMManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(OSMManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OSMManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public File getXMLAsFile(String researchString) {
        try {
            fileManager fm = new fileManager("return.xml", false);
            fm.add(getXMLAsString(researchString), false);
            return fm.getFile();
        } catch (IOException ex) {
            Logger.getLogger(OSMManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String getXML(String search) throws FileNotFoundException, IOException {
        URL url = new URL(defaultUrl + getEncodedString(search) + parametersUrl);
        Scanner scanner = new Scanner(url.openStream());
        scanner.useDelimiter("\u001a");
        String file = scanner.next();
        //System.out.println(file);
        return file;
    }

    public List<CPlace> searchPlace(String place) throws IOException, ParserConfigurationException, SAXException {
        
        List<CPlace> places = new ArrayList<CPlace>();
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        String xml = getXML(place);
        Document doc = builder.parse(new InputSource(new StringReader(xml)));

        NodeList placesNodeList = doc.getElementsByTagName("place");
        for (int i = 0; i < placesNodeList.getLength(); i++) {
            places.add(placesNodeList.item(i).getInfo());
        }
        
        return places;
    }
    public String getEncodedString(String toEncode) throws UnsupportedEncodingException {
        return URLEncoder.encode(toEncode, StandardCharsets.UTF_8.toString());
    }
}
