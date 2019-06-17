package com.example.hostelmanagementsystem;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewHoderForExpenceList extends RecyclerView.ViewHolder {


    View mView ;

    public viewHoderForExpenceList(@NonNull View itemView) {

        super(itemView);

        mView = itemView ;

        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });


    }

    //set details to recycler view row
    public void setDataToView(Context ctx, String reason , String amount , String Date   ){
        //Views
        TextView mReasonTv = mView.findViewById(R.id.reasonId);
        TextView mamount = mView.findViewById(R.id.amountID);
        TextView mDate = mView.findViewById(R.id.dateID);




        //set data to views
        mReasonTv.setText(reason);
        mamount.setText(amount);
        mDate.setText(Date );


    }

    private viewHoderForExpenceList.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(viewHoderForExpenceList.ClickListener clickListener){
        mClickListener = clickListener;
    }


}