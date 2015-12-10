package com.zemlyak.web.security;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class CustomPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
    private String principalRequestParameter = "ekbId";
    private String credentialsRequestParameter = "sid";

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
        String principal = httpServletRequest.getParameter(principalRequestParameter);
        this.logger.debug("Principal come: " + principal);
        return principal;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        String credentials = httpServletRequest.getParameter(this.credentialsRequestParameter);
        this.logger.debug("Credentials come: " + credentials);
        return this.credentialsRequestParameter != null ? credentials : "N/A";
    }

    public void setPrincipalRequestParameter(String principalRequestParameter) {
        this.principalRequestParameter = principalRequestParameter;
    }

    public void setCredentialsRequestParameter(String credentialsRequestParameter) {
        this.credentialsRequestParameter = credentialsRequestParameter;
    }
}
