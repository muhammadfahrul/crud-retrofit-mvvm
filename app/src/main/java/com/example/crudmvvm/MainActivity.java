package com.example.crudmvvm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;

import com.example.crudmvvm.adapters.UserAdapter;
import com.example.crudmvvm.databinding.ActivityMainBinding;
import com.example.crudmvvm.models.UserModel;
import com.example.crudmvvm.utils.ViewUtil;
import com.example.crudmvvm.viewmodels.UserViewModel;

import java.util.List;

import static com.example.crudmvvm.BR.users;

public class MainActivity extends AppCompatActivity {

    UserViewModel viewModel;
    UserAdapter adapter;
    ActivityMainBinding binding;

    public static final String DATA_USERS = "DATA_USERS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setIsLoading(true);
        adapter = new UserAdapter();
        viewModel = new ViewModelProvider(MainActivity.this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        viewModel.setListUsers();
        getAllUsers();
        binding.rvUsers.setLayoutManager(new LinearLayoutManager(this));
        binding.rvUsers.setAdapter(adapter);

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.searchUsers(s.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.etSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                ViewUtil.hideKeyboard(textView);
                return true;
            }

            return false;
        });

        adapter.setListener(new UserAdapter.UsersListener() {
            @Override
            public void onUpdate(UserModel users) {
                startActivity(new Intent(
                        MainActivity.this,
                        UpdateActivity.class).putExtra(DATA_USERS, users)
                );
            }

            @Override
            public void onDelete(UserModel users) {
                viewModel.deleteUser(users);

                reloadData();
            }
        });

        binding.btnAdd.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddActivity.class)));
    }

    private void getAllUsers() {
        viewModel.getListUsers().observe(MainActivity.this, new Observer<List<UserModel>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(List<UserModel> userData) {
                adapter.setListUsers(userData);
                binding.setIsLoading(false);
            }
        });
    }

    public void reloadData(){
        finish();
        overridePendingTransition( 0, 0);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);
    }
}