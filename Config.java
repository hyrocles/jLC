import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.net.URISyntaxException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Config {

	private Document xml_localInformation;

	private String getPath() {
		try {
			return Config.class.getProtectionDomain().getCodeSource()
					.getLocation().toURI().getPath();
		} catch (URISyntaxException e) {
			return "";
		}
	}

	private boolean loadLocalInformation(File obj_file) {
		Document xml_tempInformation = null;
		try {
			XMLDriver obj_XMLDriver = new XMLDriver();
			xml_tempInformation = obj_XMLDriver.loadFromFile(obj_file);
			if (xml_tempInformation != null) {
				xml_localInformation = xml_tempInformation;
				return true;
			}
		} catch (IOException e) {
			return false;
		}
		return false;
	}

	private boolean loadOnlineInformation() {
		System.out.println(getBalancers());
		return false;
	}

	private ArrayList<String> getBalancers() {
		ArrayList<String> temp = new ArrayList<String>();

		NodeList xml_connections = xml_localInformation
				.getElementsByTagName("connections");
		for (int x = 0; xml_connections.getLength() > x; x++) {
			NodeList xml_connection = xml_connections.item(x).getChildNodes();
			for (int y = 0; xml_connection.getLength() > y; y++) {
				if (xml_connection.item(y).getNodeName() == "connection") {
					Node temp_connectionType = xml_connection.item(y)
							.getAttributes().getNamedItem("type");
					if (temp_connectionType != null) {
						if (temp_connectionType.getTextContent().contentEquals(
								"balancer")) {
							Node temp_ip = xml_connection.item(y)
									.getAttributes().getNamedItem("ip");
							Node temp_port = xml_connection.item(y)
									.getAttributes().getNamedItem("port");
							temp.add(temp_ip.getTextContent() + ":"
									+ temp_port.getTextContent());
						}
					}
				}
			}
		}
		return temp;
	}

	public boolean load(String s_filePath) {
		if (loadLocalInformation(new File(getPath() + s_filePath))) {
			if (!getNodeType().equals("balancer")) {
				if (!loadOnlineInformation()) {
					System.out.println("%> cant load online Information");
					return false;
				}
				return true;
			}
			return true;
		} else {
			System.out.println("%> cant load local Information");
			return false;
		}
	}

	public String getNodeType() {
		if (xml_localInformation.getFirstChild().getNodeName().toLowerCase() == "node") {
			Node attr = xml_localInformation.getFirstChild().getAttributes()
					.getNamedItem("type");
			if (attr != null) {
				return attr.getTextContent();
			} else {
				System.out.println("%> missing type in Config");
			}
		}
		return "";
	}
}
