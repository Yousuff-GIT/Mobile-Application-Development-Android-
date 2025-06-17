// SAXXMLHandler.java
// Parses RSS XML file using SAX method

package lab.cahcet.edu.rssfeedreader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class SAXXMLHandler extends DefaultHandler {
    private ArrayList<RSSItems> channels;
    private String tagvalue;
    private RSSItems items;

    public SAXXMLHandler() {
        channels = new ArrayList<>();
    }

    public ArrayList<RSSItems> getChannels() {
        return channels;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tagvalue = "";
        if (qName.equalsIgnoreCase("channel")) {
            items = new RSSItems();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        tagvalue = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("channel"))
            channels.add(items);
        else if (qName.equalsIgnoreCase("title"))
            items.setTitle(tagvalue);
        else if (qName.equalsIgnoreCase("link"))
            items.setLink(tagvalue);
        else if (qName.equalsIgnoreCase("description"))
            items.setDescription(tagvalue);
    }
}