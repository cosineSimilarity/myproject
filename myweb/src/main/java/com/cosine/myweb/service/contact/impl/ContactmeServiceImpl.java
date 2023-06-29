package com.cosine.myweb.service.contact.impl;

import com.cosine.myweb.dao.contact.ContactmeMapper;
import com.cosine.myweb.entity.contact.ContactmeInfo;
import com.cosine.myweb.service.contact.ContactmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@title ContactmeServiceImpl
 *@author Cosine
 *@create 2023/6/28
 *@version 1.0.0
 *@description 联系我业务实现类
 */
@Service
public class ContactmeServiceImpl implements ContactmeService {

    @Autowired
    private ContactmeMapper contactmeMapper;

    public int addContactMeInfo(ContactmeInfo contactmeInfo){
        return contactmeMapper.addContactmeInfo(contactmeInfo);
    }
}
