package com.test;

import junit.framework.TestCase;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Test1 extends TestCase {
    public void _testCreate() throws IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("books");

        Element element =root.addElement("book");
        element.addAttribute("id" , "1");
        element.addAttribute("price" , "100");
        element.setText("神雕侠侣");

        Element element2 = root.addElement("book");
        element2.addAttribute("id" , "2");
        element2.addAttribute("price" , "200");
        element2.setText("射雕");

        Element element3 = root.addElement("book");
        element3.addAttribute("id" , "3");
        element3.addAttribute("price" , "300");
        element3.setText("鹿鼎记");

        XMLWriter xmlWriter = new XMLWriter(new FileWriter("D://大学学习/赛杰博/spring/spring_01.xml"), OutputFormat.createPrettyPrint());
        xmlWriter.write(document);
        xmlWriter.close();
    }

    public void _testSelectOne() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document =saxReader.read(new File("D://大学学习/赛杰博/spring/spring_01.xml"));
        Element element = (Element) document.selectSingleNode("books/book[@id=1]");
        System.out.println(element.getText());
        System.out.println(element.attribute("id").getValue());
        System.out.println(element.attribute("price").getValue());
    }

    public void _testSelectList() throws DocumentException {

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("D://大学学习/赛杰博/spring/spring_01.xml"));

        List<Element> list = document.selectNodes("books/book");
        for (Element element : list) {
            System.out.println(element.getText());
        }

    }

    public void _testUpdate() throws DocumentException, IOException {
        SAXReader saxReader = new SAXReader();
        Document document =saxReader.read(new File("D://大学学习/赛杰博/spring/spring_01.xml"));
        Element element = (Element) document.selectSingleNode("books/book[@id=1]");

        element.attribute("price").setValue("10");
        XMLWriter xmlWriter = new XMLWriter(new FileWriter("D://大学学习/赛杰博/spring/spring_01.xml"), OutputFormat.createPrettyPrint());
        xmlWriter.write(document);
        xmlWriter.close();

        System.out.println(element.attribute("price").getValue());

    }

    public void testDelete() throws DocumentException, IOException {
        SAXReader saxReader = new SAXReader();
        Document document =saxReader.read(new File("D://大学学习/赛杰博/spring/spring_01.xml"));
        Element element = (Element) document.selectSingleNode("books/book[@id=1]");

        element.getParent().remove(element);
        XMLWriter xmlWriter = new XMLWriter(new FileWriter("D://大学学习/赛杰博/spring/spring_01.xml"), OutputFormat.createPrettyPrint());
        xmlWriter.write(document);
        xmlWriter.close();

    }
}
