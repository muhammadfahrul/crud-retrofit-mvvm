package com.example.crudmvvm.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudmvvm.R;
import com.example.crudmvvm.databinding.ListUsersBinding;
import com.example.crudmvvm.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<UserModel> listUsers = new ArrayList<>();

    public void setListUsers(List<UserModel> listUsers) {
        this.listUsers = listUsers;
        notifyDataSetChanged();
    }

    public interface UsersListener {
        void onUpdate(UserModel users);

        void onDelete(UserModel users);
    }

    private UsersListener listener;
    public void setListener(UsersListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListUsersBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_users,
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        holder.bindData(listUsers.get(position), listener);
    }

    @Override
    public int getItemCount() {
        if (listUsers != null){
            return listUsers.size();
        }else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ListUsersBinding binding;
        public ViewHolder(@NonNull ListUsersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(UserModel users, UsersListener listener){
            binding.setUsers(users);
            int number = getAdapterPosition() + 1;
            binding.tvNumber.setText(String.valueOf(number));
            binding.btnUpdate.setOnClickListener(view -> listener.onUpdate(users));
            binding.btnDelete.setOnClickListener(view -> listener.onDelete(users));
        }
    }
}
