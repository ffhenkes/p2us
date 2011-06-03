package consumer
{
	import com.demonsters.debugger.MonsterDebugger;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;

	public class HTTPConsumer
	{
		private var _httpService:HTTPService;
		private static const HTTP_CONSUMER_FAULT:String = "HTTP Consumer Fault! ";
		
		public function HTTPConsumer(city:String)
		{
			MonsterDebugger.initialize(this);
			httpService = new HTTPService();
			httpService.url = "http://ws.audioscrobbler.com/2.0/?method=geo.getevents&location="+city+"&api_key=ba76c756a044a0b6ee654a00129ad49c";
			
			MonsterDebugger.trace(this, httpService);
			httpService.addEventListener(FaultEvent.FAULT, onFault);
			
		}

		private function onFault(event:FaultEvent):void
		{
			Alert.show(HTTP_CONSUMER_FAULT, "PMIC");
		}
		
		public function get httpService():HTTPService
		{
			return _httpService;
		}

		public function set httpService(value:HTTPService):void
		{
			_httpService = value;
		}

	}
}