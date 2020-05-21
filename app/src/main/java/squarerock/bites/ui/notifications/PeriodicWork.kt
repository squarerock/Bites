package squarerock.bites.ui.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import squarerock.bites.BuildConstants
import squarerock.bites.R

class PeriodicWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        createNotification("test title", "test description")
        return Result.success()
    }

    private fun createNotification(title: String, description: String) {
        val notificationManager = applicationContext.getSystemService(NotificationManager::class.java)

        if (BuildConstants.ATLEAST_ORE0) {
            val notificationChannel =
                NotificationChannel("101", "channel", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder = NotificationCompat.Builder(applicationContext, "101")
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.drawable.ic_launcher_background)

        notificationManager.notify(1, notificationBuilder.build())
    }
}