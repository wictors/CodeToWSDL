package sk.kopr.server.dao;

import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;

public class MysqlStudentDao implements StudentDao {

	private JdbcTemplate jdbcTemplate;

	public MysqlStudentDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	//Existuju 2 rovnaky ludia(menovci) lisia sa len UUID
	@Override
	public UUID pridajStudenta(String meno, String priezvisko) {
		UUID uuid = UUID.randomUUID();
		String sql = "INSERT INTO student(uuid,meno,priezvisko) VALUES(?,?,?)";
		jdbcTemplate.update(sql, new Object[] { uuid.toString(), meno, priezvisko });

		return uuid;
	}

	@Override
	public void zmazStudenta(UUID student) {
		String sql = "select count(*) from student where uuid = ?";
		int pocetStudentov = (int) jdbcTemplate.queryForObject(sql, new Object[] { student.toString() }, Integer.class);

		if (pocetStudentov > 0) {
			sql = "delete from student where uuid = ?";
			jdbcTemplate.update(sql, new Object[] { student.toString() });
			sql = "delete from studentnaprezencke where idStudent = ?";
			jdbcTemplate.update(sql, new Object[] { student.toString() });
		}
	}

}
