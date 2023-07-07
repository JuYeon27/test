package com.kgcoffee.web.order.dto;

import java.util.List;

import com.kgcoffee.web.order.domain.OrderVO;
import com.kgcoffee.web.order.domain.PaymentsVO;

public class OrderListDTO {
	
	OrderVO order;
	
	List<PaymentsVO> paymentsList;

	public OrderListDTO() {
		// TODO Auto-generated constructor stub
	}

	
	public OrderVO getOrder() {
		return order;
	}

	public void setOrder(OrderVO order) {
		this.order = order;
	}

	public List<PaymentsVO> getPaymentsList() {
		return paymentsList;
	}

	public void setPaymentsListList(List<PaymentsVO> orderDetailList) {
		this.paymentsList = orderDetailList;
	}

	public OrderListDTO(OrderVO order, List<PaymentsVO> paymentsList) {
		super();
		this.order = order;
		this.paymentsList = paymentsList;
	}
	
	


	
	

}
