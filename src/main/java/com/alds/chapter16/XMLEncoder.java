package com.alds.chapter16;

import com.alds.exception.IllegalNodeNameException;
import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.List;
import java.util.Map;

import static com.alds.util.Helper.checkNotNull;

public class XMLEncoder {
    private static final char END_TAG = '0';
    private static final char SEPARATOR = ' ';

    /**
     * <p>Task 16.12. Time for implementation and testing :<i>180 min</i></p>
     * Encodes XML document using following rules:
     * <ul>
     * <li>Element --> Code Attributes END element's children END</li>
     * <li>Attribute --> Code Value</li>
     * <li>END --> 0</li>
     * <li>Code --> corresponding code from Name-Code map</li>
     * <li>Value --> text content of a node(Element or Attribute) END</li>
     * </ul><br>
     * Performance:  <i>O(N)</i> , where N is total number of nodes in XML document (elements and attributes)<br>
     * Memory :  depth of recursion <i>O(K) where K is the deepest level of elements nesting</i>
     *
     * @param nodeNameCodeMap Name-Code map (contains elements' names and corresponding codes)
     * @param rootElement     root element of XML document
     * @return string representation of encoded document
     */
    public static String encodeByNodesMap(Map<String, Integer> nodeNameCodeMap, Element rootElement) {
        checkNotNull(nodeNameCodeMap);
        checkNotNull(rootElement);
        return encodeByNodesMapRecursively(nodeNameCodeMap, rootElement).toString();
    }

    private static StringBuilder encodeByNodesMapRecursively(Map<String, Integer> nodeNameCodeMap, Element element) {
        StringBuilder sb = new StringBuilder();
        sb.append(getNodesCodeByName(nodeNameCodeMap, element.getName(), element.getNodeTypeName()));
        sb.append(SEPARATOR);
        appendAttributes(nodeNameCodeMap, element, sb);

        List<Element> children = element.elements();
        if (!children.isEmpty()) {
            for (Element child : children) {
                sb.append(encodeByNodesMapRecursively(nodeNameCodeMap, child).append(SEPARATOR));
            }
        } else if (element.hasContent())
            sb.append(element.getText()).append(SEPARATOR);
        sb.append(END_TAG);
        return sb;
    }

    private static void appendAttributes(Map<String, Integer> nodeNameCodeMap, Element element, StringBuilder sb) {
        List<Attribute> attributes = element.attributes();
        if (!attributes.isEmpty()) {
            for (Attribute attr : attributes) {
                sb.append(getNodesCodeByName(nodeNameCodeMap, attr.getName(), element.getNodeTypeName()))
                        .append(SEPARATOR)
                        .append(attr.getText())
                        .append(SEPARATOR);
            }
            sb.append(END_TAG);
            sb.append(SEPARATOR);
        }
    }


    private static Integer getNodesCodeByName(Map<String, Integer> nodeNameCodeMap, String name, String nodeType) {
        if (!nodeNameCodeMap.containsKey(name))
            throw new IllegalNodeNameException(name, nodeType);
        return nodeNameCodeMap.get(name);
    }

    private static class ElementContainer {
        Element element;
        boolean addEndTagAfterEncoding;

        public ElementContainer(Element element, boolean addEndTagAfterEncoding) {
            this.element = element;
            this.addEndTagAfterEncoding = addEndTagAfterEncoding;
        }
    }
}
