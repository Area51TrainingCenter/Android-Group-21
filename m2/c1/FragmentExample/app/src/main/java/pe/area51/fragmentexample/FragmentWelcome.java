package pe.area51.fragmentexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentWelcome extends Fragment {

    private TextView welcomeMessageTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        welcomeMessageTextView = (TextView) view.findViewById(R.id.textview_welcome_message);
        return view;
    }

    public void showWelcomeMessage(final String visitorName) {
        welcomeMessageTextView.setText(getContext().getString(R.string.welcome_message, visitorName));
    }

}
