package com.base.servlet.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * 웹에서 요청된 URL을 기반으로 요청된 URL이 어떤 Tiles view를 사용해야할지 결정한다.
 * <p>dispatcher-servlet.xml에 정의</p>
 * 
 * @author 조용상
 * @version 1.0
 * 
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.01.27 조용상 최초 작성
 * </pre>
 */
public class TilesViewDecisionInterceptor extends HandlerInterceptorAdapter {
    private String prefix;
    private String suffix;

    /**
     * URL을 만들 때(e.g. jsp의 위치) 이름 앞에 추가되는 접두사를 설정한다.
     * @param prefix URL 접두사
     */
    public void setPrefix(String prefix) {
        this.prefix = (prefix != null ? prefix : "");
    }

    /**
     * URL을 만들 때(e.g. jsp의 확장자) 이름 앞에 추가되는 접미사를 설정한다.
     * @param suffix URL 접미사
     */
    public void setSuffix(String suffix) {
        this.suffix = (suffix != null ? suffix : "");
    }

    private PathMatcher pathMatcher;
    private Map<String, String> urlMap;
    private final Map<String, String> viewNameCache = new ConcurrentHashMap<String, String>();

    /**
     * url 패턴 매칭을 위한 도구를 등록한다.
     * @param pathMatcher url 패턴 매칭 도구
     */
    public void setPathMatcher(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    /**
     * 특정 URL이 Tiles의 어떤 view를 사용해야하는지의 정보를 매핑한다.
     * @param urlMap Tiles view 매핑 정보
     */
    public void setMappings(Map<String, String> urlMap) {
        this.urlMap = urlMap;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && !(modelAndView.getView() instanceof AbstractView)) {
            if (modelAndView.getViewName().startsWith(UrlBasedViewResolver.REDIRECT_URL_PREFIX)
                    || modelAndView.getViewName().startsWith(UrlBasedViewResolver.FORWARD_URL_PREFIX)) {
                return;
            }
            String viewName = viewNameCache.get(request.getRequestURI());

            if (viewName == null) {
                viewName = prefix + modelAndView.getViewName() + suffix;

                if (urlMap != null) {
                    for (Map.Entry<String, String> entry : urlMap.entrySet()) {
                        if (pathMatcher.match(entry.getKey(), request.getRequestURI())) {
                            viewName = entry.getValue() + viewName;
                            viewNameCache.put(request.getRequestURI(), viewName);
                            break;
                        }
                    }
                }
            }
            modelAndView.setViewName(viewName);
        }
    }
}