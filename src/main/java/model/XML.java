package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XML {
    public static void userToXML(User user, File file) {
        System.out.println("Exportando a XML...");

        try {
            // Crear un contexto JAXB para la clase User
            JAXBContext context = JAXBContext.newInstance(User.class);

            // Crear un objeto Marshaller
            Marshaller marshaller = context.createMarshaller();

            // Configurar la propiedad para formatear la salida
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Marshalling (convertir User a XML)
            marshaller.marshal(user, file);

            System.out.println("XML creado con éxito: " + file.getPath());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

