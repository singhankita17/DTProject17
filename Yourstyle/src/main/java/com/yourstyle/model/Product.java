package com.yourstyle.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Product {

		@Id
		@GeneratedValue
		private int id;
		
		private String productName;
		
		@Column(name = "productDesc", nullable = true, length = 2000)
		private String productDesc;
		
		private double price;
		
		private String brandName;
		
		private boolean inStock;
		
		private int quantityAvailable;
		
		private boolean onSale;
		
		private double salePrice;
		
		private Timestamp createdTimestamp;
		
		private String createdBy;
		
		private Timestamp updatedTimestamp;
		
		private String updatedBy;
		
		private int categoryId;
		
		private int supplierId;
		
		private String productImage;
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getProductDesc() {
			return productDesc;
		}

		public void setProductDesc(String productDesc) {
			this.productDesc = productDesc;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getBrandName() {
			return brandName;
		}

		public void setBrandName(String brandName) {
			this.brandName = brandName;
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

		public int getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(int categoryId) {
			this.categoryId = categoryId;
		}

		public int getSupplierId() {
			return supplierId;
		}

		public void setSupplierId(int supplierId) {
			this.supplierId = supplierId;
		}

		public boolean isInStock() {
			return inStock;
		}

		public void setInStock(boolean inStock) {
			this.inStock = inStock;
		}

		public int getQuantityAvailable() {
			return quantityAvailable;
		}

		public void setQuantityAvailable(int quantityAvailable) {
			this.quantityAvailable = quantityAvailable;
		}

		public boolean isOnSale() {
			return onSale;
		}

		public void setOnSale(boolean onSale) {
			this.onSale = onSale;
		}

		public double getSalePrice() {
			return salePrice;
		}

		public void setSalePrice(double salePrice) {
			this.salePrice = salePrice;
		}

		public String getProductImage() {
			return productImage;
		}

		public void setProductImage(String productImage) {
			this.productImage = productImage;
		}
		

}
