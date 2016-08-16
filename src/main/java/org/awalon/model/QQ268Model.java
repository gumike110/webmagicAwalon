package org.awalon.model;

import java.util.List;

/**
 * Created by guming on 2016-08-15.
 */
public class QQ268Model {
    private String adVersion;

    private Integer columnId;

    private List<DataList> dataList;

    private String dataVer;

    private String groupId;

    public String getAdVersion() {
        return adVersion;
    }

    public void setAdVersion(String adVersion) {
        this.adVersion = adVersion;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public List<DataList> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataList> dataList) {
        this.dataList = dataList;
    }

    public String getDataVer() {
        return dataVer;
    }

    public void setDataVer(String dataVer) {
        this.dataVer = dataVer;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
