package com.ebooka.dialogs;

import com.ebooka.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class Alert {
	public static View showPrompt(String message, Context ctx, DialogInterface.OnClickListener promptListener) {
		LayoutInflater li = LayoutInflater.from(ctx);
		View view = li.inflate(R.layout.prompt_layout, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setView(view);
		TextView tv = (TextView) view.findViewById(R.id.promptmessage);
		tv.setText(message);
		builder.setPositiveButton("OK", promptListener);
		builder.setNegativeButton("Cancel", promptListener);
		AlertDialog ad = builder.create();
		ad.show();
		return view;
	}
	public static void showAlert(String message, Context ctx) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle("Attention");
		builder.setMessage(message);
		EmptyOnClickListener el = new EmptyOnClickListener();
		builder.setPositiveButton("OK", el);
		AlertDialog ad = builder.create();
		ad.show();
	}
}

class EmptyOnClickListener implements DialogInterface.OnClickListener {
	public void onClick(DialogInterface v, int buttonId) {   }
}