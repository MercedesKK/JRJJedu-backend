package com.company.project.service.impl;

import com.company.project.dao.CollectionListMapper;
import com.company.project.model.CollectionList;
import com.company.project.service.CollectionListService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class CollectionListServiceImpl extends AbstractService<CollectionList> implements CollectionListService {

    @Resource
    private CollectionListMapper tCollectionListMapper;

}
