package pojo
{
	public class LfmPojo
	{
		public var title:String;
		public var startDate:String;
		public var lat:Number;
		public var long:Number;
		
		public function LfmPojo()
		{
		}
		
		public function toString():String {
			return title+" - "+startDate;
		}
	}
}