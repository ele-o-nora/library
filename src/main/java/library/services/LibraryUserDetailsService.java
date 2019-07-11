package library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import library.dao.UserDAO;
import library.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter(onMethod=@__(@Autowired))
@NoArgsConstructor
public class LibraryUserDetailsService implements UserDetailsService {
	
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
	    builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
	    builder.roles("USER");
		return builder.build();
	}

}
