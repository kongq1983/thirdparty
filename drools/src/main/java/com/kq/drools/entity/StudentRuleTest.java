package com.kq.drools.entity;

import org.drools.core.io.impl.ClassPathResource;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * StudentRuleTest
 *
 * @author kq
 * @date 2019-03-20
 */
public class StudentRuleTest {

    public static void main(String[] args) {
        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add(new ClassPathResource("com/kq/rules/query.drl"), ResourceType.DRL);
        Collection<KnowledgePackage> collection = kb.getKnowledgePackages();
        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(collection);
        StatefulKnowledgeSession statefulSession = knowledgeBase.newStatefulKnowledgeSession();
        List<Student> list = getStus();
        for (Student stu : list) {
            statefulSession.insert(stu);
        }
        statefulSession.fireAllRules();
        statefulSession.dispose();
        System.out.println("end....");
    }


    public static List<Student> getStus() {
        List<Student> stus = new ArrayList<>();
        stus.add(new Student("张三", 16, "male"));
        stus.add(new Student("huhu", 18, "male"));
        stus.add(new Student("王五", 32, "male"));
        stus.add(new Student("张红", 23, "female"));
        stus.add(new Student("李四", 35, "male"));
        stus.add(new Student("张小雅", 31, "female"));
        return stus;
    }

}
