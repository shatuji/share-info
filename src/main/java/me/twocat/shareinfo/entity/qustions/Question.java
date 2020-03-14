package me.twocat.shareinfo.entity.qustions;

import lombok.Data;

import java.io.Serializable;

@Data
public class Question implements Serializable {
  private Integer id;
  private String title;
  private String name;

}
