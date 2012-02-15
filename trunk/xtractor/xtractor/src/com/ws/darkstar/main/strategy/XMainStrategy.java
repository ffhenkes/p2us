package com.ws.darkstar.main.strategy;

import java.util.ResourceBundle;

import com.ws.darkstar.service.XService;

public enum XMainStrategy {

	EXPORT_JSON {

		@Override
		public void go(String[] params) {
			String response = XService.loadJSON(ResourceBundle.getBundle("xtractor").getString("csv.path"), params[1]);
			System.out.println(response);
		}
		
	},
	
	IMPORT_MONGO {

		@Override
		public void go(String[] params) {
			
			if (XService.loadMongo(ResourceBundle.getBundle("xtractor").getString("csv.path"))) 
				System.out.println("done!");
			
			else
				System.out.println("error");
		}
		
	},
	
	SHOW_COLLECTIONS {

		@Override
		public void go(String[] params) {
			XService.showCollectionsNames();
			
		}
		
	},
	
	SHOW_FIRST {

		@Override
		public void go(String[] params) {
			System.out.println(XService.showFirst());
			
		}
		
	},
	
	LIST_ALL_DOCUMENTS {

		@Override
		public void go(String[] params) {
			XService.listAllDocuments();
			
		}
		
	},
	
	GET_COLLECTION_COUNT {

		@Override
		public void go(String[] params) {
			System.out.println(XService.getCollectionCount());
			
		}
		
	},
	
	QUERY {

		@Override
		public void go(String[] params) {
			XService.findByFlProcessamento(params[1]);
			
		}
		
	},
	
	UPDATE {

		@Override
		public void go(String[] params) {
			XService.update();
			
		}
		
	},
	
	DROP_COLLECTION {

		@Override
		public void go(String[] params) {
			XService.drop();
		}
		
	};
	
	public abstract void go(String[] params);
	
	private XMainStrategy() {
		
	}
}
