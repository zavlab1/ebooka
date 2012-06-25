package com.ebooka.dialogs;

import com.ebooka.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

public class Alert {
	public static View showPrompt(String message, Context ctx) {
		LayoutInflater li = LayoutInflater.from(ctx);
		View view = li.inflate(R.layout.prompt_layout, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle("Prompt");
		builder.setView(view);
		builder.setMessage(message);
		builder.setPositiveButton("OK", (DialogInterface.OnClickListener) ctx);
		builder.setNegativeButton("Cancel", (DialogInterface.OnClickListener) ctx);
		AlertDialog ad = builder.create();
		ad.show();
		return view;
	}
}
