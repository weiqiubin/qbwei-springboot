package cn.weiqiubin.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class WeChatcontroller {

    @Autowired
    private WxMpService wxMpService;
    @GetMapping("auth")
    public String authorize(){
        String retrunUrl = "www.baidu.com";
        String url = "http://zvxkid.natappfree.cc/info";
        String result = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, retrunUrl);
        return "redirect:"+result;
    }

    @GetMapping("info")
    @ResponseBody
    public String userinfo(@RequestParam("code") String code,
                          @RequestParam("state") String state){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        WxMpUser wxMpUser = new WxMpUser();
        try {
           wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
           wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        } catch (WxErrorException e) {
            log.error(e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        String string = wxMpUser.toString();

        return string+"-"+openId;
    }



}
