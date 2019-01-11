package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.dubio.auth.Authorizable;

import java.security.Principal;

public class Parent implements DatabaseObject<Parent>, Authorizable {

    @JsonProperty
    private int id;
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String phoneNr;
    @JsonProperty
    private String email;

    public Parent (String firstName, String phoneNr, String email) {
        this.phoneNr = phoneNr;
        this.firstName = firstName;
        this.email = email;
    }

    @JsonCreator
    public Parent
    (
        @JsonProperty("id") int id,
        @JsonProperty("phoneNr") String phoneNr,
        @JsonProperty("firstName") String firstName,
        @JsonProperty("email") String email
    )
    {
        this.id = id;
        this.phoneNr = phoneNr;
        this.firstName = firstName;
        this.email = email;
    }

    public String getPhoneNr() { return phoneNr; }
    public void setPhoneNr(String phoneNr) { this.phoneNr = phoneNr; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", phoneNr='" + phoneNr + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public String getName() {
        return email;
    }
}
