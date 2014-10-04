/**
 * @author UFreedom
 * @since 2014 2014-10-2 下午9:25:04
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.views.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codecomb.infrastructure.asynctask.AvatarDownloader;
import com.codecomb.infrastructure.asynctask.AvatarDownloader.Listener;
import com.codecomb.module.contacts.Contact;
import com.codecomb.ufreedom.R;
import com.codecomb.view.widgets.CircularImageView;

public class ContactsAdapter extends BaseAdapter {

	private static final String TAG = ContactsAdapter.class.getSimpleName();

	private Context context;
	private LayoutInflater layoutInflater;
	private List<Contact> contacts;
	private AvatarDownloader<CircularImageView> avatartThead;

	public ContactsAdapter(Context context, List<Contact> cts) {

		this.context = context;
		this.contacts = cts;

		layoutInflater = LayoutInflater.from(context);

		avatartThead = new AvatarDownloader<CircularImageView>(new Handler());
		initListener();
		
		avatartThead.start();
		avatartThead.getLooper();
		
	}

	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return contacts.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return contacts.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;

		if (convertView == null) {
			viewHolder = new ViewHolder();

			convertView = layoutInflater.inflate(R.layout.lv_item_chat_contact,
					parent, false);

			viewHolder.vAvatar = (CircularImageView) convertView
					.findViewById(R.id.vAvatar);
			viewHolder.vNickname = (TextView) convertView
					.findViewById(R.id.vNickname);
			viewHolder.vMotto = (TextView) convertView
					.findViewById(R.id.vMotto);

			convertView.setTag(viewHolder);
		} else {

			viewHolder = (ViewHolder) convertView.getTag();
		}

		Contact contact = (Contact) getItem(position);

		viewHolder.vNickname.setText(contact.getNickName());

		viewHolder.vMotto.setText(contact.getMotto());
		viewHolder.vAvatar.setImageResource(R.drawable.ic_launcher);

		avatartThead.queryAvatar(viewHolder.vAvatar, contact.getAvatarURL());
		
		return convertView;
	}

	
	private void initListener() {

		avatartThead.setListener(new Listener<CircularImageView>() {

			@Override
			public void onAvatarDownloaded(CircularImageView token,
					Bitmap avatar) {

				Log.e(TAG, "头像下载完毕，开始更新头像");
				token.setImageBitmap(avatar);
		
			}	
		});
		
	}
	

	class ViewHolder {

		private TextView vNickname;
		private TextView vMotto;
		private CircularImageView vAvatar;
	}

}
