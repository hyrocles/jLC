import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.net.URISyntaxException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Config {

	static Service serv_Balancer = null;

	private Document xml_localInformation;
	private Document xml_onlineInformation;

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
		ArrayList<String> arr_balancer = getConnectionType("balancer");

		System.out.println(arr_balancer);
		for (int x = 0; arr_balancer.size() > x; x++) {
			String[] s_balancer = arr_balancer.get(x).split(":");
			String s_ip = s_balancer[0];
			String s_port = s_balancer[1];

			Service temp_Balancer = new Service();
			if (temp_Balancer.connect(s_ip, s_port)) {
				serv_Balancer = temp_Balancer;
				XMLDriver obj_XMLDriver = new XMLDriver();
				Document xml_tempInformation = obj_XMLDriver
						.loadString(serv_Balancer
								.loadConfig(getNodeDetail("ident")));
				if (xml_tempInformation != null) {
					xml_onlineInformation = xml_tempInformation;
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	private ArrayList<String> getConnectionType(String s_connectionType) {
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
								s_connectionType)) {
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
			if (!getNodeDetail("type").equalsIgnoreCase("balancer")) {
				if (loadOnlineInformation()) {
					return true;
				} else {
					System.out.println("%> cant load online Information");
					return false;
				}
			}
			return true;
		} else {
			System.out.println("%> cant load local Information");
			return false;
		}
	}

	public String getNodeDetail(String s_detail) {
		if (xml_localInformation.getFirstChild().getNodeName().toLowerCase() == "node") {
			Node attr = xml_localInformation.getFirstChild().getAttributes()
					.getNamedItem(s_detail);
			if (attr != null) {
				return attr.getTextContent();
			} else {
				System.out.println("%> missing " + s_detail + " in Config");
			}
		}
		return "";
	}
}
