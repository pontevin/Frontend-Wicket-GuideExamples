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
package org.wicketTutorial.pageparameters;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketTutorial.commons.bootstrap.layout.BootstrapBasePage;


public class HomePage extends BootstrapBasePage {
	private static final long serialVersionUID = 1L;

    public HomePage() {
    	add(new Link<Void>("pageWithIndexParam") {

			@Override
			public void onClick() {
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("foo", "foo");
				pageParameters.add("bar", "bar");
				
				setResponsePage(PageWithParameters.class, pageParameters);
			}
			
		});
    	
    	add(new Link<Void>("pageWithNamedIndexParam") {

			@Override
			public void onClick() {
				PageParameters pageParameters = new PageParameters();
				pageParameters.set(0, "foo");
				pageParameters.set(1, "bar");
				pageParameters.add("baz", "baz");
				
				setResponsePage(PageWithParameters.class, pageParameters);
			}
			
		});
    }
      
}
