/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2017 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.locationupdatesservice;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

@Kroll.module(name = "Locationupdatesservice", id = "ti.locationupdatesservice")
public class LocationupdatesserviceModule extends KrollModule {

	// Standard Debugging variables
	private static final String LCAT = "TiGeoLogger";
	Context ctx;
	private static String dbName = "geologger";
	private static String notChannel = "channel1";
	private static String notIcon = null;
	private static String notTitle = "";
	private static int interval = 0;
	private static int duration = 0;
	public static String rootActivityClassName = "";
	final static String ACTION = "LocationUpdatesServiceAction";
	final static int RQS_STOP_TRACKER = 0;
	final static int RQS_START_TRACKER = 1;
	final static int RQS_REMOVE_TRACKER = 2;
	final static String SERVICE_COMMAND_KEY = "SERVICECOMMANDKEY";

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;

	public LocationupdatesserviceModule() {
		super();
		ctx = TiApplication.getInstance().getApplicationContext();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
		Log.d(LCAT, "inside onAppCreate");
		// put module init code that needs to run when the application is
		// created
	}

	// Methods
	@Kroll.method
	public void config(KrollDict opts) {
		if (opts.containsKeyStartingWith("dbName"))
			dbName = opts.getString("dbName");
		if (opts.containsKeyStartingWith("notification")
				&& opts.get("notification") instanceof KrollDict) {
			KrollDict notification = opts.getKrollDict("notification");
			if (notification.containsKeyAndNotNull("channel"))
				notChannel = notification.getString("channel");
			if (notification.containsKeyAndNotNull("icon"))
				notIcon = notification.getString("icon");
			if (notification.containsKeyAndNotNull("title"))
				notTitle = notification.getString("title");
		}
		dbName = opts.getString("dbName");

	}

	@Kroll.method
	public void start(KrollDict opts) {
		if (opts.containsKeyStartingWith("interval"))
			interval = opts.getInt("interval");
		callServices(ACTION, SERVICE_COMMAND_KEY, RQS_START_TRACKER);

	}

	@Kroll.method
	public void stop() {
		callServices(ACTION, SERVICE_COMMAND_KEY, RQS_STOP_TRACKER);
	}

	private void callServices(String action, String extrakey, int extravalue) {
		Intent intent = new Intent(ctx.getPackageName());
		intent.setAction(action);
		intent.putExtra(extrakey, extravalue);
		try {
			ctx.startService(new Intent(ctx, LocationUpdatesService.class));
		} catch (Exception ex) {
			Log.e(LCAT, "Exception caught:" + ex);
		}
	}

	@Override
	public void onStart(Activity activity) {
		rootActivityClassName = TiApplication.getInstance()
				.getApplicationContext().getPackageName()
				+ "."
				+ TiApplication.getAppRootOrCurrentActivity().getClass()
						.getSimpleName();
		Log.d(LCAT, "Module started " + rootActivityClassName);
		super.onStart(activity);
	}

}
