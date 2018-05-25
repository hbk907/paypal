package com.example.paypal.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PayPalService {
    // 应用ID
    public static String clientId = "ATSEc6C7jd1Z7Gq00K1YqFUy5_A2u1dCr1VrXS-GpCrsXbq25rL7LygUGrxZwjfTXnz_q-WnS6X5fJin";
    // 应用对应密码
    public static String clientSecret = "EBepptseg7B8sDJEtFJEC1JlsFDpzGFxIaELzCZQmCo_S84b1k1jq2Wt7MXhR4FZDpv2v53G4L89AQnj";
    //sandbox 沙箱or live生产
    public static final String mode = "sandbox";
    //你的真实取消地址
    public static final String cancelUrl = "http://baidu.com";
    //你的paypal返回调用地址
    public static final String returnUrl = "http://baidu.com";
    //结算币种
    public static final String currency = "USD";
    //交易描述
    public static final String description = "test";
    //结算方式
    public static final String method = "paypal";
    //结算类型（立即结算）
    public static final String intent = "sale";
    //API调用上下文对象
    public static APIContext apiContext = new APIContext(clientId, clientSecret, mode);

    /**
     * 创建支付订单
     * @param total--金额
     * @throws PayPalRESTException
     */
    public static Payment createPayment(String total) throws PayPalRESTException {

        Payer payer = new Payer();
        payer.setPaymentMethod(method);
        Amount amount = new Amount();
        amount.setTotal(total);
        amount.setCurrency(currency);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(returnUrl);


        Payment payment = new Payment();
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        payment.setRedirectUrls(redirectUrls);
        payment.setIntent(intent);
        System.out.println(payment.create(apiContext).toString());
        return payment.create(apiContext);
    }

    /**
     * 执行交易支付
     * @param paymentId
     * @param payerId
     * @return
     * @throws PayPalRESTException
     */
    public static Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        System.out.println(apiContext.fetchAccessToken());
        return payment.execute(apiContext, paymentExecute);
    }

    public static void main(String[] args) {
        String payerId="56ENSS98ESZZS";
        String paymentId="PAY-6TN36693YN829380RLMDX2GA";
        try {
//            Payment pay=createPayment("2");
//            System.out.println(pay);
            System.out.println(executePayment(paymentId,payerId));
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
    }

}
