package us.party2.model.requester
{
	import com.demonsters.debugger.MonsterDebugger;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	import us.party2.controller.MainSearchCityController;
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
		private var control:MainSearchCityController;
		
		
		public function LfmCityRequester()
		{
			control = MainSearchCityController.getInstance();
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
				/*
				var p:LfmPojo = new LfmPojo();
				p.title = o.title;
				p.startDate = o.startDate;
				p.lat = o.venue.location.point.lat;
				p.long = o.venue.location.point.long;
				dataProvider.addItem(p);
				*/
				
				/*
				public var title:String;
				public var lat:Number;
				public var long:Number;
				public var venue:Object;
				public var artists:Object;
				public var url:String; 
				public var description:String;
				public var smallImg:String;
				public var mediumImg:String;
				public var largeImg:String;
				public var extraLargeImage:String;
				public var startDate:String;
				public var reviews:String;
				public var tag:String;
				public var website:String;
				public var tickets:ArrayCollection;
				public var cancelled:Number;
				public var tags:ArrayCollection;
				*/
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