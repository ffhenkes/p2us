package us.party2.model.factory
{
	import mx.rpc.http.HTTPService;
	
	import us.party2.model.adapter.IRESTRequesterAdapter;
	import us.party2.model.consumer.http.HTTPServiceConsumer;
	import us.party2.model.requester.LfmCityRequester;
	import us.party2.model.requester.LfmPartyRequester;

	public class RESTRequesterFactory
	{
		public function RESTRequesterFactory()
		{
		}
		
		public static function createLfmCityRequester(city:String, page:Number):IRESTRequesterAdapter {
			
			var url:String = "http://ws.audioscrobbler.com/2.0/?method=geo.getevents&location="+city+"&page="+page+"&api_key=ba76c756a044a0b6ee654a00129ad49c";
			var consumer:HTTPServiceConsumer = new HTTPServiceConsumer(url);
			var requester:LfmCityRequester = new LfmCityRequester();
			requester.consumer = consumer;
			
			return requester;
		
		}
		
		public static function createLfmPartyRequester(party:String, page:Number):IRESTRequesterAdapter {
			var url:String = "http://ws.audioscrobbler.com/2.0/?method=artist.getevents&artist="+party+"&autocorrect=1&page="+page+"&api_key=ba76c756a044a0b6ee654a00129ad49c";
			var consumer:HTTPServiceConsumer = new HTTPServiceConsumer(url);
			var requester:LfmPartyRequester = new LfmPartyRequester();
			requester.consumer = consumer;
			
			return requester;
		}
		
	}
}