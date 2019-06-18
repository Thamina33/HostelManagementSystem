package com.example.hostelmanagementsystem;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewHolderForComplainList extends RecyclerView.ViewHolder {



    View mView ;

    public viewHolderForComplainList(@NonNull View itemView) {




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

    //String  postID  , name  , roll , aClass , section , date , reason , details  ;

    //set details to recycler view row
    public void setDataToView(Context ctx, String postId , String name , String roll  , String aclass  ,String section , String date  , String reason ,
                              String details){
        //Views
        TextView mNameTv = mView.findViewById(R.id.nameOfROWW);
        TextView mPostTv = mView.findViewById(R.id.idOfROWW);
        TextView mDate = mView.findViewById(R.id.dateOFROWW);
        TextView mRollTv = mView.findViewById(R.id.rollOfROWW);
        TextView mClassTv = mView.findViewById(R.id.ClassOfROWW);
        TextView mSectionTv = mView.findViewById(R.id.secOfROWW);
        TextView mreasonTv = mView.findViewById(R.id.reasonOfROWW);
        TextView mdetailsTv = mView.findViewById(R.id.detailsOfROWW);


        //set data to views
        mNameTv.setText(name);
        mPostTv.setText(postId);
        mDate.setText(roll );
        mRollTv.setText(roll);
        mClassTv.setText(aclass);
        mSectionTv.setText(section);
        mreasonTv.setText(reason);
        mdetailsTv.setText("Details :" + details);


    }

    private viewHolderForComplainList.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(viewHolderForComplainList.ClickListener clickListener){
        mClickListener = clickListener;
    }



}
