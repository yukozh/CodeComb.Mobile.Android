
/**
 * @author UFreedom
 * @since 2014 2014-10-3 上午10:05:25
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.views;

import android.support.v4.app.Fragment;

import com.codecomb.module.contacts.Contact;



public class ChatActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {

		Contact contact = (Contact) getIntent().getSerializableExtra(ChatFragment.EXTRA_CHAT_CONTACT);

		return ChatFragment.newInstance(contact);
	}

}

