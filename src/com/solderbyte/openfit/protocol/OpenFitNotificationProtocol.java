package com.solderbyte.openfit.protocol;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.Iterator;
import java.util.List;

import com.solderbyte.openfit.R;
import com.solderbyte.openfit.util.OpenFitData;
import com.solderbyte.openfit.util.OpenFitDataType;
import com.solderbyte.openfit.util.OpenFitDataTypeAndString;
import com.solderbyte.openfit.util.OpenFitVariableDataComposer;

public class OpenFitNotificationProtocol {

    private static final String LOG_TAG = "OpenFit:OpenFitNotificationProtocol";

    public static Boolean SUPPORT_QUICK_REPLY = false;
    public static Boolean SHOW_ON_DEVICE = false;
    public static Boolean HAS_IMAGE = false;
    public static Boolean INCOMING_CALL_FLAG = true;

    public byte[] createMessageProtocol(int msgType, long msgId, List<OpenFitDataTypeAndString> msgData, long timeStamp) {
        OpenFitVariableDataComposer oDatacomposer = new OpenFitVariableDataComposer();;
        oDatacomposer.writeByte((byte)msgType);
        oDatacomposer.writeLong(msgId);
        StringBuilder oStringBuilder = new StringBuilder();

        Iterator<OpenFitDataTypeAndString> oIterator = msgData.iterator();
        while(oIterator.hasNext()) {
            OpenFitDataTypeAndString localDataTypeAndString = (OpenFitDataTypeAndString)oIterator.next();
            byte[] oByte = OpenFitVariableDataComposer.convertToByteArray(localDataTypeAndString.getData());
            
            if(localDataTypeAndString.getDataType() == OpenFitDataType.BYTE) {
                oDatacomposer.writeByte((byte)oByte.length);
            }
            if(localDataTypeAndString.getDataType() == OpenFitDataType.SHORT) {
                oDatacomposer.writeShort((short)oByte.length);
            }
            oStringBuilder.append(oByte.length).append(" ");
            oDatacomposer.writeBytes(oByte);
        }
        oDatacomposer.writeBoolean(SHOW_ON_DEVICE);
        oDatacomposer.writeByte((byte)0);
        OpenFitVariableDataComposer.writeTimeInfo(oDatacomposer, timeStamp);
        return oDatacomposer.toByteArray();
    }

    public static byte[] createNotificationProtocol(int msgType, long msgId, List<OpenFitDataTypeAndString> msgData, long timeStamp) {
        OpenFitVariableDataComposer oDatacomposer = new OpenFitVariableDataComposer();
        oDatacomposer.writeByte((byte)msgType);
        oDatacomposer.writeLong(msgId);
        StringBuilder oStringBuilder = new StringBuilder();
        Iterator<OpenFitDataTypeAndString> oIterator = msgData.iterator();

        while(oIterator.hasNext()) {
            OpenFitDataTypeAndString oDataString = (OpenFitDataTypeAndString)oIterator.next();
            byte[] oByte = OpenFitVariableDataComposer.convertToByteArray(oDataString.getData());

            if(oDataString.getDataType() == OpenFitDataType.BYTE) {
                oDatacomposer.writeByte((byte)oByte.length);
            }
            if(oDataString.getDataType() == OpenFitDataType.SHORT) {
                oDatacomposer.writeShort((short)oByte.length);
            }
            oStringBuilder.append(oByte.length).append(" ");
            oDatacomposer.writeBytes(oByte);
        }
        oDatacomposer.writeByte((byte) 0);
        OpenFitVariableDataComposer.writeTimeInfo(oDatacomposer, timeStamp);
        return oDatacomposer.toByteArray();
    }

    public static byte[] createEmailProtocol(int msgType, long msgId, List<OpenFitDataTypeAndString> msgData, long timeStamp) {
        OpenFitVariableDataComposer oDatacomposer = new OpenFitVariableDataComposer();
        oDatacomposer.writeByte((byte)msgType);
        oDatacomposer.writeLong(msgId);
        StringBuilder oStringBuilder = new StringBuilder();
        Iterator<OpenFitDataTypeAndString> oIterator = msgData.iterator();

        while(oIterator.hasNext()) {
            OpenFitDataTypeAndString oDataString = (OpenFitDataTypeAndString)oIterator.next();
            byte[] oByte = OpenFitVariableDataComposer.convertToByteArray(oDataString.getData());

            if(oDataString.getDataType() == OpenFitDataType.BYTE) {
                oDatacomposer.writeByte((byte)oByte.length);
            }
            if(oDataString.getDataType() == OpenFitDataType.SHORT) {
                oDatacomposer.writeShort((short)oByte.length);
            }
            oStringBuilder.append(oByte.length).append(" ");
            oDatacomposer.writeBytes(oByte);
        }
        // number of attached files num << 1
        oDatacomposer.writeByte((byte)0);
        oDatacomposer.writeBoolean(SUPPORT_QUICK_REPLY);
        oDatacomposer.writeBoolean(HAS_IMAGE);
        OpenFitVariableDataComposer.writeTimeInfo(oDatacomposer, timeStamp);
        return oDatacomposer.toByteArray();
    }

