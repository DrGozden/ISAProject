package ftn.pharmacyX.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ftn.pharmacyX.model.users.User;
import ftn.pharmacyX.serviceImpl.UserServiceImpl;


@Service
public class MyUserDtailsService implements UserDetailsService {

	@Autowired
	UserServiceImpl service;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final User user = service.findByEmail(email);

		UserDetails details = new UserDetails() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isEnabled() {

				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public boolean isAccountNonExpired() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				return user.getEmail(); 
			}

			@Override
			public String getPassword() {

				return user.getPassword();
			}

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				if (user.getUserRole() == null) {
					return new ArrayList<>();
				}
				final List<GrantedAuthority> authorities = Collections
						.singletonList(new SimpleGrantedAuthority(user.getUserRole().toString()));
				return authorities;
			}
		};

		return details;

	}

}
