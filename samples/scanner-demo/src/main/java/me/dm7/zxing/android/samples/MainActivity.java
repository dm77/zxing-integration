package me.dm7.zxing.android.samples;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import me.dm7.zxing.android.ZXingConstants;
import me.dm7.zxing.android.ZXingScannerActivity;
import com.google.zxing.BarcodeFormat;

public class MainActivity extends Activity {

    private static final int ZXING_SCANNER_REQUEST = 0;
    private static final int ZXING_QR_SCANNER_REQUEST = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void launchScanner(View v) {
        if (isCameraAvailable()) {
            Intent intent = new Intent(this, ZXingScannerActivity.class);
            startActivityForResult(intent, ZXING_SCANNER_REQUEST);
        } else {
            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    public void launchQRScanner(View v) {
        if (isCameraAvailable()) {
            Intent intent = new Intent(this, ZXingScannerActivity.class);
            intent.putExtra(ZXingConstants.SCAN_FORMATS, BarcodeFormat.QR_CODE.toString());
            startActivityForResult(intent, ZXING_SCANNER_REQUEST);
        } else {
            Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isCameraAvailable() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ZXING_SCANNER_REQUEST:
            case ZXING_QR_SCANNER_REQUEST:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "Scan Result = " + data.getStringExtra(ZXingConstants.SCAN_RESULT) +
                            ", Scan Format = " + data.getStringExtra(ZXingConstants.SCAN_RESULT_FORMAT), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
