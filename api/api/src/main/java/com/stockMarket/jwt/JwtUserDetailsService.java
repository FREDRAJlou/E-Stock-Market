package com.stockMarket.jwt;

import java.util.ArrayList;
import java.util.List;

import com.stockMarket.models.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		ResponseEntity<UserDetail> res = this.restTemplate.exchange("http://LOGINSERVICE/user/"+username,
				HttpMethod.GET, null, new ParameterizedTypeReference<UserDetail>() {
				});
		UserDetail user = res.getBody();
		System.out.print("got user "+res.getBody()+"---> "+user.getName());
		if (user!=null&&user.getName().equals(username)) {
			//encrypted fro password
			System.out.print("got user ");
			SimpleGrantedAuthority auth = new SimpleGrantedAuthority(user.getRole());
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			list.add(auth);			
			return new User(user.getName(), user.getPassword(),list);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
