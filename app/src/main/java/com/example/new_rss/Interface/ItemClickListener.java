package com.example.new_rss.Interface;

import android.view.View;

//creating a interface for classes to use
public interface ItemClickListener {

    void onClick (View view, int position, boolean isLongClick);
}
