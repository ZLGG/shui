package com.gs.lshly.rpc.api.test;


public interface IMQTestRpc {

    String sendOrder();

    String queryOrderById(String orderId);
}
