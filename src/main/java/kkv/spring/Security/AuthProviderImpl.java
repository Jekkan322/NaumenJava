package kkv.spring.Security;

import kkv.spring.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    @Autowired
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
        if(!pswrd.equals(account.getPassword())){
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
