package com.jitendra.logasservice.es.service;


import com.jitendra.logasservice.LogasserviceApplication;
import com.jitendra.logasservice.enums.AdminType;
import com.jitendra.logasservice.model.Admin;
import com.jitendra.logasservice.repository.AdminRepository;
import com.jitendra.logasservice.token.Base64BasicEncryption;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LogasserviceApplication.class)
public class AdminServiceTest {

    @Autowired
    private AdminRepository repository;

    @Test
    public void testUser() {
        Admin admin = getAdmin();
        admin = repository.save(admin);
        Admin result = null;
        result = repository.get("jitendra.sagoriya@yahoo.co.in",Base64BasicEncryption.encoding( "123456"));
        Assert.assertNotNull(result);
        repository.delete(admin);
    }

    private Admin getAdmin() {
        Admin admin = new Admin();
        admin.setEmailId("jitendra.sagoriya@yahoo.co.in");
        admin.setPassword(Base64BasicEncryption.encoding( "123456"));
        admin.setActive(Boolean.TRUE);
        admin.setRegistrationDate(new Date(System.currentTimeMillis()));
        admin.setAdminType(AdminType.SUPERADMIN.getName());
        return admin;
    }

    public static void main(String[] args) {
        System.out.println( new AdminServiceTest().getAdmin() );
    }
}
