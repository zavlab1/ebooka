package com.ebooka.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class Alert {
	public static void showPrompt(String message, Context ctx) {
		LayoutInflater li = LayoutInflater.from(ctx);
		View view = li.inflate(R.layout.prompt_layout, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle("Prompt");
		builder.setView(view);
		builder.setMessage(message);
		AlertDialog ad = builder.create();
		ad.show();		
	}
}
