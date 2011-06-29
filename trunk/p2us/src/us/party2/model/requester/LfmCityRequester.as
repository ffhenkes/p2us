package us.party2.model.requester
{
	import com.demonsters.debugger.MonsterDebugger;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	import us.party2.controller.MainSearchController;
	import us.party2.model.adapter.IRESTRequesterAdapter;
	import us.party2.model.consumer.http.HTTPServiceConsumer;
	import us.party2.model.pojo.LfmPojo;
	import us.party2.model.pojo.LfmPojo;
	
	public class LfmCityRequester implements IRESTRequesterAdapter
	{
		private var _consumer:HTTPServiceConsumer;
		private var url:String;
		private var status:String;
		private var location:String;
		private var totalPages:Number;
		private var perPage:Number;
		private var totalRecords:Number;
		private var dataProvider:ArrayCollection = new ArrayCollection();
		private var control:MainSearchController;
		
		
		public function LfmCityRequester()
		{
			control = MainSearchController.getInstance();
		}
		
		public function request():void
		{
			consumer.httpService.addEventListener(ResultEvent.RESULT, onResult);
			consumer.httpService.send();
		}
		
		public function onResult(event:ResultEvent):void
		{
			perPage = event.result.lfm.events.perPage;
			totalPages = event.result.lfm.events.totalPages;
			totalRecords = event.result.lfm.events.total;
			
			for each (var o:Object in event.result.lfm.events.event as ArrayCollection) {
				
				var p:us.party2.model.pojo.LfmPojo = new us.party2.model.pojo.LfmPojo();
				p.title = o.title;
				p.lat = o.venue.location.point.lat;
				p.long = o.venue.location.point.long;
				p.venue = o.venue;
				p.artists = o.artists;
				p.url = o.url;
				p.description = o.description;
				p.cancelled = o.cancelled;
				p.image = o.image as ArrayCollection;
				
				p.reviews = o.reviews;
				p.tag = o.tag;
				p.startDate = o.startDate;
				//p.tags;
				
				dataProvider.addItem(p);
			}
			
			control.totalRecords = totalRecords;
			control.lfmRequestResult(dataProvider);
			
		}
		
		public function set consumer(c:HTTPServiceConsumer):void {
			_consumer = c;
		}
		
		public function get consumer():HTTPServiceConsumer {
			return _consumer;
		}
	}
}