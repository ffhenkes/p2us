package us.party2.crawler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import us.party2.crawler.adapter.IDaoAdapter;
import us.party2.crawler.bean.BeanBT;
import us.party2.crawler.bean.BeanV1C;
import us.party2.crawler.bean.IBean;

public class DaoBlueTicket implements IDaoAdapter {

	Connection conn = null;
	
	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(ArrayList<IBean> beans) throws SQLException {
		conn = us.party2.crawler.factory.ConnectionFactory.getConnection();
		
		for (Iterator iterator = beans.iterator(); iterator.hasNext();) {
			String sql = "insert into crawler.btick_event(name,place,image_url,flag_processamento,event_id_btick) values(?,?,?,?,?)";
			BeanBT bean = (BeanBT) iterator.next();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, bean.getPartyName()); 
		    pstmt.setString(2, bean.getPartyPlace()); 
		    pstmt.setString(3, bean.getPartyImage()); 		    
		    pstmt.setInt(4, 1);
		    pstmt.setString(5, bean.getBtId());
		    pstmt.execute();
		}
	}

	@Override
	public void update(ArrayList<IBean> beans) {
		// TODO Auto-generated method stub

	}

}
