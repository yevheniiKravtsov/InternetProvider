package model.entity;

import java.math.BigDecimal;
import java.util.List;

public class User {
    
	private int id;
    private String login;
    private String password;
    private boolean isConfirmed;
    private List<Tarif> tarifList;
    private BigDecimal account;
    private boolean isBlocked;
    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }
    private ROLE role;

    public User(String login, String password, ROLE role, boolean isConfirmed) {
		this.login = login;
		this.password = password;
		this.role = role;
		this.isConfirmed = isConfirmed;
		this.account = BigDecimal.valueOf(0);
		this.isBlocked=false;
	}
    
    public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public boolean getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public boolean getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ROLE getRole() {
		return role;
	}

	public void setRole(ROLE role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", login=" + login + ", password=" + password + ","
				+ " role=" + role + ", isConfirmed= " + isConfirmed +"]\n";
	}

	public List<Tarif> getTarifList() {
		return tarifList;
	}

	public void setTarifList(List<Tarif> tarifList) {
		this.tarifList = tarifList;
	}
	
}
