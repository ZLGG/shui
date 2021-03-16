package com.gs.lshly.middleware.mail;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Email implements Serializable {

    //接收方邮件
    private String to;
    //主题
    private String subject;
    //邮件内容
    private String content;
    //内容是否为html
    private boolean isHtml;

}
