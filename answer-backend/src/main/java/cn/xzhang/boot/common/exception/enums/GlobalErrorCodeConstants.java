package cn.xzhang.boot.common.exception.enums;

import cn.xzhang.boot.common.exception.ErrorCode;

/**
 * 全局错误码枚举
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface GlobalErrorCodeConstants {


    // ===== 成功与失败的基本乐章 =====
    ErrorCode SUCCESS = new ErrorCode(0, "完美！一切顺利！");
    ErrorCode FAIL = new ErrorCode(1, "哎呀，出错了呢！");

    // ========== 客户端小差错集锦 ==========
    ErrorCode BAD_REQUEST = new ErrorCode(400, "调皮的参数，快回去检查一下吧！");
    ErrorCode BAD_REQUEST_PARAMS_ERROR = new ErrorCode(400, "参数有点淘气了，{} 需要你哄一哄！");
    ErrorCode UNAUTHORIZED = new ErrorCode(401, "迷路了吗？先登录再说！");
    ErrorCode FORBIDDEN = new ErrorCode(403, "嘘～这里可是禁地哦，没有通行证的！");
    ErrorCode NOT_FOUND = new ErrorCode(404, "找什么呢？这里好像什么也没有...");
    ErrorCode METHOD_NOT_ALLOWED = new ErrorCode(405, "方法不对头，换个姿势试试？");
    ErrorCode LOCKED = new ErrorCode(423, "稍安勿躁，让系统喘口气再试！");
    ErrorCode TOO_MANY_REQUESTS = new ErrorCode(429, "慢点来，别急，喘口气再继续！");
    ErrorCode BAD_REQUEST_PARAMS = new ErrorCode(400, "空空如也，参数不填怎么行？");
    ErrorCode BAD_REQUEST_PARAM_ERROR = new ErrorCode(400, "参数唱反调了，需要调教一番！");

    // ========== 服务端小坎坷 ==========
    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500, "哎哟，系统扭到腰了，稍等我揉一揉...");

    // ========== 自定义趣味乐章 ==========
    ErrorCode UNKNOWN = new ErrorCode(999, "这...这是什么神秘代码？！");
    ErrorCode PASSWORD_NOT_MATCH = new ErrorCode(4001, "左右互搏？密码两边得一致哦！");
    ErrorCode PASSWORD_LENGTH_NOT_ENOUGH = new ErrorCode(4002, "短了点哦，密码得至少8位字符长呢！");

    ErrorCode USER_NAME_REPEAT = new ErrorCode(4091, "这用户名已经名花有主啦！换一个呗？");

    ErrorCode USER_REGISTER_FAIL = new ErrorCode(4221, "注册翻车了，咱们重新发动一次吧！");

    ErrorCode USER_NOT_EXIST = new ErrorCode(4041, "这位大侠，您在江湖上还没留名呢...");

    ErrorCode USER_NOT_NORMAL = new ErrorCode(4031, "账号冬眠中，请解冻后再来闯荡江湖吧！");

    ErrorCode ADD_FAIL = new ErrorCode(4222, "新增计划泡汤了，不如我们再接再厉？");
    ErrorCode UPDATE_FAIL = new ErrorCode(4223, "更新翻车现场，调整策略再次出发！");
    ErrorCode DELETE_FAIL = new ErrorCode(4224, "删删减减，这次没成功，不妨再试一次？");

    ErrorCode REVIEW_REPEAT = new ErrorCode(4225, "审核别太着急，一次就够啦！");

    ErrorCode APP_NOT_EXIST = new ErrorCode(4042, "应用玩失踪？确认一下它是否存在吧！");
    ErrorCode APP_REVIEW_FAIL = new ErrorCode(4226, "审核之路多坎坷，这次没过，咱们再接再厉！");
    ErrorCode APP_STRATEGY_NOT_FOUND = new ErrorCode(4227, "哎呀，应用的小脑瓜里没找到策略，迷路了吗？快去检查一下配置吧！");
    ErrorCode APP_REVIEW_NOT_PASS = new ErrorCode(4228, "应用审核小哥还没点头呢，再耐心等等，精彩即将解锁！");
    ErrorCode OPERATION_ERROR = new ErrorCode(4229, "这次操作有点小插曲，别急，深呼吸，再来一次！");
    ErrorCode SCORE_ERROR = new ErrorCode(4230, "评分小火箭暂时熄火了，稍息片刻，我们继续冲向高分！");
    ErrorCode QUESTION_EXIST = new ErrorCode(4225, "这道题目已经在这儿安家啦！不如发挥创意，给咱们来个独家首映吧！");

    /**
     * 是否为服务端错误，参考 HTTP 5XX 错误码段
     *
     * @param code 错误码
     * @return 是否
     */
    static boolean isServerErrorCode(Integer code) {
        return code != null
                && code >= INTERNAL_SERVER_ERROR.getCode() && code <= INTERNAL_SERVER_ERROR.getCode() + 99;
    }


}
