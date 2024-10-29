package com.example.rapisoft.Work_request;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rapisoft.R;

import java.util.List;

public class Request_Adapter extends RecyclerView.Adapter<Request_Adapter.RequestViewHolder> {

    private List<WorkerRequestModel> workrequests;
    private Context context;

    public Request_Adapter(Context context,List<WorkerRequestModel> workrequests) {
        this.workrequests = workrequests;
        this.context = context;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_request, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        WorkerRequestModel workerRequest = workrequests.get(position);
        holder.contact.setText(workerRequest.getContact());
        holder.requests.setText(workerRequest.getRequest());
        holder.client.setText(workerRequest.getClient());
        holder.status.setText(workerRequest.getStatus());

        holder.accept.setOnClickListener(v -> {
            acceptRequest();

        });
        holder.contact_employee.setOnClickListener(v -> {
            confirmContactEmployee();

        });
    }

    @Override
    public int getItemCount() {
        return workrequests.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {

        public TextView contact, status, requests, client;
        public AppCompatButton accept, contact_employee;

        public RequestViewHolder(@NonNull View itemview) {
            super(itemview);
            contact = itemview.findViewById(R.id.contact_worker);
            status = itemview.findViewById(R.id.status_worker);
            requests = itemview.findViewById(R.id.request_worker);
            client = itemview.findViewById(R.id.client_worker);

            accept = itemview.findViewById(R.id.request_button);
            contact_employee = itemview.findViewById(R.id.contact);
        }
    }
    private void confirmContactEmployee() {
          new AlertDialog.Builder(context)
                .setTitle("Confirm Contact")
                .setMessage("Are you sure you want to contact the employee?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    Toast.makeText(context, "Employee Calling You Within 15-30min", Toast.LENGTH_SHORT).show();


                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }
    private void acceptRequest() {

        Toast.makeText(context, "Request Accepted", Toast.LENGTH_SHORT).show();
    }
}
