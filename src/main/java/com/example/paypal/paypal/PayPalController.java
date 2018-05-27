package com.example.paypal.paypal;

import com.paypal.api.payments.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
//@RequestMapping(value="/paypal")
public class PayPalController {
    @Autowired
    public PayPalService paypalService;
    @Autowired
    public HttpServletRequest request;

    /**
     * 登陆首页
     * @return
     */
    @RequestMapping(value="/index.html")
    public String login(){
        return "index.html";
    }

    @RequestMapping(value="/paypal/webhook")
    @ResponseBody
    public String webhook(){
        return "{}";
    }

    /**
     * 创建支付订单
     * @return
     */
    @RequestMapping(value="/paypal/create")
    @ResponseBody
    public String createPayment(){
        String total="3";
        Payment payment=null;
        Map<String,Object> map=new HashMap<>();
        try{
            payment=paypalService.createPayment(total);
        }catch(Exception e){
            e.printStackTrace();
        }
        return payment==null ? "": payment.toJSON();
    }

    /**
     * 执行支付
     * @param paymentID
     * @param payerID
     * @return
     */
    @RequestMapping(value="/paypal/execute")
    @ResponseBody
    public String executePayment(@RequestParam("paymentID") String paymentID,@RequestParam("payerID") String payerID){
        System.out.println("payerID="+payerID+" ; paymentID="+paymentID);
        Payment payment=null;
        try {
            payment=paypalService.executePayment(paymentID, payerID);
        }catch(Exception e){
            e.printStackTrace();
        }
        return payment==null ? "": payment.toJSON();
    }

}
