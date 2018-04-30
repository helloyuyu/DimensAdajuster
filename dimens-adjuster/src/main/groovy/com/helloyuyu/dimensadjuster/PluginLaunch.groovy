package com.helloyuyu.dimensadjuster

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class PluginLaunch implements Plugin<Project> {
    private static final String PRO_BUILD_TASK_NAME = "preBuild"

    @Override
    void apply(Project project) {
        createAdjustArgsExtension(project)
        AdjusterTask task = createAdjusterTask(project)
        hookAndroidProBuildTask(project, task)
    }

   private void createAdjustArgsExtension(Project project) {
        String defaultBasicDimensXmlFilePath = new File(project.getProjectDir(), "src/main/res/values/dimens.xml").getPath()
        project.getExtensions().create(AdjustArgs.EXT_NAME, AdjustArgs.class, defaultBasicDimensXmlFilePath)
    }

    private AdjusterTask createAdjusterTask(Project project) {
        return project.getTasks().create(AdjusterTask.NAME, AdjusterTask.class)
    }

    private void hookAndroidProBuildTask(Project project, Task adjustTask) {

        project.tasks.each {
            task ->
                if (PRO_BUILD_TASK_NAME == task.name) {
                    task.dependsOn(adjustTask)
                    return
                }
        }
        project.tasks.whenTaskAdded {
            task ->
                if (PRO_BUILD_TASK_NAME == task.name) {
                    task.dependsOn(adjustTask)
                    return
                }
        }
    }
}