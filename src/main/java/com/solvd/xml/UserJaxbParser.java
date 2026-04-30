package com.solvd.xml;

import com.solvd.model.Users;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class UserJaxbParser {

    private static final String FILE_PATH = "src/main/resources/users.xml";

    public Users readUsers() {
        try {
            JAXBContext context = JAXBContext.newInstance(Users.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (Users) unmarshaller.unmarshal(new File(FILE_PATH));

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse users.xml with JAXB", e);
        }
    }
}