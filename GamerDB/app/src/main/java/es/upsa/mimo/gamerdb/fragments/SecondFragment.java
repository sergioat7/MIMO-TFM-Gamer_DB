package es.upsa.mimo.gamerdb.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.upsa.mimo.gamerdb.R;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        return view;
    }

    @OnClick(R.id.button_second)
    public void onButtonSecondClick() {
        NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment);
    }
}