<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select v-model="listQuery.chargePersonId" placeholder="请选择" style="width: 120px;"  @change="handleFilter" clearable @clear="handleFilter" >
        <el-option
          v-for="item in userList"
          :key="item.id"
          :label="item.nickName"
          :value="item.id">
        </el-option>
      </el-select>
      <el-input v-model="listQuery.town" placeholder="街道" style="width: 120px;" class="filter-item" @keyup.enter.native="handleFilter" clearable @clear="handleFilter" />
      <el-input v-model="listQuery.village" placeholder="普查小区" style="width: 120px;" class="filter-item" @keyup.enter.native="handleFilter" clearable @clear="handleFilter"  />
      <el-input v-model="listQuery.name" placeholder="住宅小区" style="width: 120px;" class="filter-item" @keyup.enter.native="handleFilter" clearable @clear="handleFilter"  />


      <el-button  class="filter-item" type="primary" @click="handleFilter">
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary"  @click="handleAdd">
        添加
      </el-button>
      <el-button  :loading="downloadLoading" class="filter-item" type="primary"  @click="handleDownload">
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
      <el-table-column label="街道" align="center">
        <template slot-scope="{row}">
          <span>{{ row.town }}</span>
        </template>
      </el-table-column>
      <el-table-column label="普查区"  align="center">
        <template slot-scope="{row}">
          <span>{{ row.village }}</span>
        </template>
      </el-table-column>
      <el-table-column label="小区名字"  align="center">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="计划户数"  align="center">
        <template slot-scope="{row}">
          <span>{{ row.planHoldCount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际户数"  align="center">
        <template slot-scope="{row}">
          <span>{{ row.realHoldCount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="计划人数"  align="center">
        <template slot-scope="{row}">
          <span>{{ row.planPersonCount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际人数"  align="center">
        <template slot-scope="{row}">
          <span>{{ row.realPersonCount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="负责人"  align="center">
        <template slot-scope="{row}">
          <span>{{ row.chargePersonName }}</span>
        </template>
      </el-table-column>
      <!--<el-table-column label="创建日期"  align="center">-->
        <!--<template slot-scope="{row}">-->
          <!--<span>{{ row.createdDateStr }}</span>-->
        <!--</template>-->
      <!--</el-table-column>-->
      <el-table-column width="300" label="操作" align="center"  class-name="small-padding">
        <template slot-scope="{row,$index}">
          <el-button type="mini" size="mini"  @click="handleEdit(row)">
            编辑
          </el-button>
          <!--<el-button  size="mini" type="danger" @click="handleDelete(row.id)">-->
            <!--删除-->
          <!--</el-button>-->
          <el-button  size="mini" type="danger" @click="fillCensus(row)">
            填写问卷
          </el-button>
          <el-button  size="mini" type="primary" @click="qrCode(row)">
            二维码
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" @pagination="getList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="120px" style="width: 400px; margin-left:50px;">
        <el-form-item label="街道" prop="town">
          <el-input v-model="temp.town" placeholder="街道" />
        </el-form-item>
        <el-form-item label="普查小区" prop="village">
          <el-input v-model="temp.village"  placeholder="普查小区" />
        </el-form-item>
        <el-form-item label="住宅小区" prop="name">
          <el-input v-model="temp.name"  placeholder="住宅小区" />
        </el-form-item>
        <el-form-item label="计划统计户数" prop="planHoldCount">
          <el-input type="number" v-model="temp.planHoldCount"  placeholder="计划统计户数" />
        </el-form-item>
        <el-form-item label="计划统计人数" prop="planPersonCount">
          <el-input type="number"  v-model="temp.planPersonCount"  placeholder="计划统计人数" />
        </el-form-item>
        <el-form-item label="负责人" prop="chargePersonId">
          <el-select v-model="temp.chargePersonName" placeholder="请选择" @change="choiceCharge">
            <el-option
              v-for="item in userList"
              value-key="id"
              :label="item.nickName"
              :value="item">
            </el-option>
          </el-select>
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

    <el-dialog
      title="二维码"
      :visible.sync="qrDialogFormVisible"
      width="20%"
      center>
        <vue-q-art :config="config" :download="download"></vue-q-art>
    </el-dialog>

  </div>
</template>

<script>
  import { apiCommunityGetList, apiCommunityAdd, apiCommunityDelete, apiCommunityEdit} from '@/api/community'
  import { apiUserFindList} from '@/api/user'
  import { apiGetByKey } from '@/api/config'
  import { parseTime } from '@/utils'
  import Pagination from '@/components/Pagination'
  import VueQArt from 'vue-qart'

  export default {
    name: 'user',
    components: { Pagination,VueQArt  },
    data() {
      return {
        userList:[],
        frontUrl:'',
        downloadButton:true,
        downloadButtonText:'下载',
        config: {
          value: '',
          imagePath: '/src/assets/image/logo.png',
          size: 1,
          filter: 'color',
          version: 1,
          fillType: 'scale_to_fit',
        },
        download: {
          visible: true,
          text: '下载二维码',
          style: {
            fontSize: '16px',
            textAlign: 'center',
            marginLeft: '60px'
        },
          filename:'二维码'
        },
        tableKey: 0,
        list: null,
        total: 0,
        listLoading: true,
        listQuery: {
          pageNo: 1,
          pageSize: 10,
          chargePersonId: null,
          town: null,
          village: null,
          name: null,
        },
        downloadLoading: false,

        dialogStatus:'',
        dialogFormVisible:false,
        qrDialogFormVisible:false,
        textMap: {
          // edit: '编辑',
          create: '添加'
        },
        temp:{
          id:null,
          town:'',
          village:'',
          planHoldCount:0,
          planPersonCount:0,
          chargePersonId:'',
          chargePersonName:'',
        },
        rules: {
          town: [{ required: true, message: '街道是必填', trigger: 'blur' }],
          village: [{ required: true, message: '普查区是必填', trigger: 'blur' }],
          name: [{ required: true, message: '小区名字是必填', trigger: 'blur' }],
          planHoldCount: [{ required: true, message: '计划统计户数是必填', trigger: 'blur' }],
          planPersonCount: [{ required: true, message: '计划统计人数是必填', trigger: 'blur' }],
          chargePersonId: [{ required: true, message: '负责人是必填', trigger: 'blur' }],
        },
      }
    },
    created() {
      this.getList()
      this.getUserList()
      this.getFrontUrl()
    },
    methods: {
      choiceCharge(user){
        this.temp.chargePersonId = user.id;
        this.temp.chargePersonName = user.nickName;
        console.log(user)
      },
      getFrontUrl(){
        let param = {key: 'census_fill'}
        apiGetByKey(param).then((res) => {
          if(res.code == 200 && res.data != null){
            this.frontUrl = res.data.value
          }
        })
      },
      getUserList(){
        let param = {}
        apiUserFindList(param).then((res) => {
          if(res.code == 200){
            this.userList = res.data
          }
        })
      },
      qrCode(row){
        if(this.frontUrl){
          this.download.filename=row.name
          this.config.value=this.frontUrl+"?id="+row.id
          this.qrDialogFormVisible = true
        }else {
          this.$notify({
            title: '提示',
            message: '联系管理员配置前台访问的地址',
            type: 'error',
            duration: 3000
          })
        }

      },
      fillCensus(row){
        if(this.frontUrl){
          window.open(this.frontUrl+"?id="+row.id,'_blank')
        }else {
          this.$notify({
            title: '提示',
            message: '联系管理员配置前台访问的地址',
            type: 'error',
            duration: 3000
          })
        }
      },
      getList(params) {
        if(params){
          Object.assign(this.listQuery, params)
        }
        this.listLoading = true
        apiCommunityGetList(this.listQuery).then(response => {
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
          town:'',
          village:'',
          planHoldCount:'0',
          planPersonCount:'0',
          chargePersonId:'',
          chargePersonName:'',
        }
      },
      handleAdd(){
        this.resetTemp()
        console.log(this.temp)
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
              apiCommunityAdd(this.temp).then(() => {
                this.dialogFormVisible = false
                this.handleFilter()
                this.$notify({
                  title: '新增小区',
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
          let param = {id:id}
          apiCommunityDelete(param).then(() => {
            this.handleFilter()
            this.$notify({
              title: '删除小区',
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
              apiCommunityEdit(this.temp).then(() => {
                this.dialogFormVisible = false
                this.handleFilter()
                this.$notify({
                  title: '编辑小区',
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
          const tHeader = ['街道', '普查区', '小区名字','创建日期']
          const filterVal = ['town', 'village', 'name','createdDateStr']
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
