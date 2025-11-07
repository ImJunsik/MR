package com.mr.common.domain;

public class ConfigurationSetting {

    private boolean mail;
    private boolean oper;
    
	public boolean isMail() {
		return mail;
	}
	public void setMail(boolean mail) {
		System.out.println("mail = " + mail);
		this.mail = mail;
	}
	public boolean isOper() {
		return oper;
	}
	public void setOper(boolean oper) {
		System.out.println("oper = " + oper);
		this.oper = oper;
	}
	
}
