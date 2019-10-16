package com.bootdo.common.config;

import com.alibaba.fastjson.JSON;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 *
 * @author chen
 * @date 2017/9/4
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Component
public class ApplicationContextRegister implements ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(ApplicationContextRegister.class);
    private static ApplicationContext APPLICATION_CONTEXT;
    /**
     * 设置spring上下文
     * @param applicationContext spring上下文
     * @throws BeansException
     * */
    @Override  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("ApplicationContext registed-->{}", applicationContext);
        APPLICATION_CONTEXT = applicationContext;
        MenuService bean = applicationContext.getBean(MenuService.class);
        ShiroFilterFactoryBean shiroFilterFactoryBean = applicationContext.getBean(ShiroFilterFactoryBean.class);
        Map<String, String> filterChainDefinitionMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();
        List<MenuDO> list = bean.list(null);
        filterChainDefinitionMap.put("/xjbg/room/roomMng","perms[xjbg:room:roomMng]");
//        for (MenuDO menuDO : list) {
//            String perms = menuDO.getPerms();
//            String url = menuDO.getUrl();
//            if(!StringUtils.isBlank(url) &&!StringUtils.isBlank(perms)){
//                if(!url.startsWith("/")){
//                    url = "/"+url;
//                }
//                filterChainDefinitionMap.put(url,"perms["+perms+"]");
//            }
//        }
        logger.info("filterChainDefinitionMap===>"+ JSON.toJSONString(filterChainDefinitionMap));
    }

    /**
     * 获取容器
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    /**
     * 获取容器对象
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> type) {
        return APPLICATION_CONTEXT.getBean(type);
    }
}