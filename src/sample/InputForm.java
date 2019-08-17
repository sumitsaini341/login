package sample;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;




public class InputForm implements Initializable {

    @FXML
    private TextField cname;
    @FXML
    private TextField cphn;
    @FXML
    private TextField cfname;
    @FXML
    private TextField cemail;
    @FXML
    private TextField ccity;
    @FXML
    private Label status;
    @FXML
    private Label usrlb;
    @FXML
    private TextField czip;
    @FXML
    private DatePicker cdob;
    @FXML
    private TextArea caddress;
    @FXML
    private ComboBox<String> cgender;
    @FXML
    private Button b1,b2,b3;


    private final static String HOST = "localhost";
    private final static int PORT = 27017;



    ObservableList<String> list  = FXCollections.observableArrayList("Male", "Female");
    Stage primaryStage = new Stage();
    JSONObject obj = new JSONObject();


    public void getFieldValues(ActionEvent event){
        try{
            usrlb.setText(String.valueOf(Controller.usr2));
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase mongoDatabase = mongoClient.getDatabase("Confab");
            MongoCollection coll = mongoDatabase.getCollection(String.valueOf(Controller.usr2));

            Document doc = new Document("name", cname.getText())
                    .append("father_name", cfname.getText())
                    .append("email", cemail.getText())
                    .append("gender", cgender.getValue())
                    .append("phone_number", cphn.getText())
                    .append("dob", cdob.getValue())
                    .append("address",caddress.getText())
                    .append("zipcode", czip.getText())
                    .append("city", ccity.getText())
                    .append("usr",Controller.usr2);
                     coll.insertOne(doc);


            status.setText("Saved Successfully!!!");


            cname.setText("");
            cfname.setText("");
            cemail.setText("");
            cgender.setValue(null);
            cphn.setText("");
            cdob.setValue(null);

        }
        catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
//          display the error message
            status.setText("Failed to save");
        }
    }
    public void getFieldValuesj (ActionEvent event){
        JSONObject obj = new JSONObject();
        obj.put ("name",cname.getText() );
        obj.put ("FatherName", cfname.getText());
        obj.put("email", cemail.getText());
        obj.put("gender", cgender.getValue());
        obj.put("phone_number", cphn.getText());
        obj.put("dob", cdob.getValue());
        obj.put("address",caddress.getText());
        obj.put("Zipcode", czip.getText());
        obj.put("city", ccity.getText());
        obj.put("usr",Controller.usr2);

        try (FileWriter file = new FileWriter("d:\\newfile.json")) {
            //File Writer creates a file in write mode at the given location
            file.write(obj.toJSONString());
            file.flush();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }


    }


    public InputForm() throws IOException, ParseException {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cgender.setItems(list);
    }

    public void goToEntries() throws Exception{
//      get the current window
        Stage stage = (Stage)b2.getScene().getWindow();

//      close the current window
        stage.close();

//      load the attendance list window
        Parent root = FXMLLoader.load(getClass().getResource("Entries.fxml"));
        primaryStage.setTitle("Entries");
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();

    }
}
