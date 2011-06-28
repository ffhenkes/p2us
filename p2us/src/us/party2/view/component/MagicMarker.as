package us.party2.view.component
{
	import com.google.maps.LatLng;
	import com.google.maps.overlays.Marker;
	import com.google.maps.overlays.MarkerOptions;
	
	import us.party2.model.pojo.LfmPojo;
	
	public class MagicMarker extends Marker 
	{ 
		[Bindable] 
		public var data : LfmPojo; 
		
		public function MagicMarker(arg0:LatLng, arg1:MarkerOptions=null, dataRow:LfmPojo=null) 
		{
			super(arg0, arg1); 
			this.data = dataRow; 
		} 
	}
}