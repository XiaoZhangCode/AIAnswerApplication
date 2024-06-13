declare namespace API {
  type AppAddReqDTO = {
    /** 应用名 */
    appName: string;
    /** 应用描述 */
    appDesc: string;
    /** 应用图标 */
    appIcon: string;
    /** 应用类型（0-得分类，1-测评类） */
    appType?: string;
    /** 评分策略（0-自定义，1-AI） */
    scoringStrategy?: string;
  };

  type AppPageReqDTO = {
    /** 页码，从 1 开始 */
    pageNo: number;
    /** 每页条数，最大值为 100 */
    pageSize: number;
    /** id */
    id?: number;
    /** 应用名 */
    appName?: string;
    /** 应用描述 */
    appDesc?: string;
    /** 应用类型（0-得分类，1-测评类） */
    appType?: string;
    /** 评分策略（0-自定义，1-AI） */
    scoringStrategy?: string;
    /** 审核状态：0-待审核, 1-通过, 2-拒绝 */
    reviewStatus?: number;
    /** 创建用户 id */
    userId?: number;
  };

  type AppSimpleVo = {
    /** id */
    id?: number;
    /** 应用名 */
    appName?: string;
    /** 应用描述 */
    appDesc: string;
    /** 应用图标 */
    appIcon: string;
    /** 应用类型（0-得分类，1-测评类） */
    appType?: number;
    /** 评分策略（0-自定义，1-AI） */
    scoringStrategy?: number;
    /** 创建用户 id */
    userId?: number;
    /** 创建时间 */
    createTime?: string;
  };

  type AppUpdateReqDTO = {
    /** 应用名 */
    appName: string;
    /** 应用描述 */
    appDesc: string;
    /** 应用图标 */
    appIcon: string;
    /** 应用类型（0-得分类，1-测评类） */
    appType?: string;
    /** 评分策略（0-自定义，1-AI） */
    scoringStrategy?: string;
    /** id */
    id: number;
  };

  type AppVo = {
    /** id */
    id?: number;
    /** 应用名 */
    appName?: string;
    /** 应用描述 */
    appDesc: string;
    /** 应用图标 */
    appIcon: string;
    /** 应用类型（0-得分类，1-测评类） */
    appType?: string;
    /** 评分策略（0-自定义，1-AI） */
    scoringStrategy?: string;
    /** 审核状态：0-待审核, 1-通过, 2-拒绝 */
    reviewStatus?: number;
    /** 审核信息 */
    reviewMessage: string;
    /** 审核人 id */
    reviewerId: number;
    /** 审核时间 */
    reviewTime: string;
    /** 创建用户 id */
    userId?: number;
    /** 创建时间 */
    createTime?: string;
    /** 更新时间 */
    updateTime?: string;
  };

  type CommonResultAppSimpleVo = {
    /** 业务状态 */
    code?: number;
    data?: AppSimpleVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultAppVo = {
    /** 业务状态 */
    code?: number;
    data?: AppVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultBoolean = {
    /** 业务状态 */
    code?: number;
    /** 返回数据 */
    data?: boolean;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultLoginUserVO = {
    /** 业务状态 */
    code?: number;
    data?: LoginUserVO;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultLong = {
    /** 业务状态 */
    code?: number;
    /** 返回数据 */
    data?: number;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultPageResultAppSimpleVo = {
    /** 业务状态 */
    code?: number;
    data?: PageResultAppSimpleVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultPageResultAppVo = {
    /** 业务状态 */
    code?: number;
    data?: PageResultAppVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultPageResultQuestionVo = {
    /** 业务状态 */
    code?: number;
    data?: PageResultQuestionVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultPageResultScoringResultVo = {
    /** 业务状态 */
    code?: number;
    data?: PageResultScoringResultVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultPageResultUserAnswerVo = {
    /** 业务状态 */
    code?: number;
    data?: PageResultUserAnswerVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultPageResultUserVo = {
    /** 业务状态 */
    code?: number;
    data?: PageResultUserVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultQuestionSimpleVo = {
    /** 业务状态 */
    code?: number;
    data?: QuestionSimpleVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultQuestionVo = {
    /** 业务状态 */
    code?: number;
    data?: QuestionVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultScoringResultSimpleVo = {
    /** 业务状态 */
    code?: number;
    data?: ScoringResultSimpleVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultScoringResultVo = {
    /** 业务状态 */
    code?: number;
    data?: ScoringResultVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultString = {
    /** 业务状态 */
    code?: number;
    /** 返回数据 */
    data?: string;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultUserAnswerSimpleVo = {
    /** 业务状态 */
    code?: number;
    data?: UserAnswerSimpleVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultUserAnswerVo = {
    /** 业务状态 */
    code?: number;
    data?: UserAnswerVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultUserSimpleVo = {
    /** 业务状态 */
    code?: number;
    data?: UserSimpleVo;
    /** 消息提示 */
    msg?: string;
  };

  type CommonResultUserVo = {
    /** 业务状态 */
    code?: number;
    data?: UserVo;
    /** 消息提示 */
    msg?: string;
  };

  type deleteAppParams = {
    /** 应用ID */
    id: number;
  };

  type deleteQuestionParams = {
    /** 题目ID */
    id: number;
  };

  type deleteScoringResultParams = {
    /** 评分结果ID */
    id: number;
  };

  type deleteUserAnswerParams = {
    /** 答题记录ID */
    id: number;
  };

  type deleteUserParams = {
    /** 用户ID */
    id: number;
  };

  type getAppPageParams = {
    appPageReqDTO: AppPageReqDTO;
  };

  type getAppParams = {
    /** 应用ID */
    id: number;
  };

  type getAppSimplePageParams = {
    appPageReqDTO: AppPageReqDTO;
  };

  type getAppVOParams = {
    /** 应用ID */
    id: number;
  };

  type getQuestionByAppIdParams = {
    /** 应用ID */
    appId: number;
  };

  type getQuestionPageParams = {
    questionPageReqDTO: QuestionPageReqDTO;
  };

  type getQuestionParams = {
    /** 题目ID */
    id: number;
  };

  type getQuestionVOParams = {
    /** 题目ID */
    id: number;
  };

  type getScoringResultPageParams = {
    scoringresultPageReqDTO: ScoringResultPageReqDTO;
  };

  type getScoringResultParams = {
    /** 评分结果ID */
    id: number;
  };

  type getScoringResultVOParams = {
    /** 评分结果ID */
    id: number;
  };

  type getUserAnswerPageParams = {
    userAnswerPageReqDTO: UserAnswerPageReqDTO;
  };

  type getUserAnswerParams = {
    /** 答题记录ID */
    id: number;
  };

  type getUserAnswerVOParams = {
    /** 答题记录ID */
    id: number;
  };

  type getUserMyAnswerPageParams = {
    userAnswerPageReqDTO: UserAnswerPageReqDTO;
  };

  type getUserPageParams = {
    userPageReqDTO: UserPageReqDTO;
  };

  type getUserParams = {
    /** 用户ID */
    id: number;
  };

  type getUserVOParams = {
    /** 用户ID */
    id: number;
  };

  type LoginUserVO = {
    /** 用户 id */
    id?: number;
    /** 用户昵称 */
    userName?: string;
    /** 用户头像 */
    userAvatar?: string;
    /** 用户简介 */
    userProfile?: string;
    /** 用户角色：user/admin */
    userRole?: string;
    /** 创建时间 */
    createTime?: string;
    /** 更新时间 */
    updateTime?: string;
  };

  type Option = {
    /** 选项对应的结果 */
    result?: string;
    /** 选项分数 */
    score?: number;
    /** 选项的值 */
    value?: string;
    /** 选项 A B C */
    key?: string;
  };

  type PageResultAppSimpleVo = {
    /** 数据 */
    list: AppSimpleVo[];
    /** 总量 */
    total: number;
  };

  type PageResultAppVo = {
    /** 数据 */
    list: AppVo[];
    /** 总量 */
    total: number;
  };

  type PageResultQuestionVo = {
    /** 数据 */
    list: QuestionVo[];
    /** 总量 */
    total: number;
  };

  type PageResultScoringResultVo = {
    /** 数据 */
    list: ScoringResultVo[];
    /** 总量 */
    total: number;
  };

  type PageResultUserAnswerVo = {
    /** 数据 */
    list: UserAnswerVo[];
    /** 总量 */
    total: number;
  };

  type PageResultUserVo = {
    /** 数据 */
    list: UserVo[];
    /** 总量 */
    total: number;
  };

  type QuestionAddReqDTO = {
    /** 题目内容 */
    questionContent: QuestionDTO[];
    /** 应用 id */
    appId: number;
  };

  type QuestionDTO = {
    /** 题目标题 */
    title?: string;
    /** 题目选项列表 */
    options?: Option[];
  };

  type QuestionPageReqDTO = {
    /** 页码，从 1 开始 */
    pageNo: number;
    /** 每页条数，最大值为 100 */
    pageSize: number;
    /** id */
    id?: number;
    /** 题目内容（json格式） */
    questionContent: string;
    /** 应用 id */
    appId?: number;
  };

  type QuestionSimpleVo = {
    /** id */
    id?: number;
    /** 题目内容（json格式） */
    questionContent: QuestionDTO[];
    /** 应用 id */
    appId?: number;
    /** 创建者 */
    creator: string;
    /** 创建时间 */
    createTime?: string;
    /** 更新者 */
    updater: string;
    /** 更新时间 */
    updateTime?: string;
    /** 是否删除 */
    deleted?: string;
  };

  type QuestionUpdateReqDTO = {
    /** 题目内容 */
    questionContent: QuestionDTO[];
    /** 应用 id */
    appId: number;
    /** id */
    id: number;
  };

  type QuestionVo = {
    /** id */
    id?: number;
    /** 题目内容（json格式） */
    questionContent: QuestionDTO[];
    /** 应用 id */
    appId?: number;
    /** 创建者 */
    creator: string;
    /** 创建时间 */
    createTime?: string;
    /** 更新者 */
    updater: string;
    /** 更新时间 */
    updateTime?: string;
    /** 是否删除 */
    deleted?: string;
  };

  type ReviewRequestDTO = {
    /** id */
    id: number;
    /** 状态：0-待审核, 1-通过, 2-拒绝 */
    reviewStatus: number;
    /** 审核信息 */
    reviewMessage?: string;
  };

  type ScoringResultAddReqDTO = {
    /** 结果名称，如物流师 */
    resultName: string;
    /** 结果描述 */
    resultDesc: string;
    /** 结果图片 */
    resultPicture: string;
    /** 结果属性集合 JSON，如 [I,S,T,J] */
    resultProp: string[];
    /** 结果得分范围，如 80，表示 80及以上的分数命中此结果 */
    resultScoreRange: number;
    /** 应用 id */
    appId: number;
  };

  type ScoringResultPageReqDTO = {
    /** 页码，从 1 开始 */
    pageNo: number;
    /** 每页条数，最大值为 100 */
    pageSize: number;
    /** id */
    id?: number;
    /** 结果名称，如物流师 */
    resultName: string;
    /** 结果描述 */
    resultDesc: string;
    /** 结果图片 */
    resultPicture: string;
    /** 结果属性集合 JSON，如 [I,S,T,J] */
    resultProp: string;
    /** 结果得分范围，如 80，表示 80及以上的分数命中此结果 */
    resultScoreRange: number;
    /** 应用 id */
    appId?: number;
  };

  type ScoringResultSimpleVo = {
    /** id */
    id?: number;
    /** 结果名称，如物流师 */
    resultName?: string;
    /** 结果描述 */
    resultDesc: string;
    /** 结果图片 */
    resultPicture: string;
    /** 结果属性集合 JSON，如 [I,S,T,J] */
    resultProp: string[];
    /** 结果得分范围，如 80，表示 80及以上的分数命中此结果 */
    resultScoreRange: number;
    /** 应用 id */
    appId?: number;
    /** 创建者 */
    creator: string;
    /** 创建时间 */
    createTime?: string;
    /** 更新者 */
    updater: string;
    /** 更新时间 */
    updateTime?: string;
    /** 是否删除 */
    deleted?: string;
  };

  type ScoringResultUpdateReqDTO = {
    /** 结果名称，如物流师 */
    resultName: string;
    /** 结果描述 */
    resultDesc: string;
    /** 结果图片 */
    resultPicture: string;
    /** 结果属性集合 JSON，如 [I,S,T,J] */
    resultProp: string[];
    /** 结果得分范围，如 80，表示 80及以上的分数命中此结果 */
    resultScoreRange: number;
    /** 应用 id */
    appId: number;
    /** id */
    id: number;
  };

  type ScoringResultVo = {
    /** id */
    id?: number;
    /** 结果名称，如物流师 */
    resultName?: string;
    /** 结果描述 */
    resultDesc: string;
    /** 结果图片 */
    resultPicture: string;
    /** 结果属性集合 JSON，如 [I,S,T,J] */
    resultProp: string[];
    /** 结果得分范围，如 80，表示 80及以上的分数命中此结果 */
    resultScoreRange: number;
    /** 应用 id */
    appId?: number;
    /** 应用名称 */
    appName?: string;
    userInfo?: User;
  };

  type uploadFileParams = {
    /** 业务枚举 */
    biz: string;
  };

  type User = {
    createTime?: string;
    updateTime?: string;
    creator?: string;
    updater?: string;
    deleted?: boolean;
    id?: number;
    userAccount?: string;
    userPassword?: string;
    unionId?: string;
    mpOpenId?: string;
    userName?: string;
    userAvatar?: string;
    userProfile?: string;
    userRole?: string;
    userStatus?: number;
  };

  type UserAddReqDTO = {
    /** 账号 */
    userAccount?: string;
    /** 密码 */
    userPassword?: string;
    /** 用户昵称 */
    userName?: string;
    /** 用户头像 */
    userAvatar?: string;
    /** 用户简介 */
    userProfile?: string;
    /** 用户角色 */
    userRole?: string;
  };

  type UserAnswerAddReqDTO = {
    /** 应用 id */
    appId: number;
    /** 应用类型（0-得分类，1-角色测评类） */
    appType?: string;
    /** 评分策略（0-自定义，1-AI） */
    scoringStrategy?: string;
    /** 用户答案（JSON 数组） */
    choices: string[];
    /** 评分结果 id */
    resultId: number;
    /** 结果名称，如物流师 */
    resultName: string;
    /** 结果描述 */
    resultDesc: string;
    /** 结果图标 */
    resultPicture: string;
    /** 得分 */
    resultScore: number;
  };

  type UserAnswerPageReqDTO = {
    /** 页码，从 1 开始 */
    pageNo: number;
    /** 每页条数，最大值为 100 */
    pageSize: number;
    /** id */
    id?: number;
    /** 应用 id */
    appId?: number;
    /** 应用类型（0-得分类，1-角色测评类） */
    appType?: number;
    /** 评分策略（0-自定义，1-AI） */
    scoringStrategy?: number;
    /** 用户答案（JSON 数组） */
    choices: string;
    /** 评分结果 id */
    resultId: number;
    /** 结果名称，如物流师 */
    resultName: string;
    /** 结果描述 */
    resultDesc: string;
    /** 结果图标 */
    resultPicture: string;
    /** 得分 */
    resultScore: number;
    userId?: number;
  };

  type UserAnswerSimpleVo = {
    /** id */
    id?: number;
    /** 应用 id */
    appId?: number;
    /** 应用名称 */
    appName?: string;
    userInfo?: UserVo;
    /** 应用类型（0-得分类，1-角色测评类） */
    appType?: number;
    /** 评分策略（0-自定义，1-AI） */
    scoringStrategy?: number;
    /** 用户答案（JSON 数组） */
    choices: string;
    /** 评分结果 id */
    resultId: number;
    /** 结果名称，如物流师 */
    resultName: string;
    /** 结果描述 */
    resultDesc: string;
    /** 结果图标 */
    resultPicture: string;
    /** 得分 */
    resultScore: number;
    /** 创建时间 */
    createTime?: string;
  };

  type UserAnswerUpdateReqDTO = {
    /** 应用 id */
    appId: number;
    /** 应用类型（0-得分类，1-角色测评类） */
    appType?: string;
    /** 评分策略（0-自定义，1-AI） */
    scoringStrategy?: string;
    /** 用户答案（JSON 数组） */
    choices: string[];
    /** 评分结果 id */
    resultId: number;
    /** 结果名称，如物流师 */
    resultName: string;
    /** 结果描述 */
    resultDesc: string;
    /** 结果图标 */
    resultPicture: string;
    /** 得分 */
    resultScore: number;
    /** id */
    id: number;
  };

  type UserAnswerVo = {
    /** id */
    id?: number;
    /** 应用 id */
    appId?: number;
    /** 应用名称 */
    appName?: string;
    /** 应用类型（0-得分类，1-角色测评类） */
    appType?: number;
    /** 评分策略（0-自定义，1-AI） */
    scoringStrategy?: number;
    /** 用户答案（JSON 数组） */
    choices: string;
    /** 评分结果 id */
    resultId: number;
    /** 结果名称，如物流师 */
    resultName: string;
    /** 结果描述 */
    resultDesc: string;
    /** 结果图标 */
    resultPicture: string;
    /** 得分 */
    resultScore: number;
    userInfo?: UserVo;
  };

  type UserLoginReqDTO = {
    /** 账号 */
    userAccount: string;
    /** 密码 */
    userPassword: string;
  };

  type UserPageReqDTO = {
    /** 页码，从 1 开始 */
    pageNo: number;
    /** 每页条数，最大值为 100 */
    pageSize: number;
    /** id */
    id?: number;
    /** 用户昵称 */
    userName?: string;
    /** 用户角色 */
    userRole?: string;
    /** 创建时间 */
    createTime?: string;
    /** 开放平台id */
    unionId?: string;
    /** 公众号openId */
    mpOpenId?: string;
  };

  type UserProfileUpdateReqDTO = {
    /** 用户昵称 */
    userName?: string;
    /** 用户头像 */
    userAvatar?: string;
    /** 用户简介 */
    userProfile?: string;
  };

  type UserRegisterReqDTO = {
    /** 账号 */
    userAccount: string;
    /** 密码 */
    userPassword: string;
    /** 确认密码 */
    checkPassword: string;
  };

  type UserSimpleVo = {
    /** id */
    id?: number;
    /** 用户昵称 */
    userName?: string;
    /** 用户头像 */
    userAvatar?: string;
    /** 用户简介 */
    userProfile?: string;
    /** 用户角色：user/admin/ban */
    userRole?: string;
    /** 创建时间 */
    createTime?: string;
  };

  type UserUpdateReqDTO = {
    /** 账号 */
    userAccount?: string;
    /** 密码 */
    userPassword?: string;
    /** 用户昵称 */
    userName?: string;
    /** 用户头像 */
    userAvatar?: string;
    /** 用户简介 */
    userProfile?: string;
    /** 用户角色 */
    userRole?: string;
    /** id */
    id: number;
  };

  type UserVo = {
    /** id */
    id?: number;
    /** 账号 */
    userAccount?: string;
    /** 密码 */
    userPassword?: string;
    /** 微信开放平台id */
    unionId?: string;
    /** 公众号openId */
    mpOpenId?: string;
    /** 用户昵称 */
    userName?: string;
    /** 用户头像 */
    userAvatar?: string;
    /** 用户简介 */
    userProfile?: string;
    /** 用户角色：user/admin */
    userRole?: string;
    /** 用户状态（0正常 1停用） */
    userStatus?: number;
  };
}
