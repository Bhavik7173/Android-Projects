package com.example.sample;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpacingItemDecorator extends RecyclerView.ItemDecoration {
    private int verticalSpacing;

    public SpacingItemDecorator(int verticalSpacing2) {
        this.verticalSpacing = verticalSpacing2;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = this.verticalSpacing;
    }
}
