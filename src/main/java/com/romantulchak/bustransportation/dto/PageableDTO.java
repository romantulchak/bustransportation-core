package com.romantulchak.bustransportation.dto;

import java.util.List;

public class PageableDTO<T> {

    private long totalPages;

    private long totalElements;

    private int currentPage;

    private List<T> model;

    public PageableDTO(long totalPages, long totalElements, int currentPage, List<T> model){
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.currentPage = ++currentPage;
        this.model = model;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getModel() {
        return model;
    }

    public void setModel(List<T> model) {
        this.model = model;
    }
}
