package com.example.crudmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.crudmvvm.databinding.ActivityUpdateBinding;
import com.example.crudmvvm.models.UserModel;
import com.example.crudmvvm.viewmodels.UserViewModel;

public class UpdateActivity extends AppCompatActivity {

    UserViewModel viewModel;
    ActivityUpdateBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update);

        UserModel users = getIntent().getParcelableExtra(MainActivity.DATA_USERS);
        binding.setUsers(users);

        viewModel = new ViewModelProvider(UpdateActivity.this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.setName(binding.etName.getText().toString());
                users.setEmail(binding.etEmail.getText().toString());
                users.setPassword(binding.etPassword.getText().toString());
                viewModel.updateUser(users);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}