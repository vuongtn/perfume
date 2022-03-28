package com.dotv.perfume.service.impl;

import com.dotv.perfume.dto.ContactDTO;
import com.dotv.perfume.entity.Contact;
import com.dotv.perfume.repository.ContactRepository;
import com.dotv.perfume.service.ContactService;
import com.dotv.perfume.utils.PerfumeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    PerfumeUtils perfumeUtils;

    @Autowired
    ContactRepository contactRepository;

    @Override
    @Transactional
    public Contact saveContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setFullName(contactDTO.getFullName());
        contact.setEmail(contactDTO.getEmail());
        contact.setContent(contactDTO.getContent());
        contact.setCreatedDate(perfumeUtils.getDateNow());
        contact.setStatus(false);
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getListContact(int type) {
        if(type==1){
            return contactRepository.findAll();
        }
        if(type==2){
            return contactRepository.findAllByStatus(true);
        }
        if(type==3){
            return contactRepository.findAllByStatus(false);
        }
        return null;
    }

    @Override
    @Transactional
    public int updateStatusContact(int id, Boolean status, String updatedBy, Timestamp updatedDate) {
        return contactRepository.updateStatus(status,updatedBy,updatedDate,id);
    }

    @Override
    public void deleteContact(int id) {
        contactRepository.deleteById(id);
    }
}
