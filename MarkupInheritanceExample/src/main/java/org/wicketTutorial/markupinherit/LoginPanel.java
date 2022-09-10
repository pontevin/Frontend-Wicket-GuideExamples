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
package org.wicketTutorial.markupinherit;

import org.apache.logging.log4j.util.Strings;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

public class LoginPanel extends Panel {
	public LoginPanel(String id) {
		super(id);		
		initialize();
	}
	
	private void initialize(){
		final Form<Void> loginForm = new LoginForm("loginForm");
		add(loginForm);
	}
	
	public class LoginForm extends Form<Void> {
		private String username;
		private String password;
		private String loginStatus;
		
		public LoginForm(String id) {
			super(id);
			
			setDefaultModel(new CompoundPropertyModel<LoginForm>(this));
			
			add(new TextField<String>("username"));
			add(new PasswordTextField("password"));
			final Label statusLabel = new Label("loginStatus", () -> loginStatus) {
				@Override
				protected void onConfigure() {
					setVisible(Strings.isNotBlank(loginStatus));
					super.onConfigure();
				}
			};
			add(statusLabel);
		}

		public final void onSubmit() {			
			if(username.equals("test") && password.equals("test"))
				loginStatus = "Congratulations!";
			else
				loginStatus = "Wrong username or password !";			
		}
	}
}
