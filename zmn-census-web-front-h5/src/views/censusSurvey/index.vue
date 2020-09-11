<!-- home -->
<template>
  <div class="index-container">
    <van-form @submit="onSubmit" @failed="onFailed" >
      <van-collapse v-model="activeNames"  >
        <!-- 地址信息 -->
        <van-collapse-item size="large" title="地址信息" name="roomAddress">
          <van-field :disabled='submitButtonDisable' v-model="roomAddress.province" label="省(区、市)：" label-width="8em" placeholder="陕西省"
                     readonly
                     :rules="[{ required: true, message: '请填写省' }]"
          />
          <van-field :disabled='submitButtonDisable' v-model="roomAddress.city" label="市(地、州、盟)：" label-width="8em" placeholder="西安市"
                     readonly
                     :rules="[{ required: true, message: '请填写市' }]"
          />
          <van-field :disabled='submitButtonDisable' v-model="roomAddress.county" label="县(市、区、旗)：" label-width="8em" placeholder="浐灞区"
                     readonly
                     :rules="[{ required: true, message: '请填写区' }]"
          />
          <van-field :disabled='submitButtonDisable' required readonly clickable label="乡(镇、街道)："  name="乡(镇、街道)" :value="roomAddress.town" label-width="8em" placeholder="请选择" @click="showPickerTown = true"
                     :rules="[{ required: true, message:'请选择乡(镇、街道)' }]"
          />
          <van-popup v-model="showPickerTown" round position="bottom">
            <van-picker
              title="乡(镇、街道)"
              show-toolbar
              :columns="townList"
              @cancel="showPickerTown = false"
              @confirm="choiceTown"
            />
          </van-popup>
          <van-field :disabled='submitButtonDisable' required readonly clickable label="普查区：" name="普查区"  :value="roomAddress.village" label-width="8em" placeholder="请选择" @click="clickVillage"
                     :rules="[{ required: true , message:'请选择普查区' }]"
          />
          <van-popup v-model="showPickerVillage" round position="bottom">
            <van-picker
              title="普查区"
              show-toolbar
              :columns="villageList"
              @cancel="showPickerVillage = false"
              @confirm="choiceVillage"
            />
          </van-popup>
          <van-field :disabled='submitButtonDisable' required readonly clickable label="住宅小区：" name="住宅小区"  :value="roomAddress.community" label-width="8em" placeholder="请选择" @click="clickCommunity"
                     :rules="[{ required: true, message:'请选择住宅小区' }]"
          />
          <van-popup v-model="showPickerCommunity" round position="bottom">
            <van-picker
              title="住宅小区"
              show-toolbar
              :columns="communityList"
              @cancel="showPickerCommunity = false"
              @confirm="choiceCommunity"
            />
          </van-popup>
          <van-field :disabled='submitButtonDisable' v-model="roomAddress.buildNum" label="楼栋号："  type="digit" label-width="8em" placeholder="输入楼栋号"
          />
          <van-field :disabled='submitButtonDisable' v-model="roomAddress.unitNum" label="单元号："  type="digit" label-width="8em" placeholder="输入单元号"
          />
          <van-field :disabled='submitButtonDisable' v-model="roomAddress.floorNum" label="楼层："  type="digit" label-width="8em" placeholder="输入楼层号"
          />
          <van-field :disabled='submitButtonDisable' required v-model="roomAddress.roomNum" label="房间号："  name="房间号"    label-width="8em" placeholder="输入楼栋号"
                     :rules="[{ required: true , message:'请填写房间号'}]"
          />
          <van-field :disabled='submitButtonDisable' required v-model="roomAddress.fillPersonPhone" label="填报人电话：" name="填报人电话"   type="tel"    label-width="8em" placeholder="输入填报人电话"
                     :rules="[{ pattern:mobilePattern, message: '请输入正确的电话' }]"
          />
        </van-collapse-item>
        <!-- 住户信息 -->
        <van-collapse-item size="large" title="住户信息" name="houseHold" >
          <div class="houseHold_item">
            <p class="houseHold_title">H1.户别：</p>
            <van-field :disabled='submitButtonDisable' required readonly clickable label="户别："  name="户别"  :value="houseHold.h1" label-width="5em" placeholder="请选择" @click="showPickerH1 = true"
                       :rules="[{ required: true , message:'请选择户别' }]"
            />
            <van-popup v-model="showPickerH1" round position="bottom">
              <van-picker
                title="户别"
                show-toolbar
                :columns="h1List"
                @cancel="showPickerH1 = false"
                @confirm="choiceH1"
              />
            </van-popup>
          </div>

          <div class="houseHold_item">
            <p class="houseHold_title">H2.本户应登记人数：</p>
            <van-field :disabled='submitButtonDisable'  required name="stepper" label-width="12em" label="2020年10月31日晚居住本户的人数：">
              <template #input>
                <van-stepper v-model="houseHold.h2Live" min="0" />
              </template>
            </van-field>
            <van-field :disabled='submitButtonDisable'  required name="stepper" label-width="12em" label="户口在本户，2020年10月31日晚未住本户的人数：">
              <template #input>
                <van-stepper v-model="houseHold.h2NoLive"  min="0" />
              </template>
            </van-field>
          </div>

          <div class="houseHold_item">
            <p class="houseHold_title">H3.本户2019年11月1日至2020年10月31日期间的出生人口：</p>
            <van-field :disabled='submitButtonDisable'  required name="stepper" label-width="5em" label="男：">
              <template #input>
                <van-stepper v-model="houseHold.h3Man" min="0" />
              </template>
            </van-field>
            <van-field :disabled='submitButtonDisable'  required name="stepper" label-width="5em" label="女：">
              <template #input>
                <van-stepper v-model="houseHold.h3Woman"  min="0" />
              </template>
            </van-field>
          </div>

          <div class="houseHold_item">
            <p class="houseHold_title">H4.本户2019年11月1日至2020年10月31日期间的死亡人口：</p>
            <van-field :disabled='submitButtonDisable'  required name="stepper" label-width="5em" label="男：">
              <template #input>
                <van-stepper v-model="houseHold.h4Man" min="0" />
              </template>
            </van-field>
            <van-field :disabled='submitButtonDisable'  required name="stepper" label-width="5em" label="女：">
              <template #input>
                <van-stepper v-model="houseHold.h4Woman"  min="0" />
              </template>
            </van-field>
          </div>

          <div class="houseHold_item">
            <p class="houseHold_title">H5.住房类型：</p>
            <van-field :disabled='submitButtonDisable' required readonly clickable label="住房类型："  name="住房类型" :value="houseHold.h5" label-width="5em" placeholder="请选择" @click="showPickerH5 = true"
                       :rules="[{ required: true, message:'请选择住房类型' }]"
            />
            <van-popup v-model="showPickerH5" round position="bottom">
              <van-picker
                title="户别"
                show-toolbar
                :columns="h5List"
                @cancel="showPickerH5 = false"
                @confirm="choiceH5"
              />
            </van-popup>
          </div>

          <div  class="houseHold_item" v-if="isShowH6AndH7">
            <p class="houseHold_title">H6.本户住房建筑面积：</p>
            <van-field :disabled='submitButtonDisable'  required name="stepper" label-width="5em" label="平方米：">
              <template #input>
                <van-stepper v-model="houseHold.h6" min="1" />
              </template>
            </van-field>
          </div>

          <div  class="houseHold_item" v-if="isShowH6AndH7">
            <p class="houseHold_title">H7.本户现在住房数：</p>
            <van-field :disabled='submitButtonDisable'  required name="stepper" label-width="5em" label="间：">
              <template #input>
                <van-stepper v-model="houseHold.h7" min="1" />
              </template>
            </van-field>
          </div>
          <div class="houseHold_item">
            <p class="houseHold_title">H8.您家中是否有6个月及以上的孕妇：</p>
            <van-field :disabled='submitButtonDisable' required readonly clickable label="是/否："  name="孕妇" :value="houseHold.h8" label-width="5em" placeholder="请选择" @click="showPickerH8 = true"
                       :rules="[{ required: true, message:'请选择是/否' }]"
            />
            <van-popup v-model="showPickerH8" round position="bottom">
              <van-picker
                title="孕妇"
                show-toolbar
                :columns="h8List"
                @cancel="showPickerH8 = false"
                @confirm="choiceH8"
              />
            </van-popup>
          </div>
          <div class="houseHold_item">
            <p class="houseHold_title">H9.填报人类型：</p>
            <van-field :disabled='submitButtonDisable' required readonly clickable label="类型：" name="类型"  :value="houseHold.h9" label-width="5em" placeholder="请选择" @click="showPickerH9 = true"
                       :rules="[{ required: true, message:'请选择填报人类型' }]"
            />
            <van-popup v-model="showPickerH9" round position="bottom">
              <van-picker
                title="填报人类型"
                show-toolbar
                :columns="h9List"
                @cancel="showPickerH9 = false"
                @confirm="choiceH9"
              />
            </van-popup>
          </div>
        </van-collapse-item>
        <!-- 个人信息列表 -->
        <van-collapse-item size="large" title="个人信息列表" name="personInfo">
          <van-cell v-if="!submitButtonDisable" title="新增个人信息"  @click="addPerson"  icon="add-o">
          </van-cell>

          <van-collapse v-model="activePersonInfoList">
            <van-collapse-item size="large" v-for="(item, index) in personInfoList" :name="index" >
              <template #title>
                <div>
                  <span>{{(index+1)+':  '+item.d1}}</span>
                  <div style="float: right" v-if="index != 0 && !submitButtonDisable ">
                    <van-button :style="{marginRight:'30px'}" round  icon="cross" type="danger"  size="small" @click="deletePerson(index)"/>
                  </div>
                </div>
              </template>
              <van-field :disabled='submitButtonDisable' required v-model="item.d1" label="D1.姓名："  name="姓名" label-width="8em" placeholder="输入姓名"
                         :rules="[{ required: true,message:'请填写姓名' }]"
              />
              <van-field required readonly clickable :disabled="index == 0 || submitButtonDisable" label="D2.与户主关系："
                         name="与户主关系"  :value="item.d2" label-width="8em" placeholder="请选择" @click="clickD2(index)"
                         :rules="[{ required: true,message:'请选择户主关系' }]"
              />
              <van-popup v-model="item.showPickerD2" round position="bottom">
                <van-picker
                  title="与户主关系"
                  show-toolbar
                  :columns="d2List"
                  @cancel="item.showPickerD2 = false"
                  @confirm="choiceD2"
                />
              </van-popup>
              <van-field :disabled='submitButtonDisable' required v-model="item.d3" label="D3.公民身份证号：" name="身份证号"  label-width="8em" placeholder="输入身份证号"
                         @blur="showD11AndD12(index)"
                         :rules="[{ pattern:idCardNumPattern, message: '请输入正确的身份证号' }]"
              />

              <van-field :disabled='submitButtonDisable' required readonly clickable label="D4.性别：" name="性别" :value="item.d4" label-width="8em" placeholder="请选择" @click="recordCurrentPersonInfoIndex(index,'showPickerD4')"
                         :rules="[{ required: true ,message:'请选择性别'}]"
              />
              <van-popup v-model="item.showPickerD4" round position="bottom">
                <van-picker
                  title="性别"
                  show-toolbar
                  :columns="d4List"
                  @cancel="item.showPickerD4 = false"
                  @confirm="choiceD4"
                />
              </van-popup>
              <van-field :disabled='submitButtonDisable' required readonly clickable label="D5.出生年月："  name="出生年月"  :value="item.d5" label-width="8em" placeholder="请选择" @click="recordCurrentPersonInfoIndex(index,'showPickerD5')"
                         :rules="[{ required: true,message:'请选择出生年月' }]"
              />
              <van-popup v-model="item.showPickerD5" round position="bottom">
                <van-datetime-picker
                  v-model="currentDate"
                  type="year-month"
                  title="选择年月"
                  :min-date="minDate"
                  :max-date="maxDate"
                  :formatter="formatter"
                  @cancel="item.showPickerD5 = false"
                  @confirm="choiceD5"
                />
              </van-popup>
              <van-field :disabled='submitButtonDisable' required v-model="item.d6" label="D6.民族："   name="民族"  label-width="8em" placeholder="输入民族"
                         :rules="[{ required: true,message:'请填写民族' }]"
              />

              <van-field :disabled='submitButtonDisable' required readonly clickable label="D7.普查时点居住地：" name="普查时点居住地" :value="item.d7" label-width="8em" placeholder="请选择"
                         @click="recordCurrentPersonInfoIndex(index,'showPickerD7')"
                         :rules="[{ required: true,message:'请选择普查时点居住地' }]"
              />
              <van-popup v-model="item.showPickerD7" round position="bottom">
                <van-picker
                  title="普查时点居住地"
                  show-toolbar
                  :columns="d7List"
                  @cancel="item.showPickerD7 = false"
                  @confirm="choiceD7"
                />
              </van-popup>
              <div v-if="item.isShowD7Other" style="margin-left: 50px">
                <van-field :disabled='submitButtonDisable' required v-model="item.d7Province" label="省（区、市）"  label-width="8em" placeholder="输入省"
                           :rules="[{ required: true }]"
                />
                <van-field :disabled='submitButtonDisable' required v-model="item.d7City" label="市（地、州、盟）"   label-width="8em" placeholder="输入市"
                           :rules="[{ required: true }]"
                />
                <van-field :disabled='submitButtonDisable' required v-model="item.d7County" label="县（市、区、旗）"   label-width="8em" placeholder="输入县"
                           :rules="[{ required: true }]"
                />
              </div>

              <van-field :disabled='submitButtonDisable' required readonly clickable label="D8.户口登记地：" name="户口登记地" :value="item.d8" label-width="8em" placeholder="请选择"
                         @click="recordCurrentPersonInfoIndex(index,'showPickerD8')"
                         :rules="[{ required: true, message:'请选择户口登记地' }]"
              />
              <van-popup v-model="item.showPickerD8" round position="bottom">
                <van-picker
                  title="普查时点居住地"
                  show-toolbar
                  :columns="d8List"
                  @cancel="item.showPickerD8 = false"
                  @confirm="choiceD8"
                />
              </van-popup>
              <div v-if="item.isShowD8Other" style="margin-left: 50px">
                <van-field :disabled='submitButtonDisable' required v-model="item.d8Province" label="省（区、市）"  label-width="8em" placeholder="输入省"
                           :rules="[{ required: true }]"
                />
                <van-field :disabled='submitButtonDisable' required v-model="item.d8City" label="市（地、州、盟）"   label-width="8em" placeholder="输入市"
                           :rules="[{ required: true }]"
                />
                <van-field :disabled='submitButtonDisable' required v-model="item.d8County" label="县（市、区、旗）"   label-width="8em" placeholder="输入县"
                           :rules="[{ required: true }]"
                />
              </div>
             <div v-if="item.isShowD9">
               <van-field :disabled='submitButtonDisable' required readonly clickable label="D9.离开户口登记地时间："  name="离开户口登记地时间":value="item.d9" label-width="8em" placeholder="请选择"
                          @click="recordCurrentPersonInfoIndex(index,'showPickerD9')"
                          :rules="[{ required: true, message:'请选择离开户口登记地时间' }]"
               />
               <van-popup v-model="item.showPickerD9" round position="bottom">
                 <van-picker
                   title="普查时点居住地"
                   show-toolbar
                   :columns="d9List"
                   @cancel="item.showPickerD9 = false"
                   @confirm="choiceD9"
                 />
               </van-popup>
             </div>
              <div v-if="item.isShowD10">
                <van-field :disabled='submitButtonDisable' required readonly clickable label="D10.离开户口登记地原因：" name="离开户口登记地原因" :value="item.d10" label-width="8em" placeholder="请选择"
                           @click="recordCurrentPersonInfoIndex(index,'showPickerD10')"
                           :rules="[{ required: true,message:'请选择离开户口登记地原因' }]"
                />
                <van-popup v-model="item.showPickerD10" round position="bottom">
                  <van-picker
                    title="离开户口登记地原因"
                    show-toolbar
                    :columns="d10List"
                    @cancel="item.showPickerD10 = false"
                    @confirm="choiceD10"
                  />
                </van-popup>
              </div>

              <div v-if="item.isShowD11">
                <van-field :disabled='submitButtonDisable' required readonly clickable label="D11.受教育程度：" name="受教育程度" :value="item.d11" label-width="8em" placeholder="请选择"
                           @click="recordCurrentPersonInfoIndex(index,'showPickerD11')"
                           :rules="[{ required: true ,message:'请选择受教育程度'}]"
                />
                <van-popup v-model="item.showPickerD11" round position="bottom">
                  <van-picker
                    title="受教育程度"
                    show-toolbar
                    :columns="d11List"
                    @cancel="item.showPickerD11 = false"
                    @confirm="choiceD11"
                  />
                </van-popup>
              </div>

              <div v-if="item.isShowD12">
                <van-field :disabled='submitButtonDisable' required readonly clickable label="D12.是否识字：" name="是否识字" :value="item.d12" label-width="8em" placeholder="请选择"
                           @click="recordCurrentPersonInfoIndex(index,'showPickerD12')"
                           :rules="[{ required: true, message:'请选择是否识字' }]"
                />
                <van-popup v-model="item.showPickerD12" round position="bottom">
                  <van-picker
                    title="受教育程度"
                    show-toolbar
                    :columns="d12List"
                    @cancel="item.showPickerD12 = false"
                    @confirm="choiceD12"
                  />
                </van-popup>
              </div>
            </van-collapse-item>
          </van-collapse>

        </van-collapse-item>
        <!-- 其他信息 -->
        <van-collapse-item size="large" title="其他信息" name="otherInfo">
          <van-field :disabled='submitButtonDisable' v-model="roomAddress.examinePersonName" label="普查员姓名：" label-width="8em" placeholder="输入姓名"
          />
          <van-field
            :disabled='submitButtonDisable'
            v-model="roomAddress.remark"
            rows="2"
            label-width="8em"
            autosize
            label="备注："
            type="textarea"
            maxlength="100"
            placeholder="请输入备注"
            show-word-limit
          />
        </van-collapse-item>
      </van-collapse>
      <div style="margin: 16px;">
        <van-button round block type="info" native-type="submit" :disabled="submitButtonDisable">
          {{submitButtonText}}
        </van-button>
      </div>
    </van-form>

  </div>
