package com.dotv.perfume.service;

import com.dotv.perfume.dto.ContactDTO;
import com.dotv.perfume.entity.Contact;

import java.sql.Timestamp;
import java.util.List;

public interface ContactService {
    Contact saveContact(ContactDTO contactDTO);
    List<Contact> getListContact(int type);
    int updateStatusContact(int id, Boolean status, String updatedBy, Timestamp updatedDate);
    void deleteContact(int id);
}
