package org.nationwide.drools;

import java.util.Collection;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.nationwide.fields.FieldAccess;

public class HelloDrools {

	public FieldAccess applyRulesOnFieldAccess(Object person) {
		KnowledgeBuilder kbuilder = initializeKnowledgeBuilder();
		KnowledgeBase knowledgeBase = getKnowledgeBase(kbuilder);
	
		StatefulKnowledgeSession statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();
		
		FieldAccess fieldAccess = new FieldAccess();
		statefulKnowledgeSession.insert(person);
		statefulKnowledgeSession.insert(fieldAccess);
		
		statefulKnowledgeSession.fireAllRules();
		return fieldAccess;
	}

	private KnowledgeBuilder initializeKnowledgeBuilder() {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors());
			return null;
		}
		kbuilder.add(ResourceFactory.newClassPathResource("HelloDrools.drl"), ResourceType.DRL);
		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors());
			return null;
		}
		return kbuilder;
	}

	private KnowledgeBase getKnowledgeBase(KnowledgeBuilder kbuilder) {
		Collection<KnowledgePackage> knowledgePackages = kbuilder.getKnowledgePackages();
		KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
		knowledgeBase.addKnowledgePackages(knowledgePackages);
		return knowledgeBase;
	}
}
