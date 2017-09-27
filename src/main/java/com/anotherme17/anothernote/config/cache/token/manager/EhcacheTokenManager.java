package com.anotherme17.anothernote.config.cache.token.manager;

import com.anotherme17.anothernote.config.cache.token.TokenManager;
import com.anotherme17.anothernote.config.cache.token.TokenModel;
import com.anotherme17.anothernote.utils.MyDES;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 二级缓存Token
 */
@Component
public class EhcacheTokenManager implements TokenManager {

    public static final String CACHE_KEY = "token_";
    /**
     * Token 保存的时间
     */
    public static final int TOKEN_SAVE_SECOND = 3600;

    private Ehcache tokenCache;

    public EhcacheTokenManager() {
        CacheManager cacheManager = CacheManager.getInstance();
        tokenCache = cacheManager.getEhcache("tokenCache");
    }

    @Override
    public TokenModel createToken(String userId) {
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);
        Element e = new Element(CACHE_KEY + userId, token);
        e.setTimeToLive(TOKEN_SAVE_SECOND);
        tokenCache.put(e);
        return model;
    }

    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        Element e = tokenCache.get(CACHE_KEY + model.getUserId());
        if (e == null)
            return false;
        String token = (String) e.getObjectValue();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        e.setTimeToLive(TOKEN_SAVE_SECOND);
        return true;
    }

    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }

        authentication = MyDES.decryptBasedDes(authentication);

        String[] param = authentication.split("_");
        if (param.length != 3) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        String userId = param[0];
        String token = param[1];
        String url = param[2];
        if (!TokenModel.url.equals(url))
            return null;
        return new TokenModel(userId, token);
    }

    @Override
    public void deleteToken(String userId) {
        tokenCache.remove(CACHE_KEY + userId);
    }
}
