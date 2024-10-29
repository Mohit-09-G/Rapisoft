package com.example.rapisoft.Work_request;

public class WorkerRequestModel {

    private String contact;
    private String request;
    private String status;
    private String client;

    public WorkerRequestModel(String contact, String request, String status, String client) {
        this.contact = contact;
        this.request = request;
        this.status = status;
        this.client = client;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
