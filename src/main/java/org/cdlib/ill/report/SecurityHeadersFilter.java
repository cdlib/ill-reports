package org.cdlib.ill.report;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@WebFilter("/*")
@Order(2)
public class SecurityHeadersFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Prevents browsers from MIME-sniffing content types, mitigating MIME confusion attacks
        httpResponse.setHeader("X-Content-Type-Options", "nosniff");
        
        // Prevents the page from being displayed in a frame/iframe, mitigating clickjacking attacks
        httpResponse.setHeader("X-Frame-Options", "DENY");
        
        // Forces HTTPS connections for 1 year including all subdomains, prevents downgrade attacks
        httpResponse.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
        
        // CSP allows Bootstrap from maxcdn.bootstrapcdn.com
        // Stricter settings will break bootstrap download
        httpResponse.setHeader("Content-Security-Policy", 
            "default-src 'self'; style-src 'self' 'unsafe-inline' https://maxcdn.bootstrapcdn.com; script-src 'self' 'unsafe-inline'");
        
        chain.doFilter(request, response);
    }
}
