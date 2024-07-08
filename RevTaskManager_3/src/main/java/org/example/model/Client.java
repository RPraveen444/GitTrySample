package org.example.model;

public class Client {
    private int client_id;
    private String client_name;
    private String contact_information;

    public Client(){}

    public Client(int client_id, String client_name, String contact_information) {
        this.client_id = client_id;
        this.client_name = client_name;
        this.contact_information = contact_information;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getContact_information() {
        return contact_information;
    }

    public void setContact_information(String contact_information) {
        this.contact_information = contact_information;
    }
}
