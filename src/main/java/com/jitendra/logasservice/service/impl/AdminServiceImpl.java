package com.jitendra.logasservice.service.impl;

import com.jitendra.logasservice.endpoint.AuditUiLogEndpoint;
import com.jitendra.logasservice.model.Admin;
import com.jitendra.logasservice.repository.AdminRepository;
import com.jitendra.logasservice.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService<AdminRepository> {


    private final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminRepository repository;

    @Override
    public AdminRepository repository() {
        return repository;
    }

    @Override
    public Admin getByEmailAndPasswordAndActive(String email, String password) {
        if (logger.isDebugEnabled())
            logger.debug("inside  getByEmailAndPasswordAndActive");


        return repository.get(email, password);
    }

    @Override
    public Boolean activeAdmin(Long id) {
        try {
            Admin admin = repository.findById(id).get();
            if(admin != null) {
                admin.setActive(Boolean.TRUE);
                repository.save(admin);
                return Boolean.TRUE;
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return Boolean.FALSE;
    }



}
