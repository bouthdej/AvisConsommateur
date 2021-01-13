
    
package projet.rest.data.security;


import java.nio.file.attribute.UserDefinedFileAttributeView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import projet.rest.data.models.UserEntity;
import projet.rest.data.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
    private UserRepository userRepo;
    @Override
public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException {
    UserEntity user =userRepo.findByUsername(username);
        if(user==null)
            throw new UsernameNotFoundException("User Not Found ! ! !");
        return new UserPrincipal(user);
    }

} 






