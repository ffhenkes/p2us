package us.party2.model.adapter
{
	import mx.rpc.events.ResultEvent;

	public interface IRESTRequesterAdapter
	{
		function request():void;
		function onResult(result:ResultEvent):void;
	}
}