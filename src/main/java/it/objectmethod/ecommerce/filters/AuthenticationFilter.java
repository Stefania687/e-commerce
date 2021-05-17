//package it.objectmethod.ecommerce.filters;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.config.Order;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import it.objectmethod.ecommerce.services.JWTService;
//
//@Component
//@Order(2)
//public class AuthenticationFilter implements Filter {
//
//	@Autowired
//	JWTService jwtServ;
//
//	private static final Logger logger = LogManager.getLogger(AuthenticationFilter.class);
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest httpReq = (HttpServletRequest) request;
//		HttpServletResponse httpResp = (HttpServletResponse) response;
//		String url = httpReq.getRequestURI();
//		logger.info("AUTH FILTER URL: " + url);
//		if (url.endsWith("login")) {
//			logger.info("RICHIESTA APPROVATA!");
//			chain.doFilter(request, response);
//		} else {
//			String token = httpReq.getHeader("auth-token");
//			if (token != null) {
//				if (jwtServ.checkJWTToken(token)) {
//					logger.info("TOKEN VALIDO RICHIESTA APPROVATA!");
//					chain.doFilter(request, response);
//				} else {
//					logger.info("TOKEN NON VALIDO RICHIESTA BLOCCATA!");
//					httpResp.setStatus(HttpServletResponse.SC_FORBIDDEN);
//				}
//			} else {
//				logger.info("TOKEN NON PRESENTE RICHIESTA BLOCCATA!");
//				httpResp.setStatus(HttpServletResponse.SC_FORBIDDEN);
//			}
//		}
//	}
//
//}
