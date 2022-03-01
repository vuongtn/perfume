package com.dotv.perfume.service.impl;

import com.dotv.perfume.dto.ContactDTO;
import com.dotv.perfume.entity.Contact;
import com.dotv.perfume.repository.ContactRepository;
import com.dotv.perfume.service.ContactService;
import com.dotv.perfume.utils.PerfumeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        contact.setStatus(true);
        return contactRepository.save(contact);
    }
}
