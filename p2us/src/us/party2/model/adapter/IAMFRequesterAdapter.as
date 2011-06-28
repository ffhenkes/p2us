package us.party2.model.adapter
{
	import mx.rpc.events.ResultEvent;

	public interface IAMFRequesterAdapter
	{
		function onResult(result:ResultEvent):void;	
	}
}