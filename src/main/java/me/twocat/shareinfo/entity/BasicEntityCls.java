package me.twocat.shareinfo.entity;

public class BasicEntityCls {
  //start pages
  private Integer startPages;
  //end pages
  private Integer endPages;

  private String startDate;
  private String endDate;

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public Integer getStartPages() {
    return startPages;
  }

  public void setStartPages(Integer startPages) {
    this.startPages = startPages;
  }

  public Integer getEndPages() {
    return endPages;
  }

  public void setEndPages(Integer endPages) {
    this.endPages = endPages;
  }
}
