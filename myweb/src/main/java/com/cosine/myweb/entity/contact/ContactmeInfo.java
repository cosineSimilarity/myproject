package com.cosine.myweb.entity.contact;

/**
 *@title ContactmeInfo
 *@author Cosine
 *@create 2023/6/28
 *@version 1.0.0
 *@description 联系我实体类
 */
public class ContactmeInfo {

    private int id;
    private String contactmeName;
    private String contactmeEmail;
    private String contactmeSubject;
    private String contactmeComment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactmeName() {
        return contactmeName;
    }

    public void setContactmeName(String contactmeName) {
        this.contactmeName = contactmeName;
    }

    public String getContactmeEmail() {
        return contactmeEmail;
    }

    public void setContactmeEmail(String contactmeEmail) {
        this.contactmeEmail = contactmeEmail;
    }

    public String getContactmeSubject() {
        return contactmeSubject;
    }

    public void setContactmeSubject(String contactmeSubject) {
        this.contactmeSubject = contactmeSubject;
    }

    public String getContactmeComment() {
        return contactmeComment;
    }

    public void setContactmeComment(String contactmeComment) {
        this.contactmeComment = contactmeComment;
    }

    @Override
    public String toString() {
        return "ContactmeInfo{" +
                "id=" + id +
                ", contactmeName='" + contactmeName + '\'' +
                ", contactmeEmail='" + contactmeEmail + '\'' +
                ", contactmeSubject='" + contactmeSubject + '\'' +
                ", contactmeComment='" + contactmeComment + '\'' +
                '}';
    }
}
