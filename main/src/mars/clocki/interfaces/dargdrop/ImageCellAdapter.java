package mars.clocki.interfaces.dargdrop;

import mars.clocki.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * This class is used with a GridView object. It provides a set of
 * ImageCell objects that support dragging and dropping.
 *
 */
public class ImageCellAdapter extends BaseAdapter {

  public ViewGroup parentView = null;
  private Context context;
  private View.OnDragListener mDragListener = null;

  public ImageCellAdapter(Context c) {
    context = c;
    mDragListener = null;
  }

  public ImageCellAdapter(Context c, View.OnDragListener l) {
    context = c;
    mDragListener = l;
  }

  @Override
  public int getCount() {
    return 9;
  }

  @Override
  public Object getItem(int position) {
    return null;
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    parentView = parent;

    ImageCell v = null;
    if (convertView == null) {
      // If it's not recycled, create a new ImageCell.
      v = new ImageCell(context);
      v.setLayoutParams(new GridView.LayoutParams(85, 85));
      v.setScaleType(ImageView.ScaleType.CENTER_CROP);
      v.setPadding(8, 8, 8, 8);
    }
    else {
      v = (ImageCell) convertView;
      v.setImageDrawable(null);     // Important. Otherwise, recycled views show old image.
    }

    v.mCellNumber = position;
    v.mGridView = (GridView) parentView;
    v.mEmpty = true;
    v.setOnDragListener(mDragListener);
    v.setBackgroundColor(context.getResources().getColor(R.color.cell_empty));

    v.setOnTouchListener((View.OnTouchListener) context);
    v.setOnClickListener((View.OnClickListener) context);
    v.setOnLongClickListener((View.OnLongClickListener) context);

    return v;
  }

}