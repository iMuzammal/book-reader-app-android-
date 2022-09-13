package com.example.imuza.bookproject.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.WithHint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.imuza.bookproject.Adapter.RecylerAdapter;
import com.example.imuza.bookproject.Bookrecord;
import com.example.imuza.bookproject.MainActivity;
import com.example.imuza.bookproject.Model.dataModel;
import com.example.imuza.bookproject.R;
import com.example.imuza.bookproject.database.myDbHandler;

import java.util.ArrayList;

public class showbookdatafragment extends Fragment {

    private myDbHandler myDbHandler;
    private dataModel dataModel;
    private ArrayList<dataModel> arrayList = new ArrayList<>();
    private RecylerAdapter recylerAdapter;
    private RecyclerView recyclerView;
    private Cursor cursor;
    myDbHandler mydbhander;


    private Button up, del;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.showlayout, container, false );

        myDbHandler = new myDbHandler( getContext().getApplicationContext() );
        recyclerView = (RecyclerView) view.findViewById( R.id.ver );
        loadData();

        mydbhander = new myDbHandler( getActivity() );
        return view;

    }

    public void loadData() {

        myDbHandler = new myDbHandler( getActivity() );
        cursor = myDbHandler.getAllData();
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    dataModel = new dataModel();

                    dataModel.setId( cursor.getInt( 0 ) );
                    dataModel.setBname( cursor.getString( 1 ) );
                    dataModel.setWname( cursor.getString( 2 ) );
                    dataModel.setPrice( cursor.getString( 3 ) );
                    dataModel.setBookimg( cursor.getString( 4 ) );

                    arrayList.add( dataModel );

                } while (cursor.moveToNext());

            }

        }


        //Step 17:  first create the new interface Delete name . and get the method from the interface of Delete name .
        //Step 18: and get the pressdel method from the interface .
        //Step 19: Create the  myDatabase object in this fragment . and initiallized the object .
        //step 20: myDatabase object with name mydbatabase and call the deletedData() from the myDatabase. class
        //step 21: have done!
        recylerAdapter = new RecylerAdapter( getActivity(), arrayList, new RecylerAdapter.Delete() {
            @Override
            public void pressdel(int id) {
                mydbhander.deleteData( "" + id );
                recylerAdapter.notifyDataSetChanged();
                Toast.makeText( getContext(), "Data Successfully Delete", Toast.LENGTH_SHORT ).show();
                //
                // Final Step 1 :Clear Data like Real Time database :

                arrayList.clear();
                loadData();

            }
        } );
        recyclerView.setAdapter( recylerAdapter );
        recyclerView.setLayoutManager( new GridLayoutManager( getActivity(), 2 ) );
        recylerAdapter.notifyDataSetChanged();

    }


    // Clear Data ; onclick to another activity;
    //Step 1 : Create on Resume();
    //Step2 : Call arrayList and call Clear(); from actitvity
    //Step 3 : Call LoadData();
    @Override
    public void onResume() {
        super.onResume();
        arrayList.clear();
        loadData();
    }
}
