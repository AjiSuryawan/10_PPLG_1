package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<MyListData> myListData;
    MyListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        myListData = new ArrayList<>();
        myListData.add(new MyListData("Email", android.R.drawable.ic_dialog_email));
        myListData.add(new MyListData("Info", android.R.drawable.ic_dialog_info));
        myListData.add(new MyListData("Delete", android.R.drawable.ic_delete));
        myListData.add(new MyListData("Dialer", android.R.drawable.ic_dialog_dialer));
        myListData.add(new MyListData("Alert", android.R.drawable.ic_dialog_alert));
        myListData.add(new MyListData("Map", android.R.drawable.ic_dialog_map));

        recyclerView = (RecyclerView) findViewById(R.id.rvlistdata);
        adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        registerForContextMenu(recyclerView);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
//                        Toast.makeText(MainMenu.this, myListData[position].getDescription(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext() , DetailActivity.class);
                        // post data
                        startActivity(intent);
                        //pindah ke halaman detail
                    }
                })
        );
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = ((MyListAdapter)recyclerView.getAdapter()).getPosition();
        switch (item.getItemId()) {
            case R.id.delete:

                Toast.makeText(this, "You select Copy for: "+myListData.get(position).getDescription(), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure want to delete this file ?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        myListData.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();


                return true;
            case R.id.edit:
                Toast.makeText(this, "You select for: "+myListData.get(position).getDescription(), Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onContextItemSelected(item);
    }


}