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
      <el-input v-model="listQuery.community" placeholder="住宅小区" style="width: 120px;" class="filter-item" @keyup.enter.native="handleFilter" clearable @clear="handleFilter"  />
      <el-input v-model="listQuery.buildNum" placeholder="楼栋号" style="width: 120px;" class="filter-item" @keyup.enter.native="handleFilter" clearable @clear="handleFilter"  />
      <el-input v-model="listQuery.unitNum" placeholder="单元号" style="width: 120px;" class="filter-item" @keyup.enter.native="handleFilter" clearable @clear="handleFilter"  />
      <el-input v-model="listQuery.floorNum" placeholder="楼层" style="width: 120px;" class="filter-item" @keyup.enter.native="handleFilter" clearable @clear="handleFilter"  />
      <el-input v-model="listQuery.roomNum" placeholder="房间号" style="width: 120px;" class="filter-item" @keyup.enter.native="handleFilter" clearable @clear="handleFilter"  />
      <br>
      <br>
      <el-input v-model="listQuery.examinePersonName" placeholder="普查员" style="width: 120px;" class="filter-item" @keyup.enter.native="handleFilter" clearable @clear="handleFilter"  />
      <el-date-picker
        v-model="timeQuery"
        type="daterange"
        align="right"
        value-format="yyyy-MM-dd"
        unlink-panels
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        clearable
        @change="handleFilter"
        @clear="handleFilter"
        :picker-options="pickerOptions">
      </el-date-picker>

      <el-button  class="filter-item" type="primary" @click="handleFilter">
        搜索
      </el-button>
      <!--<el-button  :loading="downloadLoading" class="filter-item" type="primary"  @click="handleDownload">-->
        <!--导出-->
      <!--</el-button>-->
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      height="350"
      style="width: 100%;"
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <div>
            <ul style="color: red">
              <ol>h2：本户应登记人数,2020年10月31日晚居住本户的人数/户口在本户2020年10月31日晚未住本户的人数</ol>
              <ol>h3：户2019年11月1日至2020年10月31日期间的出生人口男人/女人</ol>
              <ol>h4：本户2019年11月1日至2020年10月31日期间的死亡人口男人/女人</ol>
            </ul>
            <el-table
              :data="props.row.houseHoldList"
              border
              :row-style="getRowClass" :header-row-style="getRowClass" :header-cell-style="getRowClass"
              style="width: 100%">
              <el-table-column prop="h1" label="户别" width="65px"> </el-table-column>

              <el-table-column label="h2(居住/未居住)" >
                <template slot-scope="{row}">
                  <span>{{ row.h2Live }}/{{ row.h2NoLive }}</span>
                </template>
              </el-table-column>
              <el-table-column label="h3(男/女)" >
                <template slot-scope="{row}">
                  <span>{{ row.h3Man }}/{{ row.h3Woman }}</span>
                </template>
              </el-table-column>
              <el-table-column label="h4(男/女)" >
                <template slot-scope="{row}">
                  <span>{{ row.h4Man }}/{{ row.h4Woman }}</span>
                </template>
              </el-table-column>
              <el-table-column prop="h5" label="住房类型"> </el-table-column>
              <el-table-column prop="h6" label="建筑面积"> </el-table-column>
              <el-table-column prop="h7" label="房间数"> </el-table-column>
              <el-table-column prop="h8" label="孕妇"> </el-table-column>
              <el-table-column prop="h9" label="填报人类型"> </el-table-column>
            </el-table>
            <el-table
              :row-style="getRowClass" :header-row-style="getRowClass" :header-cell-style="getRowClass"

              :data="props.row.personInfoList"
              border
              style="width: 100%">
              <el-table-column prop="d1" label="姓名" width="65px"> </el-table-column>
              <el-table-column prop="d2" label="关系" width="65px"> </el-table-column>
              <el-table-column prop="d3" label="身份证"  width="100px"> </el-table-column>
              <el-table-column prop="d4" label="性别" width="50px">> </el-table-column>
              <el-table-column prop="d5" label="生日"width="80px"> </el-table-column>
              <el-table-column prop="d6" label="民族" width="50px"> </el-table-column>
              <el-table-column label="普查时点居住地" >
                <template slot-scope="{row}">
                  <span>{{row.d7}}</span>
                  <span v-if="row.d7AddressAll != ''" style="color: red">({{row.d7AddressAll}})</span>
                </template>
              </el-table-column>
              <el-table-column label="户口登记地" >
                <template slot-scope="{row}">
                  <span>{{row.d8}}</span>
                  <span v-if="row.d8AddressAll != ''" style="color: red">({{row.d8AddressAll}})</span>
                </template>
              </el-table-column>
              <el-table-column prop="d9" label="离开户口登记地时间" width="100px"> </el-table-column>
              <el-table-column prop="d10" label="离开户口登记地原因" width="100px"> </el-table-column>
              <el-table-column prop="d11" label="教育程度"  width="100px"> </el-table-column>
              <el-table-column prop="d12" label="识字" width="50px"> </el-table-column>
            </el-table>
          </div>
        </template>
      </el-table-column>
      <!--<el-table-column label="ID" prop="id" sortable="custom"   >-->
        <!--<template slot-scope="{row}">-->
          <!--<span>{{ row.id }}</span>-->
        <!--</template>-->
      <!--</el-table-column>-->
      <el-table-column label="街道" >
        <template slot-scope="{row}">
          <span>{{ row.roomAddress.town }}</span>
        </template>
      </el-table-column>
      <el-table-column label="普查区"  >
        <template slot-scope="{row}">
          <span>{{ row.roomAddress.village }}</span>
        </template>
      </el-table-column>
      <el-table-column label="小区名字"  >
        <template slot-scope="{row}">
          <span>{{ row.roomAddress.community }}</span>
        </template>
      </el-table-column>
      <el-table-column label="负责人" width="65px" >
        <template slot-scope="{row}">
          <span>{{ row.roomAddress.chargePersonName }}</span>
        </template>
      </el-table-column>

      <el-table-column label="楼栋号" width="65px"  >
        <template slot-scope="{row}">
          <span>{{ row.roomAddress.buildNum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="单元号" width="65px"  >
        <template slot-scope="{row}">
          <span>{{ row.roomAddress.unitNum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="楼层" width="65px"  >
        <template slot-scope="{row}">
          <span>{{ row.roomAddress.floorNum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="房间号" width="65px"  >
        <template slot-scope="{row}">
          <span>{{ row.roomAddress.roomNum }}</span>
        </template>
      </el-table-column>
      <el-table-column label="人数" width="65px"  >
        <template slot-scope="{row}">
          <span>{{ row.roomAddress.personCount }}</span>
        </template>
      </el-table-column>
      <el-table-column label="填报人电话"   width="110px" >
        <template slot-scope="{row}">
          <span>{{ row.roomAddress.fillPersonPhone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="普查员"  >
        <template slot-scope="{row}">
          <span>{{ row.roomAddress.examinePersonName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注"  >
        <template slot-scope="{row}">
          <span>{{ row.roomAddress.remark }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="100px" >
        <template slot-scope="{row}">
          <span>{{ row.roomAddress.createdDateStr }}</span>
        </template>
      </el-table-column>
      <el-table-column width="100px" label="操作" align="center"  class-name="small-padding">
        <template slot-scope="{row,$index}">
          <el-button  size="mini" type="danger" @click="handleDelete(row.id)">
          删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" @pagination="getList" />
  </div>
</template>

<script>
  import { apiCensusSurveyGetList, apiCensusSurveyDelete} from '@/api/censusSurvey'
  import { apiUserFindList} from '@/api/user'
  import { parseTime } from '@/utils'
  import Pagination from '@/components/Pagination'

  export default {
    name: 'user',
    components: { Pagination  },
    data() {
      return {
        pickerOptions: {
          shortcuts: [
            {
              text: '今天',
              onClick(picker) {
                const end = new Date();
                const start = new Date();
                end.setTime(end.getTime() + 3600 * 1000 * 24 * 1);
                picker.$emit('pick', [start, end]);
              }
            },
            {
              text: '昨天',
              onClick(picker) {
                const end = new Date();
                const start = new Date();
                start.setTime(start.getTime() - 3600 * 1000 * 24 * 1);
                picker.$emit('pick', [start, end]);
              }
            },
           {
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              end.setTime(end.getTime() + 3600 * 1000 * 24 * 1);
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 6);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        userList:[],
        tableKey: 0,
        list: null,
        total: 0,
        listLoading: true,
        timeQuery:[],
        listQuery: {
          pageNo: 1,
          pageSize: 10,
          chargePersonId: null,
          town: null,
          village: null,
          community: null,
          buildNum: null,
          unitNum: null,
          floorNum: null,
          roomNum: null,
          examinePersonName: null,
          startTime:'',
          endTime:'',
        },
        downloadLoading: false,
      }
    },
    created() {
      this.getList()
      this.getUserList()
    },
    methods: {
      handleDelete(id){
        this.$confirm('确定删除数据', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let param = {id:id}
          apiCensusSurveyDelete(param).then(() => {
            this.handleFilter()
            this.$notify({
              title: '删除问卷',
              message: '删除成功',
              type: 'success',
              duration: 3000
            })
          })
        })
      },
      getRowClass({ row, column, rowIndex, columnIndex }) {
        return "background:#3f5c6d2c;";
      },
      getUserList(){
        let param = {}
        apiUserFindList(param).then((res) => {
          if(res.code == 200){
            this.userList = res.data
          }
        })
      },
      getList(params) {
        if(params){
          Object.assign(this.listQuery, params)
        }
        this.listLoading = true
        apiCensusSurveyGetList(this.listQuery).then(response => {
          this.list = response.data.data
          this.total = response.data.total
          this.listLoading = false
        })
      },
      handleFilter() {
        this.listQuery.pageNo = 1
        if(this.timeQuery && this.timeQuery.length > 0){
          this.listQuery.startTime = this.timeQuery[0]
          this.listQuery.endTime = this.timeQuery[1]
        }else{
          this.listQuery.startTime = ''
          this.listQuery.endTime = ''
        }
        this.getList()
      },
      handleDownload() {
        this.downloadLoading = true
        import('@/utils/vendor/Export2Excel').then(excel => {
          const tHeader = ['街道', '普查区', '小区名字','创建日期']
          const filterVal = ['roomAddress.town', 'roomAddress.village', 'roomAddress.name','roomAddress.createdDateStr']
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
