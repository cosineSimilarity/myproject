package com.cosine.myweb.dao.contact;

import com.cosine.myweb.entity.contact.ContactmeInfo;
import org.springframework.stereotype.Repository;

/**
 *@title ContactmeMapper
 *@author Cosine
 *@create 2023/6/28
 *@version 1.0.0
 *@description 联系我持久层
 */
@Repository
public interface ContactmeMapper {
    int addContactmeInfo(ContactmeInfo contactmeInfo);
}
