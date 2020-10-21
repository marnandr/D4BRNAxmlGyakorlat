package D4BRNA_1020;

import java.io.FileReader;

import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

public class SaxD4BRNA {
    public static void main(String[] args) throws Exception {
        XMLReader xr = XMLReaderFactory.createXMLReader();
	    SaxD4BRNA handler = new SaxD4BRNA();
	    xr.setContentHandler(handler);
	    xr.setErrorHandler(handler);
				// Parse each file provided on the
				// command line.
	for (int i = 0; i < args.length; i++) {
	    FileReader r = new FileReader(args[i]);
	    xr.parse(new InputSource(r));
	}

    }
}