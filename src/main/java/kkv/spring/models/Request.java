package kkv.spring.models;




import javax.persistence.*;

@Entity
@Table(name="request")
public class Request {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="login", nullable=false)
    private Account userAccount;

    @ManyToOne
    private Account employeeAccount;

    private String locationFirstPassportSpread ;

    private String locationResidencePermitPassportSpread;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    public String getLocationFirstPassportSpread() {
        return locationFirstPassportSpread;
    }

    public void setLocationFirstPassportSpread(String locationFirstPassportSpread) {
        this.locationFirstPassportSpread = locationFirstPassportSpread;
    }

    public String getLocationResidencePermitPassportSpread() {
        return locationResidencePermitPassportSpread;
    }

    public void setLocationResidencePermitPassportSpread(String locationResidencePermitPassportSpread) {
        this.locationResidencePermitPassportSpread = locationResidencePermitPassportSpread;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Account getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(Account employeeAccount) {
        this.employeeAccount = employeeAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }
}

    /*@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String locationFirstPassportSpread ;

    private String locationResidencePermitPassportSpread;

    private RequestStatus requestStatus;

    private Account customerLogin;

    private Account employeeLogin;

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Account getCustomerLogin() {
        return customerLogin;
    }

    public void setCustomerLogin(Account customerLogin) {
        this.customerLogin = customerLogin;
    }

    public Account getEmployeeLogin() {
        return employeeLogin;
    }

    public void setEmployeeLogin(Account employeeLogin) {
        this.employeeLogin = employeeLogin;
    }

    public String getLocationFirstPassportSpread() {
        return locationFirstPassportSpread;
    }

    public void setLocationFirstPassportSpread(String locationFirstPassportSpread) {
        this.locationFirstPassportSpread = locationFirstPassportSpread;
    }

    public String getLocationResidencePermitPassportSpread() {
        return locationResidencePermitPassportSpread;
    }

    public void setLocationResidencePermitPassportSpread(String locationResidencePermitPassportSpread) {
        this.locationResidencePermitPassportSpread = locationResidencePermitPassportSpread;
    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }*/
