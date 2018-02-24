package sk.kopr.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import sk.kopr.client.Chyba_Exception;
import sk.kopr.client.Prezencka;
import sk.kopr.client.Student;
import sk.kopr.client.WebAjs;
import sk.kopr.client.WebAjsService;

class TestClient {

	private WebAjsService was;
	private WebAjs wa;

	public TestClient() {
		super();
		was = new WebAjsService();
		wa = was.getWebAjsPort();

	}

	@Test
	void testPridajCvicenie() {
		String idC = wa.pridajCvicenie("Cvicenie1");
		Assert.assertEquals(36, idC.length());
		wa.zmazCvicenie(idC);
	}

	@Test
	void testPridajStudenta() {
		String idS = wa.pridajStudenta("Karol", "Karfiol");
		Assert.assertEquals(36, idS.length());
		wa.zmazStudenta(idS);
	}

	@Test
	void testPridajPrezencku() throws Chyba_Exception, DatatypeConfigurationException {
		String idS = wa.pridajStudenta("Karol", "Karfiol");
		List<String> studenti = new ArrayList();
		studenti.add(idS);
		String idC = wa.pridajCvicenie("Cvicenie1");

		// Datum >> MESIAC +1 v DB a HODINA -2 v DB
		GregorianCalendar gregorian = new GregorianCalendar(2017, 3, 20, 17, 15);
		XMLGregorianCalendar datum = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorian);
		String idP = wa.pridajPrezencku(idC, datum, studenti);
		Assert.assertEquals(36, idP.length());
		wa.zmazStudenta(idS);
		wa.zmazCvicenie(idC);
		wa.zmazPrezencku(idP);
	}

	@Test
	void testVypisStudentov() throws Chyba_Exception, DatatypeConfigurationException {
		String idS1 = wa.pridajStudenta("Karol", "Karfiol");
		String idS2 = wa.pridajStudenta("Jozo", "Mrkva");
		List<String> studenti = new ArrayList();
		studenti.add(idS1);
		studenti.add(idS2);
		String idC = wa.pridajCvicenie("Cvicenie1");

		// Datum >> MESIAC +1 v DB a HODINA -2 v DB
		GregorianCalendar gregorian = new GregorianCalendar(2017, 3, 20, 17, 15);
		XMLGregorianCalendar datum = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorian);
		String idP = wa.pridajPrezencku(idC, datum, studenti);
		List<Student> vypis = wa.vypisStudentov(idP);
		Assert.assertEquals(2, vypis.size());
		wa.zmazStudenta(idS1);
		wa.zmazStudenta(idS2);
		wa.zmazCvicenie(idC);
		wa.zmazPrezencku(idP);
	}

	@Test
	void testVypisPrezenciek() throws Chyba_Exception, DatatypeConfigurationException {
		String idS = wa.pridajStudenta("Karol", "Karfiol");
		List<String> studenti = new ArrayList();
		studenti.add(idS);
		String idC = wa.pridajCvicenie("Cvicenie1");

		// Datum >> MESIAC +1 v DB a HODINA -2 v DB
		GregorianCalendar gregorian = new GregorianCalendar(2017, 3, 20, 17, 15);
		XMLGregorianCalendar datum = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorian);
		String idP = wa.pridajPrezencku(idC, datum, studenti);
		List<Prezencka> vypis = wa.vypisPrezenciek(idS);
		Assert.assertEquals(1, vypis.size());
		wa.zmazStudenta(idS);
		wa.zmazCvicenie(idC);
		wa.zmazPrezencku(idP);
	}

}
