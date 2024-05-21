package cn.xzhang.boot.mapper;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.core.mapper.BaseMapperPlus;
import cn.xzhang.boot.model.dto.app.AppPageReqDTO;
import cn.xzhang.boot.model.entity.App;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.Objects;

/**
 * @author XiaoZhangCode
 * @description 针对表【app(应用表)】的数据库操作Mapper
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface AppMapper extends BaseMapperPlus<App> {

    default PageResult<App> selectPage(AppPageReqDTO appPageReqDTO) {
        return selectPage(appPageReqDTO, new LambdaQueryWrapper<App>()
                .eq(Objects.nonNull(appPageReqDTO.getAppName()), App::getAppName, appPageReqDTO.getAppName())
                .eq(Objects.nonNull(appPageReqDTO.getAppDesc()), App::getAppDesc, appPageReqDTO.getAppDesc())
                .orderByDesc(App::getCreateTime)

        );
    }

}




