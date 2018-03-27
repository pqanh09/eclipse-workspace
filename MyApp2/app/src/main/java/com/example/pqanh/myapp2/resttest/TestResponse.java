package com.example.pqanh.myapp2.resttest;

import com.comics.shared.vo.ChapterVO;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pqanh on 26-03-18.
 */

public class TestResponse implements Serializable{
    @SerializedName("operation")
    private String operation;
    @SerializedName("objectType")
    private String objectType;
    @SerializedName("uniqueName")
    private String uniqueName;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;
    @SerializedName("multiPart")
    private boolean multiPart;
    @SerializedName("partStatus")
    private List<PartResponseStatus> partStatus;
    @SerializedName("list")
    private List<ChapterVO> list;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isMultiPart() {
        return multiPart;
    }

    public void setMultiPart(boolean multiPart) {
        this.multiPart = multiPart;
    }

    public List<PartResponseStatus> getPartStatus() {
        return partStatus;
    }

    public void setPartStatus(List<PartResponseStatus> partStatus) {
        this.partStatus = partStatus;
    }

    public List<ChapterVO> getList() {
        return list;
    }

    public void setList(List<ChapterVO> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "TestResponse{" +
                "operation='" + operation + '\'' +
                ", objectType='" + objectType + '\'' +
                ", uniqueName='" + uniqueName + '\'' +
                ", message='" + message + '\'' +
                ", success=" + success +
                ", multiPart=" + multiPart +
                ", partStatus=" + partStatus +
                ", list=" + list +
                '}';
    }
}
