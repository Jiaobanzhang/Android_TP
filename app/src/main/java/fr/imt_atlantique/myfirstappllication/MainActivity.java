package fr.imt_atlantique.myfirstappllication;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

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

//                Snackbar.make(findViewById(R.id.myConstraintLayout), message, 3).show();

                // 5️⃣ 显示 Snackbar 并添加 DISMISS 按钮
                // 这段注释代码只能显示一行代码
//                Snackbar.make(findViewById(R.id.myConstraintLayout), message, Snackbar.LENGTH_INDEFINITE)
//                        .setAction("DISMISS", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                // 这里可以添加关闭 Snackbar 时的逻辑
//                            }
//                        })
//                        .show();
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
    }

    // 活动中显示菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}