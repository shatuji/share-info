package me.twocat.shareinfo.service;

import me.twocat.shareinfo.entity.BasicEntityCls;

import java.util.Map;

public interface GenicSelectInsertServer <T extends BasicEntityCls>{
  /***
   * find data
   * @param t
   * @return
   */
  Map selectData(T t);

  /**
   * insert T data
   *
   * @param t
   * @return
   */
  Map insertData(T t);
}
