import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLDriver {

	public Document loadFromFile(File obj_file) throws IOException {
		Document obj_doc;
		try {
			DocumentBuilderFactory obj_dbf = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder obj_db = obj_dbf.newDocumentBuilder();
			obj_doc = obj_db.parse(obj_file);
			obj_doc.getDocumentElement().normalize();
		} catch (ParserConfigurationException | SAXException e) {
			return null;
		}
		return obj_doc;
	}
}
