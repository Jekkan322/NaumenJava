package kkv.spring.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AccountKey implements Comparable<AccountKey> {

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String login;

    @Size(min = 4, max = 16, message = "Password should be between 4 and 16 characters")
    private String password;

    public AccountKey(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public AccountKey() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        var res=0;
        for (var e=1;e<=login.length();e++)
            res+=login.toCharArray()[e-1]*e;
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AccountKey))
            return false;
        var res = (AccountKey)obj;
        return res.getPassword().equals(password) &&res.getLogin().equals(login);
    }

    @Override
    public int compareTo(AccountKey o) {
        return o.getLogin().equals(login) && o.getPassword().equals(password)? 0:-1;
    }
}
