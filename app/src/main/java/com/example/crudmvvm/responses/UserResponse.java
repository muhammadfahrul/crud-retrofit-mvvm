package com.example.crudmvvm.responses;

import com.example.crudmvvm.models.UserModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("data")
    private List<UserModel> data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public void setData(List<UserModel> data){
        this.data = data;
    }

    public List<UserModel> getData(){
        return data;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus(){
        return status;
    }
}
