/*
    Inherited from https://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/DecodeFormatManager.java
 */

package me.dm7.zxing.android;

import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Pattern;

import android.content.Intent;
import android.text.TextUtils;
import com.google.zxing.BarcodeFormat;

final class DecodeFormatManager {
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    static final Collection<BarcodeFormat> ALL_FORMATS;
    static {
        ALL_FORMATS = EnumSet.of(BarcodeFormat.UPC_A,
                                 BarcodeFormat.UPC_E,
                                 BarcodeFormat.EAN_13,
                                 BarcodeFormat.EAN_8,
                                 BarcodeFormat.RSS_14,
                                 BarcodeFormat.CODE_39,
                                 BarcodeFormat.CODE_93,
                                 BarcodeFormat.CODE_128,
                                 BarcodeFormat.ITF,
                                 BarcodeFormat.CODABAR,
                                 BarcodeFormat.QR_CODE,
                                 BarcodeFormat.DATA_MATRIX,
                                 BarcodeFormat.PDF_417);
    }

    private DecodeFormatManager() {}

    static Collection<BarcodeFormat> parseDecodeFormats(Intent intent) {
        List<String> scanFormats = null;
        String scanFormatsString = intent.getStringExtra(ZXingConstants.SCAN_FORMATS);
        if (!TextUtils.isEmpty(scanFormatsString)) {
            scanFormats = Arrays.asList(COMMA_PATTERN.split(scanFormatsString));
        }
        return parseDecodeFormats(scanFormats);
    }

    private static Collection<BarcodeFormat> parseDecodeFormats(Iterable<String> scanFormats) {
        if (scanFormats != null) {
            Collection<BarcodeFormat> formats = EnumSet.noneOf(BarcodeFormat.class);
            try {
                for (String format : scanFormats) {
                    formats.add(BarcodeFormat.valueOf(format));
                }
                return formats;
            } catch (IllegalArgumentException iae) {
                // ignore it then
            }
        }
        return ALL_FORMATS;
    }

}

/*
    Inherited from https://code.google.com/p/zxing/source/browse/trunk/android/src/com/google/zxing/client/android/DecodeFormatManager.java
 */
