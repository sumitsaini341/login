package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final static String HOST = "localhost";
    public static Object usr2;

    private String usr;
    private String pass;

    private int allow=0;


    private final static int PORT = 27017;

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label lblErrors;
    @FXML
    private Button btnSignin;
    Parent root;
    {
        try {
            root = FXMLLoader.load(getClass().getResource("InputForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Stage primaryStage = new Stage();
    MongoClient mongoClient = new MongoClient(HOST,PORT);
    MongoDatabase mongoDatabase = mongoClient.getDatabase("admin");
    MongoCollection<Document> coll = mongoDatabase.getCollection("app1");


    //  private long count1 = coll.count();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleButtonAction(MouseEvent event){
        usr = txtUsername.getText();
        pass= txtPassword.getText();
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("username", usr);

        for (Document doc : coll.find(whereQuery)){
        System.out.println(doc);
        if(doc.containsValue(pass)){
            lblErrors.setText("success");
            allow = 1;
            Stage stage = (Stage) btnSignin.getScene().getWindow();
            System.out.println("success");

//      close the current window
            stage.close();

//      load the attendance list window

            primaryStage.setTitle("Attendance List");
            primaryStage.setScene(new Scene(root, 1024, 768));
            primaryStage.show();
            primaryStage.setResizable(false);
        }
        else
            {
                lblErrors.setText("password is incorrect");
                System.out.println("password is incorrect");

            }

        }
        if (allow==0){
            lblErrors.setText("Username or password is incorrect");
            System.out.println("Username or password is incorrect");
        }

            usr2 = usr;
        //System.out.println(allow);
    }}

