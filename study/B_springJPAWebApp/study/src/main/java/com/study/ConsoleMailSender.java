package com.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;

/**
 * 가짜 객체
 * 콘솔에 출력을 위해
 * 이후 진짜 객체로 바뀌도록
 */
@Profile("local")  // 특정 프로파일에서만 빈이나 설정을 활성화하도록 지정하는 역할
@Component  // 컴포넌트 스캔에 의해 Spring 애플리케이션 컨텍스트에 빈으로 등록
@Slf4j  // 이름의 로거(Logger) 객체를 자동으로 생성하고 사용
public class ConsoleMailSender implements JavaMailSender {
    @Override
    public MimeMessage createMimeMessage() {
        return null;
    }

    @Override
    public MimeMessage createMimeMessage(InputStream inputStream) throws MailException {
        return null;
    }

    @Override
    public void send(MimeMessage mimeMessage) throws MailException {

    }

    @Override
    public void send(MimeMessage... mimeMessages) throws MailException {

    }

    @Override
    public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {

    }

    @Override
    public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {

    }

    @Override
    public void send(SimpleMailMessage simpleMailMessage) throws MailException {
        // 로그로 출력
        log.info(simpleMailMessage.getText());
    }

    @Override
    public void send(SimpleMailMessage... simpleMailMessages) throws MailException {

    }
}
