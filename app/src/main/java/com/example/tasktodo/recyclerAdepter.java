package com.example.tasktodo;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class recyclerAdepter extends RecyclerView.Adapter<recyclerAdepter. ViewHolder> {
    Context context;
    ArrayList<arryNotes> arry;
    String username;

    recyclerAdepter(Context context,ArrayList<arryNotes> arry,String username){
        this.context=context;
        this.arry=arry;
        this.username=username;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vi= LayoutInflater.from(context).inflate(R.layout.task,parent,false);
        return new ViewHolder(vi);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txt1.setText(arry.get(position).Notes);

        holder.linear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context).
                        setTitle("Delete Task").setMessage("Do You Want To Delete?")
                        .setIcon(R.drawable.baseline_delete_forever_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String id=arry.get(position).id;
                                MyDbHelper db=new MyDbHelper(context);
                                db.deleteTask(username,id);
                                arry.remove(position);

                                notifyDataSetChanged();


                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arry.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt1;
        LinearLayout linear;
        public ViewHolder(View v){
            super(v);
            txt1=v.findViewById(R.id.notes);
            linear=v.findViewById(R.id.linear);
        }
    }

}
