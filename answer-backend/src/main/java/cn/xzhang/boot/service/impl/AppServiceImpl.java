package cn.xzhang.boot.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.xzhang.boot.common.exception.ServiceException;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.mapper.AppMapper;
import cn.xzhang.boot.model.dto.app.AppAddReqDTO;
import cn.xzhang.boot.model.dto.app.AppPageReqDTO;
import cn.xzhang.boot.model.dto.app.AppUpdateReqDTO;
import cn.xzhang.boot.model.entity.App;
import cn.xzhang.boot.model.vo.app.AppSimpleVo;
import cn.xzhang.boot.model.vo.app.AppVo;
import cn.xzhang.boot.service.AppService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.*;
import static cn.xzhang.boot.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 针对表【app(应用表)】的数据库操作Service实现
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App> implements AppService {

    @Resource
    private AppMapper appMapper;

    /**
     * 添加新应用
     *
     * @param appReqDTO 应用信息请求DTO
     * @return 添加成功返回应用id
     */
    @Override
    public long addApp(AppAddReqDTO appReqDTO) {
        // 校验输入参数
        if (StringUtils.isAnyBlank(appReqDTO.getAppName(), appReqDTO.getAppDesc(), appReqDTO.getAppIcon())) {
            throw exception(BAD_REQUEST);
        }
        App app = new App();
        BeanUtil.copyProperties(appReqDTO, app);

        // 获取当前登录用户
        app.setUserId(StpUtil.getLoginIdAsLong());


        if (!this.save(app)) {
            throw exception(ADD_FAIL);
        }
        return app.getId();
    }

    /**
     * 更新应用信息
     *
     * @param appReqDTO 应用信息更新请求DTO
     * @return 更新成功返回true
     */
    @Override
    public boolean updateApp(AppUpdateReqDTO appReqDTO) {
        // 校验输入参数
        if (StringUtils.isAnyBlank(appReqDTO.getAppName(), appReqDTO.getAppDesc(), appReqDTO.getAppIcon())) {
            throw exception(BAD_REQUEST);
        }
        if (appReqDTO.getId() == null) {
            throw exception(BAD_REQUEST);
        }
        App app = new App();
        BeanUtil.copyProperties(appReqDTO, app);
        boolean b = this.updateById(app);
        if (!b) {
            throw exception(UPDATE_FAIL);
        }
        return true;
    }

    /**
     * 删除应用
     *
     * @param id 应用id
     * @return 删除成功返回true
     */
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean deleteApp(Long id) {
        if (id == null) {
            throw exception(BAD_REQUEST);
        }
        boolean b = this.removeById(id);
        if (!b) {
            throw exception(DELETE_FAIL);
        }
        return true;
    }

    /**
     * 将应用对象转换为应用VO对象
     *
     * @param app 应用对象
     * @return 返回应用VO对象
     */
    @Override
    public AppSimpleVo getSimpleAppVO(App app) {
        if (app == null) {
            return null;
        }
        AppSimpleVo appSimpleVo = new AppSimpleVo();
        BeanUtil.copyProperties(app, appSimpleVo);
        return appSimpleVo;
    }

    /**
     * 获取应用分页信息
     *
     * @param appPageReqDTO 应用分页请求DTO
     * @return 返回应用分页结果
     */
    @Override
    public PageResult<AppVo> getAppPage(AppPageReqDTO appPageReqDTO) {
        PageResult<App> pageResult = appMapper.selectPage(appPageReqDTO);
        if (pageResult.getList() == null) {
            return PageResult.empty();
        }
        List<AppVo> appVos = pageResult.getList().stream().map(app -> {
            AppVo appVo = new AppVo();
            BeanUtil.copyProperties(app, appVo);
            return appVo;
        }).collect(Collectors.toList());
        return new PageResult<>(appVos, pageResult.getTotal());
    }

    @Override
    public AppVo getAppVO(App app) {
        if (app != null) {
            AppVo appVo = new AppVo();
            BeanUtil.copyProperties(app, appVo);
            return appVo;
        }
        return null;
    }


}



