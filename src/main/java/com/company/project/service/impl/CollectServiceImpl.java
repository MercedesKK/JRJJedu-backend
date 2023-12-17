package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;
import com.company.project.dao.CollectMapper;
import com.company.project.model.Collect;
import com.company.project.model.Course;
import com.company.project.service.CollectService;
import com.company.project.service.CourseService;
import com.company.project.service.MomentCommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CollectServiceImpl extends AbstractService<Collect> implements CollectService {

    @Resource
    private CollectMapper collectMapper;

    @Autowired
    private MomentCommentService momentCommentService;

    @Autowired
    private CollectService collectService;

    @Resource
    private CourseService courseService;

    @Override
    public Result list(Collect collect) {

        if (collect == null){
            collect = new Collect();
        }

        PageHelper.startPage(collect.getPage() == null ? 0 : collect.getPage(), collect.getLimit() == null ? 10 : collect.getLimit());
        collect.setIsDelete(false);
        List<Collect> list = collectMapper.list(collect);
        if (list != null && list.size() > 0){
            for (Collect d:list) {
                //收藏数量
                d.setCollectNumber(collectService.getCountByMomentId(d.getMomentId()));
                d.setMomentCommentList(momentCommentService.selectByMomentId(d.getMomentId()));
                //状态是否收藏
                d.setCollectState(collectService.getCollectState(d.getMomentId(),collect.getCreatedBy()));
            }
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Result addOrCancel(Collect collect) {
        if (null == collect.getMomentId() || null == collect.getStatus()){
            return ResultGenerator.genFailResult(ResultCode.COLLECT_PARAM_ERROR,"参数不可为空");
        }

        Course course = courseService.findByIdAndValidDelete(collect.getMomentId());
        if (null == course){
            return ResultGenerator.genFailResult(ResultCode.MOMENT_NOT_EXIST,"主题不存在或者已删除");
        }

        //已收藏
        if (1 == collect.getStatus()){
            int rows = collectMapper.findCollectCount(collect.getCreatedBy(),collect.getMomentId());
            if (rows > 0){
                return ResultGenerator.genFailResult(ResultCode.MOMENT_COLLECT_ERROR,"该主题已收藏，请勿再次收藏");
            }
            collect.setCreatedAt(new Date());
            collect.setIsDelete(false);
            save(collect);
            Result result= ResultGenerator.genSuccessResult();
            result.setData(collect);
            return result;
        }else if (0 == collect.getStatus()){
            //取消收藏
            collect.setUpdatedAt(new Date());
            collectMapper.cancel(collect);
            //取消收藏
            Result result= ResultGenerator.genSuccessResult();
            result.setData(collect);
            return result;
        }
        return ResultGenerator.genSuccessResult(collect);
    }

    @Override
    public List<Collect> selectByUserId(Long userId) {
        return collectMapper.selectByUserId(userId);
    }

    @Override
    public Integer getCollectNumber(Long userId) {
        return collectMapper.getCollectNumber(userId);
    }

    @Override
    public Integer getCountByMomentId(Long momentId) {
        return collectMapper.getCountByMomentId(momentId);
    }

    @Override
    public void deleteByMomentId(Long momentId) {
        collectMapper.deleteByMomentId(momentId);
    }

    @Override
    public Integer getCollectState(Long id, String userId) {
        return collectMapper.getCollectState(id,userId);
    }

}
