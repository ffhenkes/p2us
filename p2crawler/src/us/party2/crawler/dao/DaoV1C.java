package us.party2.crawler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import us.party2.crawler.adapter.IDaoAdapter;

public class DaoV1C implements IDaoAdapter{

	Connection conn = null;
	
	@Override
	public void delete() throws SQLException {
		conn = us.party2.crawler.factory.ConnectionFactory.getConnection();
		
		//MOCKUP
		String sql = "delete from v1c where id='1'";
		PreparedStatement ps = conn.prepareStatement(sql);
		if(ps.executeUpdate()>0){
			System.out.println("deleted >> count: " + ps.getUpdateCount());
		}
	}

	@Override
	public void insert() throws SQLException {
		conn = us.party2.crawler.factory.ConnectionFactory.getConnection();
		
		//MOCKUP
		String sql = "insert into v1c(party,date) values(?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, "party"); 
	    pstmt.setString(2, "09/06/2011"); 
	      
	    pstmt.execute();
	}

	@Override
	public void update() throws SQLException {
		conn = us.party2.crawler.factory.ConnectionFactory.getConnection();
		
		//MOCKUP
		String sql = "update v1c set party='wow' where id='1'";
		PreparedStatement ps = conn.prepareStatement(sql);
		if(ps.executeUpdate()>0){
			System.out.println("updated >> count: " + ps.getUpdateCount());
		}
	}

}
