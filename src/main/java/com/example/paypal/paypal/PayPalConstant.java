package com.example.paypal.paypal;

import com.paypal.base.Constants;
import com.paypal.base.rest.APIContext;

public class PayPalConstant {

    // 应用ID
    public static String clientId = "ATSEc6C7jd1Z7Gq00K1YqFUy5_A2u1dCr1VrXS-GpCrsXbq25rL7LygUGrxZwjfTXnz_q-WnS6X5fJin";
    // 应用对应密码
    public static String clientSecret = "EBepptseg7B8sDJEtFJEC1JlsFDpzGFxIaELzCZQmCo_S84b1k1jq2Wt7MXhR4FZDpv2v53G4L89AQnj";
    //钩子ID
    public static final String WebhookId = "4JH86294D6297924G";
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
    static{
        apiContext.addConfiguration(Constants.PAYPAL_WEBHOOK_ID, WebhookId);
    }
    //支付方式，邮箱账号
    public static final int EMAIL=1;
    //支付方式，手机号码
    public static final int PHONE=2;
    //支付方式，加密PayPal账号
    public static final int PAYPAL_ID=3;
}
