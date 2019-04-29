package com.example.lovermoneyptit.models;

import java.io.Serializable;

public class DealStatis implements Serializable {
    private long dealValue;
    private String groupName;

    public DealStatis() {
    }

    public DealStatis(long dealValue, String groupName) {
        this.dealValue = dealValue;
        this.groupName = groupName;
    }

    public long getDealValue() {
        return dealValue;
    }

    public void setDealValue(long dealValue) {
        this.dealValue = dealValue;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
