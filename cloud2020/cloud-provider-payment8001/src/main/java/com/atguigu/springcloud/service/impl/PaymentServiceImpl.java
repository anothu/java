package com.atguigu.springcloud.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Override
	public int create(Payment payment) {
		// TODO Auto-generated method stub
		return paymentDao.create(payment);
	}

	@Override
	public Payment getPaymentById(Long id) {
		// TODO Auto-generated method stub
		return paymentDao.getPaymentById(id);
	}

	@Resource
	private PaymentDao paymentDao;

}
