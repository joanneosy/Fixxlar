/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Fixxlar
 */
public class Email {

    private String emailTo;
    private String emailFrom;
    private String password;
    private String subject;
    private String content;

    public Email(String emailTo, String emailFrom, String password, String subject, String content) {
        this.emailTo = emailTo;
        this.emailFrom = emailFrom;
        this.password = password;
        this.subject = subject;
        this.content = content;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public String getPassword() {
        return password;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
