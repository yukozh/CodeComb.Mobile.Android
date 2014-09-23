package com.codecomb.module.im;

import com.codecomb.MyApplication;
import com.codecomb.infrastructure.respository.wabapi.net.Constants;
import com.zsoft.SignalA.Hubs.HubConnection;
import com.zsoft.SignalA.Transport.StateBase;
import com.zsoft.SignalA.Transport.Longpolling.LongPollingTransport;

public class SignalrConnection {

	
	private HubConnection connection;
	
	public SignalrConnection getInstance() {
		return SingletonCreator.instance;
	}

	private static class SingletonCreator {
		private static final SignalrConnection instance = new SignalrConnection();
	}

	private SignalrConnection() {

	}

	
	
	public HubConnection connect() {

		HubConnection conn = new HubConnection(
				Constants.BASE_ADDRESS_TERMINAL_SERVICE, MyApplication
						.getInstance().getApplicationContext(),
				new LongPollingTransport()) 
		{
			@Override
			public void OnError(Exception exception) {
				
			}

			@Override
			public void OnMessage(String message) {
				
			}

			@Override
			public void OnStateChanged(StateBase oldState, StateBase newState) {
				
				
				
			}
		};
		
		connection  = conn;

		return conn;

	}
	
	public void startSignalA(){
		if (connection != null) {
			connection.Start();
			
		}
	}
	
	
	
	public void stopSignalA(){
		if (connection != null) {
			connection.Stop();
			
		}
	}

}
