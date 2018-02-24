package sk.kopr.server.entity;

import java.util.Date;
import java.util.UUID;

public class Prezencka {

	private UUID uuid;
	private UUID uuidCvicenia;
	private Date datum;
	
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public UUID getUuidCvicenia() {
		return uuidCvicenia;
	}
	public void setUuidCvicenia(UUID uuidCvicenia) {
		this.uuidCvicenia = uuidCvicenia;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	
}
