package fr.imt_atlantique.myfirstappllication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

public class DateActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private Button buttonConfirm, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        datePicker = findViewById(R.id.datePicker);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonCancel = findViewById(R.id.buttonCancel);

        // 读取传入的日期（如果有的话）
        Intent intent = getIntent();
        String currentDate = intent.getStringExtra("CURRENT_DATE");

        // 设置默认日期
        if (currentDate != null && !currentDate.isEmpty()) {
            String[] parts = currentDate.split("/");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]) - 1; // 月份从 0 开始
            int day = Integer.parseInt(parts[2]);
            datePicker.updateDate(year, month, day);
        }

        // 确认按钮
        buttonConfirm.setOnClickListener(v -> {
            int year = datePicker.getYear();
            int month = datePicker.getMonth() + 1;
            int day = datePicker.getDayOfMonth();
            String selectedDate = day + "/" + month + "/" + year;

            // 返回日期数据
            Intent resultIntent = new Intent();
            resultIntent.putExtra("SELECTED_DATE", selectedDate);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // 取消按钮
        buttonCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}
