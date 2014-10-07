/**
 * @author UFreedom
 * @since 2014 2014-10-3 上午10:05:44
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.views;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codecomb.events.ShowNewMessageEvent;
import com.codecomb.infrastructure.asynctask.DataLoader;
import com.codecomb.module.contacts.Contact;
import com.codecomb.module.im.Message;
import com.codecomb.module.im.MessageManager;
import com.codecomb.module.profile.ProfileManager;
import com.codecomb.ufreedom.R;
import com.codecomb.views.adapter.ChatAdapter;

import de.greenrobot.event.EventBus;

public class ChatFragment extends Fragment
		implements
			LoaderCallbacks<List<Message>> {

	private static final String TAG = ChatFragment.class.getSimpleName();
	
	public static final String EXTRA_CHAT_CONTACT = "chat_contact";

	private static final int LOAD_CHAT_RECORDS = 0;

	private View rootView;
	private static Contact contact;
	// private List<Message> messages;
	private ChatAdapter chatAdapter;
	private ListView lvMessageContent;
	private View vBack;
	private TextView vTitle;
	private View vMessageSend;
	private EditText vMessageContent;
	private LoaderManager loaderManager;

	private ExecutorService refreshMessageExecutorService ;

	public static ChatFragment newInstance(Contact contact) {

		Bundle bundle = new Bundle();

		bundle.putSerializable(EXTRA_CHAT_CONTACT, contact);

		ChatFragment chatFragment = new ChatFragment();
		chatFragment.setArguments(bundle);

		return chatFragment;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		EventBus.getDefault().post(this);
		
		refreshMessageExecutorService =  Executors
				.newSingleThreadExecutor(new ThreadFactory() {

					@Override
					public Thread newThread(Runnable r) {
						// TODO Auto-generated method stub
						Thread thread = new Thread(r);
						thread.setName("send message");
						thread.setDaemon(true);
						return thread;
					}
				});
		

		contact = (Contact) getArguments().getSerializable(EXTRA_CHAT_CONTACT);

		
		loaderManager = getLoaderManager();
		initLoader();

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fgm_chat, null);

		initWidget();
		initListnener();

		initListView();

		
		return rootView;
	}

	private void initLoader() {

		loaderManager.initLoader(LOAD_CHAT_RECORDS, null, this);

	}

	private void restartLoader() {

		loaderManager.restartLoader(LOAD_CHAT_RECORDS, null, this);

	}

	private void initWidget() {

		vBack = rootView.findViewById(R.id.vBack);
		vTitle = (TextView) rootView.findViewById(R.id.vTitle);
		vTitle.setText(contact.getNickName());
		vMessageSend = rootView.findViewById(R.id.vMessageSend);
		vMessageContent = (EditText) rootView
				.findViewById(R.id.vMessageContent);

		
	}

	private void initListnener() {
		vBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				getActivity().finish();

			}
		});

		vMessageSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				final String content = vMessageContent.getText().toString()
						.trim();

				if (content.isEmpty()) {
					Toast.makeText(getActivity(), R.string.msg_msg_not_empty,
							Toast.LENGTH_SHORT).show();
				} else {
					chatAdapter.sendMessage(content);
					vMessageContent.setText("");
					
					refreshMessageExecutorService.submit(new Runnable() {

						@Override
						public void run() {
							
							Log.e(TAG, "重新加载消息");

							
							restartLoader();
						}
					});

				}

			}
		});

	}

	private void initListView() {

		lvMessageContent = (ListView) rootView.findViewById(R.id.lvMessageContent);
		
		lvMessageContent.setEmptyView(rootView.findViewById(R.id.vEmptyView));
		
		
		
		chatAdapter = new ChatAdapter(getActivity(), lvMessageContent, ProfileManager
				.getInstance().getProfile(), contact);
		
		lvMessageContent.setAdapter(chatAdapter);

	}

	public static class MessageDataLoader extends DataLoader<List<Message>> {

		public MessageDataLoader(Context context) {
			super(context);

		}

		@Override
		public List<Message> loadInBackground() {
			return MessageManager.getInstance().getChatRecords(
					contact.getUserID());
		}

	}

	@Override
	public Loader<List<Message>> onCreateLoader(int arg0, Bundle arg1) {

		return new MessageDataLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<List<Message>> arg0, List<Message> msgs) {

		chatAdapter.addAll(msgs);

		updateUI();
	}

	@Override
	public void onLoaderReset(Loader<List<Message>> msgs) {

		// chatAdapter.addAll(msgs.);

	//	updateUI();

	}

	public void updateUI() {

		chatAdapter.refreshAndSetListViewTobottom();

	}

	public void onEvent(ShowNewMessageEvent event) {

		Log.e(TAG, "显示消息");
		
		refreshMessageExecutorService.submit(new Runnable() {

			@Override
			public void run() {
				restartLoader();
			}
		});
	}

}
