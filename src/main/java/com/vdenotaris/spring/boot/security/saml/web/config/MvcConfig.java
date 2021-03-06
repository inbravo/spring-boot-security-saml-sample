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

package com.vdenotaris.spring.boot.security.saml.web.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.vdenotaris.spring.boot.security.saml.web.core.CurrentUserHandlerMethodArgumentResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

  private static final Logger LOG = LoggerFactory.getLogger(MvcConfig.class);

  @Autowired
  CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver;

  @Override
  public void addViewControllers(final ViewControllerRegistry registry) {

    LOG.info("addViewControllers");
    registry.addViewController("/").setViewName("index");
    registry.addViewController("/error").setViewName("error");
  }

  @Override
  public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {

    LOG.info("addArgumentResolvers");
    argumentResolvers.add(currentUserHandlerMethodArgumentResolver);
  }
}
