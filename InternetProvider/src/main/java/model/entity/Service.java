package model.entity;

import java.util.List;

public class Service {
	
	private int id;
    private String name;
    private List<Tarif> serviceList;
    public Service(String name) {
    	this.name=name;
    }
    
    public List<Tarif> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<Tarif> serviceList) {
		this.serviceList = serviceList;
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
