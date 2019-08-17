package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class entry1 {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty firstname;
    private final SimpleStringProperty lastname;
    private final SimpleStringProperty email;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty phone_number;
    private final SimpleStringProperty add1;
    private final SimpleStringProperty city1;
    private final SimpleStringProperty zip1;

    public entry1(Integer id, String firstname, String lastname, String email, String gender, String phone_number, String add1, String city1, String zip1) {
        this.id = new SimpleIntegerProperty(id);
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.email = new SimpleStringProperty(email);
        this.gender = new SimpleStringProperty(gender);
        this.phone_number = new SimpleStringProperty(phone_number);
        this.add1 = new SimpleStringProperty(add1);
        this.city1 = new SimpleStringProperty(city1);
        this.zip1 = new SimpleStringProperty(zip1);

    }

    public Integer getId() {
        return id.get();
    }
    public String getZip1(){
        return zip1.get();
    }

    public String getAdd1(){
            return add1.get();
    }
    public String getCity1(){
            return city1.get();
    }
    public String getFirstname() {
        return firstname.get();
    }

    public String getLastname() {
        return lastname.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getGender() {
        return gender.get();
    }

    public String getPhone_number() {
        return phone_number.get();
    }

    public void setFirstname(String fname) {
        firstname.set(fname);
    }

    public void setLastname(String lname) {
        lastname.set(lname);
    }

    public void setPhoneNumber(String phoneNumber) {
        phone_number.set(phoneNumber);
    }
}
