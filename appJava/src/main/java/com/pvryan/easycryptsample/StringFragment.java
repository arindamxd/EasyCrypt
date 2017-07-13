package com.pvryan.easycryptsample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pvryan.easycrypt.ECrypt;

public class StringFragment extends Fragment {

    private ECrypt eCrypt = new ECrypt();

    public StringFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static StringFragment newInstance() {
        return new StringFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_string, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        EditText edInput = (EditText) view.findViewById(R.id.edInput);
        EditText edPassword = (EditText) view.findViewById(R.id.edPassword);
        TextView tvResult = (TextView) view.findViewById(R.id.tvResult);

        view.findViewById(R.id.buttonHash).setOnClickListener(
                buttonHash -> {
                    ProgressBar progressBar = new ProgressBar(buttonHash.getContext());
                    progressBar.setIndeterminate(true);
                    eCrypt.hash(edInput.getText().toString(), ECrypt.HashAlgorithms.SHA_512,
                            new ECrypt.ECryptResultListener() {

                                @Override
                                public void onProgress(int newBytes, long bytesProcessed) {
                                    // Not required for strings
                                }

                                @Override
                                public void onFailure(@NonNull String message, @NonNull Exception e) {
                                    getActivity().runOnUiThread(() ->
                                            Toast.makeText(buttonHash.getContext(),
                                                    message, Toast.LENGTH_SHORT).show());
                                    e.printStackTrace();
                                }

                                @Override
                                public <T> void onSuccess(T result) {
                                    getActivity().runOnUiThread(() ->
                                            tvResult.setText((String) result));
                                }
                            }
                    );
                }
        );

        view.findViewById(R.id.buttonEncrypt).setOnClickListener(buttonEncrypt -> {
                    ProgressBar progressBar = new ProgressBar(buttonEncrypt.getContext());
                    progressBar.setIndeterminate(true);
                    eCrypt.encrypt(edInput.getText().toString(), edPassword.getText().toString(),
                            new ECrypt.ECryptResultListener() {

                                @Override
                                public void onProgress(int newBytes, long bytesProcessed) {
                                    // Not required for strings
                                }

                                @Override
                                public void onFailure(@NonNull String message, @NonNull Exception e) {
                                    getActivity().runOnUiThread(() ->
                                            Toast.makeText(buttonEncrypt.getContext(),
                                                    message, Toast.LENGTH_SHORT).show());
                                    e.printStackTrace();
                                }

                                @Override
                                public <T> void onSuccess(T result) {
                                    getActivity().runOnUiThread(() ->
                                            tvResult.setText((String) result));
                                }
                            }
                    );
                }
        );

        view.findViewById(R.id.buttonDecrypt).setOnClickListener(buttonDecrypt -> {
                    ProgressBar progressBar = new ProgressBar(buttonDecrypt.getContext());
                    progressBar.setIndeterminate(true);
                    eCrypt.decrypt(edInput.getText().toString(), edPassword.getText().toString(),
                            new ECrypt.ECryptResultListener() {

                                @Override
                                public void onProgress(int newBytes, long bytesProcessed) {
                                    // Not required for strings
                                }

                                @Override
                                public void onFailure(@NonNull String message, @NonNull Exception e) {
                                    getActivity().runOnUiThread(() ->
                                            Toast.makeText(buttonDecrypt.getContext(),
                                                    message, Toast.LENGTH_SHORT).show());
                                    e.printStackTrace();
                                }

                                @Override
                                public <T> void onSuccess(T result) {
                                    getActivity().runOnUiThread(() ->
                                            tvResult.setText((String) result));
                                }
                            }
                    );
                }
        );
    }
}