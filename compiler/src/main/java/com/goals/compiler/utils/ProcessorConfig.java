package com.goals.compiler.utils;

public interface ProcessorConfig {
    //@ARouter注解的包名+类名
    String AROUTER_PACKAGE = "com.goals.arouter_annotations.ARouter";

    //接受参数的TAG标记
    String OPTIONS = "moduleName";//为了接收每个module的名称
    String APT_PACKAGE = "packageNameForAPT";//为了接收包名（APT存放的包名）
}
