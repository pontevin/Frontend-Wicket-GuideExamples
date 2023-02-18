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
package org.wicketTutorial.mountedpages;

import org.wicketTutorial.commons.bootstrap.BootstrapApp;
import org.wicketTutorial.mountedpages.subPackage.StatefulPackageMount;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see org.tutorialWicket.mountedpages.Start#main(String[])
 */
public class WicketApplication extends BootstrapApp
{    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		mountPage("/pageMount", MountedPage.class);
		mountPage("/pageMount/#{foo}/otherSegm", MountedPageWithPlaceholder.class);
		// Mounting only for one class from package
		mountPackage("/packageMount", StatefulPackageMount.class);
	}
}
