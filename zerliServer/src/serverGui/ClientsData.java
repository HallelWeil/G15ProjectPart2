package serverGUI;

import javafx.beans.property.SimpleStringProperty;

/**
 * Simple class to save the relevant data for each client in the client table
 * 
 * @author hallel
 * @version 01
 */
public class ClientsData {
	public SimpleStringProperty number;
	public SimpleStringProperty ipAddress;
	public SimpleStringProperty status;
	public SimpleStringProperty host;
	private String uniqueClientName;

	/**
	 * Construct new ClientData
	 * 
	 * @param number : Number in table
	 * @param ip     : the client's ip address
	 * @param status : the connection status
	 * @param host   : the host
	 */
	public ClientsData(String number, String ip, String status, String host, String clientName) {
		this.number = new SimpleStringProperty(number);
		this.ipAddress = new SimpleStringProperty(ip);
		this.status = new SimpleStringProperty(status);
		this.host = new SimpleStringProperty(host);
		this.uniqueClientName = clientName;
	}

	public String getClientName() {
		return uniqueClientName;
	}

	public void setNumber(String number) {
		this.number.set(number);
	}

	public void setStatus(String status) {
		this.status.set(status);
	}

	public void setHost(String host) {
		this.host.set(host);
	}

	public String getNumber() {
		return number.get();
	}

	public String getIpAddress() {
		return ipAddress.get();
	}

	public String getStatus() {
		return status.get();
	}

	public String getHost() {
		return host.get();
	}

	/**
	 * Test equals by the clients unique name
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientsData other = (ClientsData) obj;
		return this.uniqueClientName.equals(other.getClientName());
	}

}
