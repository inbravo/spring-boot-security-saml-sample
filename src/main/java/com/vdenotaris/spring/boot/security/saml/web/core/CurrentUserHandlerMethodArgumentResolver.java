/*
 * Copyright 2016 Vincenzo De Notaris
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.vdenotaris.spring.boot.security.saml.web.core;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.vdenotaris.spring.boot.security.saml.web.stereotypes.CurrentUser;

@Component
public class CurrentUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

  private static final Logger LOG = LoggerFactory.getLogger(CurrentUserHandlerMethodArgumentResolver.class);

  /**
   * 
   */
  public final boolean supportsParameter(final MethodParameter methodParameter) {

    LOG.info("supportsParameter, methodParameter: " + methodParameter.getParameterName());
    return methodParameter.getParameterAnnotation(CurrentUser.class) != null && methodParameter.getParameterType().equals(User.class);
  }

  /**
   * 
   */
  public final Object resolveArgument(final MethodParameter methodParameter, final ModelAndViewContainer mavContainer,
      final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {

    LOG.info("resolveArgument, methodParameter: " + methodParameter.getParameterName());

    if (this.supportsParameter(methodParameter)) {

      final Principal principal = (Principal) webRequest.getUserPrincipal();
      LOG.info("resolveArgument, principal: " + principal.getName());

      return (User) ((Authentication) principal).getPrincipal();
    } else {
      return WebArgumentResolver.UNRESOLVED;
    }
  }
}
