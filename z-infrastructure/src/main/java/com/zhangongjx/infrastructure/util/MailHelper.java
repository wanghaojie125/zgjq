package com.zhangongjx.infrastructure.util;

import com.zhangongjx.infrastructure.domain.ResutlMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class MailHelper {

    @Autowired
    private JavaMailSender mailSender;

    public ResutlMsg sendEmail(String from, String to, String subject, String text) {
        ResutlMsg res = ResutlMsg.success();
        try {
            final MimeMessage mimeMessage = mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(mimeMessage);

            res.setMsg("发送成功");
        } catch (Exception ex) {
            res = ResutlMsg.error();
            res.setMsg("发送失败" + ex.getMessage());
        }
        return res;
    }
}
