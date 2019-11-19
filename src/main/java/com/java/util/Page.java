package com.java.util;

import java.io.Serializable;

/**
 * description: 分页对象
 * author: ws
 * time: 2019/11/19 19:28
 */

public class Page implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 总数据条数
     */
    private int totalRows;
    /**
     * 每一页的条数
     */
    private int pageSize;
    /**
     * 当前第几页
     */
    private int pageNo;

    private static final int NUMBER_20 = 20;

    public static Page createNotPaging() {
        return new Page(-1, -1, -1);
    }

    public Page() {
        this.totalRows = 0;
        this.pageSize = NUMBER_20;
        this.pageNo = 1;
    }

    public Page(int pn, int ps){
        this.totalRows = 0; //总条数
        this.pageSize = ps; //每页多少条数据
        this.pageNo = pn;   //第几页
    }

    public Page(int pn, int ps, int tr){
        this.totalRows = tr;
        this.pageSize = ps;
        this.pageNo = pn;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 设置当前页数据条数
     * @param pageSize
     */
    public void setRows(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 第几页
     * @param pageNo
     */
    public void setPage(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getRowStart(){
        return (this.pageNo > 1 ? this.pageNo - 1 : 0) * this.pageSize;
    }

    public int getRowEnd(){
        return (this.pageNo > 1 ? this.pageNo : 1) * this.pageSize;
    }

}
