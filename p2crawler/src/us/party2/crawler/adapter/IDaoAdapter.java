package us.party2.crawler.adapter;

import java.sql.SQLException;
import java.util.ArrayList;

import us.party2.crawler.bean.IBean;

public interface IDaoAdapter {

	public void insert(ArrayList<IBean> beans) throws SQLException;
	public void update(ArrayList<IBean> beans) throws SQLException;
	public void delete() throws SQLException;
	//void insert(ArrayList<BeanV1C> beans) throws SQLException;
	
}
