package us.party2.controller
{
	import com.demonsters.debugger.MonsterDebugger;
	import com.google.maps.LatLng;
	import com.google.maps.LatLngBounds;
	import com.google.maps.Map;
	import com.google.maps.MapEvent;
	import com.google.maps.MapMouseEvent;
	import com.google.maps.MapType;
	import com.google.maps.controls.MapTypeControl;
	import com.google.maps.controls.PositionControl;
	import com.google.maps.controls.ScaleControl;
	import com.google.maps.controls.ZoomControl;
	import com.google.maps.overlays.Marker;
	import com.google.maps.overlays.MarkerOptions;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.geom.Point;
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.controls.LinkButton;
	import mx.core.IFlexDisplayObject;
	import mx.managers.PopUpManager;
	import mx.rpc.events.ResultEvent;
	
	import spark.components.Group;
	import spark.components.Label;
	
	import us.party2.model.adapter.IRESTRequesterAdapter;
	import us.party2.model.consumer.map.MapConsumer;
	import us.party2.model.factory.RESTRequesterFactory;
	import us.party2.model.pojo.LfmPojo;
	import us.party2.view.component.InfoWindow;
	import us.party2.view.component.MagicMarker;
	import us.party2.view.component.MainSearch;
	import us.party2.view.component.ResizableTitleWindow;
	import us.party2.view.component.SearchResults;
	import us.party2.view.component.SmallSearch;
	import us.party2.view.skin.ResizableTitleWindowSkin;
	
	
	public class MainSearchController
	{
		private var _mainSearch:MainSearch;
		private var _searchResults:SearchResults;
		private static var _instance:MainSearchController = new MainSearchController();
		private var lResult:ArrayCollection;
		private var _map:MapConsumer;
		private var _mainView:Object;
		private var _totalRecords:Number;
		private var _url:String;
		
		[Embed(source="/us/party2/view/assets/orange-dot.png")] 
		private var purpleIcon:Class;
		
		private var _titleInfo:String;
		
		private var caller:Number;
		
		public var count:Number = 0;
		
		
		public function MainSearchController()
		{
			
		}
		
		public static function getInstance():MainSearchController {
			if (_instance == null) 
				_instance = new MainSearchController();
			
			return _instance;
		}
		
		public function searchCity(call:int, page:Number=1):void  {
			caller = call;
			
			if (call == 1) {
				titleInfo = mainSearch.txtCity.text;
			} else if (call == 0) {
				titleInfo = searchResults.smallSearch.txtCity.text;
			}
			//titleInfo = call == 1 ? mainSearch.txtCity.text : searchResults.smallSearch.txtCity.text;
			map = new MapConsumer();
			
			var requester:IRESTRequesterAdapter = RESTRequesterFactory.createLfmCityRequester(titleInfo, page);
			requester.request();
			
		}
		
		public function searchParty(call:int, page:Number=1):void {
			caller = call;
			
			if (call == 2) {
				titleInfo = mainSearch.txtParty.text;
			} else if (call == 3) {
				titleInfo = searchResults.smallSearch.txtParty.text;
			}
			//titleInfo = call == 2 ? mainSearch.txtParty.text : searchResults.smallSearch.txtParty.text;
			map = new MapConsumer();
			
			var requester:IRESTRequesterAdapter = RESTRequesterFactory.createLfmPartyRequester(titleInfo, page);
			requester.request();
		}
		
		public function lfmRequestResult(dataProvider:ArrayCollection):void {
			lResult = dataProvider;
			map.service.addEventListener(MapEvent.MAP_READY, onMapReady);
			(mainView.currentState != 'result') ? mainView.currentState = 'result': searchResults.init();
		}
		
		public function onMapReady(event:MapEvent):void {
			
			if (caller == 1) {
				searchResults.smallSearch.txtCity.text = titleInfo;
			}  else if (caller == 2) {
				searchResults.smallSearch.txtParty.text = titleInfo;
			}
			
			map.service.addControl(new ZoomControl());
			map.service.addControl(new PositionControl());
			map.service.addControl(new MapTypeControl());
			map.service.addControl(new ScaleControl());
			map.service.enableScrollWheelZoom();
			
			if (lResult.length > 0) {
				searchResults.eList.eventData = lResult;
				var i:Number = 0;
				for each (var p:LfmPojo in lResult) {
					
					var markerOptions:MarkerOptions = new MarkerOptions();
					markerOptions.icon = new purpleIcon();
					markerOptions.tooltip = p.title+" - "+p.startDate;
					markerOptions.iconAlignment = MarkerOptions.ALIGN_HORIZONTAL_CENTER;
					markerOptions.iconOffset = new Point(2, 2);
					
					if (i == 0) {
						map.service.setCenter(new LatLng(p.lat, p.long), 13, MapType.NORMAL_MAP_TYPE);
						var bounds:LatLngBounds = map.service.getLatLngBounds();
						var southWest:LatLng = bounds.getSouthWest();
						var northEast:LatLng = bounds.getNorthEast();
						var lngSpan:Number = northEast.lng() - southWest.lng();
						var latSpan:Number = northEast.lat() - southWest.lat();
					}
					
					var latlng:LatLng = new LatLng(p.lat, p.long);
					var marker:MagicMarker = new MagicMarker(latlng, markerOptions, p);
					marker.addEventListener(MapMouseEvent.CLICK, showContent);
					map.service.addOverlay(marker);
					
					i++;
				}
			}
			
		}
		
		private function onClick(e:MouseEvent):void {
			navigateToURL(new URLRequest(_url), "target");
		}
		
		public function showContent(e:MapMouseEvent):void
		{
			var rtw:ResizableTitleWindow = new ResizableTitleWindow();
			var pojo:LfmPojo = e.currentTarget.data as LfmPojo;
			rtw.title = e.currentTarget.data.toString();
			rtw.height = 200;
			rtw.width = 250;
			rtw.x = 50;
			rtw.y = 10;
			count++;
			
			_url = pojo.url;
			var st:Label = new Label();
			st.text = treatDetail(pojo);
			st.top = st.left = st.right = st.bottom = 10;
			st.addEventListener(MouseEvent.CLICK, onClick);
			
			rtw.addEventListener("close", closeHandler);
			rtw.addElement(st);
			rtw.setStyle("skinClass", ResizableTitleWindowSkin);
			PopUpManager.addPopUp(rtw, this.searchResults, false);
			PopUpManager.centerPopUp(rtw);
			map.service.setCenter(new LatLng(e.currentTarget.data.lat,e.currentTarget.data.long), 13);
			map.service.setZoom(100, true);
		}
		
		private function treatDetail(pojo:LfmPojo):String {
			var resp:String = "";
			
			resp += 
				pojo.artists.headliner+"\n"+
				pojo.venue.name+"\n"+
				pojo.venue.location.city+"\n"+
				pojo.venue.location.street+"\n"+
				pojo.venue.location.country+"\n"+
				
				pojo.tag;
			
			return resp;
		}
		
		private function closeHandler(event:Event):void
		{
			event.target.removeEventListener("close", closeHandler);
			PopUpManager.removePopUp(event.target as IFlexDisplayObject);
		}
		
		//navigation
		public function navigate(par:String):void {
			
			if (caller == 1 || caller == 0) {
				searchCity(0, parseInt(par));
			} else if (caller == 2 || caller == 3) {
				searchParty(3, parseInt(par));
			}
		}
		
		public function onDoubleClick():void {
			var pojo:LfmPojo = searchResults.eList.listEvent.selectedItem as LfmPojo;
			map.service.setCenter(new LatLng(pojo.lat,pojo.long), 13);
			map.service.setZoom(100, true);
		}
		
		public function set mainSearch(o:MainSearch):void {
			_mainSearch = o;
		}
		
		public function get mainSearch():MainSearch {
			return _mainSearch;
		}
		
		public function set searchResults(o:SearchResults):void {
			_searchResults = o;
		}
		
		public function get searchResults():SearchResults {
			return _searchResults;
		}
		
		public function set mainView(o:Object):void {
			_mainView = o;
		}
		
		public function get mainView():Object {
			return _mainView;
		}
		
		public function set map(m:MapConsumer):void {
			_map = m;
		}
		
		public function get map():MapConsumer {
			return _map;
		}
		
		public function set titleInfo(info:String):void {
			_titleInfo = info;
		}
		
		public function get titleInfo():String {
			return _titleInfo;
		}
		
		public function set totalRecords(t:Number):void {
			_totalRecords = t;
		}
		
		public function get totalRecords():Number {
			return _totalRecords;
		}
		
	}
}