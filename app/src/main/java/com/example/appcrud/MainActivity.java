package com.example.appcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.appcrud.BDHelper.ProjectDB;
import com.example.appcrud.model.Projects;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ProjectDB bdHelper;
    ArrayList<Projects> listview_Projects;
    Projects project;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCadastrar = (Button) findViewById(R.id.btn_Cadastrar);
        btnCadastrar.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, FormularioProjeto.class);
                startActivity(intent);
            }
        });

        list = (ListView) findViewById(R.id.listview_Projects);
        registerForContextMenu(list);

        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Projects projectChosen = (Projects) adapter.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this,FormularioProjeto.class);
                i.putExtra("project-chosen",projectChosen);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                project = (Projects) adapter.getItemAtPosition(position);
                return false;
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Delete this project");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                bdHelper = new ProjectDB(MainActivity.this);
                bdHelper.deleteProject(project);
                bdHelper.close();

                loadProject();
                return true;
            }
        });
    }

    protected void onResume() {
        super.onResume();
        loadProject();
    }

    public void loadProject() {
        bdHelper = new ProjectDB(MainActivity.this);
        listview_Projects = bdHelper.getList();
        bdHelper.close();

        if (listview_Projects != null) {
            adapter = new ArrayAdapter<Projects>(MainActivity.this, android.R.layout.simple_list_item_1, listview_Projects);
            list.setAdapter(adapter);
        }
//        finish();

    }

}