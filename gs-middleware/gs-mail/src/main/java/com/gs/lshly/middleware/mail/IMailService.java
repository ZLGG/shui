package com.gs.lshly.middleware.mail;

import cn.hutool.extra.mail.MailAccount;

public interface IMailService {

    void send(Email mail);

    void send(MailAccount account, Email mail);
}
