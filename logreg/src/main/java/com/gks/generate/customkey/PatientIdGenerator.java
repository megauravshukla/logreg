package com.gks.generate.customkey;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PatientIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String prefix = "pat-";
		String generatedId =null;
		Connection connection = session.connection();
		try {
			java.sql.Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select count(patient_id) as Id from patient");
			if (rs.next()) {
				int id = rs.getInt(1) + 101;
				 generatedId = prefix + new Integer(id).toString();
				//Log.info("custom query generated...");
				return generatedId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return generatedId;
	}
}