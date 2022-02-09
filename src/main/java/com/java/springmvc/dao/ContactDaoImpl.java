package com.java.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import com.java.springmvc.model.Contact;

public class ContactDaoImpl implements InterfaceContact {

	
	public JdbcTemplate jdbcTemplate;
	String sql = null;

	public ContactDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void saveOrUpdate(Contact contact) {
		// TODO Auto-generated method stub

		if (contact.getId() > 0) {
			sql = "update contact set name =?, email=?, address=?, telephone=? where contact_id =?";
			jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getAddress(),
					contact.getTelephone(), contact.getId());
		} else {
			sql = "Insert into contact (name,email,address,telephone) values(?,?,?,?)";
			jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getAddress(),
					contact.getTelephone());
		}
	}

	public void delete(int contactId) {
		// TODO Auto-generated method stub
		sql = "delete from contact where contact_id = ?";
		jdbcTemplate.update(sql, contactId);
	}

	public Contact get(int contactId) {
		// TODO Auto-generated method stub

		sql = "select * from contact where contact_id =" + contactId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {

			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					// TODO Auto-generated method stub

					Contact contact = new Contact();
					contact.setId(rs.getInt(1));
					contact.setName(rs.getString(2));
					contact.setEmail(rs.getString(3));
					contact.setAddress(rs.getString(4));
					contact.setTelephone(rs.getString(5));

					return contact;

				}
				// TODO Auto-generated method stub
				return null;
			}
		});
	}

	public List<Contact> list() {
		// TODO Auto-generated method stub

		sql = "select * from contact";
		List<Contact> list = jdbcTemplate.query(sql, new RowMapper<Contact>() {

			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub

				Contact contact = new Contact();
				contact.setId(rs.getInt(1));
				contact.setName(rs.getString(2));
				contact.setEmail(rs.getString(3));
				contact.setAddress(rs.getString(4));
				contact.setTelephone(rs.getString(5));

				return contact;
			}

		});

		return list;
	}

}
