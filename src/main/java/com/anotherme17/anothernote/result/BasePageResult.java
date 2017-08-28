package com.anotherme17.anothernote.result;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 */
@ApiModel(value = "PageResult", description = "分页返回结果")
public class BasePageResult<T> {

    @ApiModelProperty(value = "代码")
    private int code;

    @ApiModelProperty(value = "消息内容")
    private String msg;

    @ApiModelProperty(value = "数据")
    private List<T> data;

    @ApiModelProperty(value = "页大小")
    private int pageSize;

    @ApiModelProperty(value = "当前页号")
    private int pageIndex;

    @ApiModelProperty(value = "总页数")
    private int totalPageCount;

    @ApiModelProperty(value = "记录总数")
    private long record;

    @ApiModelProperty(value = "下一页")
    private Integer nextPage;

    @ApiModelProperty(value = "上一页")
    private Integer prePage;

    public BasePageResult(int code, String msg, List<T> data, int pageSize, int pageIndex, long record) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.record = record;

        this.totalPageCount = (record / pageSize) == 0 ? 1 :
                (int) ((record % pageSize) == 0 ? (record / pageSize) : (record / pageSize) + 1);

        this.nextPage = pageIndex * pageSize > record ? pageIndex : pageIndex + 1;

        this.prePage = pageIndex == 0 ? pageIndex : pageIndex - 1;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public long getRecord() {
        return record;
    }

    public void setRecord(long record) {
        this.record = record;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }
}
