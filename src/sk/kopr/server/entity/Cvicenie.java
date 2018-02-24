package sk.kopr.server.entity;

import java.util.UUID;

public class Cvicenie {
	
	private UUID uuid;
	private String nazov;
	
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getNazov() {
		return nazov;
	}
	public void setNazov(String nazov) {
		this.nazov = nazov;
	}
	
	

}
