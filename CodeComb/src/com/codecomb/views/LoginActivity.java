package com.codecomb.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.codecomb.SettingsManager;
import com.codecomb.exceptions.AppException;
import com.codecomb.module.im.SignalrConnection;
import com.codecomb.module.login.Auth;
import com.codecomb.module.login.AuthManager;
import com.codecomb.module.login.LoginParams;
import com.codecomb.ufreedom.R;
import com.dd.processbutton.iml.ActionProcessButton;
import com.dd.processbutton.util.ProgressGenerator;
import com.dd.processbutton.util.ProgressGenerator.OnCompleteListener;

public class LoginActivity extends Activity implements OnCompleteListener  {

	protected static final String TAG = "com.ufreedom.codecomb.uis.LoginActivity";
	private ActionProcessButton vLogin;
	private EditText vUsername;
	private EditText vPassword;
	private ToggleButton vRememberPassword;
	private ToggleButton vAutoLogin;

	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		context = this;

		initWidget();
		initListener();
	}

	private void initWidget() {

		vLogin = (ActionProcessButton) findViewById(R.id.vLogin);
		vLogin.setMode(ActionProcessButton.Mode.ENDLESS);

		vUsername = (EditText) findViewById(R.id.vUsername);
		vPassword = (EditText) findViewById(R.id.vPassword);
		vRememberPassword = (ToggleButton) findViewById(R.id.vRememberPassword);
		vAutoLogin = (ToggleButton) findViewById(R.id.vAutoLogin);
		
		boolean isRememberPassword = SettingsManager.getInstance().isStoreUser();
	
		if(isRememberPassword){
			
			vUsername.setText(SettingsManager.getInstance().getUsername());
			vPassword.setText(SettingsManager.getInstance().getPassword());
			vRememberPassword.setChecked(true);
			
		}
		

	}

	private void initListener() {
		
		vRememberPassword.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


				if(isChecked){
					
				}
				
				
				
			}
		});
		
		

		final ProgressGenerator progressGenerator = new ProgressGenerator(this);
				
		vLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				vUsername.setEnabled(false);
				vPassword.setEnabled(false);
				final String username = vUsername.getText().toString();
				final String password = vPassword.getText().toString();

				if (username.isEmpty() && password.isEmpty()) {
					Toast.makeText(context, R.string.msg_login_input_empty,
							Toast.LENGTH_SHORT).show();
				} else {
					
					
					

					 progressGenerator.start(vLogin);
					 
					 
					 
					 if(vRememberPassword.isChecked()){
						 SettingsManager.getInstance().setPassword(password);
						 SettingsManager.getInstance().setUsername(username);
						 SettingsManager.getInstance().isStoreUser(true);
					 }
					 
					 
					 final LoginParams loginParams = new LoginParams();
					
					 loginParams.setPassword(password);
					 loginParams.setUsername(username);
					 

					new AsyncTask<Void, Void, Boolean>() {

						Auth auth;
						@Override
						protected Boolean doInBackground(Void... params) {

		
							try {
								
								
								auth = AuthManager.getInstance().login(loginParams);
								
								
								SettingsManager.getInstance().setAccessToken(auth.getAccessToken());							
								
								
								
								
							if(auth!= null && auth.isSuccess()){
								
								SignalrConnection.getInstance().connect();
								
								return true;

							}else{
								return false;

							}

							}  catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								return false;

							} 
						}

						@Override
						protected void onPostExecute(Boolean result) {
							
							
							vUsername.setEnabled(true);
							vPassword.setEnabled(true);
							if (result) {
								progressGenerator.complete();
								
								
								
							} else {
								
								Log.e(TAG, auth.toString());
								Toast.makeText(context,
										auth.getInfo(),
										Toast.LENGTH_SHORT).show();
								progressGenerator.stop();
							}

						}

					}.execute();

				}

			}
		});

	}

	
	
	@Override
	public void onTaskComplete() {
		 Intent intent = new Intent(LoginActivity.this,MainActivity.class);
	
		 startActivity(intent);
		 finish();		
	}

	@Override
	public void onTaskCancle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTaskStop() {
		// TODO Auto-generated method stub
		
	}



}
