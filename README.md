# DimensAdjuster
基于Smallest Width的屏幕适配插件


#### 引入
在顶级build.gradle 加入classpath
```groovy
buildscript {

    dependencies {
        classpath 'com.helloyuyu:dimens-adjuster:1.1.1'
    }
}
```
在module build.gradle中引用插件
```groovy
apply plugin: 'com.helloyuyu.dimensadjuster'
```
项目全局参数配置顶级build.gradle中
```groovy
ext {
    //全局参数设置
    adjustArgsGlobal = [
            //基准的SmallestWidth
            'basicSW'         : 360,
            //要适配的SmallestWidth列表
            'adjustSWs'       : [320, 360, 390, 410],
            //是否开启
            'adjustEnable'    : true,
            //排除的dimen
            'excludes'        : ['sp18'],
            //计算时候遇到浮点数是否四舍五入
            'dimenValueHalfUp': false,
    ]
}
```
全局的参数设置是可选的

Module中如果全局参数没有配置或则想要复写全局的配置的参数设置
```groovy
android{
//...
}
adjustArgs {
    //基准的SmallestWidth
    basicSW 400
    //基准的SmallestWidth对应的dimens.xml文件路径 只有在sourceSet改变了默认路径才需要设置 默认已经赋值了的
    basicDimensXmlFilePath new File("$project.projectDir/src/main/res/values/dimens.xml").getAbsolutePath()
    //要适配的SmallestWidth列表
    adjustSWs = [320, 360, 390, 410]
    //要排除的dimen
    excludes = ['sp18','dp36']
    //编译时候是否开启
    adjustEnable = true
}
```
####参数说明
* basicDimensXmlFilePath 基准的SmallestWidth对应的dimens.xml文件路径 默认值`new File("$project.projectDir/src/main/res/values/dimens.xml").getAbsolutePath()` 一般只有在sourceSet改变了默认路径才需要设置 
* basicSW 基准的SmallestWidth
* adjustSWs 要适配的SmallestWidth列表 这部分暂时只是在测试平台上代码反馈统计，没有找到很好的数据统计来源 有多少奇葩SW的值不能确定 Android的碎片化你懂得,以上只是一些些，实际项目中可以选择 10~20dp梯度列表 如果小伙伴有统计数据请在 issue中补上不胜感激
* excludes 要排除的dimen列表 例如要排除 `<dimen name="dp36">36dp</dimen>` 则在excludes中加上‘dp36’
* adjustEnable 编译的时候是否需要生成适配文件 
* dimenValueHalfUp 在计算的时候dimen数值出现浮点是否四舍五入

####Email
jsxie1024@163.com