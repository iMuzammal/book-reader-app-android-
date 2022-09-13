package com.example.imuza.bookproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.imuza.bookproject.Bookrecord;
import com.example.imuza.bookproject.Model.dataModel;
import com.example.imuza.bookproject.R;

import java.util.ArrayList;


public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.ViewHolder> {


    Context context;
    ArrayList<dataModel> arrayList;
    //Step 4: Declare  interface object ;
    //Step 5: goto line no 32 in parameter like inter face "Delete" object "del"
    //Step 6: goto line no 36;
    //step 7: initiallized delete object with del object
    //step 8: goto line 97 ;
    Delete delete;


    public RecylerAdapter(Context context, ArrayList<dataModel> arrayList, Delete del) {
        this.context = context;
        this.arrayList = arrayList;
        this.delete = del;
    }

    @NonNull
    @Override
    public RecylerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from( context ).inflate( R.layout.list_item, viewGroup, false );
        RecylerAdapter.ViewHolder viewHolder = new RecylerAdapter.ViewHolder( view );


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecylerAdapter.ViewHolder viewHolder, int i) {

        final dataModel dataModel = arrayList.get( i );

        viewHolder.nameBook.setText( dataModel.getBname() );
        viewHolder.price.setText( "" + dataModel.getPrice() );

        //Image get code step:
        //First Step 1: add dependency like "  implementation 'com.github.bumptech.glide:glide:3.7.0' "
        //step 2 : add repositories {} in build.gradle ( Module: App) in line no 20 :
        //Step 3 : implement the image syntax :  like below :
        //step 4: Goto line 74

        Glide.with( context ).load( dataModel.getBookimg() ).into( viewHolder.image );

        viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( context, Bookrecord.class );

                //Step 5: get the imageid from the data model and goto the BookRecord.class line no 59
                intent.putExtra( "IMAGE", dataModel.getBookimg() );
                intent.putExtra( "NAME", dataModel.getBname() );
                intent.putExtra( "WRITER", dataModel.getWname() );
                intent.putExtra( "PRICE", dataModel.getPrice() );
                intent.putExtra( "ID", dataModel.getId() );
                context.startActivity( intent );

            }
        } );

        //step 12 : setonclick btn on "delete"...
        //Step 13 : goto line no 84
        viewHolder.delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Step 14: relation create with the "Delete" id and " pressdel" method from the interface.
                //step 15: get the id from the data Model .
                //step 16 : goto showbookdatafragment.class and line no 83 s
                delete.pressdel( dataModel.getId() );
            }
        } );


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameBook, price;
        //Step 10: decaler delete image view ;and create the relation with delete image find view by id:
        //Step 11 : Goto line 78;
        ImageView image, delete;


        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            nameBook = itemView.findViewById( R.id.bookname );
            image = itemView.findViewById( R.id.itemimg );
            price = itemView.findViewById( R.id.price );
            delete = itemView.findViewById( R.id.delete );


        }
    }


    //Call back from inter face :
    //Step 1 : create inter  like Delete{} name ;
    //Step 2 : Create pressdel() method;
    //Step 3 Goto 27 line no ;

    public interface Delete {


        public void pressdel(int id);

    }
}
