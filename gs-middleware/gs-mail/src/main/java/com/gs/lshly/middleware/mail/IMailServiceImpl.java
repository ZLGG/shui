package com.gs.lshly.middleware.mail;

import cn.hutool.extra.mail.GlobalMailAccount;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import org.springframework.stereotype.Service;

@Service
public class IMailServiceImpl implements IMailService {



    @Override
    public void send(Email mail){
        MailUtil.send(mail.getTo(), mail.getSubject(), mail.getContent(), mail.isHtml());
    }

    @Override
    public void send(MailAccount account, Email mail) {
        MailUtil.send(account, mail.getTo(), mail.getSubject(), mail.getContent(), mail.isHtml());
    }

    public static void main(String[] args) {
        IMailServiceImpl mailService = new IMailServiceImpl();
//
//        MailAccount account = new MailAccount();
//        account.setHost("smtp.126.com");
//        account.setPort(25);
//        account.setAuth(true);
//        account.setFrom("xulian891217@126.com");
//        account.setUser("xulian891217");
//        account.setPass("SPGCYKRLVDQVQWYQ");

//        mailService.send(
//                new Email().setTo("380326768@qq.com")
//                .setSubject("Java开发测试")
//                .setContent("你好,这里是测试邮件发送"));

        mailService.send(
                new Email().setTo("380326768@qq.com")
                        .setSubject("Java开发测试HTML")
                        .setContent("你好,这里是测试邮件发送<br/><a href='www.baidu.com'>baidu</a>").setHtml(true));


        MailAccount account = GlobalMailAccount.INSTANCE.getAccount();
        System.out.println("host:" + account.getHost());
        System.out.println("port:" + account.getPort());
        System.out.println("auth:" + account.isAuth());
        System.out.println("from:" + account.getFrom());
        System.out.println("user:" + account.getUser());
        System.out.println("pass:" + account.getPass());
    }

}
