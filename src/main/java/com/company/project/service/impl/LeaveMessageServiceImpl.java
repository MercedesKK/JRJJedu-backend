package com.company.project.service.impl;

import com.company.project.dao.LeaveMessageMapper;
import com.company.project.model.LeaveMessage;
import com.company.project.service.LeaveMessageService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class LeaveMessageServiceImpl extends AbstractService<LeaveMessage> implements LeaveMessageService {

    @Resource
    private LeaveMessageMapper tLeaveMessageMapper;

}
