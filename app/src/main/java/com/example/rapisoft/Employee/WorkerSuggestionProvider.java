package com.example.rapisoft.Employee;

import android.content.SearchRecentSuggestionsProvider;

public class WorkerSuggestionProvider extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "com.example.rapisoft.Employee.WorkerSuggestionProvider";
    public static final int MODE = DATABASE_MODE_QUERIES;

    public WorkerSuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
