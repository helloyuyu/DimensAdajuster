package com.helloyuyu.dimenadjuster

class AdjustArgs {
    /**基准dimens*/
    String basicDimensXmlFilePath
    /**基准dimens对应的smallest-width*/
    int basicSW
    /**需要调整生成的 smallest-width列表*/
    int[] adjustSWs
    /**是否运行*/
    boolean adjustEnable

    static final int NOT_SET = 0
    static final String EXT_NAME = "adjustArgs"
    static final String EXT_GLOBAL = "adjustArgsGlobal"
    static final String EXT_KEY_BASIC_SW = "basicSW"
    static final String EXT_KEY_ADJUST_SWS = "adjustSWs"
    static final String EXT_KEY_ADJUST_ENABLE = "adjustEnable"

    AdjustArgs(String basicDimensXmlFilePath) {
        this.basicDimensXmlFilePath = basicDimensXmlFilePath
        this.adjustSWs = null
        this.adjustEnable = true
        this.basicSW = NOT_SET
    }

}