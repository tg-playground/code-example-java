package com.taogen.demo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.taogen.demo.domain.User;

import java.util.List;

/**
 * Service接口
 *
 * @author Taogen
 * @date 2022-04-13
 */
public interface IUserService extends IService<User> {

    List<User> queryList(Wrapper<User> lqw);

    User getEntity(Long id);

    boolean saveEntity(User user);

    boolean updateEntity(User user);

    boolean deleteByIds(List<Long> ids);
}
