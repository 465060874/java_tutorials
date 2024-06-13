package com.javatutorials.model;

public class SystemLogException {
    private String msgType;
    private String senderID;
    private String interchangeMessageID;
    private String receiveGMT;
    private String transactionID;
    private String messageID;
    private String messagePath;
    private String exceptionCode;
    private String exceptionLevel;
    private String rawMessage;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getInterchangeMessageID() {
        return interchangeMessageID;
    }

    public void setInterchangeMessageID(String interchangeMessageID) {
        this.interchangeMessageID = interchangeMessageID;
    }

    public String getReceiveGMT() {
        return receiveGMT;
    }

    public void setReceiveGMT(String receiveGMT) {
        this.receiveGMT = receiveGMT;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getMessagePath() {
        return messagePath;
    }

    public void setMessagePath(String messagePath) {
        this.messagePath = messagePath;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionLevel() {
        return exceptionLevel;
    }

    public void setExceptionLevel(String exceptionLevel) {
        this.exceptionLevel = exceptionLevel;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    public void setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
    }
}
