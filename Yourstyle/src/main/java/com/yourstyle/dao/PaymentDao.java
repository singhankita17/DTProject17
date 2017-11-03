package com.yourstyle.dao;

import java.util.List;

import com.yourstyle.model.Payment;

public interface PaymentDao {
	
	public boolean savePaymentInfo(Payment payment);

	public Payment getPaymentInfo(int paymentId);
	
	public List<Payment> getUserPaymentInfo(int userId);
	
	public List<Payment> getUserCardPaymentInfo(int userId);
	
}
