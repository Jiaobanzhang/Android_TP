package fr.imt_atlantique.myfirstappllication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private LinearLayout phoneContainer; // (LinearLayout utilisé pour contenir le champ de saisie du numéro de téléphone) 用于存放电话号码输入框的 LinearLayout
    private int phoneCount = 0; // (Enregistrer le nombre de numéros de téléphone ajoutés) 记录已添加的电话号码数量
    private final int MAX_PHONE_COUNT = 5; // (Limiter l'ajout à un maximum de 5 numéros de téléphone) 限制最多添加 5 个电话号码
    private EditText editDateNaissance;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Lifecycle", "onCreate method");

        // 1️⃣ 获取 UI 组件 (Récupérer les composants de l'interface utilisateur (UI))
        Button buttonValidate = findViewById(R.id.button_validate);
        EditText editNom = findViewById(R.id.edit_nom);
        EditText editPrenom = findViewById(R.id.edit_prenom);
        editDateNaissance = findViewById(R.id.edit_date_naissance);
        EditText editVilleNaissance = findViewById(R.id.edit_ville_naissance);
        spinner = findViewById(R.id.spinner_departments);
        // 获取布局中的 phoneContainer 和 "Ajouter un numéro" 按钮
        phoneContainer = findViewById(R.id.phoneContainer);
        Button buttonAddPhone = findViewById(R.id.button_add_phone);

        // 2️⃣ 设置按钮的点击事件 (Définir l'événement de clic du bouton)
        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3️⃣ 读取用户输入 (Lire la saisie de l'utilisateur)
                String nom = editNom.getText().toString();
                String prenom = editPrenom.getText().toString();
                String dateNaissance = editDateNaissance.getText().toString();
                String villeNaissance = editVilleNaissance.getText().toString();
                // 直接从 Spinner 获取选中项 (Obtenir directement l'élément sélectionné depuis le Spinner)
                String selectedItem = spinner.getSelectedItem().toString();

                // 4️⃣ 组合显示的信息 (Combiner et afficher les informations)
                String message = "Nom: " + nom + "\nPrénom: " + prenom +
                        "\nDate de naissance: " + dateNaissance +
                        "\nVille: " + villeNaissance +
                        "\nDepartement: " + selectedItem;

                // 5️⃣ 显示 Snackbar 并添加 DISMISS 按钮 (Afficher un Snackbar et ajouter un bouton DISMISS)
                // 这段代码可以显示很多行代码 (Ce code peut afficher plusieurs lignes de code)
                Snackbar snackbar = Snackbar.make(findViewById(R.id.myConstraintLayout), message, Snackbar.LENGTH_LONG)
                        .setAction("DISMISS", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // 关闭 Snackbar
                            }
                        });
                // 让 Snackbar 的文本区域变大，允许多行显示
                View snackbarView = snackbar.getView();
                // TextView 是 View 的子类，专门用于显示文本
                TextView snackbarText = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
                snackbarText.setMaxLines(10);  // 设置最大显示行数
                snackbar.show();
            }
        });

        // 6️⃣设置 Ajouter un phone 按钮点击监听器
        // Mise en place de l'écouteur Ajouter un phone button click
        buttonAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhoneField();
            }
        });

        // 7️⃣设置 DatePicker 按钮的点击事件:
        // Set l'événement de clic pour le bouton DatePicker.
        editDateNaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    // 6️⃣ 活动中显示菜单
    // Affichage du menu dans l’activité
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // 7️⃣ 处理菜单点击事件
    // Gestion des événements liés au clic sur le menu
    public void resetAction(MenuItem item) {
        EditText editNom = findViewById(R.id.edit_nom);
        EditText editPrenom = findViewById(R.id.edit_prenom);
        EditText editDateNaissance = findViewById(R.id.edit_date_naissance);
        EditText editVilleNaissance = findViewById(R.id.edit_ville_naissance);
        LinearLayout phoneContainer = findViewById(R.id.phoneContainer);

        editNom.setText("");
        editPrenom.setText("");
        editDateNaissance.setText("");
        editVilleNaissance.setText("");

        // 清空 phoneContainer 里的所有电话号码输入框
        // Vider tous les champs de saisie des numéros de téléphone dans phoneContainer.
        phoneContainer.removeAllViews();
        // 重置电话号码计数
        // Réinitialiser le nombre de numéros de téléphone
        phoneCount = 0;

        Snackbar.make(findViewById(R.id.myConstraintLayout), "Champs réinitialisés", Snackbar.LENGTH_LONG).show();
    }

    // 8️⃣ 处理 Ajouter un phone 的点击事件
    private void addPhoneField() {
        if (phoneCount >= MAX_PHONE_COUNT) {
            Toast.makeText(this, "Vous ne pouvez ajouter que 5 numéros.", Toast.LENGTH_SHORT).show();
            return;
        }

        phoneCount++;

        // 创建一个新的水平 LinearLayout（用来包裹 EditText 和 删除按钮）
        // Créer un nouveau LinearLayout horizontal (pour envelopper les boutons EditText et Delete)
        LinearLayout phoneRow = new LinearLayout(this);
        phoneRow.setOrientation(LinearLayout.HORIZONTAL);
        phoneRow.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // 创建电话号码输入框
        // Création d'une boîte de saisie de numéro de téléphone
        EditText phoneInput = new EditText(this);
        phoneInput.setHint("Entrez un numéro");
        phoneInput.setLayoutParams(new LinearLayout.LayoutParams(
                0, // 宽度为 0，使用 weight 分配空间
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1 // 权重为 1，让 EditText 占据大部分空间
        ));

        // 创建删除按钮
        // Créer un bouton de suppression
        Button deleteButton = new Button(this);
        deleteButton.setText("Supprimer");
        deleteButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // 设置删除按钮点击事件
        // Définition de l'événement de clic sur le bouton Supprimer
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneContainer.removeView(phoneRow);
                phoneCount--;

                // 显示 `Snackbar`，告知用户当前的号码数量
                Snackbar.make(phoneContainer, "Numéros ajoutés: " + phoneCount, Snackbar.LENGTH_SHORT).show();
            }
        });

        // 将 EditText 和删除按钮添加到 LinearLayout（水平布局）
        // Ajout des boutons EditText et Delete à LinearLayout
        phoneRow.addView(phoneInput);
        phoneRow.addView(deleteButton);

        // 将整行（包含 EditText 和按钮）添加到 phoneContainer
        // Ajoute la ligne entière (y compris l'EditText et le bouton) au phoneContainer.
        phoneContainer.addView(phoneRow);

        // 每次添加后显示 `Snackbar`
        // Afficher la Snackbar après chaque ajout.
        Snackbar.make(phoneContainer, "Numéros ajoutés: " + phoneCount, Snackbar.LENGTH_SHORT).show();
    }

    // 9️⃣ 处理 DatePicker 按钮的点击事件
    // Gestion des événements de clic sur le bouton DatePicker
    private void showDatePickerDialog() {
        // 获取当前日期
        //  Obtenir la date du jour
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // 创建 DatePickerDialog
        // Créer un dialogue DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    // Traitement des dates sélectionnées par l'utilisateur
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // 格式化日期并显示
                        // Formater la date et l'afficher
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        editDateNaissance.setText(selectedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

    // TP4 : menu 的两个按钮 + 隐形 intent + 共享城市 share:
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.resetAction) {
            resetAction(item); // 直接调用已有的 resetAction 方法
            return true;
        } else if (id == R.id.search_wikipedia) {
            searchWikipedia(item);
            return true;
        } else if (id == R.id.share) {
            shareCity(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // TP4 : 隐形 intent 实现维基百科搜索
    public void searchWikipedia(MenuItem item) {
        spinner = findViewById(R.id.spinner_departments);
        String city = spinner.getSelectedItem().toString();

        // 确保输入框不为空
        if (city.isEmpty()) {
            Snackbar.make(phoneContainer, "Please enter a city name", Snackbar.LENGTH_SHORT).show();
            return;
        }

        // 构造 Wikipedia 搜索 URL
        String url = "http://fr.wikipedia.org/?search=" + Uri.encode(city);

        // 创建 Intent
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        // 检查是否有可以处理该 Intent 的应用
        // 确保有应用可以处理该请求
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent); // 启动浏览器
        } else {
            Snackbar.make(phoneContainer, "No application can handle this request", Snackbar.LENGTH_LONG).show();
        }
    }

    // TP4 : 隐形 intent 实现共享城市:
    public void shareCity(MenuItem item) {
        spinner = findViewById(R.id.spinner_departments);
        String city = spinner.getSelectedItem().toString();

        // 确保用户输入了城市名称
        if (city.isEmpty()) {
            Snackbar.make(phoneContainer, "Please enter a city name", Snackbar.LENGTH_SHORT).show();
            return;
        }

        // 创建分享 Intent
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain"); // 设定 MIME 类型
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this city: " + city);
        // 确保邮件应用有标题
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Interesting City Info");

        // 强制用户选择应用（即使有默认应用）
        Intent chooser = Intent.createChooser(shareIntent, "Share via");
        // 检查是否有可用的应用
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        } else {
            Snackbar.make(phoneContainer, "No app available to share", Snackbar.LENGTH_LONG).show();
        }
    }
}