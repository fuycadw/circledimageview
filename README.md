CircledImageView
=============================

Utility class to create circled images. This can be used in your xml layouts 
or programatically using Java.

![](sample.png)

Xml usage
=============================

```xml
<com.cgollner.circledimageview.CircledImageView
                android:layout_width="match_parent"
                android:layout_height="wrap_content" 
                android:src="@drawable/sample"
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

License
--------

    Copyright 2013 Christian Göllner.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: http://www.cgollner.x10.mx
