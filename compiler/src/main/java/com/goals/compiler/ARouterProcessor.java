package com.goals.compiler;

import com.goals.arouter_annotations.ARouter;
import com.goals.arouter_annotations.RouterBean;
import com.goals.compiler.utils.ProcessorConfig;
import com.goals.compiler.utils.ProcessorUtils;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
//TODO 注意这两个不同的地方：：：一个有s,一个没有s
//import javax.lang.model.element.Element;
//import javax.lang.model.util.Elements;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * TODO 之前这里为什么不能引入 AbstractProcessor
 * TODO 因为是new module 的 Android Library，然后直接copy了，gradle的配置文件，
 * TODO 这样的是不行的，要重新删掉这个Android Library，然后新建 Java or Kotlin Library才能正确引入
 * <p>
 * 把注解用在kotlin文件上了，只能在java文件中使用（困扰了几天，一直没往这方面想！！针对kotlin文件可能要换种写法，暂未研究，知道的同学可以留言）
 * 并且自动生成的代码路径因项目不同可能不同，比如我的是在
 * <p>
 * 作者：喂_balabala
 * 链接：https://www.jianshu.com/p/d665c2b49483
 */
@AutoService(Processor.class)//启用服务

//允许/支持的注解类型，让注解处理器处理
//@SupportedAnnotationTypes({"com.goals.arouter_annotations.ARouter"})//注解
@SupportedAnnotationTypes({ProcessorConfig.AROUTER_PACKAGE})

@SupportedSourceVersion(SourceVersion.RELEASE_8)//环境的版本


//注解处理器接受的参数
@SupportedOptions({ ProcessorConfig.OPTIONS, ProcessorConfig.APT_PACKAGE,"student"})
public class ARouterProcessor extends AbstractProcessor {

    //操作Element的工具类（类，函数，属性，其实都是Element）
    private Elements elementTool;

    //type(类信息）的工具类，包含用于操作TypeMirror的工具方法
    private Types typesTool;

    //Messager 用来打印 日志相关信息
    private Messager messager;

    //文件生成器，类 资源 等，就是最终要生成的文件 是需要Filer来完成的
    private Filer filer;

    private String options;//各个模块传递多来的模块名 例如：app order personal
    private String aptPackage;//各个模块传递过来的目录 用户统一存放 apt生成的文件


    //仓库一 path
    //Map<"personal",List<RouterBean>>
    private Map<String, List<RouterBean>> mAllPathMap = new HashMap<>();

    //仓库二 Group
    //Map<"personal","ARouter$$Paht$$personal.class">
    private Map<String,String> mAllGroupMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        elementTool = processingEnvironment.getElementUtils();
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();

        //接受app build.gradle中的参数传递
        String value = processingEnvironment.getOptions().get("student");
        options = processingEnvironment.getOptions().get(ProcessorConfig.OPTIONS);
        aptPackage = processingEnvironment.getOptions().get(ProcessorConfig.APT_PACKAGE);
        messager.printMessage(Diagnostic.Kind.NOTE, ">>>>>>> options:" + options);
        messager.printMessage(Diagnostic.Kind.NOTE, ">>>>>>> aptPackage:" + aptPackage);
        if (options != null && aptPackage != null) {
            messager.printMessage(Diagnostic.Kind.NOTE, "APT 环境搭建完成。。。。");
        } else {
            messager.printMessage(Diagnostic.Kind.NOTE, "APT 环境有问题，请检查 options 与 aptPackage 为null...");
        }


