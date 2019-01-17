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
    @JsonProperty
    private String token;

    public Parent (String firstName, String email, String phoneNr) {
        this.firstName = firstName;
        this.email = email;
        this.phoneNr = phoneNr;
    }

    @JsonCreator
    public Parent
    (
        @JsonProperty("id") int id,
        @JsonProperty("firstName") String firstName,
        @JsonProperty("phoneNr") String phoneNr,
        @JsonProperty("email") String email,
        @JsonProperty("token") String token
    )
    {
        this.id = id;
        this.phoneNr = phoneNr;
        this.firstName = firstName;
        this.email = email;
        this.token = token;
    }

    public String getPhoneNr() { return phoneNr; }
    public void setPhoneNr(String phoneNr) { this.phoneNr = phoneNr; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getToken() { return this.token; }
    public void setToken(String token) { this.token = token; }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", phoneNr='" + phoneNr + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + "\'" +
                '}';
    }

    @Override
    public String getName() {
        return email;
    }
}
