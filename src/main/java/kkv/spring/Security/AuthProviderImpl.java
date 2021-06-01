package kkv.spring.Security;

import kkv.spring.Repository.AccountRepository;
import kkv.spring.models.Account;
import kkv.spring.models.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    @Autowired
    public AuthProviderImpl(PasswordEncoder passwordEncoder,
                            AccountRepository accountRepository){
        this.passwordEncoder=passwordEncoder;
        this.accountRepository = accountRepository;
        if(accountRepository.findByLogin("admin@mail.ru")==null)
            accountRepository.save(new Account(
                    "admin@mail.ru",
                    passwordEncoder.encode("admin"),
                    Arrays.asList(Roles.ADMIN)));
    }
    private PasswordEncoder passwordEncoder;

    private AccountRepository accountRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("VHOD");
        var login = authentication.getName();
        var account = accountRepository.findByLogin(login);
        if(account==null){
            System.out.println("bad login");
            throw new UsernameNotFoundException("bad login");
        }
        var pswrd = authentication.getCredentials().toString();
        if(!passwordEncoder.matches(pswrd,account.getPassword())){
            System.out.println("bad password");
            System.out.println(pswrd+" " +account.getPassword());
            throw new BadCredentialsException("bad password");
        }
        var authorities = new ArrayList<GrantedAuthority>();
        for(var e : account.getRolesSet())
            authorities.add(new SimpleGrantedAuthority("ROLE_"+e.name()));
        return new UsernamePasswordAuthenticationToken(authentication.getName(),null,authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
