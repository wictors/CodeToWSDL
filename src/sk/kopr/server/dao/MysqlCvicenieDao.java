package sk.kopr.server.dao;

import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;

public class MysqlCvicenieDao implements CvicenieDao {
	
	private JdbcTemplate jdbcTemplate;
	
	

	public MysqlCvicenieDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	//Predpoklad ze vedia existovat 2 rovnake Cvicenia nazvom, lisia sa len UUID
	@Override
	public UUID pridajCvicenie(String nazov) {
		UUID uuid = UUID.randomUUID();
		String sql = "INSERT INTO cvicenie(uuid,nazov) VALUES(?,?)";
		jdbcTemplate.update(sql, new Object[] { uuid.toString(), nazov });

		return uuid;
	}

	@Override
	public void zmazCvicenie(UUID cvicenie) {
		String sql = "select count(*) from cvicenie where uuid = ?";
		int pocetCviceni = (int) jdbcTemplate.queryForObject(sql, new Object[] { cvicenie.toString() }, Integer.class);

		if (pocetCviceni > 0) {
			sql = "delete from cvicenie where uuid = ?";
			jdbcTemplate.update(sql, new Object[] { cvicenie.toString() });
		}
	}

}
