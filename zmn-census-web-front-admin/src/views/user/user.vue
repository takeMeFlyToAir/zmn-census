<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.userName" placeholder="用户名" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" clearable @clear="handleFilter" />
      <el-input v-model="listQuery.nickName" placeholder="昵称" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" clearable @clear="handleFilter"  />
      <el-input v-model="listQuery.phone" placeholder="电话号码" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" clearable @clear="handleFilter"  />

      <el-button  class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleAdd">
        添加
      </el-button>
      <el-button  :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        导出
      </el-button>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="ID" prop="id" sortable="custom" align="center"  >
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="昵称" align="center">
        <template slot-scope="{row}">
          <span>{{ row.nickName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户名"  align="center">
        <template slot-scope="{row}">
          <span>{{ row.userName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="电话号码"  align="center">
        <template slot-scope="{row}">
          <span>{{ row.phone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建日期"  align="center">
        <template slot-scope="{row}">
          <span>{{ row.createdDateStr }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center"  class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini"  @click="handleEdit(row)">
            编辑
          </el-button>
          <el-button  size="mini" type="danger" @click="handleDelete(row.id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" @pagination="getList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="80px" style="width: 400px; margin-left:50px;">
        <el-form-item label="昵称" prop="nickName">
          <el-input v-model="temp.nickName" placeholder="昵称" />
        </el-form-item>
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="temp.userName"  placeholder="用户名" />
        </el-form-item>
        <el-form-item label="电话号码" prop="phone">
          <el-input v-model="temp.phone"  placeholder="电话号码" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='add'?addData():editData()">
          提交
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { apiGetList, apiAdd, apiDelete, apiEdit, apiExistPhone, apiExistUserName } from '@/api/user'
  import { parseTime } from '@/utils'
  import Pagination from '@/components/Pagination'

  export default {
    name: 'user',
    components: { Pagination },
    data() {
      let checkPhone = (rule, value, callback) => {
        const phoneReg = /^1[3|4|5|7|8][0-9]{9}$/
        if (!value) {
          return callback(new Error('电话号码不能为空'))
        }
        setTimeout(() => {
          // Number.isInteger是es6验证数字是否为整数的方法,但是我实际用的时候输入的数字总是识别成字符串
          // 所以我就在前面加了一个+实现隐式转换
          if (phoneReg.test(value)) {
            callback()
          } else {
            callback(new Error('电话号码格式不正确'))
          }
        }, 100)
      }
      return {
        tableKey: 0,
        list: null,
        total: 0,
        listLoading: true,
        listQuery: {
          pageNo: 1,
          pageSize: 10,
          userName: null,
          nickName: null,
          phone: null,
        },
        downloadLoading: false,

        dialogStatus:'',
        dialogFormVisible:false,
        textMap: {
          edit: '编辑',
          create: '添加'
        },
        temp:{
          id:null,
          nickName:'',
          userName:'',
          phone:''
        },
        rules: {
          nickName: [{ required: true, message: '昵称是必填', trigger: 'blur' }],
          userName: [{ required: true, message: '用户名是必填', trigger: 'blur' }],
          phone: [
            { required: true, message: '电话号码是必填', trigger: 'blur' },
            { validator: checkPhone, trigger: 'blur' }
          ],
        },
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList(params) {
        if(params){
          Object.assign(this.listQuery, params)
        }
        this.listLoading = true
        apiGetList(this.listQuery).then(response => {
          this.list = response.data.data
          this.total = response.data.total
          this.listLoading = false
        })
      },
      handleFilter() {
        this.listQuery.pageNo = 1
        this.getList()
      },
      resetTemp(){
        this.temp = {
          id:null,
          nickName:'',
          userName:'',
          phone:''
        }
      },
      handleAdd(){
        this.resetTemp()
        this.dialogStatus='add'
        this.dialogFormVisible=true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      addData(){
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$confirm('是否提交数据', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              apiAdd(this.temp).then(() => {
                this.dialogFormVisible = false
                this.handleFilter()
                this.$notify({
                  title: '新增用户',
                  message: '添加成功',
                  type: 'success',
                  duration: 3000
                })
              })
            })
          }
        })
      },
      handleDelete(id){
        this.$confirm('确定删除数据', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          apiDelete(id).then(() => {
            this.handleFilter()
            this.$notify({
              title: '删除用户',
              message: '删除成功',
              type: 'success',
              duration: 3000
            })
          })
        })
      },
      handleEdit(row){
        this.temp = Object.assign({}, row) // copy obj
        this.dialogStatus='edit'
        this.dialogFormVisible=true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      editData(){
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$confirm('是否提交数据', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              apiEdit(this.temp).then(() => {
                this.dialogFormVisible = false
                this.handleFilter()
                this.$notify({
                  title: '编辑用户',
                  message: '编辑成功',
                  type: 'success',
                  duration: 3000
                })
              })
            })
          }
        })
      },
      handleDownload() {
        this.downloadLoading = true
        import('@/utils/vendor/Export2Excel').then(excel => {
          const tHeader = ['昵称', '用户名', '电话号码','创建日期']
          const filterVal = ['nickName', 'userName', 'phone','createdDateStr']
          const data = this.formatJson(filterVal)
          excel.export_json_to_excel({
            header: tHeader,
            data,
            filename: 'table-list'
          })
          this.downloadLoading = false
        })
      },
      formatJson(filterVal) {
        return this.list.map(v => filterVal.map(j => {
          return v[j]
        }))
      },
    }
  }
</script>
