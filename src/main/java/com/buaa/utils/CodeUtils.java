package com.buaa.utils;

import com.buaa.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class CodeUtils {
    @Resource
    JavaMailSender mailSender;

    @Resource
    RedisTemplate<String, User> redisTemplate;

    @Value("${spring.mail.username}")
    private String from;

    // 生成链接,并给接收的邮箱发送邮件
    public boolean sendCode(User user){
        MimeMessage message = mailSender.createMimeMessage();
        try{
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            // 生成UUID
            String token = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(token, user);
            redisTemplate.expire(token, 300, TimeUnit.SECONDS);
            //发送方的邮箱地址
            messageHelper.setFrom(from);
            // 接收方的邮箱地址
            messageHelper.setTo(user.getEmail());
            // 邮箱标题
            messageHelper.setSubject("注册");
            String html = "<html>\n" +
                    "<body>\n" +
                    "<p>您好，您的账号正在注册墨书</p>\n" +
                    "<a href=\"http://localhost/lookCode/" + token + "\">点此链接进行注册</a>" +
                    "</body>\n" +
                    "</html>";
            messageHelper.setText(html, true); // 邮箱内容
            System.out.println("邮件内容设置成功");
            System.out.println(message);
            mailSender.send(message);  // 发送邮箱
            System.out.println("发送成功");
            return true;
        }catch (Exception e){
            System.out.println("发送失败");
            e.printStackTrace();
            return false;
        }
    }

    // 判断token是否过期
    public boolean eqToken(String token){
        return redisTemplate.hasKey(token);
    }

    // 根据token查询用户的信息
    public User findUser(String token){
        return redisTemplate.opsForValue().get(token);
    }
}
