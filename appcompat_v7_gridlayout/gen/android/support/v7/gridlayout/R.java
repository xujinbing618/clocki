/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * aapt tool from the resource data it found.  It
 * should not be modified by hand.
 */

package android.support.v7.gridlayout;

public final class R {
    public static final class attr {
        /**
        When set to alignMargins, causes alignment to take place between the outer
        boundary of a view, as defined by its margins. When set to alignBounds,
        causes alignment to take place between the edges of the view.
        The default is alignMargins.
        See {@link android.widget.GridLayout#setAlignmentMode(int)}.

         <p>Must be one of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>alignBounds</code></td><td>0</td><td>
            Align the bounds of the children.
            See {@link android.widget.GridLayout#ALIGN_BOUNDS}.
            </td></tr>
<tr><td><code>alignMargins</code></td><td>1</td><td>
            Align the margins of the children.
            See {@link android.widget.GridLayout#ALIGN_MARGINS}.
            </td></tr>
</table>
         */
        public static int alignmentMode=0x7f010004;
        /**  The maximum number of columns to create when automatically positioning children.
         <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int columnCount=0x7f010002;
        /**
        When set to true, forces column boundaries to appear in the same order
        as column indices.
        The default is true.
        See {@link android.widget.GridLayout#setColumnOrderPreserved(boolean)}.

         <p>Must be a boolean value, either "<code>true</code>" or "<code>false</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int columnOrderPreserved=0x7f010006;
        /**
        The column boundary delimiting the left of the group of cells
        occupied by this view.

         <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int layout_column=0x7f010009;
        /**
        The column span: the difference between the right and left
        boundaries delimiting the group of cells occupied by this view.
        The default is one.
        See {@link android.widget.GridLayout.Spec}.

         <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int layout_columnSpan=0x7f01000a;
        /**
        Gravity specifies how a component should be placed in its group of cells.
        The default is LEFT | BASELINE.
        See {@link android.widget.GridLayout.LayoutParams#setGravity(int)}.

         <p>Must be one or more (separated by '|') of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>top</code></td><td>0x30</td><td> Push object to the top of its container, not changing its size. </td></tr>
<tr><td><code>bottom</code></td><td>0x50</td><td> Push object to the bottom of its container, not changing its size. </td></tr>
<tr><td><code>left</code></td><td>0x03</td><td> Push object to the left of its container, not changing its size. </td></tr>
<tr><td><code>right</code></td><td>0x05</td><td> Push object to the right of its container, not changing its size. </td></tr>
<tr><td><code>center_vertical</code></td><td>0x10</td><td> Place object in the vertical center of its container, not changing its size. </td></tr>
<tr><td><code>fill_vertical</code></td><td>0x70</td><td> Grow the vertical size of the object if needed so it completely fills its container. </td></tr>
<tr><td><code>center_horizontal</code></td><td>0x01</td><td> Place object in the horizontal center of its container, not changing its size. </td></tr>
<tr><td><code>fill_horizontal</code></td><td>0x07</td><td> Grow the horizontal size of the object if needed so it completely fills its container. </td></tr>
<tr><td><code>center</code></td><td>0x11</td><td> Place the object in the center of its container in both the vertical and horizontal axis, not changing its size. </td></tr>
<tr><td><code>fill</code></td><td>0x77</td><td> Grow the horizontal and vertical size of the object if needed so it completely fills its container. </td></tr>
<tr><td><code>clip_vertical</code></td><td>0x80</td><td>
             Additional option that can be set to have the top and/or bottom edges of
             the child clipped to its container's bounds.
             The clip will be based on the vertical gravity: a top gravity will clip the bottom
             edge, a bottom gravity will clip the top edge, and neither will clip both edges.
            </td></tr>
<tr><td><code>clip_horizontal</code></td><td>0x08</td><td>
             Additional option that can be set to have the left and/or right edges of
             the child clipped to its container's bounds.
             The clip will be based on the horizontal gravity: a left gravity will clip the right
             edge, a right gravity will clip the left edge, and neither will clip both edges.
            </td></tr>
<tr><td><code>start</code></td><td>0x00800003</td><td> Push object to the beginning of its container, not changing its size. </td></tr>
<tr><td><code>end</code></td><td>0x00800005</td><td> Push object to the end of its container, not changing its size. </td></tr>
</table>
         */
        public static int layout_gravity=0x7f01000b;
        /**  END MarginLayout layoutparams

        The row boundary delimiting the top of the group of cells
        occupied by this view.

         <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int layout_row=0x7f010007;
        /**
        The row span: the difference between the bottom and top
        boundaries delimiting the group of cells occupied by this view.
        The default is one.
        See {@link android.widget.GridLayout.Spec}.

         <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int layout_rowSpan=0x7f010008;
        /**
              support versions. All attributes not present in ViewGroup/View are
              redefined in the support library namespace.


        The orientation property is not used during layout. It is only used to
        allocate row and column parameters when they are not specified by its children's
        layout parameters. GridLayout works like LinearLayout in this case;
        putting all the components either in a single row or in a single column -
        depending on the value of this flag. In the horizontal case, a columnCount
        property may be additionally supplied to force new rows to be created when a
        row is full. The rowCount attribute may be used similarly in the vertical case.
        The default is horizontal.

         <p>Must be one of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>horizontal</code></td><td>0</td><td> Defines an horizontal widget. </td></tr>
<tr><td><code>vertical</code></td><td>1</td><td> Defines a vertical widget. </td></tr>
</table>
         */
        public static int orientation=0x7f010000;
        /**  The maximum number of rows to create when automatically positioning children.
         <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int rowCount=0x7f010001;
        /**
        When set to true, forces row boundaries to appear in the same order
        as row indices.
        The default is true.
        See {@link android.widget.GridLayout#setRowOrderPreserved(boolean)}.

         <p>Must be a boolean value, either "<code>true</code>" or "<code>false</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int rowOrderPreserved=0x7f010005;
        /**
        When set to true, tells GridLayout to use default margins when none are specified
        in a view's layout parameters.
        The default value is false.
        See {@link android.widget.GridLayout#setUseDefaultMargins(boolean)}.

         <p>Must be a boolean value, either "<code>true</code>" or "<code>false</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
         */
        public static int useDefaultMargins=0x7f010003;
    }
    public static final class dimen {
        /**  The default gap between components in a layout.
         */
        public static int default_gap=0x7f030000;
    }
    public static final class id {
        public static int alignBounds=0x7f020002;
        public static int alignMargins=0x7f020003;
        public static int bottom=0x7f020005;
        public static int center=0x7f02000c;
        public static int center_horizontal=0x7f02000a;
        public static int center_vertical=0x7f020008;
        public static int clip_horizontal=0x7f02000f;
        public static int clip_vertical=0x7f02000e;
        public static int end=0x7f020011;
        public static int fill=0x7f02000d;
        public static int fill_horizontal=0x7f02000b;
        public static int fill_vertical=0x7f020009;
        public static int horizontal=0x7f020000;
        public static int left=0x7f020006;
        public static int right=0x7f020007;
        public static int start=0x7f020010;
        public static int top=0x7f020004;
        public static int vertical=0x7f020001;
    }
    public static final class styleable {
        /** Attributes that can be used with a GridLayout.
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #GridLayout_alignmentMode android.support.v7.gridlayout:alignmentMode}</code></td><td>
        When set to alignMargins, causes alignment to take place between the outer
        boundary of a view, as defined by its margins.</td></tr>
           <tr><td><code>{@link #GridLayout_columnCount android.support.v7.gridlayout:columnCount}</code></td><td> The maximum number of columns to create when automatically positioning children.</td></tr>
           <tr><td><code>{@link #GridLayout_columnOrderPreserved android.support.v7.gridlayout:columnOrderPreserved}</code></td><td>
        When set to true, forces column boundaries to appear in the same order
        as column indices.</td></tr>
           <tr><td><code>{@link #GridLayout_orientation android.support.v7.gridlayout:orientation}</code></td><td>
              support versions.</td></tr>
           <tr><td><code>{@link #GridLayout_rowCount android.support.v7.gridlayout:rowCount}</code></td><td> The maximum number of rows to create when automatically positioning children.</td></tr>
           <tr><td><code>{@link #GridLayout_rowOrderPreserved android.support.v7.gridlayout:rowOrderPreserved}</code></td><td>
        When set to true, forces row boundaries to appear in the same order
        as row indices.</td></tr>
           <tr><td><code>{@link #GridLayout_useDefaultMargins android.support.v7.gridlayout:useDefaultMargins}</code></td><td>
        When set to true, tells GridLayout to use default margins when none are specified
        in a view's layout parameters.</td></tr>
           </table>
           @see #GridLayout_alignmentMode
           @see #GridLayout_columnCount
           @see #GridLayout_columnOrderPreserved
           @see #GridLayout_orientation
           @see #GridLayout_rowCount
           @see #GridLayout_rowOrderPreserved
           @see #GridLayout_useDefaultMargins
         */
        public static final int[] GridLayout = {
            0x7f010000, 0x7f010001, 0x7f010002, 0x7f010003,
            0x7f010004, 0x7f010005, 0x7f010006
        };
        /**
          <p>
          @attr description

        When set to alignMargins, causes alignment to take place between the outer
        boundary of a view, as defined by its margins. When set to alignBounds,
        causes alignment to take place between the edges of the view.
        The default is alignMargins.
        See {@link android.widget.GridLayout#setAlignmentMode(int)}.



          <p>Must be one of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>alignBounds</code></td><td>0</td><td>
            Align the bounds of the children.
            See {@link android.widget.GridLayout#ALIGN_BOUNDS}.
            </td></tr>
<tr><td><code>alignMargins</code></td><td>1</td><td>
            Align the margins of the children.
            See {@link android.widget.GridLayout#ALIGN_MARGINS}.
            </td></tr>
</table>
          <p>This is a private symbol.
          @attr name android.support.v7.gridlayout:alignmentMode
        */
        public static final int GridLayout_alignmentMode = 4;
        /**
          <p>
          @attr description
           The maximum number of columns to create when automatically positioning children.


          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          <p>This is a private symbol.
          @attr name android.support.v7.gridlayout:columnCount
        */
        public static final int GridLayout_columnCount = 2;
        /**
          <p>
          @attr description

        When set to true, forces column boundaries to appear in the same order
        as column indices.
        The default is true.
        See {@link android.widget.GridLayout#setColumnOrderPreserved(boolean)}.



          <p>Must be a boolean value, either "<code>true</code>" or "<code>false</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          <p>This is a private symbol.
          @attr name android.support.v7.gridlayout:columnOrderPreserved
        */
        public static final int GridLayout_columnOrderPreserved = 6;
        /**
          <p>
          @attr description

              support versions. All attributes not present in ViewGroup/View are
              redefined in the support library namespace.


        The orientation property is not used during layout. It is only used to
        allocate row and column parameters when they are not specified by its children's
        layout parameters. GridLayout works like LinearLayout in this case;
        putting all the components either in a single row or in a single column -
        depending on the value of this flag. In the horizontal case, a columnCount
        property may be additionally supplied to force new rows to be created when a
        row is full. The rowCount attribute may be used similarly in the vertical case.
        The default is horizontal.



          <p>Must be one of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>horizontal</code></td><td>0</td><td> Defines an horizontal widget. </td></tr>
<tr><td><code>vertical</code></td><td>1</td><td> Defines a vertical widget. </td></tr>
</table>
          <p>This is a private symbol.
          @attr name android.support.v7.gridlayout:orientation
        */
        public static final int GridLayout_orientation = 0;
        /**
          <p>
          @attr description
           The maximum number of rows to create when automatically positioning children.


          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          <p>This is a private symbol.
          @attr name android.support.v7.gridlayout:rowCount
        */
        public static final int GridLayout_rowCount = 1;
        /**
          <p>
          @attr description

        When set to true, forces row boundaries to appear in the same order
        as row indices.
        The default is true.
        See {@link android.widget.GridLayout#setRowOrderPreserved(boolean)}.



          <p>Must be a boolean value, either "<code>true</code>" or "<code>false</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          <p>This is a private symbol.
          @attr name android.support.v7.gridlayout:rowOrderPreserved
        */
        public static final int GridLayout_rowOrderPreserved = 5;
        /**
          <p>
          @attr description

        When set to true, tells GridLayout to use default margins when none are specified
        in a view's layout parameters.
        The default value is false.
        See {@link android.widget.GridLayout#setUseDefaultMargins(boolean)}.



          <p>Must be a boolean value, either "<code>true</code>" or "<code>false</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          <p>This is a private symbol.
          @attr name android.support.v7.gridlayout:useDefaultMargins
        */
        public static final int GridLayout_useDefaultMargins = 3;
        /** Attributes that can be used with a GridLayout_Layout.
           <p>Includes the following attributes:</p>
           <table>
           <colgroup align="left" />
           <colgroup align="left" />
           <tr><th>Attribute</th><th>Description</th></tr>
           <tr><td><code>{@link #GridLayout_Layout_android_layout_height android:layout_height}</code></td><td></td></tr>
           <tr><td><code>{@link #GridLayout_Layout_android_layout_margin android:layout_margin}</code></td><td>
              Specifies extra space on the left, top, right and bottom
              sides of this view.</td></tr>
           <tr><td><code>{@link #GridLayout_Layout_android_layout_marginBottom android:layout_marginBottom}</code></td><td>
              Specifies extra space on the bottom side of this view.</td></tr>
           <tr><td><code>{@link #GridLayout_Layout_android_layout_marginLeft android:layout_marginLeft}</code></td><td>
              Specifies extra space on the left side of this view.</td></tr>
           <tr><td><code>{@link #GridLayout_Layout_android_layout_marginRight android:layout_marginRight}</code></td><td>
              Specifies extra space on the right side of this view.</td></tr>
           <tr><td><code>{@link #GridLayout_Layout_android_layout_marginTop android:layout_marginTop}</code></td><td>
              Specifies extra space on the top side of this view.</td></tr>
           <tr><td><code>{@link #GridLayout_Layout_android_layout_width android:layout_width}</code></td><td>
              support versions.</td></tr>
           <tr><td><code>{@link #GridLayout_Layout_layout_column android.support.v7.gridlayout:layout_column}</code></td><td>
        The column boundary delimiting the left of the group of cells
        occupied by this view.</td></tr>
           <tr><td><code>{@link #GridLayout_Layout_layout_columnSpan android.support.v7.gridlayout:layout_columnSpan}</code></td><td>
        The column span: the difference between the right and left
        boundaries delimiting the group of cells occupied by this view.</td></tr>
           <tr><td><code>{@link #GridLayout_Layout_layout_gravity android.support.v7.gridlayout:layout_gravity}</code></td><td>
        Gravity specifies how a component should be placed in its group of cells.</td></tr>
           <tr><td><code>{@link #GridLayout_Layout_layout_row android.support.v7.gridlayout:layout_row}</code></td><td> END MarginLayout layoutparams

        The row boundary delimiting the top of the group of cells
        occupied by this view.</td></tr>
           <tr><td><code>{@link #GridLayout_Layout_layout_rowSpan android.support.v7.gridlayout:layout_rowSpan}</code></td><td>
        The row span: the difference between the bottom and top
        boundaries delimiting the group of cells occupied by this view.</td></tr>
           </table>
           @see #GridLayout_Layout_android_layout_height
           @see #GridLayout_Layout_android_layout_margin
           @see #GridLayout_Layout_android_layout_marginBottom
           @see #GridLayout_Layout_android_layout_marginLeft
           @see #GridLayout_Layout_android_layout_marginRight
           @see #GridLayout_Layout_android_layout_marginTop
           @see #GridLayout_Layout_android_layout_width
           @see #GridLayout_Layout_layout_column
           @see #GridLayout_Layout_layout_columnSpan
           @see #GridLayout_Layout_layout_gravity
           @see #GridLayout_Layout_layout_row
           @see #GridLayout_Layout_layout_rowSpan
         */
        public static final int[] GridLayout_Layout = {
            0x010100f4, 0x010100f5, 0x010100f6, 0x010100f7,
            0x010100f8, 0x010100f9, 0x010100fa, 0x7f010007,
            0x7f010008, 0x7f010009, 0x7f01000a, 0x7f01000b
        };
        /**
          <p>This symbol is the offset where the {@link android.R.attr#layout_height}
          attribute's value can be found in the {@link #GridLayout_Layout} array.
          @attr name android:layout_height
        */
        public static final int GridLayout_Layout_android_layout_height = 1;
        /**
          <p>
          @attr description

              Specifies extra space on the left, top, right and bottom
              sides of this view. This space is outside this view's bounds.

          <p>This corresponds to the global attribute
          resource symbol {@link android.R.attr#layout_margin}.
          @attr name android:layout_margin
        */
        public static final int GridLayout_Layout_android_layout_margin = 2;
        /**
          <p>
          @attr description

              Specifies extra space on the bottom side of this view.
              This space is outside this view's bounds.

          <p>This corresponds to the global attribute
          resource symbol {@link android.R.attr#layout_marginBottom}.
          @attr name android:layout_marginBottom
        */
        public static final int GridLayout_Layout_android_layout_marginBottom = 6;
        /**
          <p>
          @attr description

              Specifies extra space on the left side of this view.
              This space is outside this view's bounds.

          <p>This corresponds to the global attribute
          resource symbol {@link android.R.attr#layout_marginLeft}.
          @attr name android:layout_marginLeft
        */
        public static final int GridLayout_Layout_android_layout_marginLeft = 3;
        /**
          <p>
          @attr description

              Specifies extra space on the right side of this view.
              This space is outside this view's bounds.

          <p>This corresponds to the global attribute
          resource symbol {@link android.R.attr#layout_marginRight}.
          @attr name android:layout_marginRight
        */
        public static final int GridLayout_Layout_android_layout_marginRight = 5;
        /**
          <p>
          @attr description

              Specifies extra space on the top side of this view.
              This space is outside this view's bounds.

          <p>This corresponds to the global attribute
          resource symbol {@link android.R.attr#layout_marginTop}.
          @attr name android:layout_marginTop
        */
        public static final int GridLayout_Layout_android_layout_marginTop = 4;
        /**
          <p>
          @attr description

              support versions. All attributes not present in MarginLayout are
              redefined in the support library name space.

 START MarginLayout layoutparams
          <p>This corresponds to the global attribute
          resource symbol {@link android.R.attr#layout_width}.
          @attr name android:layout_width
        */
        public static final int GridLayout_Layout_android_layout_width = 0;
        /**
          <p>
          @attr description

        The column boundary delimiting the left of the group of cells
        occupied by this view.



          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          <p>This is a private symbol.
          @attr name android.support.v7.gridlayout:layout_column
        */
        public static final int GridLayout_Layout_layout_column = 9;
        /**
          <p>
          @attr description

        The column span: the difference between the right and left
        boundaries delimiting the group of cells occupied by this view.
        The default is one.
        See {@link android.widget.GridLayout.Spec}.



          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          <p>This is a private symbol.
          @attr name android.support.v7.gridlayout:layout_columnSpan
        */
        public static final int GridLayout_Layout_layout_columnSpan = 10;
        /**
          <p>
          @attr description

        Gravity specifies how a component should be placed in its group of cells.
        The default is LEFT | BASELINE.
        See {@link android.widget.GridLayout.LayoutParams#setGravity(int)}.



          <p>Must be one or more (separated by '|') of the following constant values.</p>
<table>
<colgroup align="left" />
<colgroup align="left" />
<colgroup align="left" />
<tr><th>Constant</th><th>Value</th><th>Description</th></tr>
<tr><td><code>top</code></td><td>0x30</td><td> Push object to the top of its container, not changing its size. </td></tr>
<tr><td><code>bottom</code></td><td>0x50</td><td> Push object to the bottom of its container, not changing its size. </td></tr>
<tr><td><code>left</code></td><td>0x03</td><td> Push object to the left of its container, not changing its size. </td></tr>
<tr><td><code>right</code></td><td>0x05</td><td> Push object to the right of its container, not changing its size. </td></tr>
<tr><td><code>center_vertical</code></td><td>0x10</td><td> Place object in the vertical center of its container, not changing its size. </td></tr>
<tr><td><code>fill_vertical</code></td><td>0x70</td><td> Grow the vertical size of the object if needed so it completely fills its container. </td></tr>
<tr><td><code>center_horizontal</code></td><td>0x01</td><td> Place object in the horizontal center of its container, not changing its size. </td></tr>
<tr><td><code>fill_horizontal</code></td><td>0x07</td><td> Grow the horizontal size of the object if needed so it completely fills its container. </td></tr>
<tr><td><code>center</code></td><td>0x11</td><td> Place the object in the center of its container in both the vertical and horizontal axis, not changing its size. </td></tr>
<tr><td><code>fill</code></td><td>0x77</td><td> Grow the horizontal and vertical size of the object if needed so it completely fills its container. </td></tr>
<tr><td><code>clip_vertical</code></td><td>0x80</td><td>
             Additional option that can be set to have the top and/or bottom edges of
             the child clipped to its container's bounds.
             The clip will be based on the vertical gravity: a top gravity will clip the bottom
             edge, a bottom gravity will clip the top edge, and neither will clip both edges.
            </td></tr>
<tr><td><code>clip_horizontal</code></td><td>0x08</td><td>
             Additional option that can be set to have the left and/or right edges of
             the child clipped to its container's bounds.
             The clip will be based on the horizontal gravity: a left gravity will clip the right
             edge, a right gravity will clip the left edge, and neither will clip both edges.
            </td></tr>
<tr><td><code>start</code></td><td>0x00800003</td><td> Push object to the beginning of its container, not changing its size. </td></tr>
<tr><td><code>end</code></td><td>0x00800005</td><td> Push object to the end of its container, not changing its size. </td></tr>
</table>
          <p>This is a private symbol.
          @attr name android.support.v7.gridlayout:layout_gravity
        */
        public static final int GridLayout_Layout_layout_gravity = 11;
        /**
          <p>
          @attr description
           END MarginLayout layoutparams

        The row boundary delimiting the top of the group of cells
        occupied by this view.



          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          <p>This is a private symbol.
          @attr name android.support.v7.gridlayout:layout_row
        */
        public static final int GridLayout_Layout_layout_row = 7;
        /**
          <p>
          @attr description

        The row span: the difference between the bottom and top
        boundaries delimiting the group of cells occupied by this view.
        The default is one.
        See {@link android.widget.GridLayout.Spec}.



          <p>Must be an integer value, such as "<code>100</code>".
<p>This may also be a reference to a resource (in the form
"<code>@[<i>package</i>:]<i>type</i>:<i>name</i></code>") or
theme attribute (in the form
"<code>?[<i>package</i>:][<i>type</i>:]<i>name</i></code>")
containing a value of this type.
          <p>This is a private symbol.
          @attr name android.support.v7.gridlayout:layout_rowSpan
        */
        public static final int GridLayout_Layout_layout_rowSpan = 8;
    };
}