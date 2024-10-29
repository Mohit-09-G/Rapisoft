package com.example.rapisoft.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rapisoft.Database.SessionManager;
import com.example.rapisoft.Database.TestDatabase;
import com.example.rapisoft.Login.Sign_in_Activity;
import com.example.rapisoft.R;
import com.example.rapisoft.Worker_Activity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler_worker;
    private ArrayList<Worker_Modal> workers_list;
    private SearchView searchView;
    private Worker_Adapter adapter;
    private AppCompatButton searchButton;
    private TextView name,logoutbtn;
    private TestDatabase testDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.olive));


        init();

        workers_list = new ArrayList<>();
        loadWorkers();
        adapter = new Worker_Adapter(this,workers_list);
        recycler_worker.setLayoutManager(new LinearLayoutManager(this));
        recycler_worker.setAdapter(adapter);

        setupSearchView();


        SessionManager sessionManager = new SessionManager(this);
        String userName = sessionManager.getUserName();
        name.setText("Welcome "+userName +" !");




        searchButton.setOnClickListener(view -> {
            String query = searchView.getQuery().toString();
            if (!query.isEmpty()) {
                searchWorkers(query);
                saveSearchQuery(query);
            } else {
                Toast.makeText(this, "Enter a search term", Toast.LENGTH_SHORT).show();
            }
        });
        logoutbtn.setOnClickListener(v -> {
            dialogLogout();

        });

    }

    private void init() {
        recycler_worker = findViewById(R.id.recycler_employee);
        searchButton = findViewById(R.id.search_button);
        searchView = findViewById(R.id.search_view);
        logoutbtn=findViewById(R.id.tv_logout);
        name=findViewById(R.id.tv_name);
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }
        });


        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                String suggestion = searchView.getSuggestionsAdapter().getCursor().getString(1);
                searchView.setQuery(suggestion, true);
                return true;
            }
        });
    }

    private void searchWorkers(String query) {
        filter(query);
    }

    private void filter(String query) {
        List<Worker_Modal> filteredList = new ArrayList<>();
        for (Worker_Modal worker : workers_list) {
            if (worker.getCategory().toLowerCase().contains(query.toLowerCase()) ||
                    worker.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(worker);
            }
        }
        adapter.updateList(filteredList);
    }

    private void saveSearchQuery(String query) {
        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                WorkerSuggestionProvider.AUTHORITY, WorkerSuggestionProvider.MODE);
        suggestions.saveRecentQuery(query, null);
    }

    private void loadWorkers() {
        workers_list.add(new Worker_Modal("Sam Ram", "Plumber", "1234567890", "sam.ram@example.com", R.drawable.worker_1));
        workers_list.add(new Worker_Modal("Worker 2", "Carpenter", "0987654321", "worker2@example.com", R.drawable.worker_1));
        workers_list.add(new Worker_Modal("Worker 3", "Electrician", "2345678901", "worker3@example.com", R.drawable.worker_1));
        workers_list.add(new Worker_Modal("Worker 4", "Plumber", "3456789012", "worker4@example.com", R.drawable.worker_1));
        workers_list.add(new Worker_Modal("Worker 5", "Carpenter", "4567890123", "worker5@example.com", R.drawable.worker_1));
        workers_list.add(new Worker_Modal("Worker 6", "Electrician", "5678901234", "worker6@example.com", R.drawable.worker_1));
        workers_list.add(new Worker_Modal("Worker 7", "Plumber", "6789012345", "worker7@example.com", R.drawable.worker_1));
        workers_list.add(new Worker_Modal("Worker 8", "Carpenter", "7890123456", "worker8@example.com", R.drawable.worker_1));
        workers_list.add(new Worker_Modal("Worker 9", "Electrician", "8901234567", "worker9@example.com", R.drawable.worker_1));
        workers_list.add(new Worker_Modal("Worker 10", "Plumber", "9012345678", "worker10@example.com", R.drawable.worker_1));
        workers_list.add(new Worker_Modal("Worker 11", "Carpenter", "0123456789", "worker11@example.com", R.drawable.worker_1));
        workers_list.add(new Worker_Modal("Worker 12", "Electrician", "1123456789", "worker12@example.com", R.drawable.worker_1));
        workers_list.add(new Worker_Modal("Worker 13", "Plumber", "2234567890", "worker13@example.com", R.drawable.worker_1));
        workers_list.add(new Worker_Modal("Worker 14", "Carpenter", "3345678901", "worker14@example.com", R.drawable.worker_1));
        workers_list.add(new Worker_Modal("Worker 15", "Electrician", "4456789012", "worker15@example.com", R.drawable.worker_1));
    }
    @Override
    public void onBackPressed() {

        searchView.setQuery("", false);
        searchWorkers("");



         super.onBackPressed();
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
                startActivity(new Intent(MainActivity.this, Sign_in_Activity.class));
                finish();
            }
        });
        dialog.show();
    }
}
