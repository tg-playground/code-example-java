package com.taogen.demo.springbootcrud.core.i18n.resolver;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Locale Resolver
 *
 * You can get HTTP request locale using LocaleContextHolder.getLocale() everywhere
 * @author Taogen
 */
public class CustomLocaleResolver extends AcceptHeaderLocaleResolver {

    private static final String ACCEPT_LANGUAGE = "Accept-Language";
    private static final List<Locale> LOCALES = Arrays.asList(new Locale("en"), new Locale("zh"));

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        if (StringUtils.isEmpty(request.getHeader(ACCEPT_LANGUAGE))) {
            return Locale.getDefault();
        }
        List<Locale.LanguageRange> list = Locale.LanguageRange.parse(request.getHeader(ACCEPT_LANGUAGE));
        Locale locale = Locale.lookup(list, LOCALES);
        return locale;
    }
}
