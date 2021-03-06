package org.owntracks.android.support.receiver;

import org.owntracks.android.App;
import org.owntracks.android.services.BackgroundService;
import org.owntracks.android.support.Preferences;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import javax.inject.Inject;

import dagger.android.DaggerBroadcastReceiver;
import timber.log.Timber;

public class BootCompleteReceiver extends DaggerBroadcastReceiver {
	@Inject
	Preferences preferences;

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context,intent);

		if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction()) && preferences.getAutostartOnBoot()){
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
				context.startForegroundService((new Intent(context, BackgroundService.class)));
			} else {
				context.startService((new Intent(context, BackgroundService.class)));
			}
		}
	}
}
