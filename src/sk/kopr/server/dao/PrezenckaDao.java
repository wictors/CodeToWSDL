package sk.kopr.server.dao;

import java.util.List;
import java.util.Date;
import java.util.UUID;

import sk.kopr.server.Chyba;
import sk.kopr.server.entity.Prezencka;
import sk.kopr.server.entity.Student;


public interface PrezenckaDao {
	
	public UUID pridajPrezencku(UUID cvicenie, Date datum, List<UUID> studenti) throws Chyba;
	public void zmazPrezencku(UUID prezencka);
	public List<Student> vypisStudentov(UUID prezencka) throws Chyba;
	public List<Prezencka> vypisPrezenciek(UUID student) throws Chyba;

}
