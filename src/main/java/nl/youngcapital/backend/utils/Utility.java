//package nl.youngcapital.backend.utils;
//
//import nl.youngcapital.backend.security.CustomerOAuth2User;
//import org.springframework.security.authentication.RememberMeAuthenticationToken;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class Utility {
//
//    public static String getEmailOfAuthenticatedCustomer(HttpServletRequest request) {
//        Object principal = request.getUserPrincipal();
//        if (principal == null) return null;
//
//        String customerEmail = null;
//
//        if (principal instanceof UsernamePasswordAuthenticationToken
//                || principal instanceof RememberMeAuthenticationToken) {
//            customerEmail = request.getUserPrincipal().getName();
//        } else if (principal instanceof OAuth2AuthenticationToken) {
//            OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) principal;
//            CustomerOAuth2User oauth2User = (CustomerOAuth2User) oauth2Token.getPrincipal();
//            customerEmail = oauth2User.getEmail();
//        }
//
//        return customerEmail;
//    }
//}
