package com.jitendra.logasservice.service;

import com.jitendra.logasservice.model.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService<R> extends BaseSerivce<R> {

    public R repository();
    public Admin getByEmailAndPasswordAndActive(String email, String password);
    public Boolean activeAdmin(Long id);
}