        //如果想在注释处理器里抛出异常，可以使用Diagnostic.Kind.ERROR,但是运行会停止
        //messager.printMessage(Diagnostic.Kind.ERROR,">>>>>>>"+value);
        messager.printMessage(Diagnostic.Kind.NOTE,
                "从app build.gradle 中传递来的信息>>>>>>>" + value);
    }

    //在编译的时候干活
    //如果没有在任何一个地方使用，这里是不会工作的
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        // 这个代码已经下毒了
        messager.printMessage(Diagnostic.Kind.NOTE, ">>>>>>> Goals run...");

        if (set.isEmpty()) {
            return false; // 不干活
        }

        //循环？
        //拿到给ARouter注解的"类节点信息"
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ARouter.class);
        for (Element element : elements) { //看代码 就会执行3次
            // 第一次 Element == MainActivity ,2 element == MainActivity1 ,3 element == MainActivity2

            /**
             模块一
             package com.example.helloworld;

             public final class HelloWorld {
             public static void main(String[] args) {
             System.out.println("Hello, JavaPoet!");
             }
             }
             */
            //java 万物皆对象
            //C 万物皆指针


            //1.方法
            MethodSpec mainMethod = MethodSpec.methodBuilder("main")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(void.class)
                    .addParameter(String[].class, "args")

                    // 增加main方法里面的内容
                    .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")

                    .build();

            // 2.类
            TypeSpec testClass = TypeSpec.classBuilder("GoalsTest" + options)
                    .addMethod(mainMethod)
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .build();

            // 3.包
            JavaFile packageff = JavaFile.builder("com.xiangxue.test", testClass).build();

            // 生成文件
            try {
                packageff.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
                messager.printMessage(Diagnostic.Kind.NOTE, "生成Test文件时失败，异常:" + e.getMessage());
            }


            /**
             * 开始真正处理注解信息
             */
            //包信息
            String packageName = elementTool.getPackageOf(element).getQualifiedName().toString();
            //获得简单类名，例如：MainActivity MainActivity2 MainActivity3
            String className = element.getSimpleName().toString();
            messager.printMessage(Diagnostic.Kind.NOTE, "被@ARouter注解的类有：" + className);

            //目标：要生成的文件名称   MainActivity$$$$$$$$$ARouter
            String finalClassName = className + "$$$$$$$$$ARouter";

            /**
             模板：
             public class MainActivity3$$$$$$$$$ARouter {
             public static Class findTargetClass(String path) {
             return path.equals("/app/MainActivity3") ? MainActivity3.class : null;
             }
             }
             */
            ARouter aRouter = element.getAnnotation(ARouter.class);

            //1.方法
            MethodSpec findTargetClass = MethodSpec.methodBuilder("findTargetClass")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(Class.class)
                    .addParameter(String.class, "path")
                    // 方法里面的内容 return path.equals("/app/MainActivity3") ? MainActivity3.class : null;
                    //                    .addStatement("return path.equals($S) ? $T.class : null",aRouter.path(),element)
                    //直接写Element会有坑，需要强转一下
                    .addStatement("return path.equals($S) ? $T.class : null",
                            aRouter.path(),
                            ClassName.get((TypeElement) element))
                    .build();

            //2.类
            TypeSpec myClass = TypeSpec.classBuilder(finalClassName)
                    .addMethod(findTargetClass)
                    .addModifiers(Modifier.PUBLIC)
                    .build();

            //3.包
            JavaFile packagef = JavaFile.builder(packageName, myClass).build();

            //开始生成
            try {
                packagef.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
                messager.printMessage(Diagnostic.Kind.NOTE,
                        "生成" + finalClassName + "文件时失败，异常：" + e.getMessage());
            }









        }


        //告诉注解处理器，false不干活了。true 干完了
        return false;
    }


    /**
     * 校验@ARouter注解的值，如果group未填写就从必填项path中截取数据
     * @param bean
     * @return
     */
    private final boolean checkRouterPath(RouterBean bean){
        String group = bean.getGroup();// "app"   "login"   "personal"
        String path = bean.getPath();// "/app/MainActivity"  "/login/LoginJavaMainActivity"

        //校验
        //@ARouter注解中的path值，必须要以 / 开头 （模仿阿里ARouter规范）
        if (ProcessorUtils.isEmpty(path) || !path.startsWith("/")){
            messager.printMessage(Diagnostic.Kind.NOTE,
                    "@ARouter注解中的path值，必须要以 / 开头");
            return false;
        }

        //比如开发者代码为：path = "/MainActivity",最后一个 / 符号必然在字符串第一位
        if (path.lastIndexOf("/") == 0){
            //架构师定义规范，让开发者遵循
            messager.printMessage(Diagnostic.Kind.ERROR,
                    "@ARouter注解未按规范配置，如：/app/MainActivity");
            return false;
        }

        //从第一个 / 到第二个 / 中间截取，如：/app/MainActivity 截取出 app,login,personal 作为Group
        String finalGroup = path.substring(1,path.indexOf("/",1));
        // app,order,personal == options

        //@ARouter注解中的group有赋值情况
        if (!ProcessorUtils.isEmpty(group) && !group.equals(options)){
            //架构师定义规范，让开发者遵循
            messager.printMessage(Diagnostic.Kind.ERROR,
                    "@ARouter注解中的group值必须和子模块名一致");
            return false;
        }else {
            bean.setGroup(finalGroup);
        }

        //如果真的返回true  RouterBean.group  xxx 赋值成功 没有问题
        return true;
    }
}