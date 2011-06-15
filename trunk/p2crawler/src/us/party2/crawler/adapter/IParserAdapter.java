package us.party2.crawler.adapter;

import java.util.ArrayList;

import us.party2.crawler.bean.BeanV1C;
import us.party2.crawler.bean.IBean;

public interface IParserAdapter {

	public ArrayList<IBean> parse();
}
