package us.party2.crawler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import us.party2.crawler.adapter.IDaoAdapter;
import us.party2.crawler.bean.BeanV1C;
import us.party2.crawler.bean.IBean;

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
	public void insert(ArrayList<IBean> beans) throws SQLException {
		conn = us.party2.crawler.factory.ConnectionFactory.getConnection();
		
		for (Iterator iterator = beans.iterator(); iterator.hasNext();) {
			String sql = "insert into crawler.v1c_event(name,show,image_url,flag_processamento) values(?,?,?,?)";
			BeanV1C bean = (BeanV1C) iterator.next();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, bean.getPartyName()); 
		    pstmt.setString(2, bean.getPartyDetail()); 
		    pstmt.setString(3, bean.getPartyImage()); 		    
		    pstmt.setInt(4, 1); 
		    pstmt.execute();
		}		
	}

	@Override
	public void update(ArrayList<IBean> beans) throws SQLException {
		conn = us.party2.crawler.factory.ConnectionFactory.getConnection();
		
		//MOCKUP
		String sql = "update v1c set party='wow' where id='1'";
		PreparedStatement ps = conn.prepareStatement(sql);
		if(ps.executeUpdate()>0){
			System.out.println("updated >> count: " + ps.getUpdateCount());
		}
	}

}
