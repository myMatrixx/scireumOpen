package com.scireum.open.xml;

import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertTrue;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * @author jaran
 */
public class XMLReaderTest {


    @Test
    public void parse_parses_text_elements_correctly() throws IOException, SAXException, ParserConfigurationException {

        XMLReader reader = new XMLReader();

        reader.addHandler("book", new NodeHandler() {
            @Override
            public void process(StructuredNode node) {

                String text = null;
                String lnr = null;
                try {
                    text = node.queryString("description/text()");
                } catch (XPathExpressionException e) {
                    fail(e.getMessage());
                }
                assertTrue(text.equals("Description"));
            }
        });

        reader.parse(getClass().getResourceAsStream("/books.xml"));
    }

}
