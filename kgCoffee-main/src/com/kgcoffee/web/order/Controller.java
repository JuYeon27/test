package com.kgcoffee.web.order;

import java.util.Map;

public interface Controller {
    /**
     * @param paramMap
     * @param model
     * @return viewName
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
