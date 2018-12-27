package com.siddharth.task.kissanhub.model;

import com.siddharth.task.kissanhub.ui.ParentListItem;

import java.util.List;

public class Country implements ParentListItem {
    private String mName;
    private List<Weather> mMovies;

    public Country(String name, List<Weather> movies) {
        mName = name;
        mMovies = movies;
    }

    public String getName() {
        return mName;
    }

    @Override
    public List<?> getChildItemList() {
        return mMovies;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
