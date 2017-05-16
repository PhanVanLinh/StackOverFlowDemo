package com.example.framgia.checkboxinrecyclerview;

/**
 * Created by phanvanlinh on 16/05/2017.
 * Email: phanvanlinh.94vn@gmail.com
 */

public class Item {
    private String title;
    private boolean checked;

    public Item(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
