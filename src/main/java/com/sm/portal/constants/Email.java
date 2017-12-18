
package com.sm.portal.constants;
import java.util.ArrayList;
import java.util.List;

public class Email {

	
	private boolean bodyTemplate ;
	private boolean subjectTemplate ;

	private String bodyTemplateFile ;
	private String subjectTemplateFile ;
	private String mailto;
	private String senderInitial;
	private String receiverInitial;
	
	public String getMailto() {
		return mailto;
	}

	public void setMailto(String mailto) {
		this.mailto = mailto;
	}

	private List<String> to;

	private List<String> bCC;

	private String from;

	private String subject;

	private byte[] body;
	
	private String channel ;

	public boolean isBodyTemplate() {
		return bodyTemplate;
	}

	public void setBodyTemplate(boolean bodyTemplate) {
		this.bodyTemplate = bodyTemplate;
	}

	public boolean isSubjectTemplate() {
		return subjectTemplate;
	}

	public void setSubjectTemplate(boolean subjectTemplate) {
		this.subjectTemplate = subjectTemplate;
	}

	public String getBodyTemplateFile() {
		return bodyTemplateFile;
	}

	public void setBodyTemplateFile(String bodyTemplateFile) {
		this.bodyTemplateFile = bodyTemplateFile;
	}

	public String getSubjectTemplateFile() {
		return subjectTemplateFile;
	}

	public void setSubjectTemplateFile(String subjectTemplateFile) {
		this.subjectTemplateFile = subjectTemplateFile;
	}

	
	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getbCC() {
		return bCC;
	}

	public void setbCC(List<String> bCC) {
		this.bCC = bCC;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public Email(){
		to = new ArrayList<String>();
		bCC = new ArrayList<String>();
	}

	public Email(int toSize){
		to = new ArrayList<String>(toSize);
		bCC = new ArrayList<String>();
	}
	public Email(int toSize, boolean bcc){
		to = new ArrayList<String>();
		this.bCC = new ArrayList<String>(toSize);

	}

	public void addTo(String emailAddress){
		if(to==null){
			to = new ArrayList<String>();
		}

		to.add(emailAddress);
	}

	public void setSenderInitial(String senderInitial) {
		this.senderInitial = senderInitial;
	}
	public String getSenderInitial() {
		return senderInitial;
	}

	public void setReceiverInitial(String receiverInitial) {
		this.receiverInitial = receiverInitial;
	}
	public String getReceiverInitial() {
		return receiverInitial;
	}
	/*public String[] getTo() {
		return (String[]) to.toArray(new String[to.size()]);
	}

	public void setTo(List to) {
		this.to = to;
	}*/
	
}
