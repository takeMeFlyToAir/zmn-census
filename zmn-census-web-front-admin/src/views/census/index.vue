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
              <el-table-column prop="h8" label="孕妇"> </el-table-column>
              <el-table-column prop="h5" label="住房类型"> </el-table-column>
              <el-table-column prop="h6" label="建筑面积"> </el-table-column>
              <el-table-column prop="h7" label="房间数"> </el-table-column>
              <el-table-column prop="h9" label="填报人类型"> </el-table-column>
              <el-table-column width="100px" label="操作" align="center"  class-name="small-padding">
                <template slot-scope="{row,$index}">
                  <el-button  size="mini" type="primary" @click="showHouseHold(row)">
                    编辑
                  </el-button>
                </template>
              </el-table-column>
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
              <el-table-column width="100px" label="操作" align="center"  class-name="small-padding">
                <template slot-scope="{row,$index}">
                  <el-button  size="mini" type="primary" @click="showPersonInfo(row)">
                    编辑
                  </el-button>
                </template>
              </el-table-column>
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
      <el-table-column width="150px" label="操作" align="center"  class-name="small-padding">
        <template slot-scope="{row,$index}">
          <el-button  size="mini" type="danger" @click="handleDelete(row.id)">
          删除
          </el-button>
          <el-button  size="mini" type="primary" @click="showRoomAddress(row.roomAddress)">
            编辑
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" @pagination="getList" />


    <el-dialog title="住址信息编辑" :visible.sync="roomAddressForDialogFormVisible" >
      <el-form :model="roomAddress" label-position="left" label-width="100px" >
        <el-form-item label="楼栋号：">
          <el-input v-model="roomAddress.buildNum" placeholder="楼栋号" />
        </el-form-item>
        <el-form-item label="单元号：">
          <el-input v-model="roomAddress.unitNum" placeholder="单元号" />
        </el-form-item>
        <el-form-item label="楼层：">
          <el-input v-model="roomAddress.floorNum" placeholder="楼层" />
        </el-form-item>
        <el-form-item label="房间号：">
          <el-input v-model="roomAddress.roomNum" placeholder="房间号" />
        </el-form-item>
        <el-form-item label="被访人电话：">
          <el-input v-model="roomAddress.fillPersonPhone" placeholder="被访人电话" />
        </el-form-item>
        <el-form-item label="普查员姓名：">
          <el-input v-model="roomAddress.examinePersonName" placeholder="普查员姓名" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetRoomAddress">取 消</el-button>
        <el-button type="primary" @click="submitRoomAddress">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="户主信息编辑" :visible.sync="houseHoldForDialogFormVisible"  >
      <el-form :model="houseHold" label-position="right" label-width="350px">
        <el-form-item label="H1.户别：">
          <el-select v-model="houseHold.h1" placeholder="户别">
            <el-option
              v-for="item in h1List"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="H2.2020年10月31日晚居住本户的人数：">
          <el-input v-model="houseHold.h2Live" />
        </el-form-item>
        <el-form-item label="H2.户口在本户，2020年10月31日晚未住本户的人数：">
          <el-input v-model="houseHold.h2NoLive" />
        </el-form-item>
        <el-form-item label="H3.男：">
          <el-input v-model="houseHold.h3Man" />
        </el-form-item>
        <el-form-item label="H3.女：">
          <el-input v-model="houseHold.h3Woman" />
        </el-form-item>
        <el-form-item label="H4.男：">
          <el-input v-model="houseHold.h4Man" />
        </el-form-item>
        <el-form-item label="H4.女：">
          <el-input v-model="houseHold.h4Woman" />
        </el-form-item>
        <el-form-item label="H5.您家中是否有怀孕6个月及以上的孕妇：">
          <el-select v-model="houseHold.h8" placeholder="孕妇">
            <el-option
              v-for="item in h8List"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="H6.住房类型：">
          <el-select v-model="houseHold.h5" placeholder="住房类型">
            <el-option
              v-for="item in h5List"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="H7.本户住房建筑面积：">
          <el-input v-model="houseHold.h6" />
        </el-form-item>
        <el-form-item label="H8.本户现在住房数：">
          <el-input v-model="houseHold.h7" />
        </el-form-item>
        <el-form-item label="H9.被访人类型：">
          <el-select v-model="houseHold.h9" placeholder="被访人类型">
            <el-option
              v-for="item in h9List"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetHouseHold">取 消</el-button>
        <el-button type="primary" @click="submitHouseHold">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="个人信息编辑" :visible.sync="personInfoForDialogFormVisible" >
      <el-form :model="personInfo"  label-position="right" label-width="230px">
        <el-form-item label="D1.姓名：">
          <el-input v-model="personInfo.d1" />
        </el-form-item>
        <el-form-item label="D2.与户主关系：">
          <el-select v-model="personInfo.d2" placeholder="与户主关系">
            <el-option
              v-for="item in d2List"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="D3.公民身份号码：">
          <el-input v-model="personInfo.d3" />
        </el-form-item>
        <el-form-item label="D4.性别：">
          <el-select v-model="personInfo.d4" placeholder="性别">
            <el-option
              v-for="item in d4List"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="D5.出生年月：">
          <el-input v-model="personInfo.d5" />
        </el-form-item>
        <el-form-item label="D6.民族：">
          <el-input v-model="personInfo.d6" />
        </el-form-item>
        <el-form-item label="D7.普查时点居住地：">
          <el-select v-model="personInfo.d7" placeholder="普查时点居住地">
            <el-option
              v-for="item in d7List"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="普查时点居住地不在本地的省：">
          <el-input v-model="personInfo.d7Province" />
        </el-form-item>
        <el-form-item label="普查时点居住地不在本地的市：">
          <el-input v-model="personInfo.d7City" />
        </el-form-item>
        <el-form-item label="普查时点居住地不在本地的县：">
          <el-input v-model="personInfo.d7County" />
        </el-form-item>
        <el-form-item label="D8.户口登记地：">
          <el-select v-model="personInfo.d8" placeholder="户口登记地">
            <el-option
              v-for="item in d8List"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="户口登记地不在本地的省：">
          <el-input v-model="personInfo.d8Province" />
        </el-form-item>
        <el-form-item label="户口登记地不在本地的市：">
          <el-input v-model="personInfo.d8City" />
        </el-form-item>
        <el-form-item label="户口登记地不在本地的县：">
          <el-input v-model="personInfo.d8County" />
        </el-form-item>
        <el-form-item label="D9.离开户口登记地时间：">
          <el-select v-model="personInfo.d9" placeholder="离开户口登记地时间">
            <el-option
              v-for="item in d9List"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="D10.离开户口登记地原因：">
          <el-select v-model="personInfo.d10" placeholder="离开户口登记地原因">
            <el-option
              v-for="item in d10List"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="D11.受教育程度：">
          <el-select v-model="personInfo.d11" placeholder="受教育程度">
            <el-option
              v-for="item in d11List"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="D12.是否识字：">
          <el-select v-model="personInfo.d12" placeholder="是否识字">
            <el-option
              v-for="item in d12List"
              :key="item"
              :label="item"
              :value="item">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetPersonInfo">取 消</el-button>
        <el-button type="primary" @click="submitPersonInfo">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { apiCensusSurveyGetList, apiCensusSurveyDelete, apiCensusSurveyEditRoomAddress, apiCensusSurveyEditHouseHold, apiCensusSurveyEditPersonInfo} from '@/api/censusSurvey'
  import { apiUserFindList} from '@/api/user'
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
        roomAddressForDialogFormVisible: false,
        houseHoldForDialogFormVisible: false,
        personInfoForDialogFormVisible: false,
        h1List: [
          '家庭户',
          '集体户'
        ],
        h5List:[
          '普通住房',
          '集体住所',
          '工作地住所',
          '其他住房',
          '无住房',
        ],
        h8List:[
          '是',
          '否'
        ],
        h9List:[
          '住户',
          '租户'
        ],
        roomAddress: {
          id: '',
          buildNum: '',
          unitNum: '',
          floorNum: '',
          roomNum: '',
          fillPersonPhone: '',
          examinePersonName: '',
        },
        houseHold: {
          id: '',
          h1: '',
          h2Live: '',
          h2NoLive: '',
          h3Man: '',
          h3Woman: '',
          h4Man: '',
          h4Woman: '',
          h5: '',
          h6: '',
          h7: '',
          h8: '',
          h9: '',
        },
        d2List: [
          '户主',
          '配偶',
          '子女',
          '父母',
          '岳父母或公婆',
          '祖父母',
          '媳婿',
          '孙子女',
          '兄弟姐妹',
          '其他',
        ],
        d4List: [
          '男',
          '女'
        ],
        d7List: [
          '本普查小区',
          '本村（居）委会其他普查小区',
          '本乡（镇、街道）其他村（居）委会',
          '本县（市、区、旗）其他乡（镇、街道）',
          '其他县（市、区、旗），请在下面填写地址',
          '香港特别行政区、澳门特别行政区、台湾地区',
          '国外',
        ],
        d8List: [
          '本村（居）委会',
          '本乡（镇、街道）其他村（居）委会',
          '本县（市、区、旗）其他乡（镇、街道）',
          '其他县（市、区、旗），请在下面填写地址',
          '户口待定',
        ],
        d9List: [
          '没有离开户口登记地',
          '不满半年',
          '半年以上',
          '一年',
          '一年以上，不满二年',
          '二年以上，不满三年',
          '三年以上，不满四年',
          '四年以上，不满五年',
          '五年以上，不满十年',
          '十年以上',
        ],
        d10List:[
          '工作就业',
          '学习培训',
          '随同离开/投亲靠友',
          '拆迁/搬家',
          '寄挂户口',
          '婚姻嫁娶',
          '照料孙子女',
          '为子女就学',
          '养老/康养',
          '其他',
        ],
        d11List:[
          '未上过学',
          '学前教育',
          '小学',
          '初中',
          '高中',
          '大学专科',
          '大学本科',
          '硕士研究生',
          '博士研究生 ',
        ],
        d12List:[
          '是',
          '否',
        ],
        personInfo: {
          id: '',
          d1: '',
          d2: '',
          d3: '',
          d4: '',
          d5: '',
          d6: '',
          d7: '',
          d7Province: '',
          d7City: '',
          d7County: '',
          d8: '',
          d8Province: '',
          d8City: '',
          d8County: '',
          d9: '',
          d10: '',
          d11: '',
          d12: '',
        }
      }
    },
    created() {
      this.getList()
      this.getUserList()
    },
    methods: {
      showRoomAddress(data){
        this.roomAddress = Object.assign({}, data) // copy obj
        this.roomAddressForDialogFormVisible = true;
        console.log(this.roomAddress)
      },
      showHouseHold(data){
        this.houseHold = Object.assign({}, data) // copy obj
        this.houseHoldForDialogFormVisible = true;
      },
      showPersonInfo(data){
        this.personInfo = Object.assign({}, data) // copy obj
        this.personInfoForDialogFormVisible = true;
      },
      submitRoomAddress(){
        console.log(this.roomAddress)
        this.$confirm('确定修改地址数据', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          apiCensusSurveyEditRoomAddress(this.roomAddress).then(() => {
            this.resetRoomAddress();
            this.handleFilter();
            this.$notify({
              title: '修改地址信息',
              message: '修改成功',
              type: 'success',
              duration: 3000
            })
          })
        })
      },
      submitHouseHold(){
        console.log(this.houseHold)
        this.$confirm('确定修改户主数据', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          apiCensusSurveyEditHouseHold(this.houseHold).then(() => {
            this.resetHouseHold();
            this.handleFilter();
            this.$notify({
              title: '修改户主信息',
              message: '修改成功',
              type: 'success',
              duration: 3000
            })
          })
        })
      },
      submitPersonInfo(){
        console.log(this.personInfo)
        this.$confirm('确定修改个人数据', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          apiCensusSurveyEditPersonInfo(this.personInfo).then(() => {
            this.resetPersonInfo();
            this.handleFilter();
            this.$notify({
              title: '修改个人信息',
              message: '修改成功',
              type: 'success',
              duration: 3000
            })
          })
        })

      },
      resetRoomAddress(){
        this.roomAddress = {
          id: '',
          buildNum: '',
          unitNum: '',
          floorNum: '',
          roomNum: '',
          fillPersonPhone: '',
          examinePersonName: '',
        }
        this.roomAddressForDialogFormVisible = false;

      },
      resetHouseHold(){
        this.houseHold= {
            id: '',
            h1: '',
            h2Live: '',
            h2NoLive: '',
            h3Man: '',
            h3Woman: '',
            h4Man: '',
            h4Woman: '',
            h5: '',
            h6: '',
            h7: '',
            h8: '',
            h9: '',
        };
        this.houseHoldForDialogFormVisible = false;
      },
      resetPersonInfo(){
        this.personInfo = {
          id: '',
          d1: '',
          d2: '',
          d3: '',
          d4: '',
          d5: '',
          d6: '',
          d7: '',
          d7Province: '',
          d7City: '',
          d7County: '',
          d8: '',
          d8Province: '',
          d8City: '',
          d8County: '',
          d9: '',
          d10: '',
          d11: '',
          d12: '',
        };
        this.personInfoForDialogFormVisible = false;

      },
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
