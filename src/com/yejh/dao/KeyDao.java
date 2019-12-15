package com.yejh.dao;/**
 * @author yejh
 * @create 2019-12_13 19:43
 */

import com.yejh.bean.Key;

import java.util.List;

/**
 * @description: TODO
 **/
public interface KeyDao {
    Key getKeyById(int id);

    List<Key> getKeysByLockId(int id);
}
