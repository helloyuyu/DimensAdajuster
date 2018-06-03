package com.helloyuyu.dimensadjuster

import org.gradle.api.internal.AbstractTask
import org.gradle.api.tasks.TaskAction

class AdjusterTask extends AbstractTask {

    static final String NAME = "adjustDimens"
    private static final boolean debug = true

    AdjusterTask() {

    }

    @TaskAction
    def run() {
        AdjustArgs adjustArgs = getAdjusterExtension()
        println(adjustArgs.toString())
        checkArguments(adjustArgs)
        if (!adjustArgs.adjustEnable) {
            return
        }
        DimenAdjuster.create(adjustArgs).run()

    }

    AdjustArgs getAdjusterExtension() {
        AdjustArgs adjustArgs = new AdjustArgs(null)
        if (project.rootProject.extensions.extraProperties.has(AdjustArgs.EXT_GLOBAL)) {
            Map<String, Object> globalArgs = project.rootProject.extensions.extraProperties.get(AdjustArgs.EXT_GLOBAL)
            if (globalArgs.containsKey(AdjustArgs.EXT_KEY_BASIC_SW)) {
                adjustArgs.basicSW = globalArgs.get(AdjustArgs.EXT_KEY_BASIC_SW)
            }
            if (globalArgs.containsKey(AdjustArgs.EXT_KEY_ADJUST_SWS)) {
                adjustArgs.adjustSWs = ((List) globalArgs.get(AdjustArgs.EXT_KEY_ADJUST_SWS)).toArray()
            }
            if (globalArgs.containsKey(AdjustArgs.EXT_KEY_ADJUST_ENABLE)) {
                adjustArgs.adjustEnable = globalArgs.get(AdjustArgs.EXT_KEY_ADJUST_ENABLE)
            }
            if (globalArgs.containsKey(AdjustArgs.EXT_KEY_EXCLUDES)) {
                adjustArgs.excludes = ((List) globalArgs.get(AdjustArgs.EXT_KEY_EXCLUDES)).toArray()
            }
            if (globalArgs.containsKey(AdjustArgs.EXT_KEY_DIMEN_VALUE_HALF_UP)) {
                adjustArgs.dimenValueHalfUp = globalArgs.get(AdjustArgs.EXT_KEY_DIMEN_VALUE_HALF_UP)
            }
        }
        AdjustArgs projectAdjustArgs = project.getExtensions().findByType(AdjustArgs.class)

        adjustArgs.basicDimensXmlFilePath = projectAdjustArgs.basicDimensXmlFilePath
        if (!projectAdjustArgs.adjustEnable) {
            adjustArgs.adjustEnable = false
        }
        if (projectAdjustArgs.adjustSWs != null) {
            adjustArgs.adjustSWs = projectAdjustArgs.adjustSWs
        }
        if (projectAdjustArgs.basicSW != AdjustArgs.NOT_SET) {
            adjustArgs.basicSW = projectAdjustArgs.basicSW
        }
        if (projectAdjustArgs.excludes != null) {
            adjustArgs.excludes = projectAdjustArgs.excludes
        }
        return adjustArgs
    }

    static boolean checkArguments(AdjustArgs adjustment) {
        if (adjustment.basicDimensXmlFilePath == null || "" == adjustment.basicDimensXmlFilePath) {
            throw new RuntimeException("'basicDimensXmlFilePath'没有设置")
        }
        if (adjustment.basicSW == AdjustArgs.NOT_SET) {
            throw new RuntimeException("'basicSW' 基准dimens对应的smallest-width没有设置")
        }
    }
}