package com.example.contextmenuformylists;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
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
        registerForContextMenu(listView); //указываем, что под listview необходимо будет создавать контекстное меню
        registerForContextMenu(textView); //указываем, что под textview необходимо будет создавать контекстное меню
        listViewFunctions(); //все наши методы работы с коротким нажатием на элемент списка listview!
    }
    //создаем контекстное меню для нажатого элемента listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { //menuInfo содержит доп.информацию, когда контекстное меню вызвано для элемента списка.
        //находим того, кто вызвал контекстное меню - вызов происходит с view.
        switch (v.getId()){
            case R.id.textView: //если контекстное меню вызвал textview
                getMenuInflater().inflate(R.menu.menu2,menu);
                break;
            case R.id.listView: //если контекстное меню вызвал listview
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo; //приводим menuInfo к AdapterContextMenuInfo, чтобы можно было вызвать переменную position, которая скажет, на какой позиции был элемент списка, вызвавший контекстное меню
                String title = arrayAdapter.getItem(info.position); //находим значение по индексу
                menu.setHeaderTitle(title);
                //menu.setHeaderIcon(android.R.drawable.ic_menu_delete); //почему-то не работает
                getMenuInflater().inflate(R.menu.menu,menu);
                break;
        }
    }
    //обрабатываем нажатие на элемент контекстного меню
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo(); //узнаем, для какой элемент listview нажал на контекстное меню
        //определяем, какой id нажатого элемента контекстного меню - item, сам элемент контекстного меню, который нажат
        switch (item.getItemId()){
            case R.id.context1delete: //если нажали на context1delete
                String nameAboutDeletingItem = arrayAdapter.getItem(info.position); //получаем имя того элемента, которого удалим
                arrayList.remove(info.position); //удаляем элемент из listview
                arrayAdapter.notifyDataSetChanged(); //сохраняем изменение
                Toast.makeText(this,getResources().getText(R.string.context_menu_delete_txt) + " " + nameAboutDeletingItem,Toast.LENGTH_SHORT).show();
                break;
            case R.id.changeColor: //если нажали на changeColor
                textView.setTextColor(Color.BLUE);
                break;
            case R.id.changeSize: //если нажали на changeSize
                textView.setTextSize(25);
                break;
        }
        return super.onContextItemSelected(item);
    }
    //Создаем меню и меняем стандарт меню на свой макет меню
    // первый запуск это и есть изменение состояние меню
    //вызывается при создании только
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("ЗАПУСК МЕНЮ: ", "ВЫ НАЖАЛИ НА onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //при нажатии на элемент menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemExit:
                finish();
//                MainActivity.this.finish(); //можно написать выход таким путем
//                this.finish(); //или таким путем
                break;
        }
        return super.onOptionsItemSelected(item);
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