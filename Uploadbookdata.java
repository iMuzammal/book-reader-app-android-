package com.example.imuza.bookproject.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.imuza.bookproject.MainActivity;
import com.example.imuza.bookproject.R;
import com.example.imuza.bookproject.database.myDbHandler;

import static android.app.Activity.RESULT_OK;

public class Uploadbookdata extends Fragment {

    myDbHandler mydbhander;
    private EditText enterbookname, enterwritername, enterprice;
    private Button uploadbook;
    //Upload image Code..... 10/12/2018 Muzammal Rasheed..
    private ImageView imageupload;
    private Button uploadbutton;
    private Uri imguri;
    private static final int PICK_IMAGE = 100;


    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate( R.layout.fragment_upload_book, container, false );
        mydbhander = new myDbHandler( view.getContext() );
        enterbookname = (EditText) view.findViewById( R.id.entername );
        enterwritername = (EditText) view.findViewById( R.id.Writerid );
        enterprice = (EditText) view.findViewById( R.id.priceid );
        uploadbook = (Button) view.findViewById( R.id.senddata );
        uploadbutton = (Button) view.findViewById( R.id.uploading );
        imageupload = (ImageView) view.findViewById( R.id.imgview );
        add();
        image();
        return view;
    }

    public void add() {
        uploadbook.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted = mydbhander.insertData(

                        enterbookname.getText().toString(),
                        enterwritername.getText().toString(),
                        enterprice.getText().toString(),
                        imguri+""
                );
                if (isInserted) {
                    Toast.makeText( getView().getContext(), "Data Inserted", Toast.LENGTH_LONG ).show();
                    ((MainActivity) getActivity()).callActivity();
                    //clearStack();

                    //       FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    if (fr != null) {
//
//                        fr.remove(abc);
//                        fr.commit();
//                       fr.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//                        fr = null;
//                    }

//                    FragmentManager fm = getActivity().getSupportFragmentManager();
//                    for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
//                        fm.popBackStack();
//                    }
                    //  fr.replace( R.id.uploadfragmentcontainer,new showbookdatafragment() );

                    //fr.addToBackStack( "first frag" );
                    //fr.commit();


                } else {

                    Toast.makeText( getView().getContext(), "Data No Intert", Toast.LENGTH_SHORT ).show();
                }

            }


        } );

    }


    public void image() {

        uploadbutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengellery();
            }
        } );

    }


    private void opengellery() {


        Intent gallery = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI );
        startActivityForResult( gallery, PICK_IMAGE );
    }

    @Override
    public void onActivityResult(int requestCode, int
            reusltCode, Intent data) {
        if (reusltCode == RESULT_OK && requestCode == PICK_IMAGE) {

            imguri = data.getData();
            imageupload.setImageURI( imguri );


        }

    }


//

////    public void removeYourFragment(){
////
////    }
//
//
//    }
}
