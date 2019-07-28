package com.jitendra.logasservice.model;

import java.util.List;

public class Result<T> {
    private long totalCount;
    private int currentPageNumber;
    private List<T> list;
    private int firstPage;
    private long lastPage;
    private long pageCount;

    public Result() {
    }
    public Result(long totalCount, int currentPageNumber, List<T> list) {
        this.totalCount = totalCount;
        this.currentPageNumber = currentPageNumber;
        this.list = list;
    }

    public Result(long totalCount, int currentPageNumber, List<T> list, int firstPage, int lastPage) {
        this.totalCount = totalCount;
        this.currentPageNumber = currentPageNumber;
        this.list = list;
        this.firstPage = firstPage;
        this.lastPage = lastPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public long getLastPage() {
        return lastPage;
    }

    public void setLastPage(long lastPage) {
        this.lastPage = lastPage;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Result{" +
                "totalCount=" + totalCount +
                ", currentPageNumber=" + currentPageNumber +
                ", list=" + list +
                ", firstPage=" + firstPage +
                ", lastPage=" + lastPage +
                ", pageCount=" + pageCount +
                '}';
    }


}
