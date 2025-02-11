package fr.imt_atlantique.myfirstappllication;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private LinearLayout phoneContainer; // 用于存放电话号码输入框的 LinearLayout
    private int phoneCount = 0; // 记录已添加的电话号码数量
    private final int MAX_PHONE_COUNT = 5; // 限制最多添加 5 个电话号码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Lifecycle", "onCreate method");

        // 1️⃣ 获取 UI 组件
        Button buttonValidate = findViewById(R.id.button_validate);
        EditText editNom = findViewById(R.id.edit_nom);
        EditText editPrenom = findViewById(R.id.edit_prenom);
        EditText editDateNaissance = findViewById(R.id.edit_date_naissance);
        EditText editVilleNaissance = findViewById(R.id.edit_ville_naissance);
        // 获取布局中的 phoneContainer 和 "Ajouter un numéro" 按钮
        phoneContainer = findViewById(R.id.phoneContainer);
        Button buttonAddPhone = findViewById(R.id.button_add_phone);

        // 2️⃣ 设置按钮的点击事件
        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3️⃣ 读取用户输入
                String nom = editNom.getText().toString();
                String prenom = editPrenom.getText().toString();
                String dateNaissance = editDateNaissance.getText().toString();
                String villeNaissance = editVilleNaissance.getText().toString();

                // 4️⃣ 组合显示的信息
                String message = "Nom: " + nom + "\nPrénom: " + prenom +
                        "\nDate de naissance: " + dateNaissance +
                        "\nVille: " + villeNaissance;

                // 5️⃣ 显示 Snackbar 并添加 DISMISS 按钮
                // 这段代码可以显示很多行代码
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
                snackbarText.setMaxLines(5);  // 设置最大显示行数
                snackbar.show();
            }
        });

        // 设置按钮点击监听器
        buttonAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhoneField();
            }
        });
    }

    // 6️⃣ 活动中显示菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // 7️⃣ 处理菜单点击事件
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
        phoneContainer.removeAllViews();
        // 重置电话号码计数
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
        LinearLayout phoneRow = new LinearLayout(this);
        phoneRow.setOrientation(LinearLayout.HORIZONTAL);
        phoneRow.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // 创建电话号码输入框
        EditText phoneInput = new EditText(this);
        phoneInput.setHint("Entrez un numéro");
        phoneInput.setLayoutParams(new LinearLayout.LayoutParams(
                0, // 宽度为 0，使用 weight 分配空间
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1 // 权重为 1，让 EditText 占据大部分空间
        ));

        // 创建删除按钮
        Button deleteButton = new Button(this);
        deleteButton.setText("Supprimer");
        deleteButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // 设置删除按钮点击事件
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
        phoneRow.addView(phoneInput);
        phoneRow.addView(deleteButton);

        // 将整行（包含 EditText 和按钮）添加到 phoneContainer
        phoneContainer.addView(phoneRow);

        // 每次添加后显示 `Snackbar`
        Snackbar.make(phoneContainer, "Numéros ajoutés: " + phoneCount, Snackbar.LENGTH_SHORT).show();
    }
}