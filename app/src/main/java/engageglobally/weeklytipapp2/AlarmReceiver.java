package engageglobally.weeklytipapp2;

import android.app.*;
import android.content.*;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.NotificationCompat;

public class AlarmReceiver extends WakefulBroadcastReceiver
{
    private NotificationManager notificationManager;

    public void onReceive(Context context, Intent intent){

        if(Helper.isAppRunning(context, "com.engageglobally.weeklytipapp2"))
        {
            System.out.println("hello");
            MainActivity.change();
        }
        else {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent repeatIntent = new Intent(context, MainActivity.class);
            repeatIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeatIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.picture)
                    .setContentTitle("Your weekly tip is here!")
                    .setContentText("Check out this week's cool information! :)")
                    .setAutoCancel(true);
            notificationManager.notify(100, builder.build());
        }
    }

    /*public void onTrimMemory(final int level) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_COMPLETE) {
        }
    }*/
}
