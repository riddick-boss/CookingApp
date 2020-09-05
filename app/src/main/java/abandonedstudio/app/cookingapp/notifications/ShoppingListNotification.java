package abandonedstudio.app.cookingapp.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import abandonedstudio.app.cookingapp.R;
import abandonedstudio.app.cookingapp.RequestCode;

public class ShoppingListNotification {

    public Notification buildNotification(Context context, String dishName, String bigText){
        createNotificationChannel(context);
        return new NotificationCompat.Builder(context, RequestCode.SHOPPING_LIST_NOTIFICATION_CHANNEL_ID)
                .setShowWhen(false)
                .setSmallIcon(R.drawable.ic_twotone_shopping_basket_24)
                .setContentTitle(context.getResources().getString(R.string.shopping_list))
                .setContentText(dishName)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(bigText))
                .build();
    }

    private void createNotificationChannel(Context context){
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getResources().getString(R.string.shopping_list_notification);
            String description = context.getResources().getString(R.string.shopping_list_notification_service_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(RequestCode.SHOPPING_LIST_NOTIFICATION_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }
}
