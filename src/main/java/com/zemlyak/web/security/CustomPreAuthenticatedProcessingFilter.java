package com.zemlyak.web.security;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class CustomPreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {
    private String principalRequestParameter = "ekbId";
    private String credentialsRequestParameter = "sid";

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
        String principal = httpServletRequest.getParameter(principalRequestParameter);
        System.out.println("[QQQQQQQQQQ] getPreAuthenticatedPrincipal: " + principal);
        return principal;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        System.out.println("[QQQQQQQQQQ] getPreAuthenticatedCredentials");
        return this.credentialsRequestParameter != null?httpServletRequest.getParameter(this.credentialsRequestParameter):"N/A";
    }

    public void setPrincipalRequestParameter(String principalRequestParameter) {
        this.principalRequestParameter = principalRequestParameter;
    }

    public void setCredentialsRequestParameter(String credentialsRequestParameter) {
        this.credentialsRequestParameter = credentialsRequestParameter;
    }
}
