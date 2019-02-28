package cl.crojas.blog.utils.exceptions;

import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * 
 * @author Christian Rojas N.
 *
 */
public class SimpleErrorHandler implements ErrorHandler {

	private static final Logger log = Logger.getLogger(SimpleErrorHandler.class);

	public void warning(SAXParseException e) throws SAXException {
		log.warn(e.getMessage());
	}

	public void error(SAXParseException e) throws SAXException {
		log.error(e.getMessage());
	}

	public void fatalError(SAXParseException e) throws SAXException {
		log.fatal(e.getMessage());
	}

}
