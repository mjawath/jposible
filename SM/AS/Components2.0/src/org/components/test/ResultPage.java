/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.components.test;

public class ResultPage {

    private Long count = -1l;
    private int noOfPages = -1;
    private int currentPage = -1;
    private Object obj;

    public ResultPage(Long count, int pages, int cp, Object obj) {
        this.count = count;
        this.noOfPages = pages;
        this.currentPage = cp;
        this.obj = obj;
    }

    public Object getResult() {
        return obj;

    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
    
    
}
