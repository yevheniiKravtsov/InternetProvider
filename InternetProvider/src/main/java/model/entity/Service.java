package model.entity;

import java.util.List;

public class Service {
	
	private int id;
    private String name;
    private List<Tarif> tarifList;
    public Service(String name) {
    	this.name=name;
    }
    
    public List<Tarif> getServiceList() {
		return tarifList;
	}
	public void setServiceList(List<Tarif> serviceList) {
		this.tarifList = serviceList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
