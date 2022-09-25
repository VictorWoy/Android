package com.example.appcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appcrud.BDHelper.ProjectDB;
import com.example.appcrud.model.Projects;

public class FormularioProjeto extends AppCompatActivity {
    EditText editTitle, editDescription, editStartDate, editEndDate, editAmount;
    Button btnModify;
    Projects editProject, project;
    ProjectDB bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_projeto);

        project =  new Projects();
        bdHelper = new ProjectDB(FormularioProjeto.this);

        Intent intent = getIntent();
        editProject = (Projects) intent.getSerializableExtra("project-choosen");

        editTitle = (EditText) findViewById(R.id.editTitle);
        editDescription = (EditText) findViewById(R.id.editDescription);
        editStartDate = (EditText) findViewById(R.id.editStartDate);
        editEndDate = (EditText) findViewById(R.id.editEndDate);
        editAmount = (EditText) findViewById(R.id.editAmount);

        btnModify = (Button) findViewById(R.id.btnModify);

        if(editProject != null) {
            btnModify.setText("Modify");

            editTitle.setText(editProject.getTitleProject());
            editDescription.setText(editProject.getDescriptionProject());
            editStartDate.setText(editProject.getStartDateProject());
            editEndDate.setText(editProject.getEndDateProject());
            editAmount.setText(editProject.getAmountPeopleProject());

            project.setId(editProject.getId());
        } else {
            btnModify.setText("Register");
        }

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                project.setTitleProject(editTitle.getText().toString());
                project.setDescriptionProject(editDescription.getText().toString());
                project.setStartDateProject(editStartDate.getText().toString());
                project.setEndDateProject(editEndDate.getText().toString());
                project.setAmountPeopleProject(Integer.parseInt(editAmount.getText().toString()));

                if (btnModify.getText().toString().equals("Register")) {
                    bdHelper.saveProject(project);
                    bdHelper.close();
                } else {
                   bdHelper.modifyProject(project);
                   bdHelper.close();
                }
            }
        });

    }
}