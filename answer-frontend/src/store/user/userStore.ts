import { defineStore } from "pinia";
import { ref } from "vue";
import ACCESS_ENUM from "@/access/accessEnum";
import { getLoginUser } from "@/api/user";

export const useLoginUserStore = defineStore("loginUser", () => {
  const loginUser = ref<API.LoginUserVO>({
    userName: "未登录",
  });

  async function fetchLoginUser() {
    const res = await getLoginUser();
    if (res?.code === 0 && res.data) {
      setLoginUser(res.data as API.LoginUserVO);
    } else {
      loginUser.value = { userRole: ACCESS_ENUM.NOT_LOGIN };
    }
  }

  function setLoginUser(newLoginUser: API.LoginUserVO) {
    loginUser.value = newLoginUser;
  }

  return { loginUser, setLoginUser, fetchLoginUser };
});
