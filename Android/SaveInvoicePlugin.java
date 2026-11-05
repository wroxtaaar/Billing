package com.fancysteelworks.invoice;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.Toast;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.JSObject;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.io.OutputStream;
import java.util.Base64;

@CapacitorPlugin(name = "InvoiceSaver")
public class SaveInvoicePlugin extends Plugin {
    @PluginMethod
    public void savePng(PluginCall call) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            call.reject("Saving invoices directly to Downloads requires Android 10 or newer.");
            return;
        }

        String filename = call.getString("filename", "invoice.png");
        String base64 = call.getString("data");
        if (base64 == null || base64.isEmpty()) {
            call.reject("No invoice image data was received.");
            return;
        }

        Uri uri = null;
        try {
            byte[] bytes = Base64.getDecoder().decode(base64);
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME, filename);
            values.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, "Download/Fancy Steel Works");
            values.put(MediaStore.MediaColumns.IS_PENDING, 1);

            uri = getContext().getContentResolver().insert(
                MediaStore.Downloads.EXTERNAL_CONTENT_URI, values
            );
            if (uri == null) throw new Exception("Android could not create the download file.");

            try (OutputStream output = getContext().getContentResolver().openOutputStream(uri)) {
                if (output == null) throw new Exception("Could not open the invoice file.");
                output.write(bytes);
                output.flush();
            }

            ContentValues complete = new ContentValues();
            complete.put(MediaStore.MediaColumns.IS_PENDING, 0);
            getContext().getContentResolver().update(uri, complete, null, null);

            Toast.makeText(getContext(), "Invoice saved in Downloads/Fancy Steel Works", Toast.LENGTH_LONG).show();
            JSObject result = new JSObject();
            result.put("uri", uri.toString());
            call.resolve(result);
        } catch (Exception error) {
            if (uri != null) {
                try { getContext().getContentResolver().delete(uri, null, null); } catch (Exception ignored) {}
            }
            call.reject("Could not save invoice to Downloads: " + error.getMessage(), error);
        }
    }
}
