package objects;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.model.*;
import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.util.*;
import java.util.logging.Logger;

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
    private Map<String,String> params;

    private AmazonDynamoDB client;
    private DynamoDB dynamoDB;



    private static final Logger log = Logger.getLogger(Postobjekt.class.getName());


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
        this.params = new HashMap<String, String>();
        this.params.put("name", name);
        this.params.put("address", address);
        this.params.put("plz", plz);
        this.params.put("ort", ort);
        this.params.put("ansprechpartner", ansprechpartner);
        this.params.put("tel", tel);
        this.params.put("fax", fax);
        this.params.put("mail", mail);
        this.params.put("url", url);
        this.params.put("kontaktart", kontaktart);
        this.params.put("option1", option1);
        this.params.put("option2", option2);
        this.params.put("option3", option3);
        this.params.put("option4", option4);
        this.params.put("kommentar", kommentar);

        log.info("constructor Postobjekt");

        this.client =AmazonDynamoDBClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();
        this.dynamoDB = new DynamoDB(client);
    }

    public boolean postQuarantaeneHelden(){
        boolean posted = false;
        log.info("in postQuarantaenehelden");
        insertIntoDB();
        log.info("after insterDB");
        return posted;
    }

    public boolean postWirHelfen(){
        boolean posted = false;
        return posted;
    }

    public boolean insertIntoDB(){
        //insert data into table "Anfragen"
        insertIntoAnfragen();
        //create needed tables, if they don't already exist
        /*if(createTables()){
            log.info("table created");
            //insert values into db
            return true;
        }
        else
        {
            return false;
        }*/
        return true;
    }

    private void insertIntoAnfragen() {
        //insert into Anfragen
        log.info("insert into Anfragen");
        Table table = dynamoDB.getTable("Anfragen");
        Map<String,AttributeValue> attributeValues = new HashMap<>();
        Item item = new Item();
        item.withPrimaryKey("AnfrageId", generateAnfrageId());

        for(Map.Entry<String, String> entry : params.entrySet()){
            if(!entry.getKey().equals("") && !entry.getValue().equals(""))
            {
                item.withString(entry.getKey(), entry.getValue());
                log.info("Key: "+entry.getKey());
                log.info("Value: "+entry.getValue());
            }
        }
        try
        {
            log.info("Adding a new item...");
            PutItemOutcome outcome = table.putItem(item);
        }
        catch(Exception e){
            log.info("Unable to add item");
            log.info(e.getMessage());
        }
    }

    private String generateAnfrageId()
    {
        return UUID.randomUUID().toString();
    }

    private boolean createTables() {
        //check, if table exists
        if(checkExists("Quarantaenehelden")){
            return false;
        }
        else
        {
            //create table Quarantaenehelden
            String tableName = "Quarantaenehelden";
            try
            {
                System.out.println("Attempting to create table; please wait...");
                Table table = dynamoDB.createTable(tableName,
                        Arrays.asList(new KeySchemaElement("id", KeyType.HASH), // Partition
                                // key
                                new KeySchemaElement("plz", KeyType.RANGE),// Sort key
                                new KeySchemaElement("message", KeyType.RANGE)),//Message
                        Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.N),
                                new AttributeDefinition("plz", ScalarAttributeType.S),
                                new AttributeDefinition("message", ScalarAttributeType.S)),
                        new ProvisionedThroughput(10L, 10L));
                table.waitForActive();
                log.info("Success.  Table status: " + table.getDescription().getTableStatus());

            }
            catch (Exception e) {
                log.info("Unable to create table: ");
                log.info(e.getMessage());
            }
        }
        return true;
    }

    private boolean checkExists(String tableName)
    {
        boolean temp = false;
        Table table = dynamoDB.getTable(tableName);
        ScanSpec scanSpec = new ScanSpec();
        try
        {
            ItemCollection <ScanOutcome> items = table.scan(scanSpec);

            Iterator<Item> iter = items.iterator();
            while(iter.hasNext()){
                temp = true;
            }
        }
        catch(Exception e)
        {
            log.info("Error while scanning table Anfragen");
            log.info(e.getMessage());
            temp = true;
        }
        return temp;
    }


    /*
*For later: If any additional validation is necessary
 */
    public boolean verify(){
        boolean dataVerified = true;
        if(!nameVerfified()){
            dataVerified = false;
        }
        if(!addressVerified()){
            dataVerified = false;
        }
        if(!plzVerified()){
            dataVerified = false;
        }
        if(!ortVerified()){
            dataVerified = false;
        }
        if(!ansprechpartnerVerified()){
            dataVerified = false;
        }
        if(!telVerified()){
            dataVerified = false;
        }
        if(!faxVerified()){
            dataVerified = false;
        }
        if(!mailVerified()){
            dataVerified = false;
        }
        if(!urlVerified()){
            dataVerified = false;
        }
        if(!kontaktartVerified()){
            dataVerified = false;
        }
        if(!option1Verified()){
            dataVerified = false;
        }
        if(!option2Verified()){
            dataVerified = false;
        }
        if(!option3Verified()){
            dataVerified = false;
        }
        if(!option4Verified()){
            dataVerified = false;
        }
        if(!kommentarVerified()){
            dataVerified = false;
        }
        return true;
    }

    private boolean kommentarVerified()
    {
        boolean temp = false;
        if (kommentar == null)
        {
            temp = true;
        }
        return true;
    }

    private boolean option4Verified() {
        boolean temp = false;
        if(option4 == null){
            temp = true;
        }
        return true;
    }

    private boolean option3Verified() {
        boolean temp = false;
        if(option3 == null){
            temp = true;
        }
        return true;
    }

    private boolean option2Verified() {
        boolean temp = false;
        if(option2 == null){
            temp = true;
        }
        return true;
    }

    private boolean option1Verified() {
        boolean temp = false;
        if(option1 == null){
            temp = true;
        }
        return true;
    }

    private boolean kontaktartVerified() {
        boolean temp = false;
        if(kontaktart.equals("Email")||kontaktart.equals("Telefon")){
            temp = true;
        }
        return true;
    }

    private boolean urlVerified() {
        boolean temp = false;
        if(url.startsWith("http:")||url.startsWith("https:")||url.startsWith("www.")){
            temp = true;
        }
        return true;
    }

    private boolean mailVerified() {
        return true;
    }

    private boolean faxVerified() {
        return true;
    }

    private boolean telVerified() {
        return true;
    }

    private boolean ansprechpartnerVerified() {
        return true;
    }

    private boolean ortVerified() {
        return true;
    }

    private boolean plzVerified() {
        return true;
    }

    private boolean addressVerified() {
        return true;
    }

    private boolean nameVerfified() {
        return true;
    }
}
