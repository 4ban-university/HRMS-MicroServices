package ca.concordia.soen6841.dbservice.pojo;

import java.util.List;

public class Response<T> {
    public String message;

    public T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
