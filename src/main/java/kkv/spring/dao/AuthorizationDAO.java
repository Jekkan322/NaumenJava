package kkv.spring.dao;

import kkv.spring.models.Account;
import kkv.spring.models.AccountKey;
import kkv.spring.models.BankClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthorizationDAO {

    private Map<AccountKey, Account> dict;

    {
        dict = new HashMap<>();
        var a = new BankClient();
        a.setAccountKey(new AccountKey("a","a"));
        a.setDateOfBirth("18-01-2000");
        a.setName("ADMIN");
        a.setAdmin(true);

    }

    public boolean hasKey(AccountKey accountKey){
        return dict.containsKey(accountKey);
    }

    public Account getAccount(AccountKey accountKey){
        return dict.getOrDefault(accountKey,null);
    }

    public void create(AccountKey key,Account account){
        dict.put(key, account);
    }
}
