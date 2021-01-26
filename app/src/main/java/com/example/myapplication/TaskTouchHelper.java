package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class TaskTouchHelper extends ItemTouchHelper.SimpleCallback {

    private TaskRecyclerAdapter taskRecyclerAdapter;

    public TaskTouchHelper(TaskRecyclerAdapter taskRecyclerAdapter) {
        super(0, ItemTouchHelper.LEFT);
        this.taskRecyclerAdapter = taskRecyclerAdapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        //get the position of the selected item
        int position = viewHolder.getAdapterPosition();

        //check the direction of swipe
        if (direction == ItemTouchHelper.LEFT) {
            popUpDelete(viewHolder, position);
        }
    }

    /**
     * Here we will add the icon for delete
     *
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        Drawable icon = ContextCompat.getDrawable(taskRecyclerAdapter.getActivity(), R.drawable.delete_icon);
        ColorDrawable background = new ColorDrawable(Color.WHITE);

        View item = viewHolder.itemView;
        int iconMargin = (item.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconTop = item.getTop() + (item.getHeight() - icon.getIntrinsicHeight()) / 2;
        int iconBottom = iconTop + icon.getIntrinsicHeight();
        int iconLeft = item.getRight() - iconMargin - icon.getIntrinsicWidth();
        int iconRight = item.getRight() - iconMargin;

        //swipe left to DELETE
        if (dX < 0) {
            icon = ContextCompat.getDrawable(taskRecyclerAdapter.getActivity(), R.drawable.delete_icon);
            background = new ColorDrawable(Color.RED);
            background.setBounds(item.getRight() + ((int) dX), item.getTop(), item.getRight(), item.getBottom());
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
        }

        background.draw(c);
        icon.draw(c);


    }

    private void popUpDelete(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(taskRecyclerAdapter.getActivity());
        alertDialogBuilder.setTitle("Delete Task");
        alertDialogBuilder.setMessage("Are you sure you want to remove this task?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                taskRecyclerAdapter.deleteTask(position);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                taskRecyclerAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
