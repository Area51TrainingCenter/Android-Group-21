package pe.area51.fragmentexample;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentLogin.OnShowWelcomeButtonClickListener {

    private FragmentWelcome fragmentWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentLogin fragmentLogin = (FragmentLogin) fragmentManager.findFragmentById(R.id.fragment_login);
        fragmentLogin.setOnShowWelcomeButtonClickListener(this);
        fragmentWelcome = (FragmentWelcome) fragmentManager.findFragmentById(R.id.fragment_welcome);
    }

    @Override
    public void onShowWelcome(String visitorName) {
        fragmentWelcome.showWelcomeMessage(visitorName);
    }
}
