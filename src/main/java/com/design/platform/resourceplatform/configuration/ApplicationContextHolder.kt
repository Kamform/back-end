package com.design.platform.resourceplatform.configuration

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

class ApplicationContextHolder : ApplicationContextAware {

    override fun setApplicationContext(input: ApplicationContext) {
        context = input
    }

    companion object {
        lateinit var context: ApplicationContext
            private set

        fun <T> getBean(type: Class<T>): T {
            return context.getBean(type)
        }
    }
}
