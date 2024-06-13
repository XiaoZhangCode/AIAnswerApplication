// 审核状态枚举
export const REVIEW_STATUS_ENUM: Record<string, number> = {
  // 待审核
  REVIEWING: 0,
  // 通过
  PASS: 1,
  // 拒绝
  REJECT: 2,
};

// 审核状态映射
export const REVIEW_STATUS_MAP: Record<number, string> = {
  0: "待审核",
  1: "通过",
  2: "拒绝",
};

// 应用类型枚举
export const APP_TYPE_ENUM: Record<string, number> = {
  // 得分类
  SCORE: 0,
  // 测评类
  TEST: 1,
};
// 应用类型映射
export const APP_TYPE_MAP: Record<number, string> = {
  0: "得分类",
  1: "测评类",
};

// 应用评分策略枚举
export const APP_SCORING_STRATEGY_ENUM: Record<string, number> = {
  // 自定义
  CUSTOM: 0,
  // AI
  AI: 1,
};

// 应用评分策略映射
export const APP_SCORING_STRATEGY_MAP: Record<number, string> = {
  0: "自定义",
  1: "AI",
};
