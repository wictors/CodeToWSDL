package sk.kopr.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import sk.kopr.server.Chyba;
import sk.kopr.server.entity.Prezencka;
import sk.kopr.server.entity.Student;

public class MysqlPrezenckaDao implements PrezenckaDao {

	JdbcTemplate jdbcTemplate;

	public MysqlPrezenckaDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public UUID pridajPrezencku(UUID cvicenie, Date datum, List<UUID> studenti) throws Chyba {
		UUID uuid = UUID.randomUUID();

		String sql = "select count(*) from cvicenie where uuid = ?";
		int pocetCviceni = (int) jdbcTemplate.queryForObject(sql, new Object[] { cvicenie.toString() }, Integer.class);
		if (pocetCviceni == 0) {
			throw new Chyba("Neexistuje zadane Cvicenie !");
		}

		sql = "select count(*) from student where uuid = ?";
		for (int i = 0; i < studenti.size(); i++) {
			int pocetStudentov = (int) jdbcTemplate.queryForObject(sql, new Object[] { studenti.get(i).toString() },
					Integer.class);
			if (pocetStudentov == 0) {
				throw new Chyba("Neexistuje student s UUID: " + studenti.get(i).toString());
			}
		}

		sql = "INSERT INTO prezencka(uuid,idCvicenie,datum) VALUES(?,?,?)";
		jdbcTemplate.update(sql, new Object[] { uuid.toString(), cvicenie.toString(), datum });

		sql = "INSERT INTO studentnaprezencke(idStudent,idPrezencka) VALUES(?,?)";
		for (int i = 0; i < studenti.size(); i++) {
			jdbcTemplate.update(sql, new Object[] { studenti.get(i).toString(), uuid.toString() });
		}

		return uuid;
	}

	@Override
	public void zmazPrezencku(UUID prezencka) {
		String sql = "select count(*) from prezencka where uuid = ?";
		int pocetPrezenciek = (int) jdbcTemplate.queryForObject(sql, new Object[] { prezencka.toString() },
				Integer.class);

		if (pocetPrezenciek > 0) {
			sql = "delete from prezencka where uuid = ?";
			jdbcTemplate.update(sql, new Object[] { prezencka.toString() });
			sql = "delete from studentnaprezencke where idPrezencka = ?";
			jdbcTemplate.update(sql, new Object[] { prezencka.toString() });
		}

	}

	@Override
	public List<Student> vypisStudentov(UUID prezencka) throws Chyba {
		String sql = "select count(*) from prezencka where uuid = ?";
		int pocetPrezenciek = (int) jdbcTemplate.queryForObject(sql, new Object[] { prezencka.toString() },
				Integer.class);
		if (pocetPrezenciek == 0) {
			throw new Chyba("Neexistuje zadana prezencna listina !");
		}
		sql = "SELECT S.uuid as UUID, S.meno as Meno, S.priezvisko as Priezvisko "
				+ "FROM student S JOIN studentnaprezencke SP JOIN prezencka P " + "ON P.uuid = SP.idPrezencka \r\n"
				+ "AND S.uuid = SP.idStudent WHERE P.uuid= ?";
		return jdbcTemplate.query(sql, new Object[] { prezencka.toString() }, new StudentRowMapper());
	}

	@Override
	public List<Prezencka> vypisPrezenciek(UUID student) throws Chyba {
		String sql = "select count(*) from student where uuid = ?";
		int pocetStudentov = (int) jdbcTemplate.queryForObject(sql, new Object[] { student.toString() }, Integer.class);
		if (pocetStudentov == 0) {
			throw new Chyba("Neexistuje zadany student !");
		}
		sql = "SELECT P.uuid as UUID, P.idCvicenie as idCvicenie, P.datum as datum FROM prezencka P JOIN studentnaprezencke SP JOIN student S on S.uuid=SP.idStudent\r\n" + 
				"AND P.uuid = SP.idPrezencka WHERE S.uuid= ?";
		return jdbcTemplate.query(sql, new Object[] { student.toString() }, new PrezenckaRowMapper());
	}

	private static class StudentRowMapper implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet rs, int i) throws SQLException {
			Student student = new Student();
			student.setUuid(UUID.fromString(rs.getString("UUID")));
			student.setMeno(rs.getString("Meno"));
			student.setPriezvisko(rs.getString("Priezvisko"));
			return student;
		}

	}

	private static class PrezenckaRowMapper implements RowMapper<Prezencka> {

		@Override
		public Prezencka mapRow(ResultSet rs, int i) throws SQLException {
			Prezencka prezencka = new Prezencka();
			prezencka.setUuid(UUID.fromString(rs.getString("UUID")));
			prezencka.setUuidCvicenia(UUID.fromString(rs.getString("idCvicenie")));
			prezencka.setDatum(rs.getDate("datum"));
			return prezencka;
		}

	}

}
