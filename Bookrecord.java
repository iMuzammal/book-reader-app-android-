package com.example.imuza.bookproject;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.imuza.bookproject.Fragment.Uploadbookdata;
import com.example.imuza.bookproject.Fragment.showbookdatafragment;
import com.example.imuza.bookproject.Model.Back;
import com.example.imuza.bookproject.Model.dataModel;
import com.example.imuza.bookproject.database.myDbHandler;

//call back
//Step 3 implements the interface  in this class and get all function from interface . goto line 221
public class Bookrecord extends AppCompatActivity implements Back {

    TextView id;
    ImageView imageView, chngePic;
    Button up, del, callid;
    EditText name, writer, price;
    myDbHandler mydbhander;
    //call back step 5
// declare interface object in this class goto line no 50
    Back back;
    Uri imguri;
    private static final int PICK_IMAGE = 100;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bookrecord );

        //call back step 5
        // initiallized book object in this class then goto line no 98 ;
        back = Bookrecord.this;

        Intent intent = getIntent();

        String Name = intent.getExtras().getString( "NAME" );
        String Writer = intent.getExtras().getString( "WRITER" );
        String Price = intent.getExtras().getString( "PRICE" );

        //Step 6: get the image from the RecyclerAdapter.class: goto line 74
        String Image = intent.getExtras().getString( "IMAGE" );
        int id_number = intent.getExtras().getInt( "ID" );

        mydbhander = new myDbHandler( this );
        imageView = (ImageView) findViewById( R.id.bookview );
        chngePic = (ImageView) findViewById( R.id.chngePic );
        name = (EditText) findViewById( R.id.nameView );
        writer = (EditText) findViewById( R.id.WriterView );
        price = (EditText) findViewById( R.id.PriceView );
        id = (TextView) findViewById( R.id.idno );
        up = (Button) findViewById( R.id.updateRecord );
        callid = (Button) findViewById( R.id.callid );
        del = (Button) findViewById( R.id.delRecord );

        //Step 7: set the image to imageView with relate the layout
        //Step 8: have Done !
        Glide.with( Bookrecord.this ).load( Image ).into( imageView );
        id.setText( String.valueOf( id_number ) );
        name.setText( Name );
        writer.setText( Writer );
        price.setText( Price );
        updateData();
        DeleteData();
        chngePic.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePic();


            }
        } );

        callid.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackfunction();
            }
        } );

    }

       public void updateData() {

        up.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean isUpdate = mydbhander.updateData( imguri + "",
                        id.getText().toString(),
                        name.getText().toString(), writer.getText().toString(), price.getText().toString()
                );
                if (isUpdate) {


                    Toast.makeText( getApplicationContext(), "Data Successfully update", Toast.LENGTH_SHORT ).show();
                    FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
                    fragmentManager.replace( R.id.delFrame, new showbookdatafragment(), null ).commitAllowingStateLoss();


                    finish();


                } else
                    Toast.makeText( getApplicationContext(), "Data not Successfully update", Toast.LENGTH_SHORT ).show();
            }
        } );
    }


    public void DeleteData() {

        del.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer DeleteRows = mydbhander.deleteData( id.getText().toString() );

                if (DeleteRows > 0) {

                    Toast.makeText( getApplicationContext(), "Books Successfull Deleted", Toast.LENGTH_SHORT ).show();


                    FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
                    fragmentManager.replace( R.id.delFrame, new showbookdatafragment(), null ).commitAllowingStateLoss();
                    finish();

                } else {
                    Toast.makeText( getApplicationContext(), "Books Not Successfull Delete", Toast.LENGTH_SHORT ).show();

                }
            }
        } );

    }


    public void ChangePic() {

        AlertDialog.Builder alertDialog;
        alertDialog = new AlertDialog.Builder( Bookrecord.this );

        // Setting Dialog Title
        alertDialog.setTitle( "Change Book Image" );

        // Setting Dialog Message
        alertDialog.setMessage( "Are you sure you want change this?" );

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.delete);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton( "YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                opengellery();

                //how to attach coustom layout with alter box ?
                //Ans : attact layout with AlertDialog.setView(R.id.layout. name);
                //then implement the action which u want to use own project :




            }
        } );

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton( "NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                Toast.makeText( getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT ).show();
                dialog.cancel();
            }
        } );

        // Showing Alert Message
        alertDialog.show();


    }


    private void opengellery() {


        Intent gallery = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI );
        startActivityForResult( gallery, PICK_IMAGE );
    }

    @Override
    protected void onActivityResult(int requestCode, int
            reusltCode, Intent data) {
        if (reusltCode == RESULT_OK && requestCode == PICK_IMAGE) {

            imguri = data.getData();
            imageView.setImageURI( imguri );


        }

    }

    ///// callback Step 4:
    //import function in this class  line no 39.


    @Override
    public void back(String abc) {

        Toast.makeText( this, abc, Toast.LENGTH_SHORT ).show();

    }

    private void callbackfunction() {
        //call back step7
        // call the function from interface
        back.back( "Clicked" );


    }




}
