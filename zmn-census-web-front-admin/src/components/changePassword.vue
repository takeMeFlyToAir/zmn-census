<template> 
 <el-dialog title="修改密码" :visible="dialogFormVisible">
    <el-form  :model="userChangePassword"
              :rules="rules"
              ref="userChangePasswordForm"
              label-width="150px">
              <el-form-item label="旧密码" prop="password">
                  <el-input v-model="userChangePassword.password" show-password  ></el-input>
              </el-form-item>
              <el-form-item label="新密码" prop="newPassword">
                  <el-input v-model="userChangePassword.newPassword" show-password  ></el-input>
              </el-form-item>
              <el-form-item label="确认密码" prop="confirmPassword">
                  <el-input v-model="userChangePassword.confirmPassword" show-password  ></el-input>
              </el-form-item>
    </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="changePassword">确 定</el-button>
  </div>
  </el-dialog>
</template>
<script>
  import { apiUserChangePassword } from '@/api/user'
  export default {
    name: 'changePassword',
    props: {
    },
    data() {
       var validConfirmPassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.userChangePassword.newPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        dialogFormVisible:false,
        userChangePassword:{
          password:'',
          newPassword:'',
          confirmPassword:''
        },
        rules: {
          password: [
              { required: true, message: '请输入旧密码', trigger: 'blur' },
              { min: 6, max: 30, message: '长度在 6 到 30 个字符'},
              { pattern: /^(\w){6,20}$/, message: '只能输入6-20个字母、数字、下划线' }
          ],
          newPassword: [
              { required: true, message: '请输入新密码', trigger: 'blur' },
              { min: 6, max: 30, message: '长度在 6 到 30 个字符'},
              { pattern: /^(\w){6,20}$/, message: '只能输入6-20个字母、数字、下划线' }
          ],
          confirmPassword: [
             { required: true, message: '请再次输入密码', trigger: 'blur' },
             {validator: validConfirmPassword, trigger: 'blur' }
          ],
      }
    };
    },
    methods: {
      changePassword() {
          this.$refs['userChangePasswordForm'].validate((valid) => {
                    if (valid) {
                        this.$confirm('是否提交数据', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'warning'
                        }).then(() =>{
                            console.log(this.userChangePassword)
                          apiUserChangePassword(this.userChangePassword)
                                .then(res=>{
                                    if(res.code == 200){
                                        this.$message({
                                            type:'success',
                                            message:res.message
                                        });
                                        this.dialogFormVisible = false;

                                      this.$store.dispatch('LogOut').then(() => {
                                        location.reload() // 为了重新实例化vue-router对象 避免bug
                                      })
                                    }
                                })
                        })
                    }else {
                        return false;
                    }
        });
      }
    }
  }
</script>
<style>

</style>


