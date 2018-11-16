package com.zhangongjx.infrastructure.domain;

/**
 * 分页
 */
public class DataTablePaging {
    private  int draw;
    private int recordsFiltered;
    private int recordsTotal;

   public DataTablePaging()
   {

   }
    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
}
