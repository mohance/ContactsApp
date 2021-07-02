package com.example.contactsapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import com.example.contactsapp.R;
import com.example.contactsapp.databinding.AddContactFragmentBinding;
import com.example.contactsapp.presenter.AddContactPresenter;


public class AddContactFragment extends BaseFragment implements AddContactPresenter.View{
    private final String TAG = AddContactFragment.class.getName();
    private AddContactFragmentBinding binding;
    private final String strMobilePattern = "[0-9]{10}";
    private AddContactPresenter addContactPresenter;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = AddContactFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addContactPresenter = new AddContactPresenter(this,getActivity());
        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername, strPhoneNumber;
                strUsername = binding.etUsername.getText().toString();
                strPhoneNumber = binding.etPhoneNumber.getText().toString();
                if(strUsername == null && strUsername.isEmpty()){
                    Toast.makeText(getActivity(),"Please enter username.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strPhoneNumber == null && strPhoneNumber.isEmpty()){
                    Toast.makeText(getActivity(),"Please enter phone number.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!strPhoneNumber.matches(strMobilePattern)) {
                    Toast.makeText(getActivity(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                addContactPresenter.insertNewContact(strUsername,strPhoneNumber);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onSuccess(String strUsername) {
        Toast.makeText(getActivity(),"Contact added successfully.", Toast.LENGTH_SHORT).show();
        binding.etUsername.setText("");
        binding.etPhoneNumber.setText("");
        NavHostFragment.findNavController(AddContactFragment.this)
                .navigate(R.id.action_AddContactFragment_to_ContactsListFragment);
    }

    @Override
    public void onFailure(String strErrorMsg) {
        Toast.makeText(getActivity(),"Error on adding new contact. Please try again later.", Toast.LENGTH_SHORT).show();
    }
}