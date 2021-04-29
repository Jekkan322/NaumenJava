package kkv.spring.Repository;


import kkv.spring.models.Account;
import kkv.spring.models.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    public Account findByLogin(String login);

    public List<Account> findAll();



}
