package com.revature.web;

import java.util.ArrayList;
import java.util.List;

public class NameDao {
    private static List<Name> namesdb = new ArrayList<Name>() {
        {
            add(new Name(1, "Mehrab"));
            add(new Name(2, "Rahman"));
        }
    };

    public static Name findById(int id) {
        return namesdb.get(id);
    }
}