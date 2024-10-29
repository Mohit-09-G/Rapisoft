package com.example.rapisoft.Employee;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rapisoft.R;

import java.util.ArrayList;
import java.util.List;

public class Worker_Adapter extends RecyclerView.Adapter<Worker_Adapter.WorkerViewHolder> {

    private List<Worker_Modal> workerList;
    private Context context;


    public Worker_Adapter(Context context, ArrayList<Worker_Modal> workerList) {
        this.context = context;
        this.workerList = workerList;
    }

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_items, parent, false);
        return new WorkerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, int position) {
        Worker_Modal worker = workerList.get(position);

        holder.workerImage.setImageResource(worker.getImageResId());
        holder.workerName.setText(worker.getName());
        holder.workerCategory.setText(worker.getCategory());
        holder.workerPhone.setText(worker.getPhone());
        holder.workerEmail.setText(worker.getEmail());

        holder.hireButton.setOnClickListener(v -> {
            showConfirmationDialog(worker);
        });
    }

    @Override
    public int getItemCount() {
        return workerList.size();
    }

    public void updateList(List<Worker_Modal> filteredList) {
        this.workerList = filteredList;
        notifyDataSetChanged();
    }

    public static class WorkerViewHolder extends RecyclerView.ViewHolder {
        public ImageView workerImage;
        public TextView workerName, workerCategory, workerPhone, workerEmail;
        public AppCompatButton hireButton;

        public WorkerViewHolder(@NonNull View itemView) {
            super(itemView);
            workerImage = itemView.findViewById(R.id.worker_image);
            workerName = itemView.findViewById(R.id.worker_name);
            workerCategory = itemView.findViewById(R.id.category_worker);
            workerPhone = itemView.findViewById(R.id.phone_worker);
            workerEmail = itemView.findViewById(R.id.worker_email);
            hireButton = itemView.findViewById(R.id.hire_button);
        }
    }

    private void showConfirmationDialog(Worker_Modal worker) {
        new AlertDialog.Builder(context)
                .setTitle("Confirm Hiring")
                .setMessage("Are you sure you want to send a hiring request?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    Toast.makeText(context, "Hiring request sent!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }
}
