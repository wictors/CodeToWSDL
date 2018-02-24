package sk.kopr.server;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

import sk.kopr.server.dao.CvicenieDao;
import sk.kopr.server.dao.MysqlCvicenieDao;
import sk.kopr.server.dao.MysqlPrezenckaDao;
import sk.kopr.server.dao.MysqlStudentDao;
import sk.kopr.server.dao.PrezenckaDao;
import sk.kopr.server.dao.StudentDao;

public enum Factory {

	INSTANCE;

	private JdbcTemplate jdbcTemplate;
	private CvicenieDao cvicenieDao;
	private PrezenckaDao prezenckaDao;
	private StudentDao studentDao;

	public JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUrl("jdbc:mysql://localhost/projekt2?serverTimezone=UTC");
			dataSource.setUser("klient");
			dataSource.setPassword("kopr");
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}

	public CvicenieDao getCvicenieDao() {
		if (cvicenieDao == null) {
			cvicenieDao = new MysqlCvicenieDao(getJdbcTemplate());
		}
		return cvicenieDao;
	}
	
	public StudentDao getStudentDao() {
		if (studentDao == null) {
			studentDao = new MysqlStudentDao(getJdbcTemplate());
		}
		return studentDao;
	}
	
	public PrezenckaDao getPrezenckaDao() {
		if (prezenckaDao == null) {
			prezenckaDao = new MysqlPrezenckaDao(getJdbcTemplate());
		}
		return prezenckaDao;
	}

}
