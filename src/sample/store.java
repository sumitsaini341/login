package sample;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class store {
    private final SimpleStringProperty txtusername;
    private final SimpleStringProperty txtpassword;
    public store(String txtusername,String txtpassword){
        this.txtusername = new SimpleStringProperty(txtusername);
        this.txtpassword = new SimpleStringProperty(txtpassword);
    }
    public String getTxtusername() {
        return txtusername.get();
    }

    public String getTxtpassword() {
        return txtpassword.get();
    }
}
