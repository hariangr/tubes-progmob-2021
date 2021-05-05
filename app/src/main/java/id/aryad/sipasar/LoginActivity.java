package id.aryad.sipasar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.aryad.sipasar.constants.IntentKey;
import id.aryad.sipasar.models.Admin;
import id.aryad.sipasar.models.AdminRole;
import id.aryad.sipasar.repositories.AuthRepository;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEt;
    private EditText passwordET;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEt = (EditText) findViewById(R.id.usernameET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _username = usernameEt.getText().toString();
                String _password = passwordET.getText().toString();

                AuthRepository _auth = AuthRepository.getInstance();
                Admin tryLogin = _auth.login(_username, _password);

                if (tryLogin == null) {
                    // Gagal login
                    Toast.makeText(getApplicationContext(), "Username/password salah", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (tryLogin.getRole() != AdminRole.MANAGER) {
                    // Salah role
                    Toast.makeText(getApplicationContext(), "Anda bukan manager", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Berhasil login
                Intent _intent = new Intent(getApplicationContext(), BayarGajiActivity.class);
                _intent.putExtra(IntentKey.CURRENT_ADMIN_ID, tryLogin.getId_admin());
                startActivity(_intent);
                finish();
            }
        });
    }
}