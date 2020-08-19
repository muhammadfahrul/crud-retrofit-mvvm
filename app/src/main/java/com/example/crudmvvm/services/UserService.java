package com.example.crudmvvm.services;

import com.example.crudmvvm.models.UserModel;
import com.example.crudmvvm.responses.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("users")
    Call<UserResponse> getUsers();

    @Headers("Content-Type: application/json")
    @POST("users")
    Call<UserResponse> addUser(@Body UserModel users);

    @Headers("Content-Type: application/json")
    @PUT("users/{id}")
    Call<UserResponse> updateUser(@Path("id") int id, @Body UserModel users);

    @DELETE("users/{id}")
    Call<UserResponse> deleteUser(@Path("id") int id);
}
