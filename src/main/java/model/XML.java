package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Collection;

public class XML {
    public static void userToXML(User user, File file) {
        System.out.println("Exportando a XML...");

        try {
            JAXBContext context = JAXBContext.newInstance(User.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(user, file);

            System.out.println("XML creado con éxito: " + file.getPath());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void usersToXML(Users users, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(users, file);
            System.out.println("Usuarios exportados a XML: " + file.getPath());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