</template>

<script>
  import { community_getById,community_findAllTown, community_findVillageByTown, community_findCommunityByTownAndVillage,  } from '@/api/community.js'
  import { censusSurvey_save } from '@/api/censusSurvey.js'

  import { formatDate,getBirthYearAndMonthByIdNo,getBirthdayByIdNo } from '@/utils/index.js'

  export default {
  data() {
    return {
      submitButtonText:'提交',
      submitButtonDisable:false,
      idCardNumPattern: /^(\d{15}|\d18|^\d{17}(\d|X|x))$/,
      mobilePattern: /^1[3456789]\d{9}$/,
      idCardNum: /\d{6}/,
      minDate: new Date(1900, 1, 1),
      maxDate: new Date(2020, 12, 30),
      currentDate: new Date(),
      activeNames:['roomAddress', 'houseHold', 'personInfo'],
      // activeNames:[],
      showPickerTown: false,
      showPickerVillage: false,
      showPickerCommunity: false,
      townList: [],
      villageList: [],
      communityList: [],
      roomAddress: {
        province: '陕西省',
        city: '西安市',
        county : '浐灞区',
        town: '',
        village: '',
        community: '',
        buildNum: '',
        unitNum: '',
        floorNum: '',
        roomNum: '',
        fillPersonPhone: '',
        examinePersonName: '',
        remark: '',
      },
      isShowH6AndH7: true,
      showPickerH1: false,
      showPickerH5: false,
      showPickerH8: false,
      showPickerH9: false,
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
      houseHold:{
        h1: "",//户别
        h2Live: "3",//本户应登记人数,2020年10月31日晚居住本户的人数 _____人
        h2NoLive: "0",//户口在本户，2020年10月31日晚未住本户的人数 _____人
        h3Man: "0",//本户2019年11月1日至2020年10月31日期间的出生人口,男人
        h3Woman: "0",//本户2019年11月1日至2020年10月31日期间的出生人口,女人
        h4Man: "0",//本户2019年11月1日至2020年10月31日期间的死亡人口,男人
        h4Woman: "0",//本户2019年11月1日至2020年10月31日期间的死亡人口,女人
        h5: "",//住房类型,h5List,（选择3-6的，跳至个人项目。）
        h6: "1",//本户住房建筑面积
        h7: "1",//本户住房间数
        h8: "",//您家中是否有6个月及以上的孕妇（是/否）
        h9: "",//填报人类型：住户，租户
      },
      activePersonInfoList:[],
      currentPersonInfoIndex: null,
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
         '博士研 ',
      ],
      d12List:[
         '是',
         '否',
      ],
      showD11Condition:"2017-10-31",
      showD12Condition:"2005-10-31",
      personInfo: {
        isShowD7Other:false,
        isShowD8Other:false,
        isShowD9:true,
        isShowD10:true,
        isShowD11:true,
        isShowD12:true,
        showPickerD2:false,
        showPickerD4:false,
        showPickerD5:false,
        showPickerD7:false,
        showPickerD8:false,
        showPickerD9:false,
        showPickerD10:false,
        showPickerD11:false,
        showPickerD12:false,
        d1: "",//姓名
        d2: "",//与户主关系
        d3: "",//公民身份号码
        d4: "",//性别
        d5: "",//出生年月
        d6: "",//民族
        d7: "",//普查时点居住地
        d7Province: "",//普查时点居住地不在本地的省
        d7City: "",//普查时点居住地不在本地的市
        d7County: "",//普查时点居住地不在本地的县
        d8: "",//户口登记地
        d8Province: "",//户口登记地不在本地的省
        d8City: "",//户口登记地不在本地的市
        d8County: "",//户口登记地不在本地的县
        d9: "",//离开户口登记地时间
        d10: "",//离开户口登记地原因
        d11: "",//是否识字
        d12: "",//受教育程度
      },
      personInfoList: [

      ]
    }
  },
  created(){
    this.dealLinkParam(this.$route.query)
    this.getTownList();
    let person = Object.assign({},this.personInfo)
    person.d2 = '户主'
    this.personInfoList.push(person)
    let length = this.personInfoList.length
    this.activePersonInfoList.splice(length,0,length-1)
  },
  computed: {
  },

  mounted() { },

  methods: {
    onSubmit(values) {
      let validateResult = true;
      let validateMsg = "";
      //校验个人项目中的出生年月和身份证号是否匹配
      this.personInfoList.forEach((item,index) => {
        let idCardNumBirthYearAndMonth = getBirthYearAndMonthByIdNo(item.d3)
        if(idCardNumBirthYearAndMonth != item.d5){
          validateResult = false
          validateMsg=item.d1+"的身份证和出生年月不匹配，请检查"
          return
        }
      })
      if(!validateResult){
        this.$toast.fail(validateMsg)
        return
      }
      console.log("onSubmit",values)
      let censusSurvey = {
        roomAddress: this.roomAddress,
        houseHold: this.houseHold,
        personInfoList: this.personInfoList
      }
      console.log(censusSurvey);
      this.$dialog.confirm({
        title: '人口普查问卷',
        message: '确认提交吗',
      })
        .then(() => {
          // on confirm
          this.activeNames = []
          this.activePersonInfoList = []
          this.submitButtonDisable = true;
          this.submitButtonText='提交中'
          censusSurvey_save(censusSurvey)
            .then((res) => {
              if(res.code == 200){
                this.submitButtonText='提交完成'
                this.$toast.success("提交成功，衷心感谢您的参与！")
              }else{
                console.log(res)
                this.submitButtonText='提交'
                this.submitButtonDisable = false;
                this.$toast.fail("提交失败")
              }
            })
            .catch(() => {
              this.submitButtonText='提交'
              this.submitButtonDisable = false;
            })
        })
        .catch(() => {
          // on cancel
          console.log('cancel')
        });
    },
    onFailed(errorInfo){
      console.log("onFailed",errorInfo)
      this.$toast.fail("请填写所有的必填项，注意输入框字体是红色的！")
    },
    dealLinkParam(query){
      if(query && query.hasOwnProperty("id")){
        let param = {id: query.id}
        community_getById(param)
          .then((res) => {
            if(res.code == 200){
              this.roomAddress.town = res.data.town;
              this.roomAddress.village = res.data.village;
              this.roomAddress.community = res.data.name;
            }
          })
          .catch(() => { })
      }
    },
    formatter(type, val) {
      if (type === 'year') {
        return `${val}年`;
      } else if (type === 'month') {
        return `${val}月`;
      }
      return val;
    },
    addPerson(){
      let length = this.personInfoList.length
      let maxPersonCount = parseInt(this.houseHold.h2Live)+parseInt(this.houseHold.h2NoLive)
      if(length >= maxPersonCount){
        this.$toast.fail("能够添加的多的个人项目总和是上述H1问题的两数之和")
        return
      }
      this.personInfoList.push(Object.assign({},this.personInfo))
      length = this.personInfoList.length
      this.activePersonInfoList.splice(length,0,length-1)
    },
    deletePerson(index){
      this.personInfoList.splice(index,1)
      let _this = this
      setTimeout(function() {
        let result = []
        for(let i =0; i < _this.activePersonInfoList.length; i++){
          if(_this.activePersonInfoList[i] < index){
            result.push(_this.activePersonInfoList[i])
          }else if(_this.activePersonInfoList[i] > index){
            result.push(_this.activePersonInfoList[i]-1)
          }
        }
        _this.activePersonInfoList = result
      }, 10 )

    },
    clickD2(index,showPicker){
      if(index != 0){
        this.personInfoList[index].showPickerD2 = true;
      }
      this.currentPersonInfoIndex = index;
    },
    recordCurrentPersonInfoIndex(index,showPicker){
      this.personInfoList[index][showPicker] = true;
      this.currentPersonInfoIndex = index;
    },
    getTownList(){
      community_findAllTown()
        .then((res) => {
          if(res.code == 200){
            this.townList = res.data
          }
        })
        .catch(() => { })
    },
    choiceTown(value,index) {
      this.roomAddress.town = value
      //每次选择town，都要清空village和community
      this.clearVillage()
      this.clearCommunity()
      let param = {
        town: this.roomAddress.town
      }
      community_findVillageByTown(param)
        .then((res) => {
          this.showPickerTown = false;
          if(res.code == 200){
            this.villageList = res.data
          }
        })
        .catch(() => { })
    },
    choiceVillage(value,index) {
      this.roomAddress.village = value
      //每次选择village，都要清空community
      this.clearCommunity()
      let param = {
        town: this.roomAddress.town,
        village: this.roomAddress.village
      }
      community_findCommunityByTownAndVillage(param)
        .then((res) => {
          this.showPickerVillage = false;
          if(res.code == 200){
            this.communityList = res.data
          }
        })
        .catch(() => { })
    },
    choiceCommunity(value,index) {
      this.roomAddress.community = value
      this.showPickerCommunity = false;
    },
    choiceH1(value, index){
      this.houseHold.h1 = value;
      this.showPickerH1 = false;
    },
    choiceH5(value, index){
      this.houseHold.h5 = value;
      this.showPickerH5 = false;
      if(index !== 0){
        this.isShowH6AndH7 = false;
      }else {
        this.isShowH6AndH7 = true;
      }
    },
    choiceH8(value, index){
      this.houseHold.h8 = value;
      this.showPickerH8 = false;
    },
    choiceH9(value, index){
      this.houseHold.h9 = value;
      this.showPickerH9 = false;
    },
    choiceD2(value, index){
      this.personInfoList[this.currentPersonInfoIndex].d2 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD2 = false
    },
    choiceD4(value, index){
      this.personInfoList[this.currentPersonInfoIndex].d4 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD4 = false
    },
    choiceD5(value, index){
      this.personInfoList[this.currentPersonInfoIndex].d5 = formatDate(value)
      this.personInfoList[this.currentPersonInfoIndex].showPickerD5 = false
    },
    choiceD7(value, index){
      this.personInfoList[this.currentPersonInfoIndex].d7 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD7 = false
      if(index == 4){
        this.personInfoList[this.currentPersonInfoIndex].isShowD7Other = true;
      }else {
        this.personInfoList[this.currentPersonInfoIndex].isShowD7Other = false;
      }
    },
    choiceD8(value, index){
      this.personInfoList[this.currentPersonInfoIndex].d8 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD8 = false
      if(index == 3){
        this.personInfoList[this.currentPersonInfoIndex].isShowD8Other = true;
      }else {
        this.personInfoList[this.currentPersonInfoIndex].isShowD8Other = false;
      }
      if(index == 4){
        this.personInfoList[this.currentPersonInfoIndex].isShowD9 = false;
        this.personInfoList[this.currentPersonInfoIndex].isShowD10 = false;
      }else {
        this.personInfoList[this.currentPersonInfoIndex].isShowD9 = true;
        this.personInfoList[this.currentPersonInfoIndex].isShowD10 = true;
      }
    },
    choiceD9(value, index){
      this.personInfoList[this.currentPersonInfoIndex].d9 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD9 = false
      if(index == 0){
        this.personInfoList[this.currentPersonInfoIndex].isShowD10 = false;
      }else {
        this.personInfoList[this.currentPersonInfoIndex].isShowD10 = true;
      }
    },
    choiceD10(value, index){
      this.personInfoList[this.currentPersonInfoIndex].d10 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD10 = false
    },
    choiceD11(value, index){
      this.personInfoList[this.currentPersonInfoIndex].d11 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD11 = false
    },
    choiceD12(value, index){
      this.personInfoList[this.currentPersonInfoIndex].d12 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD12 = false
    },
    showD11AndD12(index){
      let idCardNum = this.personInfoList[index].d3
      if(!this.idCardNumPattern.test(idCardNum)){
        return
      }
      let birthdayByIdNo = getBirthdayByIdNo(idCardNum)
      if(birthdayByIdNo >= this.showD11Condition){
        this.personInfoList[index].isShowD11 = false
      }else{
        this.personInfoList[index].isShowD11 = true
      }
      if(birthdayByIdNo >= this.showD12Condition){
        this.personInfoList[index].isShowD12 = false
      }else{
        this.personInfoList[index].isShowD12 = true
      }

    },
    clickVillage(){
      if(!this.roomAddress.town){
        this.$toast.fail("请先选择乡(镇、街道)")
        return;
      }
      this.showPickerVillage = true
    },
    clickCommunity(){
      if(!this.roomAddress.town){
        this.$toast.fail("请先选择乡(镇、街道)")
        return;
      }
      if(!this.roomAddress.village){
        this.$toast.fail("请先选择普查小区")
        return;
      }
      this.showPickerCommunity = true
    },
    clearVillage(){
      this.roomAddress.village=''
      this.villageList=[]
    },
    clearCommunity(){
      this.roomAddress.community=''
      this.communityList=[]
    }
  }
}
</script>
<style lang="scss" scoped>
  .collapse_title{
    font-size: 1.5em;
  }
.index-container {
  .warpper {
    padding: 12px;
    background: #fff;
    .demo-home__title {
      margin: 0 0 6px;
      font-size: 32px;
      .demo-home__title img,
      .demo-home__title span {
        display: inline-block;
        vertical-align: middle;
      }
      img {
        width: 32px;
      }
      span {
        margin-left: 16px;
        font-weight: 500;
      }
    }
    .demo-home__desc {
      margin: 0 0 20px;
      color: rgba(69, 90, 100, 0.6);
      font-size: 14px;
    }
  }
}
</style>
