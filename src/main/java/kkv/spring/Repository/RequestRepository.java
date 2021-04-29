package kkv.spring.Repository;

import kkv.spring.models.Account;
import kkv.spring.models.Request;
import kkv.spring.models.RequestStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends CrudRepository<Request, Long> {

    public Optional<Request> findById(Long id);

    public List<Request> findByRequestStatus(RequestStatus requestStatus);

    public List<Request> findByUserAccount (Account account);

    public List<Request> findByEmployeeAccount (Account account);

    public void deleteById(Long id);

/*
    @Query("select u from User u where u.request_status = ?1")
    public Request findByRequestStatus(RequestStatus requestStatus);*/

}

