package jidomaps.jidomap;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;


public class LatLngRequest extends StringRequest {
    private static final String LOCATION_REQUEST_URL =  "http://proj-309-sd-b-3.cs.iastate.edu/8000/api/location";
    private Map<String, String> params;

    public LatLngRequest(String sLat, String sLng){
        super(Method.POST, LOCATION_REQUEST_URL, null, null);
        params = new HashMap<>();
        params.put("latitude", sLat);
        params.put("longitude", sLng);
    }

    public Map<String, String> getParams() {
        return params;
    }

}
