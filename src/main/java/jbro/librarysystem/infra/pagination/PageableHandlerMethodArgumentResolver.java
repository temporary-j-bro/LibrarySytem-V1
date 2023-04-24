package jbro.librarysystem.infra.pagination;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PageableHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Pageable.class.isAssignableFrom(parameter.getParameterType()) && parameter.hasParameterAnnotation(PageableDefault.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        PageableDefault annotation = parameter.getParameterAnnotation(PageableDefault.class);

        int page = parseIntParameter(webRequest, "page", annotation.page());
        int size = parseIntParameter(webRequest, "size", annotation.size());

        return new PageRequest(page, size);
    }

    private int parseIntParameter(NativeWebRequest webRequest, String parameterName, int defaultValue) {
        String parameterValue = webRequest.getParameter(parameterName);

        if (parameterValue == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(parameterValue);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
