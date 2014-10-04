package com.codecomb.views;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.codecomb.infrastructure.asynctask.DataLoader;
import com.codecomb.module.contacts.Contact;
import com.codecomb.module.contacts.ContactManager;
import com.codecomb.ufreedom.R;
import com.codecomb.views.adapter.ContactsAdapter;

public class MessageFragment extends Fragment implements LoaderCallbacks<List<Contact>>{

	private static final String TAG = MessageFragment.class.getSimpleName();

	private static final int LOAD_CONTACTS = 0;
	
	
	
	public static MessageFragment newInstance() {
		return new MessageFragment();
	}

	private View rootView;
	private ListView lvContact;


	private ContactsAdapter contactsAdapter;
	private List<Contact> contacts;

	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fgm_message, container, false);
		lvContact = (ListView) rootView.findViewById(R.id.lvContact);

		contacts = new ArrayList<Contact>();
		contactsAdapter = new ContactsAdapter(getActivity(), contacts);
		lvContact.setAdapter(contactsAdapter);

		initListener();
		
		LoaderManager loaderManager = getLoaderManager();
		loaderManager.initLoader(LOAD_CONTACTS,null, this);
		
		return rootView;
	}
	
	
	

	
	
	
	private void initListener() {

		lvContact.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {


				Contact contact = (Contact) lvContact.getItemAtPosition(position);
				
				Intent intent = new Intent(getActivity(),ChatActivity.class);
				intent.putExtra(ChatFragment.EXTRA_CHAT_CONTACT, contact);
				startActivity(intent);
			
			}
			
		});
		
		
	}

	public static class ContactsLoader extends DataLoader<List<Contact>> {

		public ContactsLoader(Context context) {
			super(context);

		}

		@Override
		public List<Contact> loadInBackground() {

			return ContactManager.getInstance().getContacts();

		}

	}
	
	

	@Override
	public Loader<List<Contact>> onCreateLoader(int arg0, Bundle arg1) {

		return new ContactsLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<List<Contact>> arg0, List<Contact> cts) {
		
		
		contacts.addAll(cts);
		updateUI();
		

		
	}

	private void updateUI() {

		contactsAdapter.notifyDataSetChanged();
		
	}
	
	

	@Override
	public void onLoaderReset(Loader<List<Contact>> arg0) {


		
	}

}
