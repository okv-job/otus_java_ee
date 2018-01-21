package ru.korytnikov.oleg.controllers;

import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Helper {

    public static void marshalXmlToFile(String filename, Object object, Class cls) {
        try {
            File file = new File("/home/okv/" + filename);
            JAXBContext jaxbContext = JAXBContext.newInstance(cls);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static String getDataFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Files.lines(Paths.get("/home/okv/" + fileName), StandardCharsets.UTF_8).forEach(sb::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void findBigSallary(String filename) {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        int sum = 0;
        try {
            DocumentBuilder builder = f.newDocumentBuilder();
            Document doc = builder.parse(new File("/home/okv/" + filename));
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();

            //using Xpath
            NodeList nodeList = (NodeList) xpath.compile("/users/user/sallary").evaluate(doc, XPathConstants.NODESET);
            for (int index = 0; index < nodeList.getLength(); index++) {
                Node node = nodeList.item(index);
                sum += Integer.parseInt(node.getTextContent());
            }
            int avr = sum / nodeList.getLength();

            //using dom parser
            NodeList nodeList1 = doc.getElementsByTagName("user");
            for (int i = 0; i < nodeList1.getLength(); i++) {
                Element element = (Element) nodeList1.item(i);
                if (avr < Integer.parseInt(element.getElementsByTagName("sallary").item(0).getTextContent())) {
                    System.out.println(element.getElementsByTagName("firstName").item(0).getTextContent() + " " +
                            element.getElementsByTagName("secondName").item(0).getTextContent() +
                            " - Зарплата выше среднего значения");
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public static String xmlToJson(String xml) {
        JSONObject jsonObject = XML.toJSONObject(xml);
        return jsonObject.toString(4);
    }

    public static void saveJsonToFile(String filename, String json) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("/home/okv/" + filename))) {
            pw.write(json);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
