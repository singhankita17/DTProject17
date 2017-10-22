package com.yourstyle.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Address {

		@Id
		@GeneratedValue
		private int id;
		
		private String name;
		
		private String address1;
		
		private String address2;
		
		private String landmark;
		
		private String city;
		
		private String state;
		
		private int pincode;
		
		private String email;
		
		private long phone;
		
		private int personId;
		
		private String addressType;
		
		private Timestamp createdTimestamp;
		
		private String createdBy;
		
		private Timestamp updatedTimestamp;
		
		private String updatedBy;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress1() {
			return address1;
		}

		public void setAddress1(String address1) {
			this.address1 = address1;
		}

		public String getAddress2() {
			return address2;
		}

		public void setAddress2(String address2) {
			this.address2 = address2;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public int getPincode() {
			return pincode;
		}

		public void setPincode(int pincode) {
			this.pincode = pincode;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public long getPhone() {
			return phone;
		}

		public void setPhone(long phone) {
			this.phone = phone;
		}

		public String getAddressType() {
			return addressType;
		}

		public void setAddressType(String addressType) {
			this.addressType = addressType;
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

		public int getPersonId() {
			return personId;
		}

		public void setPersonId(int personId) {
			this.personId = personId;
		}

		public String getLandmark() {
			return landmark;
		}

		public void setLandmark(String landmark) {
			this.landmark = landmark;
		}
		
		
		
}
