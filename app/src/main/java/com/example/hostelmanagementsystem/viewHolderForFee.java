package com.example.hostelmanagementsystem;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewHolderForFee extends RecyclerView.ViewHolder {

    View mView ;


    public viewHolderForFee(@NonNull View itemView) {


        super(itemView);

        mView = itemView ;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mClickListener.OnItemClick(view , getAdapterPosition() ) ;


            }
        });

        // item long click

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.OnItemLongClick(view , getAdapterPosition() ) ;

                return true;
            }
        });




    }
    public  void  setDataToRow(Context ctx  , String postId ,String name , String roll  ,String aclass  ,String doa  ,String paymonthList ,
                               String section , String address ,String ph  ){

        //assaingning the views

        TextView mNameTv = mView.findViewById(R.id.nameOfRow) ;
        TextView mPostTv = mView.findViewById(R.id.idOfRow);
        TextView mDate = mView.findViewById(R.id.doaOfRow);
        TextView mPaymentTv = mView.findViewById(R.id.paymentOfRow);
        TextView mRollTv = mView.findViewById(R.id.rollOfRow);
        TextView mClassTv = mView.findViewById(R.id.classOfRow);
        TextView mSectionTv = mView.findViewById(R.id.secOFRow);
        TextView mAdress = mView.findViewById(R.id.adressOfRow);
        TextView phTv = mView.findViewById(R.id.phoneOfRow);

        mPaymentTv.setVisibility(View.VISIBLE);
        mAdress.setVisibility(View.GONE);
        phTv.setVisibility(View.GONE);


//setting data to the views
        mNameTv.setText(name);
        mPostTv.setText(postId);
        mDate.setText(roll );
        mPaymentTv.setText("Month List :"+paymonthList);
        mRollTv.setText(roll);
        mClassTv.setText(aclass);
        mSectionTv.setText(section);



    }


        private  viewHolderForFee.ClickListener mClickListener ;


    // interface to send callbacks

    public  interface  ClickListener{

                Void OnItemClick(View view , int position);
              Void OnItemLongClick(View view , int position);

}

public  void  setsetOnClickListener(viewHolderForFee.ClickListener clickListener){



        mClickListener = clickListener ;



}






}
