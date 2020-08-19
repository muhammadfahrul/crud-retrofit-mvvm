package com.example.crudmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.crudmvvm.databinding.ActivityAddBinding;
import com.example.crudmvvm.models.UserModel;
import com.example.crudmvvm.viewmodels.UserViewModel;

public class AddActivity extends AppCompatActivity {

    UserViewModel viewModel;
    ActivityAddBinding binding;
    UserModel users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        users = new UserModel();
        viewModel = new ViewModelProvider(AddActivity.this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        binding.btnAdd.setOnClickListener(v -> {
            users.setName(binding.etName.getText().toString());
            users.setEmail(binding.etEmail.getText().toString());
            users.setPassword(binding.etPassword.getText().toString());
            viewModel.addUser(users);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
    }
}