package com.example.rapisoft;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rapisoft.Login.Sign_in_Activity;
import com.example.rapisoft.Work_request.Request_Adapter;
import com.example.rapisoft.Work_request.WorkerRequestModel;

import java.util.ArrayList;

public class Worker_Activity extends AppCompatActivity {
    public RecyclerView recycler_worker;
    public ArrayList<WorkerRequestModel> workersrequest;
    private Request_Adapter workerAdapter;
    private AppCompatButton btnlog_out;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
        getWindow().setStatusBarColor(ContextCompat.getColor(Worker_Activity.this,R.color.olive));


        inIt();
        setRecycler();
        loadWorkers();
        btnlog_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLogout();
            }
        });
    }

    public void inIt(){
        recycler_worker = findViewById(R.id.recycler_worker);
        workersrequest = new ArrayList<>();
        btnlog_out=findViewById(R.id.btn_logout);
    }

    public void setRecycler() {
        workerAdapter = new Request_Adapter(this, workersrequest);
        recycler_worker.setLayoutManager(new LinearLayoutManager(this));
        recycler_worker.setAdapter(workerAdapter);
    }

    private void loadWorkers() {

        workersrequest.add(new WorkerRequestModel("Client 1", "Pending", "26/10/2024 || 10am", "1234567890"));
        workersrequest.add(new WorkerRequestModel("Client 2", "Pending", "26/10/2024 || 10am", "1234567891"));
        workersrequest.add(new WorkerRequestModel("Client 3", "Pending", "26/10/2024 || 10am", "1234567892"));
        workersrequest.add(new WorkerRequestModel("Client 4", "Pending", "26/10/2024 || 10am", "1234567893"));
        workersrequest.add(new WorkerRequestModel("Client 5", "Pending", "26/10/2024 || 10am", "1234567894"));
        workersrequest.add(new WorkerRequestModel("Client 6", "Pending", "26/10/2024 || 10am", "1234567895"));

        workerAdapter.notifyDataSetChanged();
    }

    private void dialogLogout(){
        LayoutInflater inflater =getLayoutInflater();
        View dialogview=inflater.inflate(R.layout.dialog_logout,null);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(dialogview);

        TextView title = dialogview.findViewById(R.id.dialog_title);
        TextView message = dialogview.findViewById(R.id.dialog_message);
        AppCompatButton btnStay = dialogview.findViewById(R.id.button_stay);
        AppCompatButton btnLeave = dialogview.findViewById(R.id.button_leave);

        AlertDialog dialog=builder.create();
        btnStay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Worker_Activity.this, Sign_in_Activity.class));
                finish();
            }
        });
        dialog.show();
    }
}
