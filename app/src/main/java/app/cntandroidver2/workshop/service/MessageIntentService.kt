//package app.cntandroidver2.workshop.service
//
//import android.app.IntentService
//import android.content.Intent
//import java.util.logging.Handler
//
//
//class MessageIntentService() : IntentService("MessageService") {
//
//
//    override fun onHandleIntent(workIntent: Intent?) {
//
//        val dataString = workIntent?.getStringExtra("message")
//        val intent = Intent()
//        intent.action = MESSAGE_INFO
//        intent.putExtra("cashback", "message from service")
//        sendBroadcast(intent)
//    }
//
//    companion object {
//        const val MESSAGE_INFO = "MESSAGE_INFO"
//    }
//
//}