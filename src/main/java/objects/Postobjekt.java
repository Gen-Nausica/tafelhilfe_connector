package objects;

import netscape.javascript.JSObject;
import org.json.JSONObject;

public class Postobjekt {

    private String name;
    private String address;
    private String plz;
    private String ort;
    private String ansprechpartner;
    private String tel;
    private String fax;
    private String mail;
    private String url;
    private String kontaktart;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String kommentar;


    public Postobjekt(String name, String address, String plz, String ort, String ansprechpartner, String tel, String fax, String mail, String url, String kontaktart, String option1, String option2, String option3, String option4, String kommentar){
        this.name = name;
        this.address = address;
        this.plz = plz;
        this.ort = ort;
        this.ansprechpartner = ansprechpartner;
        this.tel = tel;
        this.fax = fax;
        this.mail = mail;
        this.url = url;
        this.kontaktart = kontaktart;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.kommentar = kommentar;
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
