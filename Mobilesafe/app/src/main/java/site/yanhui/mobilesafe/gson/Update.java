package site.yanhui.mobilesafe.gson;

/**
 * Created by Archer on 2017/9/16.
 * <p>
 * 功能描述：
 * GsonBean，用来传入更新的基本信息。Json实体类
 */

public class Update {
    /**
     * versionName : 2.0
     * versionCode : 2
     * versionDes : 2.0版本发布了，狂拽酷炫吊炸天
     * downloadUrl : http://yanhui.site
     */
    private String versionName;
    private String versionCode;
    private String versionDes;
    private String downloadUrl;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionDes() {
        return versionDes;
    }

    public void setVersionDes(String versionDes) {
        this.versionDes = versionDes;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
