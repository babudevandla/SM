package com.sm.portal.constants;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.sm.portal.model.UsersDto;

@Component
public class EmailSender {
	
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(EmailSender.class);
	
	public static String host;
	
	@Autowired
	private JavaMailSender mailSender;
	
	//Email Credentials

	@Value("${EmailContactus}")
	private String contactus;
	
	@Autowired
	public  VelocityEngine velocityEngine;
	

	public void sendUserRegisterConformation(final UsersDto users, final Email email, final VelocityEngine velocityEngine,final HttpServletRequest request){
		  
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				Map<String, Object> model = new HashMap<String, Object>();
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
				
				model.put("home", host);
				model.put("user",users);
				message.setTo(email.getMailto());
				message.setFrom(new InternetAddress(contactus));
				message.setSubject("User Registration Confirmation!");
				String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"welcomeUser.vm", "UTF-8", model);
				message.setText(body, true);
				FileSystemResource res = new FileSystemResource(new File(request.getServletContext().getRealPath("/resources/default/images/logo.png")));
				message.addInline("cmpLogo", res);
			}
		};
		JavaMailSenderImpl mailSender2=(JavaMailSenderImpl) mailSender;
		mailSender2.setHost("smtp.gmail.com");
		mailSender2.setPort(587);
		mailSender2.setUsername("softiddev@gmail.com");
		mailSender2.setPassword("ganesh@softid");
		mailSender.send(preparator);
		System.out.println("Register successfully");
	}


	/** ContactUs sending EMAILS **//*
	/* public void sendContactUsEmail(final Email email, final ContactUs contactUs,
			final SmtpDetails smtp, final VelocityEngine velocityEngine, final HttpServletRequest request,final EmailTemplateContent emailTemplatesContent) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				Map<String, Object> model = new HashMap<String, Object>();
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
				
				String tableContent1 = emailTemplatesContent.getTemplateBody().replace("${firstName}", contactUs.getParentName());
				String tableContent  = tableContent1.replace("${message}", contactUs.getMessage());
				model.put("home", host);
				model.put("tableBody", tableContent);
				message.setTo(new InternetAddress(contactus));
				message.setFrom(email.getFrom());
				message.setSubject("contact Us");
				String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"contactUsBody.vm", "UTF-8", model);
				message.setText(body, true);
				FileSystemResource res = new FileSystemResource(new File(request.getServletContext().getRealPath("/resources/users/images/site-logo.png")));
				message.addInline("contactImg", res);
			}
		};
		JavaMailSenderImpl mailSender2=(JavaMailSenderImpl) mailSender;
		mailSender2.setHost(smtp.getHostname());
		mailSender2.setPort(smtp.getPortno());
		mailSender2.setUsername(smtp.getUsername());
		mailSender2.setPassword(smtp.getPassword());
		mailSender.send(preparator);
		System.out.println("Contastus sent successfully");
	}




	*//** Tutor REGISTRATION EMAILS **//*
	
	public void sendUserRegisterConformation(final UserInfo userInfo, final VelocityEngine velocityEngine,final HttpServletRequest request,final EmailTemplateContent emailTemplatesContent,final SmtpDetails smtp){
		  
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				Map<String, Object> model = new HashMap<String, Object>();
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
				
				String tableContent1 = emailTemplatesContent.getTemplateBody().replace("${email}", userInfo.getEmail());
				tableContent1=tableContent1.replace("${userName}", userInfo.getFirstname()+" "+userInfo.getLastname());
				model.put("home", host);
				model.put("user",userInfo);
				model.put("tableBody", tableContent1);
				message.setTo(userInfo.getEmail());
				message.setFrom(new InternetAddress(contactus));
				message.setSubject(emailTemplatesContent.getTemplateSubject());
				String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"GenerateRegistrationUserConformation.vm", "UTF-8", model);
				message.setText(body, true);
				FileSystemResource res = new FileSystemResource(new File(request.getServletContext().getRealPath("/resources/users/images/site-logo.png")));
				message.addInline("contactImg", res);
			}
		};
		JavaMailSenderImpl mailSender2=(JavaMailSenderImpl) mailSender;
		mailSender2.setHost(smtp.getHostname());
		mailSender2.setPort(smtp.getPortno());
		mailSender2.setUsername(smtp.getUsername());
		mailSender2.setPassword(smtp.getPassword());
		mailSender.send(preparator);
		System.out.println("Register successfully");
	}

	*//** Tutor Test Qualified EMAILS **//*
	public void testQualifiedUser(final UserInfo user, final MessageDto messageDto,final VelocityEngine velocityEngine2,final HttpServletRequest request,final EmailTemplateContent emailTemplatesContent,final SmtpDetails smtp) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				Map<String, Object> model = new HashMap<String, Object>();
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
				
				String tableContent1 = emailTemplatesContent.getTemplateBody().replace("${userName}", user.getFirstname()+" "+user.getLastname());
				tableContent1=tableContent1.replace("${marks}",messageDto.getMarks().toString());
				model.put("home", host);
				model.put("user",user);
				model.put("tableBody", tableContent1);
				message.setTo(user.getEmail());
				message.setFrom(new InternetAddress(contactus));
				message.setSubject(emailTemplatesContent.getTemplateSubject());
				String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"testQualifiedUser.vm", "UTF-8", model);
				message.setText(body, true);
				FileSystemResource res = new FileSystemResource(new File(request.getServletContext().getRealPath("/resources/users/images/site-logo.png")));
				message.addInline("contactImg", res);
			}
		};
		JavaMailSenderImpl mailSender2=(JavaMailSenderImpl) mailSender;
		mailSender2.setHost(smtp.getHostname());
		mailSender2.setPort(smtp.getPortno());
		mailSender2.setUsername(smtp.getUsername());
		mailSender2.setPassword(smtp.getPassword());
		mailSender.send(preparator);
		System.out.println("Email send successfully");
	}




	*//** Tutor Test Failure EMAILS **//*
	public void testFailureUser(final UserInfo user,final MessageDto messageDto,final VelocityEngine velocityEngine2,final HttpServletRequest request,final EmailTemplateContent emailTemplatesContent,final SmtpDetails smtp) {
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				Map<String, Object> model = new HashMap<String, Object>();
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
				
				String tableContent1 = emailTemplatesContent.getTemplateBody().replace("${userName}", user.getFirstname()+" "+user.getLastname());
				tableContent1=tableContent1.replace("${marks}",messageDto.getMarks().toString());
				model.put("home", host);
				model.put("user",user);
				model.put("tableBody", tableContent1);
				message.setTo(user.getEmail());
				message.setFrom(new InternetAddress(contactus));
				message.setSubject(emailTemplatesContent.getTemplateSubject());
				String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"GenerateRegistrationUserConformation.vm", "UTF-8", model);
				message.setText(body, true);
				FileSystemResource res = new FileSystemResource(new File(request.getServletContext().getRealPath("/resources/users/images/site-logo.png")));
				message.addInline("contactImg", res);
			}
		};
		JavaMailSenderImpl mailSender2=(JavaMailSenderImpl) mailSender;
		mailSender2.setHost(smtp.getHostname());
		mailSender2.setPort(smtp.getPortno());
		mailSender2.setUsername(smtp.getUsername());
		mailSender2.setPassword(smtp.getPassword());
		mailSender.send(preparator);
		System.out.println("Email send successfully");
		
	}


	*//** Student REGISTRATION Success EMAILS **//*
	public void sendStudentRegisterConformation(final StudentInformationDto studentInformationDto,final VelocityEngine velocityEngine2,final HttpServletRequest request,final EmailTemplateContent emailTemplatesContent,final SmtpDetails smtp) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				Map<String, Object> model = new HashMap<String, Object>();
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
				
				String tableContent1 = emailTemplatesContent.getTemplateBody().replace("${email}", studentInformationDto.getEmail());
				tableContent1=tableContent1.replace("${userName}", studentInformationDto.getFirstname()+" "+studentInformationDto.getLastname());
				model.put("home", host);
				model.put("user",studentInformationDto);
				model.put("tableBody", tableContent1);
				message.setTo(studentInformationDto.getEmail());
				message.setFrom(new InternetAddress(contactus));
				message.setSubject(emailTemplatesContent.getTemplateSubject());
				String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"StudentRegistrationSuccess.vm", "UTF-8", model);
				message.setText(body, true);
				FileSystemResource res = new FileSystemResource(new File(request.getServletContext().getRealPath("/resources/users/images/site-logo.png")));
				message.addInline("contactImg", res);
			}
		};
		JavaMailSenderImpl mailSender2=(JavaMailSenderImpl) mailSender;
		mailSender2.setHost(smtp.getHostname());
		mailSender2.setPort(smtp.getPortno());
		mailSender2.setUsername(smtp.getUsername());
		mailSender2.setPassword(smtp.getPassword());
		mailSender.send(preparator);
		System.out.println("Register successfully");
		
	}

	*//** Student Schedule Conformation EMAILS **//*
	public void sendStudentScheduleConformation(final StudentInformationDto studentInfo, final VelocityEngine velocityEngine2,final HttpServletRequest request,final EmailTemplateContent emailTemplatesContent,final SmtpDetails smtp) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				Map<String, Object> model = new HashMap<String, Object>();
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
				
				String tableContent1 = emailTemplatesContent.getTemplateBody().replace("${scheduleDate}", studentInfo.getStudentScheduleDate());
				String tableContent2=tableContent1.replace("${schedulestartTime}",studentInfo.getStudentscheduleFromTime());
				String tableContent3 = tableContent2.replace("${scheduleendTime}", studentInfo.getStudentscheduleToime());
				String tableContent4 = tableContent3.replace("${tutorName}", studentInfo.getFirstname()+" "+studentInfo.getLastname());
				model.put("home", host);
				model.put("tableBody", tableContent4);
				message.setTo(studentInfo.getStudentEmail());
				message.setFrom(new InternetAddress(contactus));
				message.setSubject(emailTemplatesContent.getTemplateSubject());
				String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"GenerateStudentScheduleConformation.vm", "UTF-8", model);
				message.setText(body, true);
				FileSystemResource res = new FileSystemResource(new File(request.getServletContext().getRealPath("/resources/users/images/site-logo.png")));
				message.addInline("studentScheduleImg", res);
			}
		};
		JavaMailSenderImpl mailSender2=(JavaMailSenderImpl) mailSender;
		mailSender2.setHost(smtp.getHostname());
		mailSender2.setPort(smtp.getPortno());
		mailSender2.setUsername(smtp.getUsername());
		mailSender2.setPassword(smtp.getPassword());
		mailSender.send(preparator);
		System.out.println("Email send successfully");
		
		
	}

	*//** User Reset Password After enter six times wrong EMAILS **//*
	public void sendResetPasswordEmail(final String userName, final String url,final SmtpDetails smtp,final  EmailTemplateContent emailTemplatesContent,final ServletContext context) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				Map<String, Object> model = new HashMap<String, Object>();
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
				String tableContent1 = emailTemplatesContent.getTemplateBody().replace("${url}", url);
				model.put("home", host);
				model.put("tableBody", tableContent1);
				message.setTo(userName);
				message.setFrom(new InternetAddress(contactus));
				message.setSubject(emailTemplatesContent.getTemplateSubject());
				String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"GenerateResetPassword.vm", "UTF-8", model);
				message.setText(body, true);
				FileSystemResource res = new FileSystemResource(new File(context.getRealPath("/resources/users/images/site-logo.png")));
				message.addInline("resetImg", res);
			}
		};
		JavaMailSenderImpl mailSender2=(JavaMailSenderImpl) mailSender;
		mailSender2.setHost(smtp.getHostname());
		mailSender2.setPort(smtp.getPortno());
		mailSender2.setUsername(smtp.getUsername());
		mailSender2.setPassword(smtp.getPassword());
		mailSender.send(preparator);
		System.out.println("Email send successfully");
	}*/
}
