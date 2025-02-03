/*
 * SonarQube :: SCM :: TFVC :: Plugin
 * Copyright (c) SonarSource SA and Microsoft Corporation.  All rights reserved.
 *
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
package org.sonar.plugins.scm.tfs;

import com.google.common.collect.ImmutableList;
import org.sonar.api.CoreProperties;
import org.sonar.api.PropertyType;
import org.sonar.api.batch.InstantiationStrategy;
import org.sonar.api.batch.ScannerSide;
import org.sonar.api.config.Configuration;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import java.util.List;

@InstantiationStrategy(InstantiationStrategy.PER_BATCH)
@ScannerSide()
public class TfsConfiguration {

  private static final String CATEGORY = "TFVC";
  private static final String USERNAME_PROPERTY_KEY = "sonar.tfvc.username";

  @SuppressWarnings("squid:S2068") // false-positive identified hard-coded password
  private static final String PASSWORD_PROPERTY_KEY = "sonar.tfvc.password.secured";
  private static final String COLLECTIONURI_PROPERTY_KEY = "sonar.tfvc.collectionuri";
  private static final String PAT_PROPERTY_KEY = "sonar.tfvc.pat.secured";
  private final Configuration settings;

  public TfsConfiguration(Configuration settings) {
    this.settings = settings;
  }

  public static List<PropertyDefinition> getProperties() {
    return ImmutableList.of(
      PropertyDefinition.builder(PAT_PROPERTY_KEY)
        .name("PersonalAccessToken")
        .description("All scopes PAT when connecting to Azure DevOps Services")
        .type(PropertyType.PASSWORD)
        .onQualifiers(Qualifiers.PROJECT)
        .category(CoreProperties.CATEGORY_SCM)
        .subCategory(CATEGORY)
        .index(0)
        .build(),
      PropertyDefinition.builder(USERNAME_PROPERTY_KEY)
        .name("Username")
        .description("Username when connecting to on-premises Azure DevOps Server")
        .type(PropertyType.STRING)
        .onQualifiers(Qualifiers.PROJECT)
        .category(CoreProperties.CATEGORY_SCM)
        .subCategory(CATEGORY)
        .index(1)
        .build(),
      PropertyDefinition.builder(PASSWORD_PROPERTY_KEY)
        .name("Password")
        .description("Password when connecting to on-premises Azure DevOps Server")
        .type(PropertyType.PASSWORD)
        .onQualifiers(Qualifiers.PROJECT)
        .category(CoreProperties.CATEGORY_SCM)
        .subCategory(CATEGORY)
        .index(2)
        .build(),
      PropertyDefinition.builder(COLLECTIONURI_PROPERTY_KEY)
        .name("CollectionURI")
        .description("Example: - https://dev.azure.com/SampleProject or https://DevOps.domain.local/tfs/SampleCollection")
        .type(PropertyType.STRING)
        .onQualifiers(Qualifiers.PROJECT)
        .category(CoreProperties.CATEGORY_SCM)
        .subCategory(CATEGORY)
        .index(3)
        .build());
  }

  public String username() {
    return settings.get(USERNAME_PROPERTY_KEY).orElse("");
  }

  public String password() {
    return settings.get(PASSWORD_PROPERTY_KEY).orElse("");
  }

  public String collectionUri() {
    return settings.get(COLLECTIONURI_PROPERTY_KEY).orElse("");
  }

  public String pat() {
    return settings.get(PAT_PROPERTY_KEY).orElse("");
  }

}
