package com.carParkingNew.carParking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String name;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name="user_id", nullable = false)
		private User user;

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

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public UserRole(int id, String name, User user) {
			super();
			this.id = id;
			this.name = name;
			this.user = user;
		}
		
        public UserRole() {
			
		}
}
