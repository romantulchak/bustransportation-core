package com.romantulchak.bustransportation.model.email;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractEmail {

    private String from;
    private String to;
    private String subject;
    private String email;
    private String attachment;
    private String fromDisplayName;
    private String emailLanguage;
    private String displayName;
    private String templateLocation;
    private Map<String, Object> context;

    public AbstractEmail() {
        this.context = new HashMap<>();
    }

    public abstract <T> void init(T context, String token);

    protected Object put(String key, Object value) {
        return key == null ? null : this.context.put(key.intern(), value);
    }

    public String getFrom() {
        return from;
    }

    protected void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    protected void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    protected void setSubject(String subject) {
        this.subject = subject;
    }

    protected String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected String getAttachment() {
        return attachment;
    }

    protected void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    protected String getFromDisplayName() {
        return fromDisplayName;
    }

    protected void setFromDisplayName(String fromDisplayName) {
        this.fromDisplayName = fromDisplayName;
    }

    protected String getEmailLanguage() {
        return emailLanguage;
    }

    protected void setEmailLanguage(String emailLanguage) {
        this.emailLanguage = emailLanguage;
    }

    protected String getDisplayName() {
        return displayName;
    }

    protected void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTemplateLocation() {
        return templateLocation;
    }

    protected void setTemplateLocation(String templateLocation) {
        this.templateLocation = templateLocation;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    protected void setContext(Map<String, Object> context) {
        this.context = context;
    }
}
