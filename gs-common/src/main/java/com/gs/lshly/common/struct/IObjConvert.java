package com.gs.lshly.common.struct;

public interface IObjConvert<T,S> {
    public abstract void cnv(T target, S source);
}
