package sk.kopr.server;

import java.util.Date;
import java.util.UUID;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import sk.kopr.server.dao.CvicenieDao;
import sk.kopr.server.dao.PrezenckaDao;
import sk.kopr.server.dao.StudentDao;
import sk.kopr.server.entity.Prezencka;
import sk.kopr.server.entity.Student;

@WebService
public class WebAjs {

	private CvicenieDao cvicenieDao = Factory.INSTANCE.getCvicenieDao();
	private PrezenckaDao prezenckaDao = Factory.INSTANCE.getPrezenckaDao();
	private StudentDao studentDao = Factory.INSTANCE.getStudentDao();

	@WebMethod
	public UUID pridajStudenta(@WebParam(name = "meno") String meno, @WebParam(name = "priezvisko") String priezvisko) {
		return studentDao.pridajStudenta(meno, priezvisko);
	}
	
	@WebMethod
	public UUID pridajCvicenie(@WebParam(name = "nazov") String nazov) {
		return cvicenieDao.pridajCvicenie(nazov);
	}
	
	@WebMethod
	public UUID pridajPrezencku(@WebParam(name = "cvicenie")UUID cvicenie, @WebParam(name = "datum") Date datum,
			@WebParam(name = "studenti") List<UUID> studenti) throws Chyba {
		return prezenckaDao.pridajPrezencku(cvicenie, datum, studenti);
	}
	
	@WebMethod
	public void zmazCvicenie(@WebParam(name = "uuidc") UUID uuid) {
		 cvicenieDao.zmazCvicenie(uuid);
	}
	
	@WebMethod
	public void zmazStudenta(@WebParam(name = "uuids") UUID uuid) {
		 studentDao.zmazStudenta(uuid);
	}
	
	@WebMethod
	public void zmazPrezencku(@WebParam(name = "uuidc") UUID uuid) {
		 prezenckaDao.zmazPrezencku(uuid);
	}
	
	@WebMethod
	public List<Student> vypisStudentov(@WebParam(name = "uuidc") UUID uuid) throws Chyba {
		 return prezenckaDao.vypisStudentov(uuid);
	}
	
	@WebMethod
	public List<Prezencka> vypisPrezenciek(@WebParam(name = "uuids") UUID uuid) throws Chyba {
		 return prezenckaDao.vypisPrezenciek(uuid);
	}

}
