package ru.korotkov.testforlab.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Korotkov Sergey
 */

@Entity
public class MessageForUser {

    public MessageForUser() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String correlationId;
    private Date dataCreate;
    private Date sendMail;
    private Boolean isSendMail;
    private String resultCheck;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getResultCheck() {
        return resultCheck;
    }

    public void setResultCheck(String resultCheck) {
        this.resultCheck = resultCheck;
    }

    public void setIsSendMail(Boolean isSendMail) {
        this.isSendMail = isSendMail;
    }

    public Boolean getIsSendMail() {
        return isSendMail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(Date dataCreate) {
        this.dataCreate = dataCreate;
    }

    public Date getSendMail() {
        return sendMail;
    }

    public void setSendMail(Date sendMail) {
        this.sendMail = sendMail;
    }
}
