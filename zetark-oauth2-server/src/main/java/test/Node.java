package test;

import java.util.List;

/**
 * Copyright (c) 2017, 北京卡拉卡尔科技股份有限公司 All rights reserved.
 * d
 *
 * @author chao.he@karakal.com.cn
 * @version 1.0
 * @since 2017年11月09日    23:20
 */
public class Node {

        public int Id ;
        public String Key ;
        public String Value ;
        public int Pid ;
        public List<Node> Children;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public int getPid() {
        return Pid;
    }

    public void setPid(int pid) {
        Pid = pid;
    }

    public List<Node> getChildren() {
        return Children;
    }

    public void setChildren(List<Node> children) {
        Children = children;
    }
}
