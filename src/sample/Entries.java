package sample;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.bson.Document;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Entries implements Initializable {
    private final static String HOST = "localhost";
    private final static int PORT = 27017;
Controller usr = new Controller();

    private String name1;
    private String fname;
    private String _email;
    private String _gender;
    private String pnumber;
    private String dob;
    private String add;
    private String cty;
    private String zip;
    private String __id;
    private int pos;


    @FXML
    private TableView<entry1> table;
    @FXML
    private TableColumn<entry1, String> id;
    @FXML
    private  TableColumn<entry1, String> cname;
    @FXML
    private TableColumn<entry1, String>cfname;
    @FXML
    private  TableColumn<entry1, String> caddress;
    @FXML
    private TableColumn <entry1, String> cgender;
    @FXML
    private TableColumn<entry1, String>czip;
    @FXML
    private  TableColumn<entry1, String> ccity;
    @FXML
    private TableColumn<entry1, String> cemail;
    @FXML
    private TableColumn<entry1, String> cphn;
    @FXML
    private Label status;
    @FXML
    private Button addentry11;



    Stage primaryStage = new Stage();
    MongoClient mongoClient = new MongoClient(HOST, PORT);
    MongoDatabase mongoDatabase = mongoClient.getDatabase("Confab");
    MongoCollection coll = mongoDatabase.getCollection("admin");
    MongoCursor<Document> cursor = coll.find().iterator();
    public List evaluee = new ArrayList();
    public ObservableList<entry1> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setEditable(true);

        try{
            for(int i = 0; i < coll.count(); i++){
                pos = i +1;

                Document doc = cursor.next();
                name1 = doc.getString("name");
                fname = doc.getString("father_name");
                _email = doc.getString("email");
                _gender = doc.getString("gender");
                pnumber = doc.getString("phone_number");

                evaluee.add(new entry1(pos, fname, fname, _email, _gender, pnumber ));
            }
            list = FXCollections.observableArrayList(evaluee);


        }
        finally {
//          close the connection
            cursor.close();
        }

//      call the setTable method
        setTable();
    }


    private void setTable() {
        //      this makes the table editable
        table.setEditable(true);


//      set the values of each columns to display on the table
        id.setCellValueFactory( new PropertyValueFactory<entry1, String>("id"));
        cname.setCellValueFactory( new PropertyValueFactory<entry1, String>("firstname"));
        cfname.setCellValueFactory( new PropertyValueFactory<entry1, String>("lastname"));
        cemail.setCellValueFactory( new PropertyValueFactory<entry1, String>("email"));
        cgender.setCellValueFactory( new PropertyValueFactory<entry1, String>("gender"));
        cphn.setCellValueFactory( new PropertyValueFactory<entry1, String>("phone_number"));
        caddress.setCellValueFactory( new PropertyValueFactory<entry1, String>("address"));
        table.setItems(list);
    }
}