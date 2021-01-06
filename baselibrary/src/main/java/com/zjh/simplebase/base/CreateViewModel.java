package com.zjh.simplebase.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 生成viewModel注解
 *
 * @author zhujianhua
 * on 2021/1/6
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateViewModel {
    Class<? extends BaseViewModel> viewModel();
}
