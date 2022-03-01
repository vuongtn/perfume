package com.dotv.perfume.service;

import com.dotv.perfume.entity.Introduce;

import java.util.List;

public interface IntroduceService {
    List<Introduce> getIntroduct(Boolean status);
}
