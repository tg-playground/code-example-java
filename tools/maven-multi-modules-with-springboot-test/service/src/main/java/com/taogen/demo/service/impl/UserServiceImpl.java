package com.taogen.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.taogen.demo.domain.User;
import com.taogen.demo.mapper.UserMapper;
import com.taogen.demo.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Service业务层处理
 *
 * @author Taogen
 * @date 2022-04-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public List<User> queryList(Wrapper<User> lqw) {
        List<User> userList = this.baseMapper.selectList(lqw);
        setOtherFieldsForList(userList);
        return userList;
    }

    private void setOtherFieldsForList(List<User> userList) {
        if (userList == null || userList.isEmpty()) {
            return;
        }
    }



    @Override
    public User getEntity(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveEntity(User user) {
        return SqlHelper.retBool(this.baseMapper.insert(user));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateEntity(User user) {
        return SqlHelper.retBool(this.baseMapper.updateById(user));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteByIds(List<Long> ids) {
        return CollectionUtils.isEmpty(ids) ? false : SqlHelper.retBool(this.getBaseMapper().deleteBatchIds(ids));
    }
}
