package com.jitendra.logasservice.endpoint;

import com.jitendra.logasservice.model.Admin;
import com.jitendra.logasservice.model.Application;
import com.jitendra.logasservice.model.AuditUiLogs;
import com.jitendra.logasservice.service.AdminService;
import com.jitendra.logasservice.service.impl.AdminServiceImpl;
import com.jitendra.logasservice.service.impl.ApplicationServiceImpl;
import com.jitendra.logasservice.token.Base64BasicEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "api/admin/", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AdminEndpoint {

    private final Logger logger = LoggerFactory.getLogger(AdminEndpoint.class);

    @Autowired
    private AdminServiceImpl service;

    @Autowired
    private ApplicationServiceImpl applicationService;

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> addAdmin(@RequestBody Admin admin) {
        if (logger.isDebugEnabled())
            logger.debug("inside addAdmin");

        if (logger.isDebugEnabled())
            logger.debug("Admin : "+ admin.toString());

        try{
            admin.setPassword(Base64BasicEncryption.encoding(admin.getPassword() ));
            admin = service.repository().save(admin);
            return new ResponseEntity<Admin>(admin, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping(path = "admin-login/")
    public ResponseEntity<?> getAdminByEmailAndPassword(@RequestParam(name = "email") String email,
                                                        @RequestParam(name = "password") String password) {
        if (logger.isDebugEnabled())
            logger.debug("inside getAdminByEmailAndPassword");

        if (logger.isDebugEnabled())
            logger.debug(String.format("User name %s and password is %s"),email, Base64BasicEncryption.encoding(password));


        Admin admin = service.getByEmailAndPasswordAndActive(email,
                Base64BasicEncryption.encoding(password.trim()));
        admin.setPassword("");
        return new ResponseEntity<Admin>(admin, HttpStatus.OK);
    }

    @GetMapping(path = "all/")
    public ResponseEntity<?> getFindAll(){
        List<Application> applications = null;
        applications = applicationService.findAll();
        return new ResponseEntity<List>(applications,HttpStatus.OK);
    }

    @PutMapping(path = "activate/{id}/")
    public ResponseEntity<?> activateUser(@PathVariable(name = "id") Long id ) {
        if (logger.isDebugEnabled())
            logger.debug("inside activateUser");

        if (logger.isDebugEnabled())
            logger.debug(String.format("ID is %s"),id);
        Boolean result = Boolean.FALSE;

        try {
            result =  service.activeAdmin(id);
        }catch (Exception e){

        }
        return new ResponseEntity<Boolean>(result,HttpStatus.OK);
    }

}
