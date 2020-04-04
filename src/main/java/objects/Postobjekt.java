package objects;

import netscape.javascript.JSObject;
import org.json.JSONObject;

public class Postobjekt {

    private JSONObject data;

    public Postobjekt(JSONObject request){
        this.data = request;
    }

    public boolean postQuarantaeneHelden(){
        boolean posted = false;
        return posted;
    }

    public boolean postWirHelfen(){
        boolean posted = false;
        return posted;
    }

    public boolean verify(){
        boolean dataVerified = false;
        return dataVerified;
    }
}
