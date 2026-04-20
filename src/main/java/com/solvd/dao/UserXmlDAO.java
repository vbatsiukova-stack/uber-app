package com.solvd.dao;

import com.solvd.model.User;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserXmlDAO implements IUserDAO {

    private static final String FILE_PATH = "src/main/resources/users.xml";

    @Override
    public User create(User user) {
        validateUser(user);
        List<User> users = getAll();
        long nextId = users.stream()
                .map(User::getId)
                .filter(id -> id != null)
                .mapToLong(Long::longValue)
                .max()
                .orElse(0) + 1;

        user.setId(nextId);
        users.add(user);
        writeAll(users);
        return user;
    }

    @Override
    public Optional<User> getById(Long id) {
        return getAll().stream()
                .filter(user -> user.getId() != null && user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return users;
            }

            Document document = getDocument(file);
            NodeList nodeList = document.getElementsByTagName("user");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    User user = new User();
                    user.setId(Long.parseLong(element.getAttribute("id")));
                    user.setFirstName(getTagValue("firstName", element));
                    user.setLastName(getTagValue("lastName", element));
                    user.setEmail(getTagValue("email", element));
                    user.setPhoneNumber(getTagValue("phoneNumber", element));

                    users.add(user);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read users from XML", e);
        }

        return users;
    }

    @Override
    public User update(User user) {
        validateUser(user);
        if (user.getId() == null) {
            throw new IllegalArgumentException("User id cannot be null");
        }

        List<User> users = getAll();
        boolean updated = false;

        for (int i = 0; i < users.size(); i++) {
            if (user.getId().equals(users.get(i).getId())) {
                users.set(i, user);
                updated = true;
                break;
            }
        }

        if (!updated) {
            throw new IllegalArgumentException("User with id " + user.getId() + " not found");
        }

        writeAll(users);
        return user;
    }

    @Override
    public boolean deleteById(Long id) {
        List<User> users = getAll();
        boolean removed = users.removeIf(user -> user.getId() != null && user.getId().equals(id));

        if (removed) {
            writeAll(users);
        }

        return removed;
    }
    @Override
    public Optional<User> getByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }

        return getAll().stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst();
    }
    private void writeAll(List<User> users) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("users");
            document.appendChild(root);

            for (User user : users) {
                Element userElement = document.createElement("user");
                userElement.setAttribute("id", String.valueOf(user.getId()));

                appendChild(document, userElement, "firstName", user.getFirstName());
                appendChild(document, userElement, "lastName", user.getLastName());
                appendChild(document, userElement, "email", user.getEmail());
                appendChild(document, userElement, "phoneNumber", user.getPhoneNumber());

                root.appendChild(userElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(FILE_PATH));
            transformer.transform(source, result);

        } catch (Exception e) {
            throw new RuntimeException("Failed to write users to XML", e);
        }
    }

    private Document getDocument(File file) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        document.getDocumentElement().normalize();
        return document;
    }

    private String getTagValue(String tagName, Element parentElement) {
        NodeList list = parentElement.getElementsByTagName(tagName);
        if (list.getLength() == 0) {
            return null;
        }
        return list.item(0).getTextContent();
    }

    private void appendChild(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(value != null ? value : ""));
        parent.appendChild(element);
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
    }
}