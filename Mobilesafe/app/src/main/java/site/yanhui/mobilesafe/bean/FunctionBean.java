package site.yanhui.mobilesafe.bean;

/**
 * Created by Archer on 2017/9/18.
 * <p>
 * 功能描述：
 * 主要功能的项目的列表
 */

public class FunctionBean {
    private  String name; //项目模块的名字
    private  int imageId;//项目模块对应的id

    public FunctionBean(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
