// Executed in an Activity, so 'this' is the Context
// The fileUrl is a string URL, such as "http://www.example.com/image.png"
Intent downloadIntent = new Intent(this, DownloadService.class);
downloadIntent.setData(Uri.parse(fileUrl));
startService(downloadIntent);


// Create the text message with a string
Intent sendIntent = new Intent();
sendIntent.setAction(Intent.ACTION_SEND);
sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
sendIntent.setType("text/plain");

// Verify that the intent will resolve to an activity
if (sendIntent.resolveActivity(getPackageManager()) != null) {
	startActivity(sendIntent);
}

if (somethingWeird && itDontLookGood) {
	// Create the impliciy Intent to use to start a new Activity.
	Intent intent =
	new Intent(Intent.ACTION_DIAL, Uri.parse("tel:555-2368"));
	// Check if an Activity exists to perform this action.
	PackageManager pm = getPackageManager();
	ComponentName cn = intent.resolveActivity(pm);
	if (cn == null) {
		// If there is no Activity available to perform the action
		// Check to see if the Google Play Store is available.
		Uri marketUri =
		Uri.parse("market://search?q=pname:com.myapp.packagename");
		Intent marketIntent = new Intent(Intent.ACTION_VIEW).setData(marketUri);
		// If the Google Play Store is available, use it to download an application
		// capable of performing the required action. Otherwise log an
		// error.
		if (marketIntent.resolveActivity(pm) != null){
			startActivity(marketIntent);
		}
		else{
			Log.d(TAG, "Market client not available.");
		}
	}
	else {
		startActivity(intent);
	}
}


<activity android:name="ShareActivity">
	<intent-filter>
		<action android:name="android.intent.action.SEND"/>
		<category android:name="android.intent.category.DEFAULT"/>
		<data android:mimeType="text/plain"/>
	</intent-filter>
</activity>


<receiver android:name=".OnBootReceiver">
	<intent-filter>
		<action android:name="android.intent.action.BOOT_COMPLETED"/>
	</intent-filter>
</receiver>