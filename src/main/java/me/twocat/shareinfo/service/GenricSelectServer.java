package me.twocat.shareinfo.service;

import me.twocat.shareinfo.entity.BasicEntityCls;

import java.util.Map;

public interface GenricSelectServer<T extends BasicEntityCls> {

  /***
   * find data
   * @param t
   * @return
   */
   Map selectData(T t);
}
