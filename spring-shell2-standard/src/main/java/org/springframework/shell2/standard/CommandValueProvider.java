/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.shell2.standard;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.MethodParameter;
import org.springframework.shell2.CompletionContext;
import org.springframework.shell2.CompletionProposal;
import org.springframework.shell2.Shell;
import org.springframework.stereotype.Component;

/**
 * A {@link ValueProvider} that can be used to auto-complete names of shell commands.
 *
 * @author Eric Bottard
 */
@Component
public class CommandValueProvider extends ValueProviderSupport {

	private final Shell shell;

	@Lazy
	@Autowired
	public CommandValueProvider(Shell shell) {
		this.shell = shell;
	}

	@Override
	public List<CompletionProposal> complete(MethodParameter parameter, CompletionContext completionContext, String[] hints) {
		return shell.listCommands().keySet().stream()
			.map(CompletionProposal::new)
			.collect(Collectors.toList());
	}
}