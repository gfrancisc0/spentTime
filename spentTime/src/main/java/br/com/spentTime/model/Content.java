
package br.com.spentTime.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("content")
    @Expose
    private List<ContentType> content = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ContentType> getContent() {
        return content;
    }

    public void setContent(List<ContentType> content) {
        this.content = content;
    }

}
