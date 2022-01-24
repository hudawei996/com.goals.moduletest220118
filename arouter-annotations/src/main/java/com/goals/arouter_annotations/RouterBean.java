package com.goals.arouter_annotations;

import javax.lang.model.element.Element;

/**
 * 最终路由要传递的对象
 *
 * 路由路径path的最终实体封装类
 * 例如：app分组中的MainActivity对象，这个对象有更多的属性
 */
public class RouterBean {

    //为了以后的发展
    public enum TypeEnum{
        ACTIVITY
    }

    private TypeEnum typeEnum;//枚举类型：Activity
    private Element element;//类节点 JavaPoet学习的时候，可以拿到很多信息
    private Class<?> myClass;//被注解的Class对象 例如：MainActivity.class
    private String path;//路由地址 例如：/app/MainActivity
    private String group;//路由组 例如：app personal login

    public TypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(TypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public Class<?> getMyClass() {
        return myClass;
    }

    public void setMyClass(Class<?> myClass) {
        this.myClass = myClass;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    private RouterBean(TypeEnum typeEnum, Class<?> myClass, String path, String group) {
        this.typeEnum = typeEnum;
        this.myClass = myClass;
        this.path = path;
        this.group = group;
    }

    //对外暴露
    //对外提供简易版构造方法，主要是为了方便APT生成代码
    public static RouterBean create(TypeEnum type,Class<?> clazz, String path,String group){
        return new RouterBean(type,clazz,path,group);
    }


    //构建者模式相关
    private RouterBean(Builder builder){
        this.typeEnum = builder.typeEnum;
        this.element = builder.element;
        this.myClass = builder.myClass;
        this.path = builder.path;
        this.group = builder.group;
    }

    /**
     * 构建者模式
     */
    public static class Builder{
        private TypeEnum typeEnum;//枚举类型：Activity
        private Element element;//类节点 JavaPoet学习的时候，可以拿到很多信息
        private Class<?> myClass;//被注解的Class对象 例如：MainActivity.class
        private String path;//路由地址 例如：/app/MainActivity
        private String group;//路由组 例如：app personal login

        public Builder addType(TypeEnum typeEnum){
            this.typeEnum = typeEnum;
            return this;
        }

        public Builder addElement(Element element){
            this.element = element;
            return this;
        }

        public Builder addClazz(Class<?> clazz){
            this.myClass = clazz;
            return this;
        }

        public Builder addPath(String path){
            this.path = path;
            return this;
        }

        public Builder addGroup(String group){
            this.group = group;
            return this;
        }

        public RouterBean build(){
            //构建的时候判空，报错
            if (path == null || path.length() == 0){
                throw new IllegalArgumentException("path必填项为空，如：/app/MainActivity");
            }
            return new RouterBean(this);
        }
    }

    @Override
    public String toString() {
        return "RouterBean{" +
                "path='" + path + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}