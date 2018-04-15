package com.example.toshiba.parkme;

public class User {

    private String email;
    private String password;
    private String name;
    private String phone_number;
    private String confpassword;
    private int from,to;

    public User() {

    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getConfpassword() {
        return confpassword;
    }

    public void setConfpassword(String confpassword) {
        this.confpassword = confpassword;
    }


}
    //  private Long date;
//  private Long from_time;
//  private Long to_time;

    /*initialisation of data
    date = 0;
    from_time = 0;
    to_time = 0;*/



 /*   public User(String email, String password, String name, String phone_number, String confpassword) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone_number = phone_number;
        this.confpassword = confpassword;
    }

}

*/