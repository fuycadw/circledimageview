CircledImageView
=============================

Utility class to create circled images. This can be used in your xml layouts 
or programatically using Java.

![ScreenShot](https://dl.dropboxusercontent.com/u/9699990/circledimageview/2013-08-09%2012.54.47.png)

Xml usage
=============================

```xml
<com.cgollner.circledimageview.CircledImageView
                android:layout_width="match_parent"
                android:layout_height="wrap_content" 
                />
```

Java usage
=============================
```java
Bitmap originalBitmap = ...
Bitmap circledBitmap = CircledImageView.getCircleBitmap(originalBitmap);
```

Developed by
=============================
* [Christian Göllner][1]

[1]: http://www.cgollner.x10.mx
