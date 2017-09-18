package site.yanhui.mobilesafe.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import site.yanhui.mobilesafe.R;
import site.yanhui.mobilesafe.bean.FunctionBean;

/**
 * Created by Archer on 2017/9/18.
 * <p>
 * 功能描述：
 * HomeActivity的FunctionBean的适配器。
 */


/**
 * 我们自己写一个function适配器，继承自RecyclerView.Adapter指定泛型是FunctionAdapter.ViewHolder（我们自己写的内部类）
 */
public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.ViewHolder> {



    private List<FunctionBean> mFunctionList;

    /**
     * 自己定义一个接口,用与根view的监听事件
     * http://blog.csdn.net/lmj623565791/article/details/45059587
     */
    public interface OnItemClicklistener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClicklistener mOnItemClicklistener;

    public void setOnItemClickLitener(OnItemClicklistener mOnItemClicklistener)
    {
        this.mOnItemClicklistener = mOnItemClicklistener;
    }


    //1.自己写的内部类ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_icon;
        TextView tv_title;
        private Context mContext;

        /**
         * 用这个内部类ViewHolder 获得缓存的相关组件
         *
         * @param itemView RecyclerView子项的最外层布局
         */
        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }

    }


    /**
     * @param mFunctionList 要求传入一个带有funtionBean的List
     */
    public FunctionAdapter(List<FunctionBean> mFunctionList) {
        this.mFunctionList = mFunctionList;
    }

    /**
     * 创建ViewHolder
     *
     * @param parent   父级的引用
     * @param viewType 类型
     * @return 返回要给ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //把functionBean_item的xml解析成view展示
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.functionbean_item, parent, false);

        return new ViewHolder(view);//把view装进viewholder里面返回出去，进行缓存
    }

    /**
     * @param holder  缓存的模型
     * @param position 具体的实例位置
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        FunctionBean functionBean = mFunctionList.get(position);//拿到具体的实例
        holder.iv_icon.setImageResource(functionBean.getImageId());
        holder.tv_title.setText(functionBean.getName());

        //绑定的时候,进行监听,设置图标和文字的监听
        if (mOnItemClicklistener != null) {
            holder.iv_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClicklistener.onItemClick(holder.itemView, pos);
                }
            });

            holder.tv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClicklistener.onItemClick(holder.itemView, pos);
                }
            });



        }
    }



    @Override
    public int getItemCount() {
        return mFunctionList.size();//返回集合的大小
    }


}
