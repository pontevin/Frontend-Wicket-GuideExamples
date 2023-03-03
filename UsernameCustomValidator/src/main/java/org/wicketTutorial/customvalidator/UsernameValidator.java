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
package org.wicketTutorial.customvalidator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class UsernameValidator implements IValidator<String> {
	List<String> existingUsernames = Arrays.asList("bigJack", "anonymous", "mrSmith");

	public void validate(IValidatable<String> validatable) {
		String chosenUserName = validatable.getValue();
		
		if (existingUsernames.contains(chosenUserName)) {
			ValidationError error = new ValidationError(this);
			error.addKey("username.duplicate");
			error.setVariable("suggestedUserName", 
					validatable.getValue() + new Random().nextInt());
			validatable.error(error);
		}
	}	
}
