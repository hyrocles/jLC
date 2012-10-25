/**
 * ServiceNode.
 * 
 * @author T. Schmalenberg
 * @version 21.10.2012
 */
public class ServiceNode {

	private static boolean initServices() {
		Config obj_config = new Config();

		if (obj_config.load("conf/config.xml")) {
			// ...
			switch (obj_config.getNodeDetail("type").toLowerCase()) {
			case "balancer":
				System.out.println("%> job:balancer");
				// ...
				// break;

			case "node":
				// ...
				break;
			}
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		if (initServices()) {
			System.out.println("%> no Error");
		}
	}
}
