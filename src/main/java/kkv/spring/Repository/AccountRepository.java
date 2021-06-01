package kkv.spring.Repository;

import kkv.spring.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    public Account findByLogin(String login);

    public List<Account> findAll();
}
