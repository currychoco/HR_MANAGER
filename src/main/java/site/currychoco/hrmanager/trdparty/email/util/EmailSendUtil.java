package site.currychoco.hrmanager.trdparty.email.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@RequiredArgsConstructor
@Component
public class EmailSendUtil {

    private final JavaMailSender emailSender;

    @Value("${admin-mail.id}")
    private String adminMailId;

    public static final String ePw = createKey();

    private MimeMessage createMessage(String to) {

        MimeMessage  message = emailSender.createMimeMessage();

        try {

            message.addRecipients(Message.RecipientType.TO, to);//보내는 대상
            message.setSubject("이메일 인증번호를 확인해 주세요");//제목

            String msg = "";
            msg+= "<div style='margin:20px;'>";
            msg+= "<h1> 안녕하세요 파인애플입니다. </h1>";
            msg+= "<br>";
            msg+= "<p>아래 코드를 복사해 입력해주세요<p>";
            msg+= "<br>";
            msg+= "<p>감사합니다.<p>";
            msg+= "<br>";
            msg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
            msg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
            msg+= "<div style='font-size:130%'>";
            msg+= "CODE : <strong>";
            msg+= ePw+"</strong><div><br/> ";
            msg+= "</div>";
            message.setText(msg, "utf-8", "html");//내용
            message.setFrom(new InternetAddress(adminMailId,"파인애플 인사팀")); //보내는 사람
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }

    public String sendSimpleMessage(String to) {

        MimeMessage message = createMessage(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }
}
