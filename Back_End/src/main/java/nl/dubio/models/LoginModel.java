package nl.dubio.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class LoginModel {

    @JsonProperty
    @Length(min = 5, max = 20)
    private String email;
    @JsonProperty
    @Length(min = 8, max = 30)
    private String password;
    @JsonProperty
    private String type = "";
    @JsonProperty
    private String name = "";
    @JsonProperty
    private int right;

    public LoginModel(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
