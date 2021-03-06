package com.example.teamdevhackers.encomm.Complaints;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.example.teamdevhackers.encomm.R;

public class ComplainRegister  extends AppCompatActivity
{

    private EditText requester;
    private EditText complainTitle;
    private EditText houseNo;
    private Spinner hostelName;
    private Spinner complainType;
    private EditText complainDesc;
    private CheckBox isPrivate;
    private Button submitButton;
    private ProgressDialog complainProgressDialog;

    private FirebaseUser user;
    private DatabaseReference complainRootRef;
    private DatabaseReference complainRef;


    private ComplainItemClass newComplain;

    String requesterName;
    String title;
    String house;
    String hostel;
    String type;
    String desc;
    Boolean isChecked=false;
    String receivers ="abc";
    private int status=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        Log.d("ComplainRegister","Reaching");

        if(savedInstanceState!=null){
            onRestoreInstanceState(savedInstanceState);
        }
        initComplain();


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkIfNull()){

                    showProgressDialog();



                newComplain=new ComplainItemClass(user.getEmail(),requesterName,house,hostel,type, status,title,desc,isChecked, receivers);
                complainRef=complainRootRef.child("Complaints").push().getRef();
                complainRef.setValue(newComplain).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            complainRef.child("complainTime").setValue(ServerValue.TIMESTAMP);
                            hideProgressDialog();
                            Toast.makeText(getApplicationContext(),"Registration Complete", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ComplainRegister.this,com.example.teamdevhackers.encomm.HomeActivity.class));

                        }else
                        {
                            hideProgressDialog();
                            Toast.makeText(getApplicationContext(),"Failed to Register", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ComplainRegister.this,com.example.teamdevhackers.encomm.HomeActivity.class));


                        }
                    }
                });


                }


            }
        });

    }


    protected void onResume() {
        super.onResume();

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5cae80")));
        actionBar.setTitle(Html.fromHtml("<font color='#ffffff'>EnComm Registration</font>"));
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return true;

    }



    private void initComplain(){

        requester=findViewById(R.id.complain_username);
        complainTitle=findViewById(R.id.complain_title);
        houseNo=findViewById(R.id.complain_house_no);
        hostelName=findViewById(R.id.add_complaint_hostel);
        complainType=findViewById(R.id.add_complaint_type);
        complainDesc=findViewById(R.id.et_complain_desc);
        isPrivate=findViewById(R.id.complain_is_private);
        submitButton=findViewById(R.id.complain_submit);

        FirebaseAuth complainAuth = FirebaseAuth.getInstance();
        user = complainAuth.getCurrentUser();
        FirebaseDatabase complainInstance = FirebaseDatabase.getInstance();
        complainRootRef= complainInstance.getReference("Maintenance");
        complainRef=complainRootRef.child("Complaints").getRef();

    }


    private Boolean checkIfNull(){


         requesterName=requester.getText().toString().trim();
         title=complainTitle.getText().toString().trim();
         house=houseNo.getText().toString().trim();
         hostel=hostelName.getSelectedItem().toString();
         type=complainType.getSelectedItem().toString();
         desc=complainDesc.getText().toString().trim();
         isChecked=isPrivate.isChecked();


        if (requesterName.equals("") || TextUtils.isEmpty(requesterName) || requesterName.length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter your Name", Toast.LENGTH_SHORT).show();
            return false ;
        }

        if (title.equals("") || TextUtils.isEmpty(title) || title.length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter the Complain title", Toast.LENGTH_SHORT).show();
            return false ;

        } if (house.equals("") || TextUtils.isEmpty(house) || house.length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter your House Number", Toast.LENGTH_SHORT).show();
            return false ;

        } if (hostel.equals("") || TextUtils.isEmpty(hostel) || hostel.length() == 0) {
            Toast.makeText(getApplicationContext(), "Select your Hostel Number/Name", Toast.LENGTH_SHORT).show();
            return false ;

        } if (type.equals("") || TextUtils.isEmpty(type) || type.length() == 0) {
            Toast.makeText(getApplicationContext(), "Select your Complain type", Toast.LENGTH_SHORT).show();
            return false ;
        }

        return true;
    }

    public void showProgressDialog() {

        if (complainProgressDialog == null) {
            complainProgressDialog = new ProgressDialog(this,R.style.MyAlertDialogStyle);
            complainProgressDialog.setMessage("Registering ...");
            complainProgressDialog.setIndeterminate(true);
        }

        complainProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (complainProgressDialog != null && complainProgressDialog.isShowing()) {
            complainProgressDialog.dismiss();
        }
    }


}
