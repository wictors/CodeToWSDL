package sk.kopr.server.dao;

import java.util.UUID;

public interface CvicenieDao {

	public UUID pridajCvicenie(String nazov);
	public void zmazCvicenie(UUID cvicenie);
}
