package org.trams.hello.web.common;

import java.util.ArrayList;
import java.util.List;

import org.trams.hello.bean.web.counselor.PaymentStatuss;

public class UtilsConstant {
	public static List<PaymentStatuss> getPaymentStatus(){
		List<PaymentStatuss> list = new ArrayList<>();
		for (int i = 1; i < 6; i++) {
			PaymentStatuss p = new PaymentStatuss();
			p.setStatus(i);
			if (i == 1) {
				p.setTitle("결제 대기");
			}
			if (i == 2) {
				p.setTitle("결제 완료");
			}
			if (i == 3) {
				p.setTitle("결제 포기");	
			}
			if (i == 4) {
				p.setTitle("결제 취소");
			}
			if (i == 5) {
				p.setTitle("요청 취소");
			}
			list.add(p);
		}
		return list;
	}
}
