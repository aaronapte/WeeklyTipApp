package engageglobally.weeklytipapp2;

import android.app.*;
import android.content.*;
import android.support.v7.app.*;

public class NotificationHandler extends IntentService{

   private NotificationManager notificationManager;

    public NotificationHandler(){
        super("NotificationHandler");
    }

    @Override
    public void onHandleIntent(Intent intent)
    {
        sendNotification("Your weekly tip is here!", "Check out this week's cool information! :)");
    }

    private void sendNotification(String title, String msg)
    {
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(
                this).setContentTitle(title).setSmallIcon(R.drawable.picture).setStyle(
                new NotificationCompat.BigTextStyle().bigText(msg)).setContentText(msg);

        notificationBuilder.setContentIntent(contentIntent);
        notificationManager.notify(NotificationManager.IMPORTANCE_LOW, notificationBuilder.build());
    }
}
