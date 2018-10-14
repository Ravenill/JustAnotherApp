package com.kruczek.justanotherapp.decoder;

import com.kruczek.justanotherapp.exception.XmlProblemException;
import com.kruczek.justanotherapp.model.Order;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlDecoder implements Decoder {

    private final static String CLIENT_ID = "clientId";
    private final static String REQUEST_ID = "requestId";
    private final static String NAME = "name";
    private final static String QUANTITY = "quantity";
    private final static String PRICE = "price";

    @Override
    public void decode(String path, List<Order> orders) {
        Document document = getDocument(path);
        document.getDocumentElement().normalize();

        NodeList requests = document.getElementsByTagName("request");

        for (int i = 0; i < requests.getLength(); i++) {
            Node request = requests.item(i);

            if (request.getNodeType() == Node.ELEMENT_NODE) {
                addOrderTo(orders, request);
            }
        }
    }

    private Document getDocument(String path) {
        Document document;

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(new File(path));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            throw new XmlProblemException();
        }

        return document;
    }

    private void addOrderTo(List<Order> orders, Node request) {
        Element actualNode = (Element)request;

        Order order = createOrderFrom(actualNode);
        orders.add(order);
    }

    private Order createOrderFrom(Element actualNode) {
        return Order.builder()
                .clientID(getFrom(actualNode, CLIENT_ID))
                .requestID(Long.parseLong(getFrom(actualNode, REQUEST_ID)))
                .name(getFrom(actualNode, NAME))
                .quantity(Integer.parseInt(getFrom(actualNode, QUANTITY)))
                .price(Double.parseDouble(getFrom(actualNode, PRICE)))
                .build();
    }

    private String getFrom(Element actualNode, String tag) {
        return actualNode.getElementsByTagName(tag).item(0).getTextContent();
    }
}
