package test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2017, 北京卡拉卡尔科技股份有限公司 All rights reserved.
 * t
 *
 * @author chao.he@karakal.com.cn
 * @version 1.0
 * @since 2017年11月09日    22:43
 */
public class ttt {
    public List<Node> GetTree(List<Node> nodes)
    {
        List<Node> root = new ArrayList<Node>();
        for(Node node:nodes){
            if(node.getPid()==-1){
                root.add(node);
            }
        }
        return BuildTree(nodes, root);
    }

    public  List<Node> BuildTree(List<Node> nodes, List<Node> root)
    {
        for (int i = 0; i < root.size(); i++)
        {
            List<Node> children = this.findChildre(root.get(i),nodes);
            if(children!=null&&children.size()>0){
                BuildTree(nodes, children);
            }
            root.get(i).Children = children;
        }
        return root;
    }
   public List<Node> findChildre( Node node, List<Node> roots ){
       List<Node> children = new ArrayList<Node>();
            for(Node node1:roots){
                if(node.getId()==node1.getPid()){
                    children.add(node1);
                }
            }
       return  children;
   }

    public static void main(String [] args){
        String a="[{  \n" +
                "    \"Id\": 1,  \n" +
                "    \"Key\": \"params\",  \n" +
                "    \"Value\": \"\",  \n" +
                "    \"Pid\": -1  \n" +
                "}, {  \n" +
                "    \"Id\": 2,  \n" +
                "    \"Key\": \"method\",  \n" +
                "    \"Value\": \"Post|Get\",  \n" +
                "    \"Pid\": 1  \n" +
                "}, {  \n" +
                "    \"Id\": 3,  \n" +
                "    \"Key\": \"filter\",  \n" +
                "    \"Value\": \"\",  \n" +
                "    \"Pid\": 1  \n" +
                "}, {  \n" +
                "    \"Id\": 4,  \n" +
                "    \"Key\": \"$and\",  \n" +
                "    \"Value\": \"\",  \n" +
                "    \"Pid\": 3  \n" +
                "}, {  \n" +
                "    \"Id\": 5,  \n" +
                "    \"Key\": \"bo:well\",  \n" +
                "    \"Value\": \"@bo:well\",  \n" +
                "    \"Pid\": 4  \n" +
                "}, {  \n" +
                "    \"Id\": 6,  \n" +
                "    \"Key\": \"it:pt\",  \n" +
                "    \"Value\": \"@it:pt\",  \n" +
                "    \"Pid\": 4  \n" +
                "}]  ";
        List<Node> nodes= JSON.parseArray(a,Node.class);
         List<Node> nodes1=  new ttt().GetTree(nodes);
        System.out.println(JSON.toJSONString(nodes1));
    }

}
