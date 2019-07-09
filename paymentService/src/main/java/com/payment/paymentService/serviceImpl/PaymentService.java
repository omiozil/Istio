package com.payment.paymentService.serviceImpl;

import java.util.List;


import com.payment.paymentService.model.Payment;

public interface PaymentService {
	

	Payment getPaymentId();
	void createPay(Payment payment);
	
	List<Payment> getAllPaymentsDetails();
	
	void updatePaymentStatus(Payment payment);

	
	
	
	/*	void createPay(Payment payment);
	
	List<Payment> getAllPaymentsDetails();
	
	Payment findPaymentDetailsByID(int paymentId);
*/
}
