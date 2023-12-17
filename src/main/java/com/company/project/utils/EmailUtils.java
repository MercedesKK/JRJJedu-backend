package com.company.project.utils;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

@Configuration
@Component
public class EmailUtils {


    /**
     * 发送短信
     * @param email 接收人邮箱
     * @param account 账号
     * @param password 密码
     * @return
     */
    public Result sendMail(String email, String account, String password) throws GeneralSecurityException, MessagingException {
        //创建一个配置文件并保存
        Properties properties = new Properties();

        properties.setProperty("mail.host","smtp.qq.com");

        properties.setProperty("mail.transport.protocol","smtp");

        properties.setProperty("mail.smtp.auth","true");

        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1061076142@qq.com","mdlozgfpydrwbege");
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();

        //连接服务器
        transport.connect("smtp.qq.com","1061076142@qq.com","mdlozgfpydrwbege");

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress("1061076142@qq.com"));

        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(email));

        //邮件标题
        mimeMessage.setSubject("教师匿名评测系统给您发送邮件了，请注意查收");

        //邮件内容
        mimeMessage.setContent("您的账号为："+ account + " 您的初始密码为：" + password,"text/html;charset=UTF-8");
        //邮件内容
        //mimeMessage.setContent("您的问卷"+ url +" ，已生成请点击链接前往查看","text/html;charset=UTF-8");

        //发送邮件
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

        //关闭连接
        transport.close();

        return ResultGenerator.genSuccessResult();
    }

}
