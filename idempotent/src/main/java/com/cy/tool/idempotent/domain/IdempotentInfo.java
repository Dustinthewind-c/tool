package com.cy.tool.idempotent.domain;



import com.cy.tool.idempotent.annotation.Idempotent;
import org.apache.commons.lang3.StringUtils;


import java.lang.reflect.Method;
import java.util.Locale;

/**
 * @author ext.chenyi18
 * @ClassName IdempotentInfo
 * @Description: 幂等性实体
 * @date 2022/7/16 21:36
 */
public class IdempotentInfo {


    public static final int DEFAULT_DURATION = 60;

    /**
     * 前缀
     */
    private String prefix;

    /**
     * id
     */
    private String id;

    /**
     * 过期时间
     */
    private Integer expired;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public String getKey() {
        return String.format("%s_%s", this.prefix, this.id);
    }


    public static final class IdempotentInfoBuilder {

        private String prefix;

        private String id;

        private Integer expired;

        private Method method;

        private Idempotent idempotent;

        private IdempotentInfoBuilder() {
        }

        public static IdempotentInfoBuilder anIdempotentInfo() {
            return new IdempotentInfoBuilder();
        }

        public IdempotentInfoBuilder prefix(String prefix) {
            this.prefix = prefix;
            return this;
        }

        public IdempotentInfoBuilder id(String id) {
            this.id = id;
            return this;
        }

        public IdempotentInfoBuilder expired(Integer expired) {
            this.expired = expired;
            return this;
        }

        public IdempotentInfoBuilder method(Method method) {
            this.method = method;
            return this;
        }

        public IdempotentInfoBuilder idempotent(Idempotent idempotent) {
            this.idempotent = idempotent;
            return this;
        }

        public IdempotentInfo build() throws IllegalArgumentException {
            if (null == idempotent) {
                throw new IllegalArgumentException("idempotent must not be null");
            }
            if (null == method) {
                throw new IllegalArgumentException("method must not be null");
            }

            IdempotentInfo idempotentInfo = new IdempotentInfo();
            String prefix = idempotent.prefix();
            if (StringUtils.isBlank(prefix)) {
                prefix = method.getName().toUpperCase(Locale.ROOT);
            }
            idempotentInfo.setPrefix(prefix);
            idempotentInfo.setId(id);
            idempotentInfo.setExpired(idempotent.expired());
            return idempotentInfo;
        }
    }
}
