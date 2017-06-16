package fokia.gq.zhifu.model;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by archie on 6/16/17.
 */

public class RecyclerItemClickListener extends RecyclerView.SimpleOnItemTouchListener {
//public class RecyclerItemClickListener extends RecyclerView.OnItemTouchListener {

    private OnItemClickListener clickListener;
    //    private GestureDetector gestureDetector;
    private GestureDetectorCompat gestureDetector; //v4 兼容包中

    public interface OnItemClickListener {
        /**
         * 点击时回调
         *
         * @param view 点击的View
         * @param position 点击的位置
         */
        void onItemClick(View view, int position);

        /**
         * 长点击时回调
         *
         * @param view 点击的View
         * @param position 点击的位置
         */
        void onItemLongClick(View view, int position);
    }

    public RecyclerItemClickListener(final RecyclerView recyclerView, OnItemClickListener listener) {
        this.clickListener = listener;
        gestureDetector = new GestureDetectorCompat(recyclerView.getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public void onLongPress(MotionEvent e) {
                        View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        if (childView != null && clickListener != null) {
                            clickListener.onItemLongClick(childView,
                                    recyclerView.getChildAdapterPosition(childView));
                        }
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }
//
//    @Override
//    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//    }
//
//    @Override
//    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//    }
}