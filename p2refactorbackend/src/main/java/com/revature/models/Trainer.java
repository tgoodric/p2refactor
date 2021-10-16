package com.revature.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "trainer")
public class Trainer /*implements UserDetails*/ {	//brace yourself, this is about to get nasty
	
	//TODO: finish implementation of Spring Security, 3:07:18 of Amigoscode Spring Security full course
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trainer_id")
	private int userId; //serial primary key
	@Column(unique = true, name = "username")
	private String username; //unique
	@Column(name = "password")
	private String password; 
	/*//everything below added for Spring Security purposes
	@Column(name = "account_active")
	private boolean isAccountNonExpired; 							//only true for the project
	@Column(name = "granted_authorities")
	private List<? extends GrantedAuthority> grantedAuthorities; 	//everyone will be a standard user for the project
	@Column(name = "account_unlocked") 	
	private boolean isAccountNonLocked;  							//only true for the project
	@Column(name = "credentials_valid")
	private boolean isCredentialsNonExpired;						//only true for the project
	@Column(name = "is_enabled")
	private boolean isEnabled; 										//only true for the project
	*/
	//Constructors
	public Trainer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trainer(int userId, String username, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		//this.grantedAuthorities = grantAuthorities(); //for now this does nothing, I will implement it when/if I need to
		//this.isAccountNonExpired = true;
		//this.isAccountNonLocked = true;
		//this.isCredentialsNonExpired = true;
		//this.isEnabled = true;
	}

	
	
	public Trainer(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		//this.grantedAuthorities = grantAuthorities();
		//this.isAccountNonExpired = true;
		//this.isAccountNonLocked = true;
		//this.isCredentialsNonExpired = true;
		//this.isEnabled = true;
	}
	/*
	//Methods required for UserDetails interface
	private List<? extends GrantedAuthority> grantAuthorities() {
		// TODO: figure this nonsense out
		return null;
	}
	*/
	/*
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}
	*/
	
	public String getPassword() {
		return this.password;
	}
	
	
	public String getUsername() {
		return this.username;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + userId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trainer other = (Trainer) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trainer [userId=" + userId + ", username=" + username + ", password=" + password + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	/*
	public List<? extends GrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public void setGrantedAuthorities(List<? extends GrantedAuthority> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}
	*/
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/*
	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}
	
	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}*/
	
}
