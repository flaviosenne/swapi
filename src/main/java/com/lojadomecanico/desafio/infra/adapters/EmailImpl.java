package com.lojadomecanico.desafio.infra.adapters;

import com.lojadomecanico.desafio.domain.entities.User;
import com.lojadomecanico.desafio.domain.exceptions.ServerErrorException;
import com.lojadomecanico.desafio.domain.protocols.EmailProtocol;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailImpl implements EmailProtocol {
    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;

    @Override
    public String templateRetrievePassword(User user, String code) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("code", code);
        return templateEngine.process("mail/mailRetrievePasswordTemplate", context);
    }

    @Override
    @Async
    public void sendEmail(String content, String subject,  String[] to) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( to );
            helper.setSubject( subject);
            helper.setText(content, true);
            mailSender.send(mail);

        } catch (Exception e) {
            throw  new ServerErrorException(e.getMessage());
        }
    }
}
