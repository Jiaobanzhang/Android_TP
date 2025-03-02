package fr.imt_atlantique.myfirstappllication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        TextView userInfoTextView = findViewById(R.id.userInfoTextView);

        User user = getIntent().getParcelableExtra("user");

        if (user != null) {
            StringBuilder userInfo = new StringBuilder();
            userInfo.append("Nom: ").append(user.getNom()).append("\n");
            userInfo.append("Prénom: ").append(user.getPrenom()).append("\n");
            userInfo.append("Ville de naissance: ").append(user.getVilleNaissance()).append("\n");

            if (user.getDateNaissance() != null && !user.getDateNaissance().isEmpty()) {
                userInfo.append("Date de naissance: ").append(user.getDateNaissance()).append("\n");
            }
            if (user.getDepartementNaissance() != null && !user.getDepartementNaissance().isEmpty()) {
                userInfo.append("Département de naissance: ").append(user.getDepartementNaissance()).append("\n");
            }
            if (user.getPhoneNumbers() != null && !user.getPhoneNumbers().isEmpty()) {
                userInfo.append("Numéros de téléphone:\n");
                for (String phone : user.getPhoneNumbers()) {
                    userInfo.append("- ").append(phone).append("\n");
                }
            }

            userInfoTextView.setText(userInfo.toString());
        }
    }
}

