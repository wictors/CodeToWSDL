package sk.kopr.server.dao;

import java.util.UUID;

public interface StudentDao {

	public UUID pridajStudenta (String meno, String priezvisko);
	public void zmazStudenta(UUID student);
	
}
