package com.example.crudmvvm.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.crudmvvm.clients.ApiClient;
import com.example.crudmvvm.models.UserModel;
import com.example.crudmvvm.responses.UserResponse;
import com.example.crudmvvm.services.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class UserViewModel extends ViewModel {
    MutableLiveData<List<UserModel>> listUsers = new MutableLiveData<>();

    public MutableLiveData<List<UserModel>> getListUsers() {
        return listUsers;
    }

    private List<UserModel> temp = new ArrayList<>();

    private final String BASE_URL = "https://restful-lumen-mysql.herokuapp.com/api/v1/";

    public void setListUsers() {
        ApiClient.client(UserService.class, BASE_URL)
                .getUsers().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, retrofit2.Response<UserResponse> response) {
                listUsers.setValue(response.body().getData());
                temp = listUsers.getValue();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void searchUsers(CharSequence s){
        List<UserModel> usersModel = new ArrayList<>();

        if (listUsers.getValue() != null){
            for (UserModel user : temp){
                if (user.getName().toLowerCase().contains(s)){
                    usersModel.add(user);
                }
            }
            listUsers.setValue(usersModel);
        }
    }

    public void addUser(UserModel users){
        ApiClient.client(UserService.class, BASE_URL).addUser(users).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, retrofit2.Response<UserResponse> response) {

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    public void updateUser(UserModel users){
        ApiClient.client(UserService.class, BASE_URL).updateUser(users.getId(), users).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, retrofit2.Response<UserResponse> response) {
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void deleteUser(UserModel users){
        ApiClient.client(UserService.class, BASE_URL).deleteUser(users.getId()).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, retrofit2.Response<UserResponse> response) {

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
}
