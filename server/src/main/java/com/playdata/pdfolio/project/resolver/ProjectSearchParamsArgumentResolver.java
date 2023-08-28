package com.playdata.pdfolio.project.resolver;

import com.playdata.pdfolio.domain.request.project.ProjectSearchParameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ProjectSearchParamsArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ProjectSearchParams.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        // page, size, sort(조회순, 하트순, 최신순), category(List<String>)
        Map<String, String> parameterMap = new HashMap<>();

        Enumeration<String> parameterNames = nativeRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            parameterMap.put(name, nativeRequest.getParameter(name));
        }

        // parameterMap = {size=10, page=0, sort=createdAt, category=react,java}
        log.info("parameterMap = {}", parameterMap);

        return ProjectSearchParameter.of(
                parameterMap.getOrDefault("page", "1"),
                parameterMap.getOrDefault("size", "10"),
                parameterMap.getOrDefault("sort", "createdAt"),
                parameterMap.getOrDefault("category", null)
        );
    }

}
