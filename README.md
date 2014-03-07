ATTENTION!
==========

This project will no longer be maintained. Moving forward, I am going to spend my time on development of this project here: https://github.com/dm77/barcodescanner

-------------------------------------------------------------------------------------

This is an Android library project that simplifies the integration with zxing (https://code.google.com/p/zxing/).

### How to install?

#### With Gradle

Clone this repo and you can use either approaches 1 or 2 below:

#### Approach 1 (Folder Reference): 

Add a reference to the folder in your build.gradle file. More info here: http://tools.android.com/tech-docs/new-build-system/user-guide#TOC-Referencing-a-Library


#### Approach 2 (Install to local maven repository): 

* Go to the zxing-integration sub-folder
* Make sure maven is installed
* From command line, run the commands: 
gradle assemble
gradle publishToLocal

### How to use?

1. Download this project and add it as a library project to your existing Android app.
2. Open the AndroidManifest.xml file in your project and add these lines under the manifest element:
<pre>
&lt;uses-permission android:name="android.permission.CAMERA"/&gt;
&lt;uses-feature android:name="android.hardware.camera" /&gt;
</pre>
3. Within the application element, add the activity declartion:
<pre>
&lt;activity android:name="me.dm7.zxing.android.ZXingScannerActivity"`
            android:screenOrientation="landscape"
            android:label="@string/app_name" /&gt;
</pre>
4. Now from inside your Android application, you can launch the scanner from any activity using this intent:

```java
Intent intent = new Intent(this, ZXingScannerActivity.class);
startActivityForResult(intent, ZXING_SCANNER_REQUEST);
```

To receive the results of the SCAN, add this method to your activity:

```java
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
```  

### Advanced Options
If you are interested in scanning only QR codes, you can specify the SCAN mode in the intent as shown below:
```java
Intent intent = new Intent(this, ZXingScannerActivity.class);
intent.putExtra(ZXingConstants.SCAN_FORMATS, BarcodeFormat.QR_CODE.toString());
startActivityForResult(intent, ZXING_SCANNER_REQUEST);
```

Supported formats are here: https://code.google.com/p/zxing/source/browse/trunk/core/src/com/google/zxing/BarcodeFormat.java

If you do not specify scan_modes, the scanner processes these:
```java
EnumSet.of(BarcodeFormat.UPC_A,
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
           BarcodeFormat.DATA_MATRIX);
```

But if you are interested in QRCodes and PDF_417, you would setup the intent as follows:
```java
Intent intent = new Intent(this, ZXingScannerActivity.class);
intent.putExtra(ZXingConstants.SCAN_FORMATS, BarcodeFormat.QR_CODE.toString() + "," + BarcodeFormat.PDF_417.toString());
startActivityForResult(intent, ZXING_SCANNER_REQUEST);
```

### Example app
There is a scanner_demo app in the samples folder which demonstrates the use of this library.

### Tests
I have tested the scanner functionality on these devices without any issues so far:
* Motorola Droid running Android 2.2.3
* HTC Thunderbolt running Android 2.3.4
* Samsung Galaxy Nexus running Android 4.0.4

### Credits
Almost all of the code for this library project has been taken from these two places:

1. CameraPreview app from Android SDK APIDemos 
2. The zxing source code: https://code.google.com/p/zxing/

### License
MIT

-----------------------------------------------------------------

ATTENTION!
==========

This project will no longer be maintained. Moving forward, I am going to spend my time on development of this project here: https://github.com/dm77/barcodescanner


