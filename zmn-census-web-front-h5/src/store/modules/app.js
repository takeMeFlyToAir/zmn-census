const state = {
  userName: '',
  examinePersonName: '',
}
const mutations = {
  SET_USER_NAME(state, name) {
    state.userName = name
  },
  SET_EXAMINE_PERSON_NAME(state, examinePersonName) {
    state.examinePersonName = examinePersonName
  }
}
const actions = {
  // 设置name
  setUserName({ commit }, name) {
    commit('SET_USER_NAME', name)
  },
  // 设置普查员姓名
  setExaminePersonName({ commit }, examinePersonName) {
    commit('SET_EXAMINE_PERSON_NAME', examinePersonName)
  }
}
export default {
  state,
  mutations,
  actions
}
