package us.party2.model.consumer.http
{
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;
	
	public class HTTPServiceConsumer
	{
		private var _httpService:HTTPService;
		private static const HTTP_CONSUMER_FAULT:String = "HTTP Consumer Fault! ";
		
		public function HTTPServiceConsumer(url:String)
		{
			httpService = new HTTPService();
			httpService.url = encodeURI(url);
			httpService.showBusyCursor = true;
			httpService.addEventListener(FaultEvent.FAULT, onFault);
			
		}
		
		private function onFault(event:FaultEvent):void
		{
			Alert.show(">> code: "+event.fault.faultCode+"\n >> detail: "+event.fault.faultDetail+"\n >> string: "+event.fault.faultString+"\n >> root cause: "+event.fault.rootCause.toString(), HTTP_CONSUMER_FAULT);
		}
		
		public function get httpService():HTTPService
		{
			return _httpService;
		}
		
		public function set httpService(hs:HTTPService):void
		{
			_httpService = hs;
		}
		
	}
}