    public static byte[] createIncomingCallProtocol(int msgType, long msgId, List<OpenFitDataTypeAndString> msgData, long timeStamp) {
        OpenFitVariableDataComposer oDatacomposer = new OpenFitVariableDataComposer();
        oDatacomposer.writeByte((byte)msgType);
        oDatacomposer.writeLong(msgId);
        if(INCOMING_CALL_FLAG) {
            oDatacomposer.writeByte((byte)0);
        }
        else {
            oDatacomposer.writeByte((byte)1);
        }
        StringBuilder oStringBuilder = new StringBuilder();
        Iterator<OpenFitDataTypeAndString> oIterator = msgData.iterator();

        while(oIterator.hasNext()) {
            OpenFitDataTypeAndString oDataString = (OpenFitDataTypeAndString)oIterator.next();
            byte[] oByte = OpenFitVariableDataComposer.convertToByteArray(oDataString.getData());

            if(oDataString.getDataType() == OpenFitDataType.BYTE) {
                oDatacomposer.writeByte((byte)oByte.length);
            }
            if(oDataString.getDataType() == OpenFitDataType.SHORT) {
                oDatacomposer.writeShort((short)oByte.length);
            }
            oStringBuilder.append(oByte.length).append(" ");
            oDatacomposer.writeBytes(oByte);
        }

        OpenFitVariableDataComposer.writeTimeInfo(oDatacomposer, timeStamp);
        return oDatacomposer.toByteArray();
    }

    public static byte[] createMediaTrackProtocol(int msgType, String msgData) {
        OpenFitVariableDataComposer oDatacomposer = new OpenFitVariableDataComposer();
        oDatacomposer.writeByte((byte) msgType);
        OpenFitVariableDataComposer.writeStringWithOneByteLength(oDatacomposer, msgData);

        return oDatacomposer.toByteArray();
    }

    public static byte[] createAlarmProtocol(int msgType, long msgId, List<OpenFitDataTypeAndString> msgData, long timeStamp) {
        OpenFitVariableDataComposer oDatacomposer = new OpenFitVariableDataComposer();
        oDatacomposer.writeByte((byte)msgType);
        oDatacomposer.writeLong(msgId);
        StringBuilder oStringBuilder = new StringBuilder();
        Iterator<OpenFitDataTypeAndString> oIterator = msgData.iterator();

        while(oIterator.hasNext()) {
            OpenFitDataTypeAndString oDataString = (OpenFitDataTypeAndString)oIterator.next();
            byte[] oByte = OpenFitVariableDataComposer.convertToByteArray(oDataString.getData());

            if(oDataString.getDataType() == OpenFitDataType.BYTE) {
                oDatacomposer.writeByte((byte)oByte.length);
            }
            if(oDataString.getDataType() == OpenFitDataType.SHORT) {
                oDatacomposer.writeShort((short)oByte.length);
            }
            oStringBuilder.append(oByte.length).append(" ");
            oDatacomposer.writeBytes(oByte);
        }
        //OpenFitVariableDataComposer.writeTimeInfo(oDatacomposer, timeStamp);

        oDatacomposer.writeInt((int) timeStamp);
        // snooze or dissmiss
        oDatacomposer.writeInt(0);
        return oDatacomposer.toByteArray();
    }
    
    public static byte[] createWeatherProtocol(int msgType, long msgId, String msgData, int icon, long timeStamp) {
        OpenFitVariableDataComposer oDatacomposer = new OpenFitVariableDataComposer();
        oDatacomposer.writeByte((byte)msgType);
        oDatacomposer.writeLong(msgId);

        byte[] oByte = OpenFitVariableDataComposer.convertToByteArray(msgData);

        oDatacomposer.writeByte((byte)oByte.length);
        oDatacomposer.writeBytes(oByte);
        oDatacomposer.writeInt(icon);
        OpenFitVariableDataComposer.writeTimeInfo(oDatacomposer, timeStamp);
        return oDatacomposer.toByteArray();
    }


    public static byte[] sendAppIconToWingtip(Context context, String packageName) {

        if (context == null) {
            return null;
        }
        try {
            Bitmap iconBitmap;
/*            if (packageName.equals(ServicePackages.NOTI_PACKAGE_SHEATHE)) {
                iconBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.manager_noti_voice_mail);
            } else if (packageName.equals(ServicePackages.PACKAGE_HEALTH) || packageName.equals(ServicePackages.PACKAGE_HEALTHLITE)) {
                iconBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.wt_icon_shealth_63);
            } else
            */
            {
                Drawable d = context.getPackageManager().getApplicationIcon(packageName);
                iconBitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(iconBitmap);
                d.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                d.draw(canvas);
            }
            Bitmap resizedIconBitmap = Bitmap.createScaledBitmap(iconBitmap, 63, 63, true);
            if (iconBitmap == null || resizedIconBitmap == null) {
                return null;
            }
            Log.d(LOG_TAG, "image size, height : " + resizedIconBitmap.getHeight() + " / width : " + resizedIconBitmap.getWidth());
            OpenFitVariableDataComposer composer = new OpenFitVariableDataComposer();

            composer.writeByte((byte) OpenFitData.DATA_TYPE_IMAGE);
            byte[] dataBytes = OpenFitVariableDataComposer.convertToByteArray(packageName);
            composer.writeByte((byte) dataBytes.length);
            composer.writeBytes(dataBytes);
            byte[] iconSPI = com.samsung.android.sdk.cup.ScupSpiCodec.encode(resizedIconBitmap);
            composer.writeShort((short) iconSPI.length);
            composer.writeBytes(iconSPI);

            return composer.toByteArray();

            //this.mDataSender.send(WingtipApp.NOTIFICATION, composer.toByteArray());
            //Log.d(LOG_TAG, "send to AppIcon Bitmap image to Wingtip  bipmap size : " + iconSPI.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
