package com.fnd.psi.utils;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fnd.psi.dto.user.PsiUserInfoDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: chenchaohai
 * @Date: 2023-09-22 14:28
 * @Desc:
 * @See:
 */
public class TokenUtil {

    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    /**
     * token秘钥
     */
    private static final String TOKEN_SECRET = "MTIzNDU2Nzg5Zm5i";

    /**
     * 私钥和加密算法
     */
    private static Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

    /**
     * 生成签名
     * @return 生成的token
     */
    public static String sign(PsiUserInfoDTO psiUserInfoDTO) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");

            PsiUserInfoDTO tokenInfo = CopyBeanUtils.convert(psiUserInfoDTO, PsiUserInfoDTO.class);
            tokenInfo.setRoles(null);
            tokenInfo.setPermissions(null);
            tokenInfo.setMenuData(null);

            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("token", JSONUtil.toJsonStr(tokenInfo))
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     * @param request 需要校验的token
     * @return 校验是否成功
     */
    public static String verify(HttpServletRequest request){
        Enumeration<String> headers = request.getHeaders("Authorization");
        String authHeaderValue = null;
        while (headers.hasMoreElements()) { // typically there is only one (most servers enforce that)
            String value = headers.nextElement();

            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(value);

            authHeaderValue = jwt.getToken();
        }

        return authHeaderValue;
    }


    public static String getTokenInfo(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            jwt = jwtVerifier.verify(JWT.decode(token));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String tokenInfo = jwt.getClaim("token").asString();
        return tokenInfo;
    }
}
