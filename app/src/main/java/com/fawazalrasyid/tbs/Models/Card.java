package com.fawazalrasyid.tbs.Models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Card {
    public String cardname;
    public String cardlogo;
    public String id;

    public Card() {}

    public Card(String cardname, String cardlogo, String id) {
        this.cardname = cardname;
        this.cardlogo = cardlogo;
        this.id = id;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("cardname", cardname);
        result.put("cardlogo", cardlogo);
        result.put("id", id);

        return result;
    }
}
