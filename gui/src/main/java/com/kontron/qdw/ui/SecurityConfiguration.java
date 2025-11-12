package com.kontron.qdw.ui;

import jakarta.security.enterprise.identitystore.*;
import jakarta.security.enterprise.authentication.mechanism.http.*;
import jakarta.enterprise.context.*;
import net.sourceforge.jbizmo.commons.server.security.*;

@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(loginPage = "/login.jsf", errorPage = ""))
@DatabaseIdentityStoreDefinition(dataSourceLookup = "${'java:jboss/datasources/QdwDS'}", callerQuery = "#{'select password from user_tab where name =?'}", groupsQuery = "select b.name from user_tab a, role_tab b , user_roles_tab c where a.id = c.user_pk and b.id = c.role_pk and a.name =? and a.active is true", hashAlgorithm = SHA256PasswordHash.class)
@ApplicationScoped
public class SecurityConfiguration {
}