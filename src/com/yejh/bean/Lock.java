package com.yejh.bean;/**
 * @author yejh
 * @create 2019-12_13 19:41
 */

import java.util.List;

/**
 * @description: TODO
 **/
public class Lock {
    private int id;

    private String lockName;

    List<Key> keys;


    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    @Override
    public String toString() {
        return "Lock{" +
                "id=" + id +
                ", lockName='" + lockName + '\'' +
                ", keys=" + keys +
                '}';
    }
}
