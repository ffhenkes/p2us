package us.party2.model.consumer.map
{
	import com.google.maps.Map;

	public class MapConsumer
	{
		private var _service:Map;
		
		public function MapConsumer()
		{
			service = new Map();
			service.key = "ABQIAAAAVPaFV4CWFvBIwGR4z-11txQhG5Sw1wCwxEUYTEao3gMfKdKjTxRRxitOL4LbuTt80kCKAXHlGCVyuQ";
			service.sensor = "false";
		}
		
		public function set service(m:Map):void {
			_service = m;
		}
		
		public function get service():Map {
			return _service;
		}
	}
}