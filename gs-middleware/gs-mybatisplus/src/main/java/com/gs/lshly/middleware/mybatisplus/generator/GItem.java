package com.gs.lshly.middleware.mybatisplus.generator;

import lombok.Getter;
import lombok.Setter;


public class GItem {

    @Getter
    @Setter
    private String templatePath;
    @Getter
    @Setter
    private String packageKey;
    @Setter
    @Getter
    private  String packageName;
    @Getter
    @Setter
    private String pch5;
    @Getter
    @Setter
    private String centerName;
    @Getter
    @Setter
    private String clientName;
    @Getter
    @Setter
    private String tableName;
    @Getter
    @Setter
    private String itemType;
    @Getter
    @Setter
    private String gkey;


    public String getExtDir(){
        String extDir = "/";
        if(this.gkey.equals("dto") || this.gkey.equals("qto") || this.gkey.equals("vo")){
            extDir = "/common/";
        }else if(this.gkey.equals("dao") || this.gkey.equals("daoImpl") || this.gkey.equals("entity") || this.gkey.equals("mapper")){
            extDir = "/repository/";
        }else if(this.gkey.equals("srv") || this.gkey.equals("srvImpl")){
            extDir = "/service/";
        }else if(this.gkey.equals("rpc") || this.gkey.equals("rpcImpl")){
            extDir = "/rpc/";
        }
        return extDir;
    }
}
