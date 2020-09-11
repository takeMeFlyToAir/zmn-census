<template>
  <div class="login-container">
    <div class="login-wrapper">
      <div class="header">
        <p id="title" contenteditable="true" spellcheck="false"><span>数</span><span>据</span><span>通</span><span>平</span><span>台</span></p>
      </div>
      <div class="form-wrapper">
        <input type="text" name="userName" placeholder="用户名" class="input-item">
        <input type="password" name="password" placeholder="密码" class="input-item">
        <div class="btn" >登录</div>
      </div>
    </div>
  </div>
</template>
<script>
  export default {
    name: 'login',
    data() {
      return {
        loginForm: {
          userName: 'zzr',
          password: '123456'
        },
        loginRules: {
          userName: [{required: true, trigger: 'blur', message: "请输入用户名"}],
          password: [{required: true, trigger: 'blur', message: "请输入密码"}]
        },
        loading: false
      }
    },
    methods: {
      handleLogin() {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.loading = true
            this.$store.dispatch('Login', this.loginForm).then(data => {
              this.loading = false
              this.$router.push({path: '/'})
            }).catch(() => {
              this.loading = false
            })
          } else {
            return false
          }
        })
      }
    }
  }
</script>
<style scoped>
  .login-container{
    padding: 0;
    margin: 0;
    font-family: 'Open Sans Light';
    letter-spacing: .05em;
    height: 100%;
    background-image: linear-gradient(to right,#2b5876 ,#4e4376);
  }
  .login-wrapper{
    background-color: #fff;
    width: 350px;
    padding: 0 50px;
    height: 350px;
    border-radius: 15px;
    position: relative;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
  }
  .login-wrapper .header{
    font-size: 30px;
    font-weight: bold;
    text-align: center;
    line-height: 100px;
  }

  span {
    text-shadow: -0.06em 0 red,  0.06em 0 cyan; /* This creates the 3D effect, pretty easy, eh? */
    letter-spacing: 0.08em; /* make sure the letters don't overlap */
  }

  p {
    margin: 0;
  }


  #title {
    cursor: crosshair;
    color:#434343 ;
  }
  #title:focus {
    outline: none;
  }
  #title span {
    vertical-align: middle;
    line-height: 1.5em;
    transition: font-size 2s cubic-bezier(0, 1, 0, 1);
  }
  #title span:hover {
    font-size: 1.5em; /* This adds a hover effect */
    line-height: 1em;
    transition: font-size .2s cubic-bezier(0, 0.75, 0, 1);
  }
  #title span:active {
    font-size: 1em;
    text-shadow: none;
  }
  #title span::selection {
    background-color: red; /* This hides the selection */
  }

  .login-wrapper .form-wrapper .input-item{
    display: inline;
    width: 100%;
    margin-bottom: 20px;
    border: 0;
    padding: 10px;
    border-bottom: 1px solid rgb(128, 125, 125);
    font-size: 15px;
    outline: none;
    -webkit-apprarance:none !important;
  }
  .login-wrapper .form-wrapper .input-item::placeholder{
    text-transform: uppercase;
  }
  .login-wrapper .form-wrapper .btn{
    cursor: pointer;
    text-align: center;
    padding: 10px;
    width: 100%;
    background-image: linear-gradient(to right,#4e4376,#2b5876);
    color: #fff;
    margin-top: 40px;
    border-radius: 10px;
  }
</style>
