package org.trams.hello.common.mail;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.Map;

public class Mail {

    private MailSender mailSender;
    private SimpleMailMessage simpleMailMessage;

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void sendMail(String to, String subject) {
        try {
            System.setProperty("mail.mime.charset", "utf8");
            SimpleMailMessage message = new SimpleMailMessage(simpleMailMessage);
            message.setTo(to);
            message.setText(String.format(simpleMailMessage.getText(), subject));
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public static boolean sendEmail(String toEmail, String content) {
        try {
            if (toEmail != null) {
                ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
                JavaMailSenderImpl sender = (JavaMailSenderImpl) context.getBean("mailSender");
                System.out.println(sender.getUsername() + "|" + sender.getPassword() + "|" + sender.getHost());
                Mail mm = (Mail) context.getBean("mail");
                mm.sendMail(toEmail, content);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return false;
    }

    public enum EmailTemplate {
        AUTHEN_REGISTER_EMAIL("velocity/authen_register_email.vm"),
        FINDPW_VERYFY_EMAIL("velocity/findpw_verify_email.vm"),
        FINDPW_EMAIL("velocity/findpw_email.vm"),
        AUTHEN_FINDPWD("velocity/authen_findpwd.vm"),
        AUTHEN_TMPPWD("velocity/authen_tmppwd.vm"),
        ACCEPT_COUNSELOR("velocity/accept_counselor.vm"),
    	REFUSE_COUNSELOR("velocity/refuse_counselor.vm"),
    	REFUSE_COUNSELOR_CHANGE_PROFILE("velocity/refuse_change_profile_counselor.vm"),
    	COMPLETE_PSYCHOLOGICAL_TEST("velocity/complete_payment_psychological_test.vm"),
    	ACCEPT_PROMOTION_PAGE("velocity/accept_promotion_page.vm"),
    	REFUSE_PROMOTION_PAGE("velocity/refuse_promotion_page.vm"),
    	BAN_ACCOUNT_MEMBER("velocity/ban_account_member.vm"),
    	BAN_ACCOUNT_COUNSELOR("velocity/ban_account_counselor.vm"),
    	CHANGE_ACCOUNT_TO_DORMANT_ACCOUNT("velocity/change_account_to_dormant_account.vm"),
    	;
        String template;

        EmailTemplate(String template) {
            this.template = template;
        }

        public String getTemplate() {
            return template;
        }
    }

    public static void sendEmailTemplate(String to, Map<String, Object> params, EmailTemplate template, String subject) {
    	try {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
        VelocityEngine velocityEngine = (VelocityEngine) context.getBean("velocityEngine");

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(to);
                message.setFrom("hello@aimmed.com");
                message.setSubject(params.getOrDefault("subject", subject).toString());

                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template.template, "UTF-8", params);

                message.setText(text, true);
            }
        };

        JavaMailSenderImpl sender = (JavaMailSenderImpl) context.getBean("mailSender");
        sender.setDefaultEncoding("UTF-8");
        sender.send(preparator);
        System.out.println("Send email success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
