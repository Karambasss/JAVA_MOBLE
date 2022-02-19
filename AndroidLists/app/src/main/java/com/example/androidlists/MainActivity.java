package com.example.androidlists;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList); //чтобы добавлялись новые listView
        listView.setAdapter(arrayAdapter);
        listViewFunctions(); //все наши методы работы с нажатием на элемент списка!
    }

    //Обработка нажатия на кнопку для установления нового имени в ListView и метод нажатия на каждый элемент ListView
    public void click_btn(View view) {
        String s = String.valueOf(editText.getText());
        if (s.equals("")){
            Toast.makeText(this, R.string.error1, Toast.LENGTH_SHORT).show();
            return;
        }
        arrayList.add(s); //добавляем эл-т в arraylist (который выступает как контейнер)
        removeDuplicates(); //удаление повторов
        sortArray(); //сортировка
        arrayAdapter.notifyDataSetChanged(); //изменяем и сохраняем новые data для arrayAdapter
        textView.setText(s + " " + getResources().getText(R.string.label2_txt));
        editText.getText().clear();
    }

    // При нажатии на кнопку будет определяться с какой кнопки произвелось нажатие путем определения View.getId()
    public void doNewText(View view) {
        switch (view.getId()){
            case R.id.btn_ok:
                textView.setText(getResources().getText(R.string.clicked_txt) + " " + getResources().getText(R.string.ok_txt));
                Toast.makeText(this,getResources().getText(R.string.clicked_txt) + " " + getResources().getText(R.string.ok_txt), Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_cancel:
                textView.setText(getResources().getText(R.string.clicked_txt) + " " + getResources().getText(R.string.cancel_txt));
                Toast.makeText(this, getResources().getText(R.string.clicked_txt) + " " + getResources().getText(R.string.cancel_txt), Toast.LENGTH_LONG).show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
    public void sortArray(){
        Collections.sort(arrayList);
    }
    public void removeDuplicates(){
        Set<String> set = new HashSet<>(arrayList);
        arrayList.clear();
        arrayList.addAll(set);
    }
    public void listViewFunctions(){
        //вешаем обработчик нажатия на эл-т list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String name = arrayList.get(position);
                //Или можно использовать через сам listView!
                String name1 = (String) listView.getItemAtPosition(position);
                int positionBeautiful = position + 1;
                Toast.makeText(MainActivity.this, getResources().getText(
                        R.string.pos_msg) + " " + positionBeautiful + "\n" +
                        getResources().getText(R.string.msg1) + " " + name, Toast.LENGTH_SHORT).show();
                //Удаление элемента!
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this); //диалоговое окно
                alertDialog.setTitle(getResources().getText(R.string.dialog_1_txt));
                alertDialog.setMessage(getResources().getText(R.string.dialog_2_txt));
                alertDialog.setNegativeButton(R.string.dialog_3_txt,null); //добавили кнопку выхода (null позволяет закрыть диалоговое окно)
                alertDialog.setPositiveButton(R.string.dialog_4_txt, new DialogInterface.OnClickListener() { //кнопка продолжить!
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        arrayList.remove(position);
                        arrayAdapter.notifyDataSetChanged(); //перезаписываем данные!!!
                    }
                });
                alertDialog.show();
            }
        });
    }
}