package sk.kopr.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import junit.framework.Assert;
import sk.kopr.server.Chyba;
import sk.kopr.server.Factory;
import sk.kopr.server.dao.CvicenieDao;
import sk.kopr.server.dao.PrezenckaDao;
import sk.kopr.server.dao.StudentDao;
import sk.kopr.server.entity.Prezencka;
import sk.kopr.server.entity.Student;

class DaoTestServer {
	
	JdbcTemplate jdbcTemplate = Factory.INSTANCE.getJdbcTemplate();
	CvicenieDao cvicenieDao = Factory.INSTANCE.getCvicenieDao();
	PrezenckaDao prezenckaDao = Factory.INSTANCE.getPrezenckaDao();
	StudentDao studentDao = Factory.INSTANCE.getStudentDao();

	@Test
	void testPridajStudenta() {
		int pocetStudentovPred = jdbcTemplate.queryForObject("select count(*) from student", Integer.class);
		UUID uuid = studentDao.pridajStudenta("Jozo", "Mrkva");
		int pocetStudentovPo = jdbcTemplate.queryForObject("select count(*) from student", Integer.class);
		Assert.assertEquals(pocetStudentovPred+1, pocetStudentovPo);
		studentDao.zmazStudenta(uuid);
	}
	
	@Test
	void testZmazStudenta() {
		UUID uuid = studentDao.pridajStudenta("Jozo", "Mrkva");
		int pocetStudentovPred = jdbcTemplate.queryForObject("select count(*) from student", Integer.class);
		studentDao.zmazStudenta(uuid);
		int pocetStudentovPo = jdbcTemplate.queryForObject("select count(*) from student", Integer.class);
		Assert.assertEquals(pocetStudentovPred-1, pocetStudentovPo);		
	}
	
	@Test
	void testPridajCvicenie() {
		int pocetCviceniPred = jdbcTemplate.queryForObject("select count(*) from cvicenie", Integer.class);
		UUID uuid = cvicenieDao.pridajCvicenie("Cvicenie1");
		int pocetCviceniPo = jdbcTemplate.queryForObject("select count(*) from cvicenie", Integer.class);
		Assert.assertEquals(pocetCviceniPred+1, pocetCviceniPo);
		cvicenieDao.zmazCvicenie(uuid);
	}
	
	@Test
	void testZmazCvicenie() {
		UUID uuid = cvicenieDao.pridajCvicenie("Cvicenie1");
		int pocetCviceniPred = jdbcTemplate.queryForObject("select count(*) from cvicenie", Integer.class);	
		cvicenieDao.zmazCvicenie(uuid);
		int pocetCviceniPo = jdbcTemplate.queryForObject("select count(*) from cvicenie", Integer.class);
		Assert.assertEquals(pocetCviceniPred-1, pocetCviceniPo);
	}
	
	@Test
	void testPridajPrezencku() throws Chyba {
		int pocetPrezenciekPred = jdbcTemplate.queryForObject("select count(*) from prezencka", Integer.class);
		UUID idC = cvicenieDao.pridajCvicenie("Cvicenie1");
		UUID idS1 = studentDao.pridajStudenta("Jozo", "Mrkva");
		UUID idS2 = studentDao.pridajStudenta("Rudo", "Zemiak");
		List<UUID> studenti = new ArrayList<UUID>();
		studenti.add(idS1);
		studenti.add(idS2);
		Date datum = new Date(2017, 2, 23, 10, 15, 30);
		UUID idP = prezenckaDao.pridajPrezencku(idC, datum, studenti);
		int pocetPrezenciekPo = jdbcTemplate.queryForObject("select count(*) from prezencka", Integer.class);
		Assert.assertEquals(pocetPrezenciekPred+1, pocetPrezenciekPo);
		prezenckaDao.zmazPrezencku(idP);
		cvicenieDao.zmazCvicenie(idC);
		studentDao.zmazStudenta(idS1);
		studentDao.zmazStudenta(idS2);
	}
	
	
	@Test
	void testZmazPrezencku() throws Chyba {
		UUID idC = cvicenieDao.pridajCvicenie("Cvicenie1");
		UUID idS1 = studentDao.pridajStudenta("Jozo", "Mrkva");
		UUID idS2 = studentDao.pridajStudenta("Rudo", "Zemiak");
		List<UUID> studenti = new ArrayList<UUID>();
		studenti.add(idS1);
		studenti.add(idS2);
		Date datum = new Date(2017, 2, 23, 10, 15, 30);
		UUID idP = prezenckaDao.pridajPrezencku(idC, datum, studenti);
		int pocetPrezenciekPred = jdbcTemplate.queryForObject("select count(*) from prezencka", Integer.class);
		prezenckaDao.zmazPrezencku(idP);
		int pocetPrezenciekPo = jdbcTemplate.queryForObject("select count(*) from prezencka", Integer.class);
		Assert.assertEquals(pocetPrezenciekPred-1, pocetPrezenciekPo);
		cvicenieDao.zmazCvicenie(idC);
		studentDao.zmazStudenta(idS1);
		studentDao.zmazStudenta(idS2);
	}
	
	@Test
	void testVypisStudentov() throws Chyba {
		UUID idC = cvicenieDao.pridajCvicenie("Cvicenie1");
		UUID idS1 = studentDao.pridajStudenta("Jozo", "Mrkva");
		UUID idS2 = studentDao.pridajStudenta("Rudo", "Zemiak");
		List<UUID> studenti = new ArrayList<UUID>();
		studenti.add(idS1);
		studenti.add(idS2);
		Date datum = new Date(2017, 2, 23, 10, 15, 30);
		UUID idP = prezenckaDao.pridajPrezencku(idC, datum, studenti);
		List<Student> vypisStudentov = prezenckaDao.vypisStudentov(idP);
		Assert.assertEquals(vypisStudentov.get(0).getUuid(), idS1);
		prezenckaDao.zmazPrezencku(idP);
		cvicenieDao.zmazCvicenie(idC);
		studentDao.zmazStudenta(idS1);
		studentDao.zmazStudenta(idS2);		
	}
	
	@Test
	void testVypisPrezenciek() throws Chyba {
		UUID idC = cvicenieDao.pridajCvicenie("Cvicenie1");
		UUID idS1 = studentDao.pridajStudenta("Jozo", "Mrkva");
		UUID idS2 = studentDao.pridajStudenta("Rudo", "Zemiak");
		List<UUID> studenti = new ArrayList<UUID>();
		studenti.add(idS1);
		studenti.add(idS2);
		Date datum = new Date(2017, 2, 23, 10, 15, 30);
		UUID idP = prezenckaDao.pridajPrezencku(idC, datum, studenti);
		List<Prezencka> vypisPrezenciek = prezenckaDao.vypisPrezenciek(idS1);
		Assert.assertEquals(vypisPrezenciek.get(0).getUuid(), idP);
		prezenckaDao.zmazPrezencku(idP);
		cvicenieDao.zmazCvicenie(idC);
		studentDao.zmazStudenta(idS1);
		studentDao.zmazStudenta(idS2);
	}
	
	
}
