package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;

public class CoupleRegistry {

    @JsonProperty
    @Length(min = 2, max = 20)
    private String firstName1;
    @JsonProperty
    @Length(min = 10, max = 15)
    private String phoneNr1;
    @JsonProperty
    @Length(max = 50)
    private String email1;
    @JsonProperty
    @Length(min = 2, max = 20)
    private String firstName2;
    @JsonProperty
    @Length(min = 10, max = 15)
    private String phoneNr2;
    @JsonProperty
    @Length(max = 50)
    private String email2;
    @JsonProperty
    private boolean isBorn;
    @JsonProperty
    private Date date;
    @JsonProperty()
    @Length(min = 8)
    private String password;

    public CoupleRegistry(
        @JsonProperty("firstName1") String firstName1,
        @JsonProperty("phoneNr1") String phoneNr1,
        @JsonProperty("email1") String email1,
        @JsonProperty("firstName2") String firstName2,
        @JsonProperty("phoneNr2") String phoneNr2,
        @JsonProperty("email2") String email2,
        @JsonProperty("isBorn") boolean isBorn,
        @JsonProperty("date") long date,
        @JsonProperty("password") String password
    ) {
        this.phoneNr1 = phoneNr1;
        this.firstName1 = firstName1;
        this.email1 = email1;
        this.phoneNr2 = phoneNr2;
        this.firstName2 = firstName2;
        this.email2 = email2;
        this.isBorn = isBorn;
        this.date = new Date(date);
        this.password = password;
    }

    public String getPhoneNr1() { return phoneNr1; }
    public void setPhoneNr1(String phoneNr1) { this.phoneNr1 = phoneNr1; }
    public String getFirstName1() { return firstName1; }
    public void setFirstName1(String firstName1) { this.firstName1 = firstName1; }
    public String getEmail1() { return email1; }
    public void setEmail1(String email1) { this.email1 = email1; }
    public String getPhoneNr2() { return phoneNr2; }
    public void setPhoneNr2(String phoneNr2) { this.phoneNr2 = phoneNr2; }
    public String getFirstName2() { return firstName2; }
    public void setFirstName2(String firstName2) { this.firstName2 = firstName2; }
    public String getEmail2() { return email2; }
    public void setEmail2(String email2) { this.email2 = email2; }
    public boolean getIsBorn() { return isBorn; }
    public void setBorn(boolean born) { isBorn = born; }
    public Date getDate() { return date; }
    public void setDate(long date) { this.date = new Date(date); }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "CoupleRegistry{" +
                "firstName1='" + firstName1 + '\'' +
                ", phoneNr1='" + phoneNr1 + '\'' +
                ", email1='" + email1 + '\'' +
                ", firstName2='" + firstName2 + '\'' +
                ", phoneNr2='" + phoneNr2 + '\'' +
                ", email2='" + email2 + '\'' +
                ", isBorn=" + isBorn +
                ", date=" + date +
                '}';
    }
}
