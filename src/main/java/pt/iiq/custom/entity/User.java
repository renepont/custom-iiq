package pt.iiq.custom.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

	
	@XmlAttribute(name = "id")
	private Long id;
	@XmlAttribute
	private String userName;
	@XmlAttribute
	private String firstName;
	@XmlAttribute
	private String lastName;
	@XmlAttribute
	private String email;
	@XmlAttribute
	private Boolean status;
	@XmlAttribute
	private String password;
	@XmlAttribute
	private String role;
	
	public User(String userName) {
		this.userName = userName; 
	}
	
	public User() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String objectToJsonString() {
		StringBuilder jsonRetorno = new StringBuilder();
		
    	jsonRetorno.append("{");
        jsonRetorno.append("\"name\":");        
        jsonRetorno.append("\"username\":");
        jsonRetorno.append("\"" + this.userName + "\",");
        jsonRetorno.append("\"email\":");
        jsonRetorno.append("\"" + this.email + "\",");
        jsonRetorno.append("\"password\":");
        jsonRetorno.append("\"Credibom##2021\",");        
        jsonRetorno.append("\"extension\":");
        jsonRetorno.append("null,");
        jsonRetorno.append("\"role\":");
        jsonRetorno.append("\"" + this.role + "\"");
        jsonRetorno.append("}");
        return jsonRetorno.toString();
	}
	
	public String getJsonToChangeStatus() {
		StringBuilder jsonRetorno = new StringBuilder();
						
		jsonRetorno.append("\"" + this.userName + "\"");		
		return jsonRetorno.toString();
	}
	
}
