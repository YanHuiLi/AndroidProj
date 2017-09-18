package site.yanhui.mobilesafe.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Archer on 2017/9/18.
 * <p>
 * 功能描述： 自定义空间，实现移动字幕的（跑马灯的效果）
 * 能够一直获取焦点的textView
 */

public class FocusTextView extends android.support.v7.widget.AppCompatTextView {

    /**
     * 通过java代码创建控件
     * @param context  上下文环境
     */
    public FocusTextView(Context context) {
        super(context);
    }

    /**
     *由系统调用
     * @param context 上下文环境
     * @param attrs  属性集合
     */
    public FocusTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     *系统调用
     * @param context 上下文环境
     * @param attrs   自定义属性集合
     * @param defStyleAttr 指定当前的样式
     */
    public FocusTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 重写获取焦点的方法，有系统同意调用
     * @return true
     */
    @Override
    public boolean isFocused() {
        return true;
    }
}
