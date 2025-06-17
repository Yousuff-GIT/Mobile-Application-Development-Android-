// SAXXMLParser.java
// Initializes SAX Parser and returns parsed RSSItems list

package lab.cahcet.edu.rssfeedreader;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.SAXParserFactory;

public class SAXXMLParser {
    public static ArrayList<RSSItems> rssParser(InputStream is) {
        ArrayList<RSSItems> channellist = null;
        try {
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            SAXXMLHandler saxHandler = new SAXXMLHandler();
            xmlReader.setContentHandler(saxHandler);
            xmlReader.parse(new InputSource(is));
            channellist = saxHandler.getChannels();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channellist;
    }
}