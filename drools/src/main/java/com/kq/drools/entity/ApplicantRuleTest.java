package com.kq.drools.entity;

import org.drools.core.io.impl.ClassPathResource;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.util.Collection;

/**
 * ApplicantRuleTest
 *
 * @author kq
 * @date 2019-03-20
 */
public class ApplicantRuleTest {

    public static void main(String[] args) {

//        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
//        kb.add(new ClassPathResource("com/kq/rules/query.drl"), ResourceType.DRL);
//        Collection<KnowledgePackage> collection = kb.getKnowledgePackages();
//        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
//        knowledgeBase.addKnowledgePackages(collection);
//        StatefulKnowledgeSession statefulSession = knowledgeBase.newStatefulKnowledgeSession();
//
//
//        Applicant applicant = new Applicant( "Mr John Smith", 16 );
//        boolean valid = applicant.isValid() ;
//
//        System.out.println("valid1="+valid);
//
//        statefulSession.execute( applicant );
//        valid = applicant.isValid() ;
//        System.out.println("valid2="+valid);

    }

}
