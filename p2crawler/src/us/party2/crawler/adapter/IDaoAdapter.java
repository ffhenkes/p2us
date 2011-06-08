package us.party2.crawler.adapter;

import java.sql.SQLException;

public interface IDaoAdapter {

	public void insert() throws SQLException;
	public void update() throws SQLException;
	public void delete() throws SQLException;
	
}
