package com.dotv.perfume.service.impl;

import com.dotv.perfume.entity.Introduce;
import com.dotv.perfume.repository.IntroductRepository;
import com.dotv.perfume.service.IntroduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntroductServiceImpl implements IntroduceService {
    @Autowired
    IntroductRepository introductRepository;

    @Override
    public List<Introduce> getIntroduct(Boolean status) {
        return introductRepository.findAllByStatus(status);
    }
}
