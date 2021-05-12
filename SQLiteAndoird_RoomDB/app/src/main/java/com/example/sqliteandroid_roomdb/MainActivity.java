    package com.example.sqliteandroid_roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

    public class MainActivity extends AppCompatActivity {

        ListView listView;
        ArrayAdapter<Student> adapter;
        List<Student> studentList = new ArrayList<>();
        Button btnAdd, btnRemove, btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);
        btnUpdate = findViewById(R.id.btnUpdate);
        EditText editText = findViewById(R.id.editText);
        SearchView searchView = findViewById(R.id.searchBar);

//        Student student = new Student(1, "Đỗ Anh Bôn");

        AppDB db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "SinhVien_RoomDB")
                .allowMainThreadQueries()
                .build();

        StudentDao studentDao = db.studentDao();
//        studentDao.insertAll(student);
        studentList = studentDao.getAll();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0, count = studentList.size();
                String name = editText.getText().toString();
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).getName() != name && editText.length() != 0) {
                        Student s = new Student(count + 1, name);
                        studentDao.insertAll(s);
                        adapter.add(s);
                        Toast.makeText(MainActivity.this, "Thêm sinh viên thành công!!!", Toast.LENGTH_SHORT).show();
                        flag++;
                        break;
                    }
                    else
                        flag = 0;
                }
                if(flag == 0)
                    Toast.makeText(MainActivity.this, "Tên sinh viên không hợp lệ hoặc bị trùng!!!", Toast.LENGTH_SHORT).show();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 0;
                String name = editText.getText().toString();
                Student student = null;
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).getName().equals(name)) {
                        student = studentList.get(i);
                        studentDao.delete(student);
                        studentList.remove(i);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Xóa sinh viên thành công!!!", Toast.LENGTH_SHORT).show();
                        flag++;
                        break;
                    } else
                        flag = 0;
                }
                if (flag == 0)
                    Toast.makeText(MainActivity.this, "Tên sinh viên không hợp lệ!!!", Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MainActivity.this.adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                MainActivity.this.adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}