package cn.xzhang.boot.service;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.model.dto.app.AppAddReqDTO;
import cn.xzhang.boot.model.dto.app.AppPageReqDTO;
import cn.xzhang.boot.model.dto.app.AppUpdateReqDTO;
import cn.xzhang.boot.model.entity.App;
import cn.xzhang.boot.model.vo.app.AppSimpleVo;
import cn.xzhang.boot.model.vo.app.AppVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @description 针对表【app(应用表)】的数据库操作Service
 */
public interface AppService extends IService<App> {

    /**
     * 添加应用信息。
     *
     * @param appReqDTO 应用添加请求数据传输对象，包含要添加的应用的所有必要信息。
     * @return 返回添加操作的自增ID，用于标识此次添加操作。
     */
    long addApp(AppAddReqDTO appReqDTO);

    /**
     * 更新应用信息。
     *
     * @param appReqDTO 包含应用更新信息的请求DTO（数据传输对象）。该对象应包含需要更新的应用属性。
     * @return boolean 返回true如果应用信息更新成功，返回false如果更新失败或遇到错误。
     */
    boolean updateApp(AppUpdateReqDTO appReqDTO);

    /**
     * 删除应用
     *
     * @param id 应用的唯一标识符
     * @return boolean 返回操作是否成功。true表示删除成功，false表示删除失败。
     */
    boolean deleteApp(Long id);

    /**
     * 根据App对象获取对应的AppVo对象。
     *
     * @param app 一个包含应用信息的App对象。
     * @return 返回一个包含应用信息的AppVo对象。
     */
    AppSimpleVo getSimpleAppVO(App app);

    /**
     * 获取应用页面信息
     *
     * @param appPageReqDTO 包含分页和筛选条件的应用请求数据传输对象
     * @return 返回应用页面的结果，包括应用列表和分页信息
     */
    PageResult<AppVo> getAppPage(AppPageReqDTO appPageReqDTO);

    /**
     * 根据App对象获取对应的AppVo对象。
     *
     * @param app 一个包含应用信息的App对象。
     * @return 返回一个包含应用信息的AppVo对象。
     */
    AppVo getAppVO(App app);
}
