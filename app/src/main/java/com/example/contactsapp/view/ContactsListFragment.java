package com.example.contactsapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.contactsapp.R;
import com.example.contactsapp.adapter.ContactsListAdapter;
import com.example.contactsapp.databinding.ContactsListFragmentBinding;
import com.example.contactsapp.model.ContactDataBean;
import com.example.contactsapp.presenter.ContactsListPresenter;

import java.util.List;

public class ContactsListFragment extends BaseFragment implements ContactsListPresenter.View {

    private ContactsListFragmentBinding binding;
    private ContactsListPresenter contactsListPresenter;
    private ContactsListAdapter contactsListAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = ContactsListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.contactsListFabAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ContactsListFragment.this)
                        .navigate(R.id.action_ContactsListFragment_to_AddContactFragment);
            }
        });
        contactsListPresenter = new ContactsListPresenter(this,getActivity());
        contactsListPresenter.mGetContactsListData();
        binding.listFragmentProgressBar.setIndeterminate(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void updateContactsListData(List<ContactDataBean> list) {
        contactsListAdapter = new ContactsListAdapter(getActivity(), list);
        binding.contactsListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.contactsListRecyclerView.setAdapter(contactsListAdapter);
    }

    @Override
    public void showProgressBar() {
        binding.listFragmentProgressBar.setIndeterminate(true);
    }

    @Override
    public void hideProgressBar() {
        binding.listFragmentProgressBar.setIndeterminate(false);
    }
}