package com.evol.plugin.pojo;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class OauthTokenRuleDTO {

    private String ignorePath;

    private List<String> ignorePathList;


    public List<String> getIgnorePathList(){
        if(this.ignorePathList != null){
            return this.ignorePathList;
        }
        if(StringUtils.isBlank(this.ignorePath)){
            ignorePathList = new ArrayList<String>();
        }
        ignorePathList =  Arrays.asList(StringUtils.trim("").split(","));
        return ignorePathList;
    }

}
