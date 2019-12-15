package com.yejh.dao;

import com.yejh.bean.Lock;

/**
 * @author yejh
 * @create 2019-12_13 20:10
 */
public interface LockDao {

    Lock getLockById(int id);

    Lock getLockByIdByStep(int id);
}
