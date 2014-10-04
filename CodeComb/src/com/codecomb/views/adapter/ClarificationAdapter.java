/**
 * @author UFreedom
 * @since 2014 2014-10-1 下午9:11:40
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.views.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codecomb.module.clarifications.Clarification;
import com.codecomb.ufreedom.R;

public class ClarificationAdapter extends BaseAdapter {

	private List<Clarification> clarifications;
	private Context context;
	private LayoutInflater layoutInflater;

	public ClarificationAdapter(Context context, List<Clarification> cs) {
		this.context = context;
		this.clarifications = cs;
		
		layoutInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		return clarifications.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return clarifications.get(position);
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
			convertView = layoutInflater.inflate(R.layout.lv_item_clarifications, null);
			viewHolder.vClarificationTitle = (TextView) convertView.findViewById(R.id.vClarificationTitle);
			viewHolder.vClarificationStatus = (TextView) convertView.findViewById(R.id.vClarificationStatus);
			
			convertView.setTag(viewHolder);
		}else{
			
			viewHolder = (ViewHolder) convertView.getTag();
		
		}
		
		viewHolder.vClarificationStatus.setText(clarifications.get(position).getStatus());
		viewHolder.vClarificationTitle.setText(clarifications.get(position).getQuestion());

		
		

		
		return convertView;
	}
	
	
	
	
	class ViewHolder{
		
		private TextView vClarificationTitle;
		
		private TextView vClarificationStatus;

		
	}

}
