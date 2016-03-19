package pe.area51.fragmentexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentLogin extends Fragment {

    private EditText nameEditText;
    private Button showWelcomeButton;

    private OnShowWelcomeButtonClickListener onShowWelcomeButtonClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        nameEditText = (EditText) view.findViewById(R.id.edittext_name);
        showWelcomeButton = (Button) view.findViewById(R.id.button_show_welcome);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showWelcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = nameEditText.getText().toString();
                if (onShowWelcomeButtonClickListener != null) {
                    onShowWelcomeButtonClickListener.onShowWelcome(name);
                }
            }
        });
    }

    public void setOnShowWelcomeButtonClickListener(final OnShowWelcomeButtonClickListener onShowWelcomeButtonClickListener) {
        this.onShowWelcomeButtonClickListener = onShowWelcomeButtonClickListener;
    }

    public static interface OnShowWelcomeButtonClickListener {

        public void onShowWelcome(final String visitorName);

    }

}
