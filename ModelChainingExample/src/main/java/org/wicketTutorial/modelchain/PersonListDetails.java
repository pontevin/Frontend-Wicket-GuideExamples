/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wicketTutorial.modelchain;

import java.util.List;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.wicketTutorial.commons.bootstrap.layout.BootstrapBasePage;

public class PersonListDetails extends BootstrapBasePage {
	private final Form<Person> form;
	private final DropDownChoice<Person> personChoice;
	
	public PersonListDetails(){

		final Model<Person> personChoiceModel = new Model<Person>();
		final ChoiceRenderer<Person> personRenderer = new ChoiceRenderer<Person>() {
			@Override
			public Object getDisplayValue(final Person person) {
				return person.getName() + " " + person.getSurname();
			}
			@Override
			public String getIdValue(final Person person, final int index) {
				return person.getEmail();
			}
		};
		
		personChoice = new DropDownChoice<Person>("persons", personChoiceModel, getPersons(), personRenderer);
		personChoice.add(new FormComponentUpdatingBehavior());
		add(personChoice);		
		
		final CompoundPropertyModel<Person> personFormModel = new CompoundPropertyModel<Person>(personChoiceModel);
		form = new Form<Person>("form", personFormModel);		
		form.add(new TextField<String>("firstName", personFormModel.bind("name")));
		form.add(new TextField<String>("lastName", personFormModel.bind("surname")));
		form.add(new TextField<String>("address"));
		form.add(new TextField<String>("email"));
		add(form);
	}
	
	private static List<Person> getPersons() {
		final List<Person> persons = List.of(
			createJohn(),
			createJill(),
			createTim()
		);
		return persons;
	}

	private static Person createJohn() {
		final Person person = new Person("John", "Smith");
		person.setAddress("Corner street");
		person.setEmail("john.smith@gmail.com");
		return person;
	}

	private static Person createJill() {
		final Person person = new Person("Jill", "Smith");
		person.setAddress("Main street");
		person.setEmail("jill.smith@gmail.com");
		return person;
	}

	private static Person createTim() {
		final Person person = new Person("Tim", "Spencer");
		person.setAddress("Second street");
		person.setEmail("tim.spencer@gmail.com");
		return person;
	}
}
