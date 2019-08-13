package sample;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
    private String firstna;
    private String lastna;
    private String genda;
    private String email_;
    private String fone_no;
    private String name;
    private String fname;
    private String _email;
    private String _gender;
    private String pnumber;
    private String dob;
    private String add;
    private String cty;
    private String zip;
    private int pos;


    @FXML
    private TableView<entry> table;
    @FXML
    private TableColumn<entry, Integer> id;
    @FXML
    private  TableColumn<entry, String> cname;
    @FXML
    private TableColumn<entry, String>cfname;
    @FXML
    private  TableColumn<entry, String> caddress;
    @FXML
    private TableColumn <entry, String> cgender;
    @FXML
    private TableColumn<entry, String>czip;
    @FXML
    private  TableColumn<entry, String> ccity;
    @FXML
    private TableColumn<entry, String> cemail;
    @FXML
    private TableColumn<entry, String> cphn;
    @FXML
    private Button addentry1;


    Stage primaryStage = new Stage();
    MongoClient mongoClient = new MongoClient(HOST, PORT);
    MongoDatabase mongoDatabase = mongoClient.getDatabase("Confab");
    MongoCollection coll = mongoDatabase.getCollection(String.valueOf(Controller.usr2));
    MongoCursor<Document> cursor = coll.find().iterator();
    public List evaluee = new ArrayList();
    public ObservableList<entry> list;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setEditable(true);
        try{
            for(int i = 0; i < coll.count(); i++){
                pos = i +1;

                Document doc = cursor.next();
                name = doc.getString("Name");
                fname = doc.getString("FatherName");
             //   dob = doc.getString("dob");
                _email = doc.getString("email");
                _gender = doc.getString("gender");
                pnumber = doc.getString("phone_number");
                add = doc.getString("address");
                cty = doc.getString("city");
                zip = doc.getString("zip");


                evaluee.add(new entry(pos, name, fname, _email, _gender, pnumber,add,cty,zip));
            }
            list = FXCollections.observableArrayList(evaluee);


        } 
        finally 
        {
//          close the connection
            cursor.close();
        }
        setTable();
    }

    private void setTable() {
        //      this makes the table editable
        table.setEditable(true);

//      make firstname column editable with a textfield
        cname.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setFirstname method
        cname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<entry, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<entry, String> event) {

                ((entry)event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setCname(event.getNewValue());

            }
        });

//      make lastname column editable with a textfield
        cfname.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setFirstname method
        cfname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<entry, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<entry, String> event) {
                ((entry)event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setCfname(event.getNewValue());
            }
        });

//      make phone number column editable with a textfield
        cphn.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setFirstname method
        cphn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<entry, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<entry, String> event) {
                ((entry)event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setCphn(event.getNewValue());
            }
        });
        //      make phone number column editable with a textfield
        caddress.setCellFactory(TextFieldTableCell.forTableColumn());

//      gets the new value and calls the setFirstname method
        caddress.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<entry, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<entry, String> event) {
                ((entry)event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setCaddress(event.getNewValue());
            }
        });

//      set the values of each columns to display on the table
        id.setCellValueFactory(new PropertyValueFactory<entry, Integer>("id"));
        cname.setCellValueFactory( new PropertyValueFactory<entry, String>("Name"));
        cfname.setCellValueFactory( new PropertyValueFactory<entry, String>("FatherName"));
        cemail.setCellValueFactory( new PropertyValueFactory<entry, String>("email"));
        cgender.setCellValueFactory( new PropertyValueFactory<entry, String>("gender"));
        cphn.setCellValueFactory( new PropertyValueFactory<entry, String>("phone_number"));
        caddress.setCellValueFactory( new PropertyValueFactory<entry, String>("address"));
        table.setItems(list);
    }
}