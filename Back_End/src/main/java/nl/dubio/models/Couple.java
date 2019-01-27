package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import nl.dubio.View;

import java.sql.Date;

public class Couple implements DatabaseObject<Couple> {

    @JsonProperty
    @JsonView(View.Public.class)
    private int id;
    @JsonProperty
    @JsonView(View.Public.class)
    private Date signupDate;
    @JsonProperty
    @JsonView(View.Public.class)
    private int parent1Id;
    @JsonProperty
    @JsonView(View.Public.class)
    private int parent2Id;
    @JsonProperty
    @JsonView(View.Private.class)
    private String password;
    @JsonProperty
    @JsonView(View.Private.class)
    private String token;
    @JsonProperty
    @JsonView(View.Private.class)
    private String passwordToken;

    public Couple (Date signupDate, int parent1Id, int parent2Id, String password, String token, String passwordToken) {
        this.signupDate = signupDate;
        this.parent1Id = parent1Id;
        this.parent2Id = parent2Id;
        this.password = password;
        this.token = token;
        this.passwordToken = passwordToken;
    }

    @JsonCreator
    public Couple
            (
                    @JsonProperty("id") int id,
                    @JsonProperty("signupDate") Date signupDate,
                    @JsonProperty("parent1Id") int parent1Id,
                    @JsonProperty("parent2Id") int parent2Id,
                    @JsonProperty("password") String password,
                    @JsonProperty("token") String token,
                    @JsonProperty("passwordToken") String passwordToken
            )
    {
        this.id = id;
        this.signupDate = signupDate;
        this.parent1Id = parent1Id;
        this.parent2Id = parent2Id;
        this.password = password;
        this.token = token;
        this.passwordToken = passwordToken;
    }

    public Date getSignupDate() { return signupDate; }
    public void setSignupDate(Date signupDate) { this.signupDate = signupDate; }
    public int getParent1Id() { return parent1Id; }
    public void setParent1Id(int parent1Id) { this.parent1Id = parent1Id; }
    public int getParent2Id() { return parent2Id; }
    public void setParent2Id(int parent2Id) { this.parent2Id = parent2Id; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getToken() { return token; }
    public String getPasswordToken() { return passwordToken; }
    public void setPasswordToken(String passwordToken) { this.passwordToken = passwordToken; }

    public void setToken(String token) { this.token = token; }

    @Override
    public String toString() {
        return "Couple{" +
                "id=" + id +
                ", signupDate=" + signupDate +
                ", parent1Id=" + parent1Id +
                ", parent2Id=" + parent2Id +
                '}';
    }
}
