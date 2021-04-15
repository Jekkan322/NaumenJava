/*
package kkv.spring.DAO;

import kkv.spring.models.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AuthorizationDAO {

    @Autowired
    public AuthorizationDAO(){
        dict = new HashMap<>();

        a.setAccountKey(new AccountKey("kefirchik.2000@mail.ru","a"));
        a.setDateOfBirth("18-01-2000");
        a.setName("ADMIN");
        a.setAdmin(true);
        dict.put(a.getAccountKey().getLogin(),a);

        b.setAccountKey(new AccountKey("123","123"));
        b.setDateOfBirth("18-01-2000");
        b.setName("KIRILL");
        dict.put(b.getAccountKey().getLogin(),b);

    }
    private static Map<String, Account> dict;


    public List<Account> getUsers(){
        return dict.values().stream().filter(x->!x.isAdmin()).collect(Collectors.toList());
    }

    public boolean hasKey(AccountKey accountKey){
        try {
            return dict.containsKey(accountKey.getLogin()) && dict.get(accountKey.getLogin()).getAccountKey().getPassword().equals(accountKey.getPassword());
        }catch (Exception e){
            return false;
        }

    }

    public boolean hasEmail(AccountKey accountKey){
        return dict.containsKey(accountKey.getLogin());
    }

    public Account getAccount(AccountKey accountKey){
        if(!dict.containsKey(accountKey.getLogin()))
            return null;
        var key = dict.get(accountKey.getLogin());
        return key.getAccountKey().getPassword().equals(accountKey.getPassword())? key:null;
    }

    public Account getAccount(String login){
        return dict.getOrDefault(login,null);
    }

    public void create(AccountKey key,Account account){
        dict.put(key.getLogin(), account);
    }
}
*/
