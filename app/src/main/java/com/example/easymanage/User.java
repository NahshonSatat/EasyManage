package com.example.easymanage;

public class User {

    private String Email ;
    private String UID ;
    private String Type ;
    private String Name ;
    private String LastName;


    public User(String Email , String UID , String Type, String Name , String Lastname)
    {
       setEmail(Email);
       setType(Type);
       setUID(UID);
       setLastName(Lastname);
       setName(Name);
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
