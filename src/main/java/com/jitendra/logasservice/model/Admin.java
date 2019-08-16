package com.jitendra.logasservice.model;

import com.jitendra.logasservice.enums.AdminType;
import com.jitendra.logasservice.token.Base64BasicEncryption;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ADMIN")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "EMAIL",nullable = false,unique = true)
    private String emailId;

    @Column(name = "PASSWORD",nullable = false)
    private String password;

    @Column(name = "DATE",nullable = false)
    private Date registrationDate;

    @Column(name = "ACTIVE",nullable = false)
    private boolean isActive;

    @Column(name = "TYPE",nullable = false)
    private String adminType;

    public Admin(Long id) {
        this.id = id;
    }

    public Admin(Long id, String emailId, String password, Date registrationDate, boolean isActive) {
        this.id = id;
        this.emailId = emailId;
        this.password = password;
        this.registrationDate = registrationDate;
        this.isActive = isActive;
    }

    public Admin(String emailId, String password, Date registrationDate, boolean isActive, String adminType) {
        this.emailId = emailId;
        this.password = password;
        this.registrationDate = registrationDate;
        this.isActive = isActive;
        this.adminType = adminType;
    }

    public Admin() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return isActive == admin.isActive &&
                id.equals(admin.id) &&
                emailId.equals(admin.emailId) &&
                password.equals(admin.password) &&
                registrationDate.equals(admin.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emailId, password, registrationDate, isActive);
    }

    @Override
    public String toString() {
        return "{" +
                "\"id \":" + "\"" +  id + "\"" +
                ", \"emailId\":" + "\"" + emailId + "\"" +
                ", \"password \":" + "\"" +  password + "\"" +
                ", \"registrationDate\" :" + "\"" +  registrationDate + "\"" +
                ", \"isActive\" :" + "\"" + isActive + "\"" +
                ", \"adminType\" :" + "\"" +  adminType + "\"" +
                "}";
    }

    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.setEmailId("jitendra.sagoriya@yahoo.co.in");
        admin.setPassword(Base64BasicEncryption.encoding( "123456"));
        admin.setActive(Boolean.TRUE);
        admin.setRegistrationDate(new Date(System.currentTimeMillis()));
        admin.setAdminType(AdminType.SUPERADMIN.getName());

        System.out.println(admin);
    }
}
