package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.dubio.auth.Authorizable;

import java.sql.Date;

public class Admin implements DatabaseObject<Admin>, Authorizable {

    @JsonProperty
    private int id;
    @JsonProperty
    private String email;
    @JsonProperty
    private String password;
    @JsonProperty
    private int rightId;
    @JsonProperty
    private Date signupDate;
    @JsonProperty
    private String passwordToken;

    public Admin (String email, String password, int rightId) {
        this.email = email;
        this.password = password;
        this.rightId = rightId;
    }

    @JsonCreator
    public Admin
            (
                    @JsonProperty("id") int id,
                    @JsonProperty("email") String email,
                    @JsonProperty("password") String password,
                    @JsonProperty("rightId") int rightId,
                    @JsonProperty("signupDate") Date signupDate,
                    @JsonProperty("passwordToken") String passwordToken
            )
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.rightId = rightId;
        this.signupDate = signupDate;
        this.passwordToken = passwordToken;
    }

    public void setId(int id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRightId(int rightId) { this.rightId = rightId; }
    public void setSignupDate(Date signupDate) { this.signupDate = signupDate; }
    public void setPasswordToken(String passwordToken) { this.passwordToken = passwordToken; }
    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public int getRightId() { return rightId; }
    public String getPasswordToken() { return passwordToken; }
    public Date getSignupDate() { return signupDate; }



    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rightId=" + rightId +
                ", signupDate=" + signupDate +
                '}';
    }

    @Override
    public String getName() {
        return email;
    }
}

