package com.dotv.perfume.service;

import com.dotv.perfume.dto.ContactDTO;
import com.dotv.perfume.entity.Contact;

public interface ContactService {
    Contact saveContact(ContactDTO contactDTO);
}
