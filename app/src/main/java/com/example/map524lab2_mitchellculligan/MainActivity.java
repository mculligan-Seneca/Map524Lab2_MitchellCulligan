package com.example.map524lab2_mitchellculligan;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.DialogInterface;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import android.widget.Switch;


public class MainActivity extends AppCompatActivity {

    Button purchase;

   RadioGroup movieGroup;

    CheckBox popcorn;
    CheckBox coke;
    CheckBox gummy;

    Switch switch3D;

    Builder builder;

    Spinner typeSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] ticketType= {"Child","Adult","Senior"};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this,R.layout.ticket_spinner_design,
                R.id.ticket_typeId,ticketType);

        this.builder = new Builder(this);
       // this.img = findViewById(R.id.movieImgId);
        this.purchase = findViewById(R.id.purchase_btn);
        this.movieGroup=findViewById(R.id.movieGroupId);
        this.popcorn = findViewById(R.id.popcorn_check);
        this.coke = findViewById(R.id.coke_check);
        this.gummy=findViewById(R.id.gum_check);
        this.switch3D = findViewById(R.id.threeD_switch);
        this.typeSpinner =findViewById(R.id.type_spinner);

        this.typeSpinner.setAdapter(typeAdapter);




        this.purchase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String type;
                StringBuilder snackList = new StringBuilder();
                String movie=null;
                    if(checkValidity()){
                        Toast.makeText(getApplicationContext(),R.string.success_message,Toast.LENGTH_SHORT).show();
                        type=typeSpinner.getSelectedItem().toString();
                        switch(movieGroup.getCheckedRadioButtonId()){
                            case R.id.avengersMovie_btn:
                                movie="Avengers";
                                break;
                            case  R.id.spiritedAway_btn:
                                movie="Spirited Away";
                                break;
                            case R.id.darkKnight_btn:
                                movie="The Dark Knight";
                                break;
                        }

                        if(popcorn.isChecked())
                            snackList.append(" Popcorn ");
                        if(coke.isChecked())
                            snackList.append(" Coke ");
                        if(gummy.isChecked())
                            snackList.append(" Gummies ");

                        builder.setMessage("Thank you for your purchase, enjoy the show!\n"+
                                "ticket: "+ type+"\n"+
                                "Movie: "+ movie +"\n"+
                                 "Snacks: "+(snackList.toString()==""?"None":snackList.toString())
                                    +"\n"+
                                  "Viewing: "+ (switch3D.isChecked()?"3D":"Regular"))
                                .setCancelable(true)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).setIcon(R.drawable.popcorn_logo).setTitle("Ticket Menu");

                       AlertDialog alert= builder.create();
                       alert.show();



                    }else{
                        Toast.makeText(getApplicationContext(),R.string.error_message,Toast.LENGTH_SHORT).show();
                    }
            }
        });


    }





    public boolean checkValidity(){
        boolean valid=false;
        if(this.movieGroup.getCheckedRadioButtonId()!=-1)
            valid=true;

        return valid;
    }
}