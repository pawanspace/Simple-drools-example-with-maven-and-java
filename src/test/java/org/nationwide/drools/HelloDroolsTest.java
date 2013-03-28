package org.nationwide.drools;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.nationwide.drools.object.City;
import org.nationwide.drools.object.Person;
import org.nationwide.fields.FieldAccess;

public class HelloDroolsTest {

	private HelloDrools helloDrools;

	@Before
	public void setUp(){
		helloDrools = new HelloDrools();
	}
	
	
	@Test
	public void getFieldAccessToAllFields() throws Exception {
		Person person = new Person("Pawan", new City("Des Moines"));
		FieldAccess result = helloDrools.applyRulesOnFieldAccess(person);

		assertThat(result.getWritableFields(), nullValue());
		assertThat(result.hasAccessToAll(), is(true));

	}
	
	@Test
	public void getFieldAccessToLimitedFields() throws Exception {
		Person person = new Person("Pawan", new City("West Des Moines"));
		FieldAccess result = helloDrools.applyRulesOnFieldAccess(person);

		assertThat(result.getWritableFields().size(), is(3));
		assertThat(result.getWritableFields().iterator().next(), is("field 1"));
		assertThat(result.hasAccessToAll(), is(false));

	}

}
