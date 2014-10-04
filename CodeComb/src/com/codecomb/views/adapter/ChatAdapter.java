/**
 * @author UFreedom
 * @since 2014 2014-10-3 上午10:57:13
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.views.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.codecomb.module.base.Base;
import com.codecomb.module.contacts.Contact;
import com.codecomb.module.im.Message;
import com.codecomb.module.im.MessageManager;
import com.codecomb.module.profile.Profile;
import com.codecomb.ufreedom.R;
import com.codecomb.utils.DateUtils;

public class ChatAdapter extends BaseAdapter {

	private static final String TAG = ChatAdapter.class.getSimpleName();

	private static final int TIMELINE_INTERVAL = 300000;
	private static final int TYPE_COUNT = 3;
	private static final int TYPE_TIMELINE = 0;
	private static final int TYPE_SEND_TEXT = 1;
	private static final int TYPE_RECV_TEXT = 2;

	private Context context;
	private LayoutInflater layoutInflater;
	private List<Object> chatDate;
	private ListView listview;
	private Contact contact;

	private Profile profile;
	private Date lastDateTime;
	private Handler hander;

	private ExecutorService sendMessageExecutorService;

	// private CopyOnWriteArrayList<Object> chatData;

	public ChatAdapter(Context context, ListView lv, Profile profile,
			Contact contact) {

		this.context = context;
		listview = lv;
		this.profile = profile;
		this.contact = contact;
		this.chatDate = new ArrayList<Object>();
		layoutInflater = LayoutInflater.from(context);

		// chatData = new CopyOnWriteArrayList<Object>();

		hander = new Handler();
		sendMessageExecutorService = Executors
				.newSingleThreadExecutor(new ThreadFactory() {

					@Override
					public Thread newThread(Runnable r) {

						Thread thread = new Thread(r);
						thread.setName("message send");
						thread.setDaemon(true);

						return thread;
					}
				});
	}

	@Override
	public int getCount() {

		return chatDate.size();
	}

	@Override
	public Object getItem(int position) {
		return chatDate.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {

		Object data = getItem(position);
		if (Message.class.isInstance(data)) {

			Message message = (Message) data;

			return message.getSenderID() == profile.getUserId()
					? TYPE_SEND_TEXT
					: TYPE_RECV_TEXT;

		} else {
			return TYPE_TIMELINE;
		}

	}

	@Override
	public int getViewTypeCount() {
		return TYPE_COUNT;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Object data = getItem(position);

		if (data != null) {

			int type = getItemViewType(position);

			switch (type) {
				case TYPE_SEND_TEXT :

					convertView = getSendTextView(convertView, (Message) data);
					break;

				case TYPE_RECV_TEXT :

					convertView = getRecvTextView(convertView, (Message) data);

					break;
				case TYPE_TIMELINE :

					convertView = getTimeLineView(convertView, (TimeLine) data);

					break;
				default :
					break;
			}

		}

		return convertView;
	}

	private View getRecvTextView(View converView, Message message) {

		ViewHolder holder = null;
		if (converView == null) {

			holder = new ViewHolder();
			converView = layoutInflater.inflate(
					R.layout.lv_item_chatting_msg_text_recv, null);

			holder.vContent = (TextView) converView
					.findViewById(R.id.vMessageContent);
			converView.setTag(holder);

		} else {
			holder = (ViewHolder) converView.getTag();
		}

		holder.vContent.setText(message.getContent());

		return converView;

	}

	private View getSendTextView(View convertView, Message message) {

		ViewHolder holder = null;
		if (convertView == null) {

			holder = new ViewHolder();
			convertView = layoutInflater.inflate(
					R.layout.lv_item_chatting_msg_text_sender, null);

			holder.vContent = (TextView) convertView
					.findViewById(R.id.vMessageContent);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.vContent.setText(message.getContent());

		return convertView;

	}

	private View getTimeLineView(View converView, TimeLine timeLine) {

		TimeLineHolder holder = null;

		if (converView == null) {

			holder = new TimeLineHolder();
			converView = layoutInflater.inflate(
					R.layout.lv_item_chatting_msg_time_line, null);
			holder.vDateTime = (TextView) converView
					.findViewById(R.id.vDatetime);
			converView.setTag(holder);
		} else {
			holder = (TimeLineHolder) converView.getTag();
		}

		holder.vDateTime.setText(timeLine.getDateTime());

		return converView;

	}

	public void sendMessage(final String content) {

		sendMessageExecutorService.execute(new Runnable() {

			@Override
			public void run() {

				MessageManager.getInstance().sendMessage(contact.getUserID(),
						content);

			}
		});

	}

	public void add(Message message) {
		Date nextDate = message.getTime();

		if (lastDateTime == null
				|| nextDate.getTime() - lastDateTime.getTime() > TIMELINE_INTERVAL) {
			chatDate.add(new TimeLine(DateUtils.format(message.getTime(),
					"yyyy-MM-dd HH:mm")));

			lastDateTime = nextDate;

		}

		chatDate.add(message);

	}

	public void addAll(Collection<? extends Message> data) {

		for (Object ob : data) {
			add((Message) ob);
		}

	}

	public void refresh() {
		hander.post(new Runnable() {

			@Override
			public void run() {

				notifyDataSetChanged();

			}
		});

	}

	public void refreshAndSetListViewTobottom() {

		hander.post(new Runnable() {

			@Override
			public void run() {

				notifyDataSetChanged();
				listview.setSelection(getCount() - 1);

			}
		});
	}

	class ViewHolder {
		public TextView vContent;

	}

	class TimeLineHolder {
		TextView vDateTime;
	}

	class TimeLine {

		public TimeLine(String dateTime) {
			this.dateTime = dateTime;
		}
		private String dateTime;

		public String getDateTime() {
			return dateTime;
		}

		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
		}

	}

}
