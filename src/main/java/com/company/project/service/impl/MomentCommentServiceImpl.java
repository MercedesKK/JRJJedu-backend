package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.MomentCommentMapper;
import com.company.project.model.MomentComment;
import com.company.project.model.User;
import com.company.project.service.MomentCommentService;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MomentCommentServiceImpl extends AbstractService<MomentComment> implements MomentCommentService {

    @Autowired
    private MomentCommentMapper momentCommentMapper;

    @Autowired
    private UserService userService;

    @Override
    public Result add(MomentComment momentComment) {
        momentComment.setCreatedAt(new Date());
        momentComment.setIsDelete(false);
        save(momentComment);
        Result result= ResultGenerator.genSuccessResult();
        result.setData(momentComment);
        return result;
    }

    @Override
    public List<MomentComment> selectByMomentId(Long momentId) {
        List<MomentComment> momentCommentList = momentCommentMapper.selectParentByMomentId(momentId);
        if (momentCommentList != null && momentCommentList.size() > 0){
            for (MomentComment d:momentCommentList) {
                d.setChildrenList(momentCommentMapper.selectChildrenByParentId(d.getId()));
            }
        }
        return momentCommentList;
    }

    @Override
    public Result detail(Long momentId) {
        List<MomentComment> list = momentCommentMapper.detail(momentId);
        for (MomentComment d:list) {
            d.setUserName(userService.findByIdAndValidDelete(Long.valueOf(d.getCreatedBy())).getUserName());
        }
        return ResultGenerator.genSuccessResult(list);
    }

    @Override
    public Result list(MomentComment momentComment) {

        if (null != momentComment.getCreatedBy()){
            User user = userService.findByIdAndValidDelete(Long.valueOf(momentComment.getCreatedBy()));
            if (user != null){
                if (0 == user.getRoleId()){
                    momentComment.setCreatedBy(null);
                }
            }
        }

        PageHelper.startPage(momentComment.getPage() == null ? 0 : momentComment.getPage(), momentComment.getLimit() == null ? 10 : momentComment.getLimit());
        momentComment.setIsDelete(false);
        List<MomentComment> list = momentCommentMapper.list(momentComment);
        for (MomentComment d:list) {
            if (d.getCreatedBy() != null){
                User newUser = userService.findByIdAndValidDelete(Long.valueOf(d.getCreatedBy()));
                if (newUser != null){
                    d.setUserName(newUser.getUserName());
                }
            }
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Integer getCountByMomentId(Long momentId) {
        return momentCommentMapper.getCountByMomentId(momentId);
    }

}
