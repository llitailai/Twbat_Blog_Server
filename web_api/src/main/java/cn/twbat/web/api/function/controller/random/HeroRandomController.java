package cn.twbat.web.api.function.controller.random;

import cn.twbat.web.api.global.ApiController;
import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.temp.HeroRandom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b></b>
 *
 * @author litailai
 * @date 2023/6/16
 * @email <a href="mailto:darkltl@163.com">darkltl@163.com</a>
 */
@RestController
public class HeroRandomController extends ApiController {

    @GetMapping("/hero/heroRandom")
    public ApiResult<?> heroRandom() {
        return ApiResult.success(((Object)HeroRandom.getInstance().heroRandom()));
    }
}
