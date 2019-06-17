package com.example.hostelmanagementsystem;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewHolderForStudentStudentList extends RecyclerView.ViewHolder {

    View mView ;

    public viewHolderForStudentStudentList(@NonNull View itemView) {




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
    public void setDataToView(Context ctx, String postId ,String name , String roll  ,String aclass  ,String doa  ,String paymonthList ,
                              String section , String address ,String ph  ){
        //Views
        TextView mNameTv = mView.findViewById(R.id.nameOfRow);
        TextView mPostTv = mView.findViewById(R.id.idOfRow);
        TextView mDate = mView.findViewById(R.id.doaOfRow);
        TextView mPaymentTv = mView.findViewById(R.id.paymentOfRow);
        TextView mRollTv = mView.findViewById(R.id.rollOfRow);
        TextView mClassTv = mView.findViewById(R.id.classOfRow);
        TextView mSectionTv = mView.findViewById(R.id.secOFRow);
        TextView mAdress = mView.findViewById(R.id.adressOfRow);
        TextView phTv = mView.findViewById(R.id.phoneOfRow);


        //set data to views
        mNameTv.setText(name);
        mPostTv.setText(postId);
        mDate.setText(roll );
        mPaymentTv.setText( paymonthList);
        mRollTv.setText(roll);
        mClassTv.setText(aclass);
        mSectionTv.setText(section);
        mAdress.setText(address);
        phTv.setText(ph);


    }

    private viewHolderForStudentStudentList.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(viewHolderForStudentStudentList.ClickListener clickListener){
        mClickListener = clickListener;
    }


}
