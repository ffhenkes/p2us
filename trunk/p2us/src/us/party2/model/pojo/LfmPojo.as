package us.party2.model.pojo
{
	import mx.collections.ArrayCollection;
	
	/**
	 * <id>1934532</id>
	 * <title>Glee Live!</title>
	 * <artists>
	 *  <artist>Glee Cast</artist>
	 *  <artist>Lea Michele</artist>
	 *  <artist>Mark Salling</artist>
	 *  <artist>Amber Riley</artist>
	 *  <artist>Darren Criss</artist>
	 *  <artist>Chris Colfer</artist>
	 *  <artist>Cory Monteith</artist>
	 *  <artist>Kevin McHale</artist>
	 *  <artist>Dianna Agron</artist>
	 *  <artist>Jenna Ushkowitz</artist>
	 *  <artist>The Warblers</artist>
	 *  <artist>Heather Morris</artist>
	 *  <artist>Naya Rivera</artist>
	 *  <artist>Chord Overstreet</artist>
	 *  <artist>Ashley Fink</artist>
	 *  <artist>Harry Shum Jnr</artist>
	 *  <headliner>Glee Cast</headliner>
	 * </artists>
	 * <venue>
	 * 	<id>8783707</id>
	 * 	<name>O2 Arena</name>
	 * 	<location>
	 * 		<city>London</city>
	 * 		<country>United Kingdom</country>
	 * 		<street>Peninsula Square</street>
	 * 		<postalcode>SE10 0DX</postalcode>
	 * 			<geo:point>
	 * 				<geo:lat>51.487582</geo:lat>
	 * 				<geo:long>0.006877</geo:long>
	 * 			</geo:point>
	 * </location>
	 *  <url>http://www.last.fm/venue/8783707+O2+Arena</url>
	 *  <website>http://www.theo2.co.uk</website>
	 *  <phonenumber>Tickets: 0161 385 3211, Venue: 020 8463 2000</phonenumber>
	 *  <image size="small">http://userserve-ak.last.fm/serve/34/58448.jpg</image>
	 *  <image size="medium">http://userserve-ak.last.fm/serve/64/58448.jpg</image>
	 *  <image size="large">http://userserve-ak.last.fm/serve/126/58448.jpg</image>
	 *  <image size="extralarge">http://userserve-ak.last.fm/serve/252/58448.jpg</image>
	 *  <image size="mega">http://userserve-ak.last.fm/serve/_/58448/O2+Arena.jpg</image>
	 * </venue>
	 * <startDate>Mon, 27 Jun 2011 18:30:00</startDate>
	 * <description><div class="bbcode"><a href="http://www.last.fm/tag/glee" class="bbcode_tag" rel="tag">glee</a></div></description>
	 * <image size="small">http://userserve-ak.last.fm/serve/34/60224139.png</image>
	 * <image size="medium">http://userserve-ak.last.fm/serve/64/60224139.png</image>
	 * <image size="large">http://userserve-ak.last.fm/serve/126/60224139.png</image>
	 * <image size="extralarge">http://userserve-ak.last.fm/serve/252/60224139.png</image>
	 * <attendance>1</attendance>
	 * <reviews>0</reviews>
	 * <tag>lastfm:event=1934532</tag>
	 * <url>http://www.last.fm/event/1934532+Glee+Live%21</url>
	 * <website/>
	 * <tickets></tickets>
	 * <cancelled>0</cancelled>
	 * <tags>
	 * 	<tag>glee</tag>
	 * </tags>
	 */

	public class LfmPojo
	{
		public var title:String;
		public var lat:Number;
		public var long:Number;
		public var venue:Object;
		public var artists:Object;
		public var url:String; 
		public var description:String;
		public var image:ArrayCollection;
		public var startDate:String;
		public var reviews:String;
		public var tag:String;
		public var website:String;
		public var tickets:ArrayCollection;
		public var cancelled:Number;
		public var tags:ArrayCollection;
		
		public function LfmPojo()
		{
		}
		
		public function toString():String {
			return title+" - "+startDate;
		}
	}
}