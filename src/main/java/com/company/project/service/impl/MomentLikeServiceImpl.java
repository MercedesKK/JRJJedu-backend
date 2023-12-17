package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.MomentLikeMapper;
import com.company.project.model.MomentLike;
import com.company.project.service.MomentLikeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MomentLikeServiceImpl extends AbstractService<MomentLike> implements MomentLikeService {

    @Resource
    private MomentLikeMapper momentLikeMapper;

    @Override
    public Result addOrCancel(MomentLike momentLike) {

        if ( null ==  momentLike.getStatus() || null ==  momentLike.getCreatedBy() || null ==  momentLike.getSubjectId()){
            return ResultGenerator.genFailResult(ResultCode.LIKE_PARAM_ERROR,"点赞缺少必要参数");
        }

        //点赞
        if (1 == momentLike.getStatus()){
            //预防重复点赞
            int rows = momentLikeMapper.findUserMomentLikeById(momentLike);
            if (rows > 0) {
                return ResultGenerator.genFailResult(ResultCode.REPEAT_THE_THUMB_UP,"不可重复点赞!");
            }
            momentLike.setCreatedAt(new Date());
            momentLike.setIsDelete(false);
            save(momentLike);
            Result result= ResultGenerator.genSuccessResult();
            result.setData(momentLike);
            return result;
        }else if (2 == momentLike.getStatus()){
            //取消点赞
            MomentLike newMomentLike = momentLikeMapper.selectByIdAndIsDelete(momentLike.getSubjectId(),momentLike.getCreatedBy(),momentLike.getStatus());
            if (null == newMomentLike){
                return ResultGenerator.genFailResult(ResultCode.MOMENT_COMENT_LIKE_RECORD_ERROR,"点赞记录不存在，或者已删除!");
            }

            int rows = momentLikeMapper.cancelMomentLike(momentLike);
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Integer getCountBySubjectId(Long subjectId) {
        return momentLikeMapper.getCountBySubjectId(subjectId);
    }

    @Override
    public Integer getCountStateBySubjectId(String userId, Long subjectId) {
        return momentLikeMapper.getCountStateBySubjectId(userId,subjectId);
    }

    @Override
    public Result list(MomentLike momentLike) {

        if (null == momentLike){
            momentLike = new MomentLike();
        }

        //调用PageHelper公共方法实现分页
        PageHelper.startPage(momentLike.getPage() == null ? 0 : momentLike.getPage(), momentLike.getLimit() == null ? 10 : momentLike.getLimit());
        PageHelper.startPage(momentLike.getPage(), momentLike.getLimit());
        momentLike.setIsDelete(false);
        List<MomentLike> list = findByModel(momentLike);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
