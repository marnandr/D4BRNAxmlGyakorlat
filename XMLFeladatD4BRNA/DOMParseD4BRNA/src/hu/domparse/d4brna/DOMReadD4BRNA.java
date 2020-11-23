package XMLFeladatD4BRNA.DOMParseD4BRNA.src.hu.domparse.d4brna;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class DOMReadD4BRNA {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

        //  Az XML file objektumának létrehozása
        File xmlFile = new File("XMLFeladatD4BRNA/DOMParseD4BRNA/src/hu/domparse/d4brna/XMLD4BRNA.xml");

        //  Document Builder létrehozása.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        //  Az XML fájl DOM objektummá való konvertálása.
        Document doc = dBuilder.parse(xmlFile);

        //  A dokumentum normalizálása.
        doc.getDocumentElement().normalize();

        //A gyökér elem nevének meghatározása és az óvodához kapcsolódó elemek és attribútumok kiírása.
        System.out.println("A dokuementum gyökér eleme: " + doc.getDocumentElement().getNodeName());
        System.out.println(doc.getDocumentElement().getAttribute("id"));
        System.out.println(doc.getElementsByTagName("nev").item(0).getTextContent());
        System.out.println(doc.getElementsByTagName("varos").item(0).getTextContent());
        System.out.println(doc.getElementsByTagName("telefonszam").item(0).getTextContent());

        //  Az összes <csoport> elem beillesztése egy NodeList-be.
        NodeList nList = doc.getElementsByTagName("csoport");

        /*
            For ciklussal végig menni a NodeList-en és kiírni a dokumentum összes elemét és attribútumát.
            A csoportokon megy keresztül a ciklus.
            Minden csoporthoz érve a csoport ID-t átadva meghívja az óvodás és az óvónő kiíró függvényeket.        
        */
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println("------------------------------------------");
                System.out.println();
                System.out.println("------------------------------------------");

                Element elem = (Element) nNode;

                String id = elem.getAttribute("id");

                String ovodaid = elem.getAttribute("ovodaid");

                Node node1 = elem.getElementsByTagName("nev").item(0);
                String nev = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("evfolyam").item(0);
                String evfolyam = node2.getTextContent();

                System.out.println("Csoport ID: " + id);
                System.out.println("Óvoda ID: " + ovodaid);
                System.out.println("\tCsoportnév: " + nev);
                System.out.println("\tÉvfolyam: " + evfolyam);

                System.out.println("\nA " + nev + " csoport óvónői:\n");
                ListOvono(doc, id);

                System.out.println("\nA " + nev + " csoport óvodásai:\n");
                ListOvodas(doc, id);
            }
        }
    }

    public static void ListOvodas (Document doc, String csoportid) {
        NodeList nList = doc.getElementsByTagName("ovodas");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                if (elem.getAttribute("csoportid").toString().equals(csoportid)) {
                    String id = elem.getAttribute("id");

                    Node node1 = elem.getElementsByTagName("vezeteknev").item(0);
                    String vezeteknev = node1.getTextContent();

                    Node node2 = elem.getElementsByTagName("keresztnev").item(0);
                    String keresztnev = node2.getTextContent();

                    Node node3 = elem.getElementsByTagName("szulinap").item(0);
                    String szulinap = node3.getTextContent();

                    Node node4 = elem.getElementsByTagName("nem").item(0);
                    String nem = node4.getTextContent();

                    Node node5 = elem.getElementsByTagName("cim").item(0);
                    String cim = node5.getTextContent();

                    System.out.println("Óvodas ID: " + id);
                    System.out.println("\tVezetéknév: " + vezeteknev);
                    System.out.println("\tKeresztnév: " + keresztnev);
                    System.out.println("\tSzületési idő: " + szulinap);
                    System.out.println("\tNem: " + nem);
                    System.out.println("\tLakcím: " + cim);
                }
            }
        }
    }

    public static void ListOvono (Document doc, String csoportid) {
        NodeList nList = doc.getElementsByTagName("ovono");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                if (elem.getAttribute("csoportid").toString().equals(csoportid)) {
                    String id = elem.getAttribute("id");

                    Node node1 = elem.getElementsByTagName("vezeteknev").item(0);
                    String vezeteknev = node1.getTextContent();

                    Node node2 = elem.getElementsByTagName("keresztnev").item(0);
                    String keresztnev = node2.getTextContent();

                    Node node3 = elem.getElementsByTagName("szulinap").item(0);
                    String szulinap = node3.getTextContent();

                    Node node4 = elem.getElementsByTagName("fokozat").item(0);
                    String fokozat = node4.getTextContent();

                    Node node5 = elem.getElementsByTagName("cim").item(0);
                    String cim = node5.getTextContent();

                    System.out.println("Óvónő ID: " + id);
                    System.out.println("\tVezetéknév: " + vezeteknev);
                    System.out.println("\tKeresztnév: " + keresztnev);
                    System.out.println("\tSzületési idő: " + szulinap);
                    System.out.println("\tFokozat: " + fokozat);
                    System.out.println("\tLakcím: " + cim);
                }
            }
        }
    }
}