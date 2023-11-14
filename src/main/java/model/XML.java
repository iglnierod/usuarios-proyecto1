package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;

// source : https://mkyong.com/java/how-to-create-xml-file-in-java-dom/ + GPT
public class XML {
    public static void userToXML(User user, File file) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Create the root element "user"
            Document doc = docBuilder.newDocument();
            Element userElement = doc.createElement("user");
            doc.appendChild(userElement);

            // Create child elements for "user" with user data
            createElementWithValue(doc, userElement, "nombre", user.getName());
            createElementWithValue(doc, userElement, "edad", String.valueOf(user.getAge()));
            createElementWithValue(doc, userElement, "email", user.getEmail());
            createElementWithValue(doc, userElement, "image", user.getImageName());

            // Write the DOM document to the provided file with pretty-print format
            try (FileOutputStream output = new FileOutputStream(file)) {
                writeXml(doc, output);
            }
            System.out.println("XML generado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void usersToXML(Users users, File file) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Create the root element "users"
            Document doc = docBuilder.newDocument();
            Element usersElement = doc.createElement("users");
            doc.appendChild(usersElement);

            for (User user : users.getAllUsers()) {
                // Create a "user" element for each user
                Element userElement = doc.createElement("user");
                usersElement.appendChild(userElement);

                // Create child elements for "user" with user data
                createElementWithValue(doc, userElement, "nombre", user.getName());
                createElementWithValue(doc, userElement, "edad", String.valueOf(user.getAge()));
                createElementWithValue(doc, userElement, "email", user.getEmail());
                createElementWithValue(doc, userElement, "image", user.getImageName());
            }

            // Write the DOM document to the provided file
            try (FileOutputStream output = new FileOutputStream(file)) {
                writeXml(doc, output);
            }
            System.out.println("XML generado con éxito.");
        } catch (Exception e) {
            System.err.println("No se ha podido exportar a XML");
        }
    }

    // Helper method to create and append child elements with values
    private static void createElementWithValue(Document doc, Element parent, String elementName, String value) {
        Element element = doc.createElement(elementName);
        element.appendChild(doc.createTextNode(value));
        parent.appendChild(element);
    }

    // Write the DOM document to the output stream
    private static void writeXml(Document doc, FileOutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Enable pretty-print format
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }

}

