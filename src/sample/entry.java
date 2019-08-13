package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class entry {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty Name;
    private final SimpleStringProperty FatherName;
    //private final SimpleStringProperty dob;
    private final SimpleStringProperty email;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty phone_number;
    private final SimpleStringProperty Zipcode;
    private final SimpleStringProperty address;
    private final SimpleStringProperty city;


    public entry(Integer id, String Name, String FatherName,String email, String gender, String phone_number, String address, String city, String Zipcode) {
        this.id = new SimpleIntegerProperty(id);
        this.Name = new SimpleStringProperty(Name);
        this.FatherName = new SimpleStringProperty(FatherName);
        //this.dob = new SimpleStringProperty(dob);
        this.email = new SimpleStringProperty(email);
        this.gender = new SimpleStringProperty(gender);
        this.phone_number = new SimpleStringProperty(phone_number);
        this.address = new SimpleStringProperty(address);
        this.city =  new SimpleStringProperty(city);
        this.Zipcode = new SimpleStringProperty(Zipcode);
    }

    public Integer getId(){ return id.get();}
    public String getCname(){return Name.get();}
    public String getCfname(){return FatherName.get();}
    public String getCemail(){return email.get();}
    public String getCgender(){return gender.get();}
    public String getCphn(){return phone_number.get();}
    public String getCaddress(){return address.get();}
    public String getCzip(){return Zipcode.get();}
    public String getCcity(){return city.get();}
   // public String getDob(){return dob.get();}

    public void setCname(String name){ Name.set(name);}
    public void setCfname(String name){ FatherName.set(name);}
    public void setCemail(String name){ email.set(name);}
    public void setCgender(String name){ gender.set(name);}
    public void setCphn(String name){ phone_number.set(name);}
    public void setCaddress(String name){ address.set(name);}
    public void setCzip(String name){ Zipcode.set(name);}
    public void setCcity(String name){ city.set(name);}

}
