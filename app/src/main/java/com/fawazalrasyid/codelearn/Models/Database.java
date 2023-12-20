package com.fawazalrasyid.codelearn.Models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class Database {
    public Query query(DatabaseReference reference){
        Query query = reference;
        query.keepSynced(true);

        return query;
    }
}
