package com.twbat.blog.center.permission.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.twbat.blog.common.util.util.config.Constant;
import com.twbat.blog.common.util.util.config.ProjectConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


/**
 * @author darkltl
 * @className PermissionConfig
 * @email darkltl@163.com
 * @date 2021/7/22 - 16:08
 * @description 权限MVC配置
 */
@Configuration
public class PermissionConfig implements WebMvcConfigurer {

    @Resource
    private PermissionInterceptor permissionInterceptor;

    @Resource
    private JwtInterceptor jwtInterceptor;

    @Resource
    private WebUserSafeInterceptor webUserSafeInterceptor;

    @Resource
    private CorsInterceptor corsInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(Constant.RESOURCE_PREFIX.getCode() + "/**").addResourceLocations("file:" + ProjectConfig.getProfile() + "/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
        registry.addInterceptor(permissionInterceptor).excludePathPatterns("/static/*").excludePathPatterns("/templates/*")
                .excludePathPatterns("/error").addPathPatterns("/**");
        registry.addInterceptor(jwtInterceptor).excludePathPatterns("/static/*").excludePathPatterns("/error").addPathPatterns("/**");
        registry.addInterceptor(webUserSafeInterceptor).excludePathPatterns("/static/*").excludePathPatterns("/error").addPathPatterns("/api/user/operate/record");
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converters.clear();
        //自定义配置...
        FastJsonConfig config = new FastJsonConfig();
        JSON.defaultTimeZone = TimeZone.getTimeZone("GMT+08");
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        config.setCharset(StandardCharsets.UTF_8);
        config.setSerializerFeatures(
                //输出类名
                SerializerFeature.WriteClassName,
                //输出map中value为null的数据
                SerializerFeature.WriteMapNullValue,
                //json格式化
                SerializerFeature.PrettyFormat,
                //输出空list为[]，而不是null
                SerializerFeature.WriteNullListAsEmpty,
                //输出空string为""，而不是null
                SerializerFeature.WriteNullStringAsEmpty
        );
        converter.setFastJsonConfig(config);
        // config.set...
        // converter.setFastJsonConfig(config);

        // 高版本无需配置，低版本不配置报错：Content-Type cannot contain wildcard type '*'
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastMediaTypes.add(MediaType.TEXT_PLAIN);
        fastMediaTypes.add(MediaType.ALL);
        converter.setSupportedMediaTypes(fastMediaTypes);

        converters.add(0, converter);
    }

}
