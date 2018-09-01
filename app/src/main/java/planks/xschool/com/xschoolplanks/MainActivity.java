package planks.xschool.com.xschoolplanks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import planks.xschool.com.xschoolplanks.model.User;


public class MainActivity extends AppCompatActivity {

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    //private DatabaseReference userRef  = reference.child("Database").child("Users");

    private TextView hud; //Botão hud
    private boolean status = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hud = (TextView) findViewById(R.id.hud);

        //TODO checkUser();
        //Esse método deverá checar se o usuário está logado no planks
        //Se não estiver, deve-se iniciar o modo anônimo

        //TODO checkOnline();
        //Esse método deverá checar se o usuário está conectado na internet
        //Se não estiver, iniciar modo offline, se estiver logado + offline, deve abrir fragment planks_salvos



        hud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                /*
                */
                if(status){
                    OpenedHudFragment openedHudFragment = new OpenedHudFragment();
                    fragmentTransaction.add(R.id.rl_hud_id, openedHudFragment);
                    fragmentTransaction.commit();
                    status = false;
                } else {
                    Fragment fragment = fragmentManager.findFragmentById(R.id.rl_hud_id);
                    fragmentTransaction.remove(fragment);
                    fragmentTransaction.commit();
                    status = true;
                }

            }
        });



        //FIREBASE EXAMPLE
        /*
        User user = new User();
        user.setUserName("Anyone");
        String nome = user.getUserName();
        userRef.child("Usuários").setValue(nome);
        */


    }

    public void toastShort(String str){
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

}
