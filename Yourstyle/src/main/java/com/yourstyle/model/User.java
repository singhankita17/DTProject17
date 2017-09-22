package com.yourstyle.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "USERS")
@Component
public class User {

		@Id
		@GeneratedValue
		private int Id;
		
		@Column(name = "first_name")
		private String firstName;
		
		@Column(name = "last_name")
		private String lastName;
		
		@Column
		private String email;
		
		@Column
		private String password;
		
		@Column
		private Long phone;
		
		@Column
		private String role;
		
		@Column
		private Boolean enabled;
		
		@Column(name = "Created_Timestamp")
		private Timestamp createdTimestamp;
		
		@Column(name = "Created_By")
		private String createdBy;
		
		@Column(name = "Updated_Timestamp")
		private Timestamp updatedTimestamp;
		
		@Column(name = "Updated_By")
		private String updatedBy;
		
		@OneToMany(fetch = FetchType.EAGER, mappedBy = "personId", cascade = CascadeType.ALL)
		private Set<Address> userAddress = new HashSet<Address>(0);
		

		public User(int id, String firstName, String lastName, String email, String password, String role,
				Boolean enabled, Timestamp createdTimestamp, String createdBy, Timestamp updatedTimestamp,
				String updatedBy) {
			super();
			Id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
			this.role = role;
			this.enabled = enabled;
			this.createdTimestamp = createdTimestamp;
			this.createdBy = createdBy;
			this.updatedTimestamp = updatedTimestamp;
			this.updatedBy = updatedBy;
		}

		public User() {
			
		}

		@Override
		public String toString() {
			
			return Id+" "+firstName+" "+lastName+" "+email+" "+password+" "+role+" "+enabled;
		}

		public int getId() {
			return Id;
		}

		public void setId(int id) {
			Id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public Boolean getEnabled() {
			return enabled;
		}

		public void setEnabled(Boolean enabled) {
			this.enabled = enabled;
		}

		public Timestamp getCreatedTimestamp() {
			return createdTimestamp;
		}

		public void setCreatedTimestamp(Timestamp createdTimestamp) {
			this.createdTimestamp = createdTimestamp;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public Timestamp getUpdatedTimestamp() {
			return updatedTimestamp;
		}

		public void setUpdatedTimestamp(Timestamp updatedTimestamp) {
			this.updatedTimestamp = updatedTimestamp;
		}

		public String getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}

		public Set<Address> getUserAddress() {
			return userAddress;
		}

		public void setUserAddress(Set<Address> userAddress) {
			this.userAddress = userAddress;
		}

		public Long getPhone() {
			return phone;
		}

		public void setPhone(Long phone) {
			this.phone = phone;
		}

		
				
}
