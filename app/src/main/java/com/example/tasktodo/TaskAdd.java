package com.example.tasktodo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.badge.BadgeUtils;

import java.util.ArrayList;

public class TaskAdd extends AppCompatActivity {

    MyDbHelper db ;
    Toolbar toolbar;
    String username,password;
    RecyclerView recyclerView;
    @SuppressLint({"NotifyDataSetChanged", "RestrictedApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task_add);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");

        db= new MyDbHelper(this);
        ArrayList<arryNotes> arry = db.getNotes(username);
            db.close();
            recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdepter rec = new recyclerAdepter(TaskAdd.this, arry,username);
        recyclerView.setAdapter(rec);
        recyclerView.scrollToPosition(arry.size() - 1);

        EditText editText = findViewById(R.id.editTextTask);
        Button btnAdd = findViewById(R.id.buttonAddTask);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitle("Tasks");
        toolbar.setSubtitle(username.toUpperCase());


        btnAdd.setOnClickListener(v -> {

            String note = editText.getText().toString();
            if (!note.isEmpty()) {
                MyDbHelper db=new MyDbHelper(this);
                if (db.insertUserData(username, note)) {
                    ArrayList<arryNotes>   arryy = db.getNotes(username);
                    recyclerAdepter r = new recyclerAdepter(TaskAdd.this, arryy,username);
                    recyclerView.setAdapter(r);
                    recyclerView.scrollToPosition(arryy.size() - 1);
                    editText.setText("");
                    Toast.makeText(TaskAdd.this, "insert", Toast.LENGTH_SHORT).show();
                    db.close();
                } else {
                    Toast.makeText(TaskAdd.this, "Not Insert", Toast.LENGTH_SHORT).show();
                    db.close();
                }
            } else {
                Toast.makeText(TaskAdd.this, "Enter notes", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.user_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id=item.getItemId();
        if(item_id==R.id.LOGOUT){
            AlertDialog.Builder builder= new AlertDialog.Builder(this).
                    setTitle(" LOGOUT ").setMessage("Do You Want To Logout?").
                    setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(TaskAdd.this,Login.class);
                            startActivity(intent);
                                finish();
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            builder.show();


        }
        if(item_id==R.id.dltUser){

            AlertDialog.Builder builder=new AlertDialog.Builder(this).
                    setTitle(" DELETE USER ").setMessage("Do You Want To Delete User?").
                    setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                              Intent intent = new Intent(TaskAdd.this, Login.class);
                            db.dropTable(username);
                            db.deleteUser(username);
                              startActivity(intent);
                              finish();

                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            builder.show();
        }
        if(item_id==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}