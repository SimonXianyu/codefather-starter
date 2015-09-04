package io.github.simonxianyu.codefather.starter.util;

/**
 * A pojo for containing configuration used in web.
 * Created by simon on 15/9/4.
 */
public class WebConfig {
    private boolean localFlag;
    private String extResUrl;
    private String serverUrl;

    public boolean isLocalFlag() {
        return localFlag;
    }

    public void setLocalFlag(boolean localFlag) {
        this.localFlag = localFlag;
    }

    public String getExtResUrl() {
        return extResUrl;
    }

    public void setExtResUrl(String extResUrl) {
        this.extResUrl = extResUrl;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
