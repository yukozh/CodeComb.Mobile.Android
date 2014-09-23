
package com.codecomb.views.adapter;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codecomb.module.contests.Contest;
import com.codecomb.ufreedom.R;

public class ContestsAdapter extends BaseAdapter {

	private static final String TAG = "ContestsAdapter";
	private Context context;
	private List<Contest> contests;
	private LayoutInflater layoutInflater;

	public ContestsAdapter(Context context, List<Contest> contests) {

		this.context = context;
		this.contests = contests;

		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {

		return contests.size();
	}

	@Override
	public Object getItem(int position) {

		return contests.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		
		if( convertView == null){
			
			convertView = layoutInflater.inflate(R.layout.lv_item_contests, null);
		
			viewHolder = new ViewHolder();
			
			viewHolder.vContestStatus = (TextView) convertView.findViewById(R.id.vContestStatus);
			viewHolder.vContestTitle = (TextView) convertView.findViewById(R.id.vContestTitle);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		
		
		viewHolder.vContestTitle.setText(contests.get(position).getTitle());
		
		
		
		Date date = contests.get(position).getBegin();
		
		int compare = date.compareTo(new Date());
		
		
		if(compare < 0){
			
			viewHolder.vContestStatus.setText("Ended");	
			int color = context.getResources().getColor(R.color.col_contest_status_ended);
			viewHolder.vContestStatus.setTextColor(color);

			
			
		}else if(compare > 0) {

			viewHolder.vContestStatus.setText("Pending");
			int color = context.getResources().getColor(R.color.col_contest_status_pending);
			viewHolder.vContestStatus.setTextColor(color);
			
			
		}else{
			viewHolder.vContestStatus.setText("Live");	
			int color = context.getResources().getColor(R.color.col_contest_status_live);
			viewHolder.vContestStatus.setTextColor(color);
			
		}
	
		
		
		return convertView;
	}

	

	
	class ViewHolder {

		TextView vContestTitle;
		TextView vContestStatus;

	}

}
