package com.alds.chapter16;

import com.alds.exception.IllegalNodeNameException;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class XMLEncoderTest {
    private static final String XMLS_PATH = "src/test/resources/xmlencoder/";
    private static Map<String, Integer> codeMap;

    @BeforeClass
    public static void initVocabulary() {
        codeMap = new HashMap<>();
        codeMap.put("families", 1);
        codeMap.put("family", 2);
        codeMap.put("person", 3);
        codeMap.put("firstName", 4);
        codeMap.put("lastName", 5);
        codeMap.put("state", 6);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testEncodeXMLWhenCodesMapIsNull() throws DocumentException {
        XMLEncoder.encodeByNodesMap(null, getRootElement("emptyElement.xml"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEncodeXMLWhenElementIsNull() {
        XMLEncoder.encodeByNodesMap(codeMap, null);
    }

    @Test(expected = IllegalNodeNameException.class)
    public void testEncodeXMLWhenCodeMapIsEmpty() throws DocumentException {
        XMLEncoder.encodeByNodesMap(new HashMap<>(), getRootElement("emptyElement.xml"));
    }

    @Test(expected = IllegalNodeNameException.class)
    public void testEncodeXMLWhenMapDoesNotContainElement() throws DocumentException {
        XMLEncoder.encodeByNodesMap(codeMap, getRootElement("invalidTagName.xml"));
    }

    @Test(expected = IllegalNodeNameException.class)
    public void testEncodeXMLWhenMapDoesNotContainAttribute() throws DocumentException {
        XMLEncoder.encodeByNodesMap(codeMap, getRootElement("invalidAttributeName.xml"));
    }

    @Test
    public void testEncodeXMLWhenOneElementWithNoAttributes() throws DocumentException {
        assertEquals("2 0",
                XMLEncoder.encodeByNodesMap(codeMap, getRootElement("emptyElement.xml")));
    }

    @Test
    public void testEncodeXMLWhenOneElementWithAttributes() throws DocumentException {
        assertEquals("2 5 Heinz 6 CA 0 0",
                XMLEncoder.encodeByNodesMap(codeMap, getRootElement("oneElementWithAttributes.xml")));
    }

    @Test
    public void testEncodeXMLWithTwoLevelNesting() throws DocumentException {
        assertEquals("2 5 Heinz 0 3 4 Hans 0 Hello 0 3 4 Helga 0 World 0 0",
                XMLEncoder.encodeByNodesMap(codeMap, getRootElement("twoLevelNesting.xml")));
    }

    @Test
    public void testEncodeXMLWithThreeLevelNesting() throws DocumentException {
        String result = "1 2 5 Heinz 6 CA 0 3 4 Hans 0 One 0 3 4 Helga 0 Two 0 0 " +
                "2 5 Zimmerman 6 CA 0 3 4 Markus 0 Three 0 3 4 Erika 0 Four 0 3 4 Timmy 0 Five 0 0 0";
        assertEquals(result,
                XMLEncoder.encodeByNodesMap(codeMap, getRootElement("threeLevelNesting.xml")));
    }

    Element getRootElement(String fileName) throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(new File(XMLS_PATH + fileName)).getRootElement();
    }


}
