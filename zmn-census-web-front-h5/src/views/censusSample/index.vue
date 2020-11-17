<!-- home -->
<template>
  <div class="page">
    <van-form @submit="onSubmit" @failed="onFailed">
      <div class="div_top">
        <div class="div_top_text">
          人口普查抽样
        </div>
      </div>
      <div class="div_content">
        <van-collapse v-model="activeNames">
          <!-- 地址信息 -->
          <van-collapse-item size="large" name="roomAddress" class="big_title">
            <template #title>
              <div>地址信息</div>
            </template>
            <van-cell-group>
              <van-field
                center
                v-model="roomAddressId"
                label="唯一编码"
                placeholder="唯一编码"
                :rules="[{required : true, message: '唯一编码' }]"
                @click-icon="roomAddressId = ''"
              >
                <van-button slot="button" native-type="button" size="small" type="primary" @click="getCensusSurveyById">
                  查询
                </van-button>
              </van-field>
            </van-cell-group>
            <van-field :disabled='queryDisable' v-model="roomAddress.province" label="省(区、市)：" label-width="8em"
                       placeholder="陕西省"
                       readonly
            />
            <van-field :disabled='queryDisable' v-model="roomAddress.city" label="市(地、州、盟)：" label-width="8em"
                       placeholder="西安市"
                       readonly
            />
            <van-field :disabled='queryDisable' v-model="roomAddress.county" label="县(市、区、旗)：" label-width="8em"
                       placeholder="浐灞生态区"
                       readonly
            />
            <van-field :disabled='queryDisable' readonly label="乡(镇、街道)：" name="乡(镇、街道)" :value="roomAddress.town"
                       label-width="8em" placeholder="请查询"
            />
            <van-field :disabled='queryDisable' readonly label="普查区：" name="普查区" :value="roomAddress.village"
                       label-width="8em" placeholder="请查询"
            />
            <van-field :disabled='queryDisable' readonly label="住宅小区：" name="住宅小区" :value="roomAddress.community"
                       label-width="8em" placeholder="请查询"
            />
            <van-field :disabled='queryDisable' v-model="roomAddress.buildNum" label="楼栋号：" type="digit"
                       label-width="8em" placeholder="请输入楼号/组,例如:1"
            />
            <van-field :disabled='queryDisable' v-model="roomAddress.unitNum" label="单元号：" type="digit"
                       label-width="8em" placeholder="请输入单元号,例如:2,如无填0"
            />
            <van-field :disabled='queryDisable' v-model="roomAddress.floorNum" label="楼层：" type="digit"
                       label-width="8em" placeholder="请输入楼层,如3"
            />
            <van-field :disabled='queryDisable' v-model="roomAddress.roomNum" label="门牌号：" name="门牌号" label-width="8em"
                       placeholder="请输入门牌号,例如:2702"
            />
            <van-field :disabled='queryDisable' v-model="roomAddress.fillPersonPhone" label="被访人电话：" name="被访人电话"
                       type="tel" label-width="8em" placeholder="输入被访人电话"
            />
          </van-collapse-item>
          <!-- 住户信息 -->
          <van-collapse-item size="large" name="houseHold" class="big_title">
            <template #title>
              <div>住户信息</div>
            </template>
            <div class="houseHold_item">
              <p class="houseHold_title houseHold_title_tip">H1.户别：</p>
              <p class="question_tip">提示：这里的住户类别与户口本上的“户别”无关,应以实际居住情况为准,家庭户即
                有家庭成员关系或单身居住生活的,集体户即相互之间没有家庭成员关系</p>
              <van-field :disabled='queryDisable' readonly label="户别：" name="户别" :value="houseHold.h1" label-width="5em"
                         placeholder="请查询"
              />
            </div>

            <div class="houseHold_item">
              <p class="houseHold_title">H2.本户应登记人数：</p>
              <van-field :disabled='queryDisable' v-model="houseHold.h2Live" label-width="12em"
                         label="2020年10月31日晚居住本户的人数：">
              </van-field>
              <van-field :disabled='queryDisable' v-model="houseHold.h2NoLive" label-width="12em"
                         label="户口在本户，2020年10月31日晚未住本户的人数：">
              </van-field>
            </div>

            <div class="houseHold_item">
              <p class="houseHold_title">H3.本户2019年11月1日至2020年10月31日期间的出生人口：</p>
              <van-field :disabled='queryDisable' v-model="houseHold.h3Man" label-width="12em" label="男：">
              </van-field>
              <van-field :disabled='queryDisable' v-model="houseHold.h3Woman" label-width="12em" label="女：">
              </van-field>
            </div>

            <div class="houseHold_item">
              <p class="houseHold_title">H4.本户2019年11月1日至2020年10月31日期间的死亡人口：</p>
              <van-field :disabled='queryDisable' v-model="houseHold.h4Man" label-width="5em" label="男：">
              </van-field>
              <van-field :disabled='queryDisable' v-model="houseHold.h4Woman" label-width="5em" label="女：">
              </van-field>
            </div>
            <div class="houseHold_item">
              <p class="houseHold_title">H5.您家中是否有怀孕6个月及以上的孕妇：</p>
              <van-field :disabled='queryDisable' readonly label="是/否：" name="孕妇" :value="houseHold.h8"
                         label-width="5em" placeholder="请查询"
              />
            </div>
            <div class="houseHold_item">
              <p class="houseHold_title">H6.住房类型：</p>
              <van-field :disabled='queryDisable' readonly label="住房类型：" name="住房类型" :value="houseHold.h5"
                         label-width="5em" placeholder="请查询"
              />
            </div>

            <div class="houseHold_item">
              <p class="houseHold_title">H7.本户住房建筑面积：</p>
              <van-field :disabled='queryDisable' v-model="houseHold.h6" placeholder="输入住房面积" type="digit"
                         label-width="5em" label="平方米："
              />
            </div>

            <div class="houseHold_item">
              <p class="houseHold_title houseHold_title_tip">H8.本户现在住房数：</p>
              <p class="question_tip">提示：本户住房间数指除厨房、厕所、过道和厅以外的所有自然间数(包含扩建的房间),租户合租住同一所住房的,在填写住房间数时,填写其独立使用的房间数</p>
              <van-field :disabled='queryDisable' v-model="houseHold.h7" label-width="5em" label="间：">
              </van-field>
            </div>
            <div class="houseHold_item">
              <p class="houseHold_title">H9.被访人类型：</p>
              <van-field :disabled='queryDisable' readonly label="类型：" name="类型" :value="houseHold.h9" label-width="5em"
                         placeholder="请查询"
              />
            </div>
            <div class="houseHold_item">
              <p class="houseHold_title">H10.承重类型：</p>
              <van-field :disabled='submitButtonDisable' required readonly clickable :value="houseHold.h10"
                         label-width="5em" placeholder="请选择" @click="showPickerH10 = true"
                         :rules="[{ required: true, message:'请选择承重类型' }]"
              />
              <van-popup v-model="showPickerH10" round position="bottom">
                <van-picker
                  title="承重类型"
                  show-toolbar
                  :columns="h10List"
                  @cancel="showPickerH10 = false"
                  @confirm="choiceH10"
                />
              </van-popup>
            </div>
            <div class="houseHold_item">
              <p class="houseHold_title">H11.住房建成年代：</p>
              <van-field :disabled='submitButtonDisable' required readonly clickable :value="houseHold.h11"
                         label-width="5em" placeholder="请选择" @click="showPickerH11 = true"
                         :rules="[{ required: true, message:'请选择住房建成年代' }]"
              />
              <van-popup v-model="showPickerH11" round position="bottom">
                <van-picker
                  title="住房建成年代"
                  show-toolbar
                  :columns="h11List"
                  @cancel="showPickerH11 = false"
                  @confirm="choiceH11"
                />
              </van-popup>
            </div>
            <div class="houseHold_item">
              <p class="houseHold_title">H12.住房所在建筑有无电梯：</p>
              <van-field :disabled='submitButtonDisable' required readonly clickable :value="houseHold.h12"
                         label-width="5em" placeholder="请选择" @click="showPickerH12 = true"
                         :rules="[{ required: true, message:'请选择住房所在建筑有无电梯' }]"
              />
              <van-popup v-model="showPickerH12" round position="bottom">
                <van-picker
                  title="住房所在建筑有无电梯"
                  show-toolbar
                  :columns="h12List"
                  @cancel="showPickerH12 = false"
                  @confirm="choiceH12"
                />
              </van-popup>
            </div>
            <div class="houseHold_item">
              <p class="houseHold_title">H13.主要炊事燃料：</p>
              <van-field :disabled='submitButtonDisable' required readonly clickable :value="houseHold.h13"
                         label-width="5em" placeholder="请选择" @click="showPickerH13 = true"
                         :rules="[{ required: true, message:'请选择主要炊事燃料' }]"
              />
              <van-popup v-model="showPickerH13" round position="bottom">
                <van-picker
                  title="主要炊事燃料"
                  show-toolbar
                  :columns="h13List"
                  @cancel="showPickerH13 = false"
                  @confirm="choiceH13"
                />
              </van-popup>
            </div>
            <div class="houseHold_item">
              <p class="houseHold_title">H14.住房内有无管道自来水：</p>
              <van-field :disabled='submitButtonDisable' required readonly clickable :value="houseHold.h14"
                         label-width="5em" placeholder="请选择" @click="showPickerH14 = true"
                         :rules="[{ required: true, message:'请选择住房内有无管道自来水' }]"
              />
              <van-popup v-model="showPickerH14" round position="bottom">
                <van-picker
                  title="住房内有无管道自来水"
                  show-toolbar
                  :columns="h14List"
                  @cancel="showPickerH14 = false"
                  @confirm="choiceH14"
                />
              </van-popup>
            </div>
            <div class="houseHold_item">
              <p class="houseHold_title">H15.住房内有无厨房：</p>
              <van-field :disabled='submitButtonDisable' required readonly clickable :value="houseHold.h15"
                         label-width="5em" placeholder="请选择" @click="showPickerH15 = true"
                         :rules="[{ required: true, message:'请选择住房内有无厨房' }]"
              />
              <van-popup v-model="showPickerH15" round position="bottom">
                <van-picker
                  title="住房内有无厨房"
                  show-toolbar
                  :columns="h15List"
                  @cancel="showPickerH15 = false"
                  @confirm="choiceH15"
                />
              </van-popup>
            </div>
            <div class="houseHold_item">
              <p class="houseHold_title">H16.住房内有无厕所：</p>
              <van-field :disabled='submitButtonDisable' required readonly clickable :value="houseHold.h16"
                         label-width="5em" placeholder="请选择" @click="showPickerH16 = true"
                         :rules="[{ required: true, message:'请选择住房内有无厕所' }]"
              />
              <van-popup v-model="showPickerH16" round position="bottom">
                <van-picker
                  title="住房内有无厕所"
                  show-toolbar
                  :columns="h16List"
                  @cancel="showPickerH16 = false"
                  @confirm="choiceH16"
                />
              </van-popup>
            </div>
            <div class="houseHold_item">
              <p class="houseHold_title">H17.住房内有无洗澡设施：</p>
              <van-field :disabled='submitButtonDisable' required readonly clickable :value="houseHold.h17"
                         label-width="5em" placeholder="请选择" @click="showPickerH17 = true"
                         :rules="[{ required: true, message:'请选择住房内有无洗澡设施' }]"
              />
              <van-popup v-model="showPickerH17" round position="bottom">
                <van-picker
                  title="住房内有无洗澡设施"
                  show-toolbar
                  :columns="h17List"
                  @cancel="showPickerH17 = false"
                  @confirm="choiceH17"
                />
              </van-popup>
            </div>
            <div class="houseHold_item">
              <p class="houseHold_title">H18.住房来源：</p>
              <van-field :disabled='submitButtonDisable' required readonly clickable :value="houseHold.h18"
                         label-width="5em" placeholder="请选择" @click="showPickerH18 = true"
                         :rules="[{ required: true, message:'请选择住房来源' }]"
              />
              <van-popup v-model="showPickerH18" round position="bottom">
                <van-picker
                  title="住房来源"
                  show-toolbar
                  :columns="h18List"
                  @cancel="showPickerH18 = false"
                  @confirm="choiceH18"
                />
              </van-popup>
            </div>
            <div class="houseHold_item" v-if="isShowH19">
              <p class="houseHold_title">H19.月租房费用：</p>
              <van-field :disabled='submitButtonDisable' required readonly clickable :value="houseHold.h19"
                         label-width="5em" placeholder="请选择" @click="showPickerH19 = true"
                         :rules="[{ required: true, message:'请选择月租房费用' }]"
              />
              <van-popup v-model="showPickerH19" round position="bottom">
                <van-picker
                  title="月租房费用"
                  show-toolbar
                  :columns="h19List"
                  @cancel="showPickerH19 = false"
                  @confirm="choiceH19"
                />
              </van-popup>
            </div>
            <div class="houseHold_item">
              <p class="houseHold_title">H20.拥有全部家用汽车的总价：</p>
              <van-field :disabled='submitButtonDisable' required readonly clickable :value="houseHold.h20"
                         label-width="5em" placeholder="请选择" @click="showPickerH20 = true"
                         :rules="[{ required: true, message:'请选择拥有全部家用汽车的总价' }]"
              />
              <van-popup v-model="showPickerH20" round position="bottom">
                <van-picker
                  title="拥有全部家用汽车的总价"
                  show-toolbar
                  :columns="h20List"
                  @cancel="showPickerH20 = false"
                  @confirm="choiceH20"
                />
              </van-popup>
            </div>
          </van-collapse-item>
          <!-- 个人信息列表 -->
          <van-collapse-item size="large" name="personInfo" class="big_title">
            <template #title>
              <div>个人信息列表</div>
            </template>

            <van-collapse v-model="activePersonInfoList">
              <van-collapse-item size="large" v-for="(item, index) in personInfoList" :name="index">
                <template #title>
                  <div>
                    <span>{{ (index + 1) + ':  ' + item.d1 }}</span>
                  </div>
                </template>
                <van-field :disabled='queryDisable' v-model="item.d1" label="D1.姓名：" name="姓名" label-width="8em"
                           placeholder="输入姓名"
                />
                <van-field :disabled='queryDisable' readonly label="D2.与户主关系："
                           name="与户主关系" :value="item.d2" label-width="8em" placeholder="请查询">

                </van-field>
                <p class="question_tip question_tip_field">
                  提示：这里的户主按家庭日常生活习惯确定为户主,在登记集体户时,任选一人登记为户主,本户其他成员与户主关系一律登记为其他
                </p>
                <van-field :disabled='queryDisable' v-model="item.d3" label="D3.公民身份证号：" name="身份证号" label-width="8em"
                           placeholder="输入身份证号"
                />

                <van-field :disabled='queryDisable' readonly label="D4.性别：" name="性别" :value="item.d4" label-width="8em"
                           placeholder="请查询"
                />
                <van-field :disabled='true' readonly label="D5.出生年月：" name="出生年月" :value="item.d5" label-width="8em"
                           placeholder="请查询"
                />
                <van-field :disabled='queryDisable' v-model="item.d6" label="D6.民族：" name="民族" label-width="8em"
                           placeholder="输入民族"
                />

                <van-field :disabled='queryDisable' readonly label="D7.普查时点居住地：" name="普查时点居住地" :value="item.d7"
                           label-width="8em" placeholder="请查询"
                />
                <div style="margin-left: 50px">
                  <van-field :disabled='queryDisable' v-model="item.d7Province" label="省（区、市）" label-width="8em"
                             placeholder="输入省"
                  />
                  <van-field :disabled='queryDisable' v-model="item.d7City" label="市（地、州、盟）" label-width="8em"
                             placeholder="输入市"
                  />
                  <van-field :disabled='queryDisable' v-model="item.d7County" label="县（市、区、旗）" label-width="8em"
                             placeholder="输入县"
                  />
                </div>

                <van-field :disabled='queryDisable' readonly label="D8.户口登记地：" name="户口登记地" :value="item.d8"
                           label-width="8em" placeholder="请查询"
                />
                <div style="margin-left: 50px">
                  <van-field :disabled='queryDisable' v-model="item.d8Province" label="省（区、市）" label-width="8em"
                             placeholder="输入省"
                  />
                  <van-field :disabled='queryDisable' v-model="item.d8City" label="市（地、州、盟）" label-width="8em"
                             placeholder="输入市"
                  />
                  <van-field :disabled='queryDisable' v-model="item.d8County" label="县（市、区、旗）" label-width="8em"
                             placeholder="输入县"
                  />
                </div>
                <div>
                  <van-field :disabled='queryDisable' readonly label="D9.离开户口登记地时间：" name="离开户口登记地时间" :value="item.d9"
                             label-width="8em" placeholder="请查询"
                  />
                </div>
                <div>
                  <van-field :disabled='queryDisable' readonly label="D10.离开户口登记地原因：" name="离开户口登记地原因" :value="item.d10"
                             label-width="8em" placeholder="请查询"
                  />
                </div>

                <div>
                  <van-field :disabled='queryDisable' readonly label="D11.受教育程度：" name="受教育程度" :value="item.d11"
                             label-width="8em" placeholder="请查询"
                  />
                </div>

                <div>
                  <van-field :disabled='queryDisable' readonly label="D12.是否识字：" name="是否识字" :value="item.d12"
                             label-width="8em" placeholder="请查询"
                  />
                </div>

                <div v-if="item.isShowD13">
                  <van-field :disabled='submitButtonDisable' required readonly clickable label="D13.户口登记地类型："
                             name="户口登记地类型" :value="item.d13" label-width="8em" placeholder="请选择"
                             @click="recordCurrentPersonInfoIndex(index,'showPickerD13')"
                             :rules="[{ required: true ,message:'请选择户口登记地类型'}]"
                  />
                  <van-popup v-model="item.showPickerD13" round position="bottom">
                    <van-picker
                      title="户口登记地类型"
                      show-toolbar
                      :columns="d13List"
                      @cancel="item.showPickerD13 = false"
                      @confirm="choiceD13"
                    />
                  </van-popup>
                </div>


                <van-field :disabled='submitButtonDisable' required readonly clickable label="D14.是否有农村土地承包经营权："
                           :value="item.d14" label-width="8em" placeholder="请选择"
                           @click="recordCurrentPersonInfoIndex(index,'showPickerD14')"
                           :rules="[{ required: true ,message:'请选择是否有农村土地承包经营权'}]"
                />

                <van-popup v-model="item.showPickerD14" round position="bottom">
                  <van-picker
                    title="是否有农村土地承包经营权"
                    show-toolbar
                    :columns="d14List"
                    @cancel="item.showPickerD14 = false"
                    @confirm="choiceD14"
                  />
                </van-popup>
                <van-field :disabled='submitButtonDisable' required readonly clickable label="D15.出生地："
                           :value="item.d15" label-width="8em" placeholder="请选择"
                           @click="recordCurrentPersonInfoIndex(index,'showPickerD15')"
                           :rules="[{ required: true ,message:'请选择出生地'}]"
                />
                <van-popup v-model="item.showPickerD15" round position="bottom">
                  <van-picker
                    title="出生地"
                    show-toolbar
                    :columns="d15List"
                    @cancel="item.showPickerD15 = false"
                    @confirm="choiceD15"
                  />
                </van-popup>
                <div v-if="item.isShowD15Other" style="margin-left: 50px">
                  <van-field :disabled='submitButtonDisable' required v-model="item.d15Province" label="省（区、市）"
                             label-width="8em" placeholder="输入省"
                             :rules="[{ required: true }]"
                  />
                </div>
                <div v-if="item.d5 < '2015-11'">
                  <van-field :disabled='submitButtonDisable' required readonly clickable
                             label="D16.五年前常住地，2015年11月1日常住地：" :value="item.d16" label-width="8em" placeholder="请选择"
                             @click="recordCurrentPersonInfoIndex(index,'showPickerD16')"
                             :rules="[{ required: true ,message:'请选择'}]"
                  />
                  <van-popup v-model="item.showPickerD16" round position="bottom">
                    <van-picker
                      title="五年前常住地，2015年11月1日常住地"
                      show-toolbar
                      :columns="d16List"
                      @cancel="item.showPickerD16 = false"
                      @confirm="choiceD16"
                    />
                  </van-popup>
                  <div v-if="item.isShowD16Other" style="margin-left: 50px">
                    <van-field :disabled='submitButtonDisable' required v-model="item.d16Province" label="省（区、市）"
                               label-width="8em" placeholder="输入省"
                               :rules="[{ required: true }]"
                    />
                    <van-field :disabled='submitButtonDisable' required v-model="item.d16City" label="市（地、州、盟）"
                               label-width="8em" placeholder="输入市"
                               :rules="[{ required: true }]"
                    />
                    <van-field :disabled='submitButtonDisable' required v-model="item.d16County" label="县（市、区、旗）"
                               label-width="8em" placeholder="输入县"
                               :rules="[{ required: true }]"
                    />
                  </div>
                </div>
                <div v-if="item.isShowD18 && item.d5 < '2017-11'">
                  <van-field :disabled='submitButtonDisable' required readonly clickable label="D18.学业完成情况："
                             :value="item.d18" label-width="8em" placeholder="请选择"
                             @click="recordCurrentPersonInfoIndex(index,'showPickerD18')"
                             :rules="[{ required: true ,message:'请选择'}]"
                  />
                  <van-popup v-model="item.showPickerD18" round position="bottom">
                    <van-picker
                      title="学业完成情况"
                      show-toolbar
                      :columns="d18List"
                      @cancel="item.showPickerD18 = false"
                      @confirm="choiceD18"
                    />
                  </van-popup>
                </div>
             <div>
               <van-field :disabled='submitButtonDisable' required readonly clickable label="D20.工作情况：(10月25—31日是否为取得收入而工作了一小时以上（包括临时工、依托互联网灵活就业、家庭经营无酬帮工）)" :value="item.d20" label-width="8em" placeholder="请选择"
                          @click="recordCurrentPersonInfoIndex(index,'showPickerD20')"
                          :rules="[{ required: true ,message:'请选择'}]"
               />
               <van-popup v-model="item.showPickerD20" round position="bottom">
                 <van-picker
                   title="工作情况"
                   show-toolbar
                   :columns="d20List"
                   @cancel="item.showPickerD20 = false"
                   @confirm="choiceD20"
                 />
               </van-popup>
               <div v-if="item.isShowD20Other" style="margin-left: 50px">
                 <van-field :disabled='submitButtonDisable' type="number" required v-model="item.d20Hour" label="上周工作时间-小时"
                            label-width="8em" placeholder="输入小时数"
                            :rules="[{ required: true }]"
                 />
               </div>
             </div>
              <div v-if="item.isShowD21">
                <van-field :disabled='submitButtonDisable' required readonly clickable label="D21.工作单位或生产经营活动所属类型："
                           :value="item.d21" label-width="8em" placeholder="请选择"
                           @click="recordCurrentPersonInfoIndex(index,'showPickerD21')"
                           :rules="[{ required: true ,message:'请选择'}]"
                />
                <van-popup v-model="item.showPickerD21" round position="bottom">
                  <van-picker
                    title="工作单位或生产经营活动所属类型"
                    show-toolbar
                    :columns="d21List"
                    @cancel="item.showPickerD21 = false"
                    @confirm="choiceD21"
                  />
                </van-popup>
              </div>
                <div v-if="item.isShowD22">
                  <van-field v-model="item.d22Name" required label-width="8em" label="D22.行业:单位详细名称："
                             :rules="[{ required: true ,message:'请填写'}]"

                  >
                  </van-field>
                  <van-field v-model="item.d22Business" required label-width="8em" label="D22.行业:主要产品或主要业务："
                             :rules="[{ required: true ,message:'请填写'}]"

                  >
                  </van-field>
                </div>
                <div v-if="item.isShowD23">
                  <van-field :disabled='submitButtonDisable' v-model="item.d23" required label-width="8em"
                             :rules="[{ required: true ,message:'请填写'}]"
                             @blur="choiceD23(index)"
                             label="D23.本人从事的具体工作：">
                  </van-field>
                </div>

              <div v-if="item.isShowD24">
                <van-field :disabled='submitButtonDisable' required readonly clickable label="D24.未工作原因："
                           :value="item.d24" label-width="8em" placeholder="请选择"
                           @click="recordCurrentPersonInfoIndex(index,'showPickerD24')"
                           :rules="[{ required: true ,message:'请选择'}]"
                />
                <van-popup v-model="item.showPickerD24" round position="bottom">
                  <van-picker
                    title="未工作原因"
                    show-toolbar
                    :columns="d24List"
                    @cancel="item.showPickerD24 = false"
                    @confirm="choiceD24"
                  />
                </van-popup>
              </div>
                <van-field :disabled='submitButtonDisable' required readonly clickable label="D25.主要生活来源："
                           :value="item.d25" label-width="8em" placeholder="请选择"
                           @click="recordCurrentPersonInfoIndex(index,'showPickerD25')"
                           :rules="[{ required: true ,message:'请选择'}]"
                />
                <van-popup v-model="item.showPickerD25" round position="bottom">
                  <van-picker
                    title="主要生活来源"
                    show-toolbar
                    :columns="d25List"
                    @cancel="item.showPickerD25 = false"
                    @confirm="choiceD25"
                  />
                </van-popup>
                <van-field :disabled='submitButtonDisable' required readonly clickable label="D26.婚姻状况："
                           :value="item.d26" label-width="8em" placeholder="请选择"
                           @click="recordCurrentPersonInfoIndex(index,'showPickerD26')"
                           :rules="[{ required: true ,message:'请选择'}]"
                />
                <van-popup v-model="item.showPickerD26" round position="bottom">
                  <van-picker
                    title="婚姻状况"
                    show-toolbar
                    :columns="d26List"
                    @cancel="item.showPickerD26 = false"
                    @confirm="choiceD26"
                  />
                </van-popup>
                <div v-if="item.isShowD27">
                  <van-field :disabled='submitButtonDisable' required type="number" v-model="item.d27Year" required label-width="8em"
                             :rules="[{ required: true ,message:'请填写'}]"

                             label="D27.初婚:年：">
                  </van-field>
                  <van-field :disabled='submitButtonDisable' required type="number" v-model="item.d27Month" required label-width="8em"
                             :rules="[{ required: true ,message:'请填写'}]"
                             label="D27.初婚:月：">
                  </van-field>
                </div>
                <div v-if="item.isShowD28 && (item.d5 > '1955-10' && item.d5 < '2005-11') && item.d4=='女'">
                  <van-field :disabled='submitButtonDisable' required readonly clickable label="D28.生育子女数："
                             :value="item.d28" label-width="8em" placeholder="请选择"
                             @click="recordCurrentPersonInfoIndex(index,'showPickerD28')"
                             :rules="[{ required: true ,message:'请选择'}]"
                  />
                  <van-popup v-model="item.showPickerD28" round position="bottom">
                    <van-picker
                      title="生育子女数"
                      show-toolbar
                      :columns="d28List"
                      @cancel="item.showPickerD28 = false"
                      @confirm="choiceD28"
                    />
                  </van-popup>
                  <div v-if="item.isShowD28Other" style="margin-left: 50px">
                    <van-field :disabled='submitButtonDisable' required v-model="item.d28BirthMan" label="D28.生过几个孩子:男"  label-width="8em" placeholder="输入"
                               :rules="[{ required: true }]"
                    />
                    <van-field :disabled='submitButtonDisable' required v-model="item.d28BirthWoman" label="D28.生过几个孩子:女"  label-width="8em" placeholder="输入"
                               :rules="[{ required: true }]"
                    />
                    <van-field :disabled='submitButtonDisable' required v-model="item.d28LiveMan" label="D28.其中现在存活几个孩子:男"  label-width="8em" placeholder="输入"
                               :rules="[{ required: true }]"
                    />
                    <van-field :disabled='submitButtonDisable' required v-model="item.d28LiveWoman" label="D28.其中现在存活几个孩子:女"  label-width="8em" placeholder="输入"
                               :rules="[{ required: true }]"
                    />
                  </div>
                </div>
                <div v-if="item.isShowD29 && (item.d5 > '1969-10' && item.d5 < '2005-11') && item.d4=='女'">
                  <van-field :disabled='submitButtonDisable' required readonly clickable
                             label="D29.过去一年（2019年11月1日-2020年10月31日）的生育状况：" :value="item.d29" label-width="8em"
                             placeholder="请选择"
                             @click="recordCurrentPersonInfoIndex(index,'showPickerD29')"
                             :rules="[{ required: true ,message:'请选择'}]"
                  />
                  <van-popup v-model="item.showPickerD29" round position="bottom">
                    <van-picker
                      title="过去一年（2019年11月1日-2020年10月31日）的生育状况"
                      show-toolbar
                      :columns="d29List"
                      @cancel="item.showPickerD29 = false"
                      @confirm="choiceD29"
                    />
                  </van-popup>
                  <div v-if="item.isShowD29Other" style="margin-left: 50px">
                    <van-field :disabled='submitButtonDisable' required v-model="item.d29FirstBirth" type="number" label="D29.生育时间:月"  label-width="8em" placeholder="输入"
                               :rules="[{ required: true }]"
                    />
                    <van-field :disabled='submitButtonDisable' required readonly clickable label="婴儿性别是：" name="性别" :value="item.d29FirstGender" label-width="8em" placeholder="请选择" @click="recordCurrentPersonInfoIndex(index,'showPickerD29FirstGender')"
                               :rules="[{ required: true ,message:'请选择性别'}]"
                    />
                    <van-popup v-model="item.showPickerD29FirstGender" round position="bottom">
                      <van-picker
                        title="性别"
                        show-toolbar
                        :columns="genderList"
                        @cancel="item.showPickerD29FirstGender = false"
                        @confirm="choiceD29FirstGender"
                      />
                    </van-popup>

                    <van-field :disabled='submitButtonDisable'  v-model="item.d29SecondBirth" type="number" label="D29.如果一年内有两次生育或生育多胞胎的，请填报其他孩子的:月"  label-width="8em" placeholder="输入"
                    />
                    <van-field :disabled='submitButtonDisable'  readonly clickable label="婴儿性别是：" name="性别" :value="item.d29SecondGender" label-width="8em" placeholder="请选择" @click="recordCurrentPersonInfoIndex(index,'showPickerD29SecondGender')"
                    />
                    <van-popup v-model="item.showPickerD29SecondGender" round position="bottom">
                      <van-picker
                        title="性别"
                        show-toolbar
                        :columns="genderList"
                        @cancel="item.showPickerD29SecondGender = false"
                        @confirm="choiceD29SecondGender"
                      />
                    </van-popup>

                  </div>
                </div>

                <div v-if="item.d5 < '1960-11' ">

                  <van-field :disabled='submitButtonDisable' required readonly clickable label="D30.居住状况："
                             :value="item.d30" label-width="8em" placeholder="请选择"
                             @click="recordCurrentPersonInfoIndex(index,'showPickerD30')"
                             :rules="[{ required: true ,message:'请选择'}]"
                  />
                  <van-popup v-model="item.showPickerD30" round position="bottom">
                    <van-picker
                      title="居住状况"
                      show-toolbar
                      :columns="d30List"
                      @cancel="item.showPickerD30 = false"
                      @confirm="choiceD30"
                    />
                  </van-popup>
                </div>
                <van-field :disabled='submitButtonDisable' required readonly clickable label="D31.身体健康状况："
                           :value="item.d31" label-width="8em" placeholder="请选择"
                           @click="recordCurrentPersonInfoIndex(index,'showPickerD31')"
                           :rules="[{ required: true ,message:'请选择'}]"
                />
                <van-popup v-model="item.showPickerD31" round position="bottom">
                  <van-picker
                    title="身体健康状况"
                    show-toolbar
                    :columns="d31List"
                    @cancel="item.showPickerD31 = false"
                    @confirm="choiceD31"
                  />
                </van-popup>
              </van-collapse-item>
            </van-collapse>

          </van-collapse-item>
          <!-- 其他信息 -->
          <van-collapse-item size="large" name="otherInfo" class="big_title">
            <template #title>
              <div>其他信息</div>
            </template>
            <van-field :disabled='submitButtonDisable' v-model="roomAddress.examinePersonName" label="普查员姓名：" label-width="8em"
                       placeholder="输入姓名"
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
      </div>
      <div class="div_foot">
        <div class="div_button">
          <van-button v-if="!isAgain" round block type="info" size="small" native-type="submit"
                      :disabled="submitButtonDisable">
            <template #default>
              <div class="submit_button_text">{{ submitButtonText }}</div>
            </template>
          </van-button>
          <van-button v-if="isAgain" round block type="info" size="small" native-type="button" @click="againFill">
            <template #default>
              <div class="submit_button_text">继续填报</div>
            </template>
          </van-button>
        </div>
      </div>

    </van-form>
  </div>
</template>

<script>
import {
  community_getById,
} from '@/api/community.js'
import { censusSurvey_update, censusSurvey_getById } from '@/api/censusSurvey.js'
import { mapGetters } from 'vuex'

export default {
  inject: ['reload'],
  data() {
    return {
      xxx: {
        color: 'red'
      },
      roomAddressId: '',
      submitButtonText: '提交',
      isAgain: false,
      queryDisable: true,
      submitButtonDisable: false,
      minDate: new Date(1900, 1, 1),
      maxDate: new Date(2020, 12, 30),
      currentDate: new Date(),
      activeNames: ['roomAddress', 'houseHold', 'personInfo', 'otherInfo'],
      // activeNames:[],
      roomAddress: {
        chargePersonId: '',
        chargePersonName: '',
        communityId: '',
        personCount: '',
        province: '陕西省',
        city: '西安市',
        county: '浐灞生态区',
        town: '',
        village: '',
        community: '',
        buildNum: '',
        unitNum: '',
        floorNum: '',
        roomNum: '',
        fillPersonPhone: '',
        examinePersonName: '',
        remark: ''
      },
      h10List: [
        '钢及钢筋混凝土结构',
        '混合结构',
        '砖木结构',
        '竹草土坯结构',
        '其他结构'
      ],
      h11List: [
        '1949年以前',
        '1949-1959年',
        '1960-1969年',
        '1970-1979年',
        '1980-1989年',
        '1990-1999年',
        '2000-2009年',
        '2010-2014年',
        '2015年以后'
      ],
      h12List: [
        '有',
        '无'
      ],
      h13List: [
        '燃气',
        '电',
        '煤炭',
        '柴草',
        '其他'
      ],
      h14List: [
        '有',
        '无'
      ],
      h15List: [
        '独立使用',
        '与其他户合用',
        '无'
      ],
      h16List: [
        '水冲式卫生厕所',
        '水冲式非卫生测试',
        '卫生旱厕',
        '普遍旱厕',
        '无'
      ],
      h17List: [
        '统一供热水',
        '家庭自装热水器',
        '其他',
        '无'
      ],
      h18List: [
        '租赁廉租房、公租房',
        '租赁其他住房',
        '购买新建商品房',
        '购买二手房',
        '购买原公有住房',
        '购买经济适用房/两限房',
        '自建住房',
        '继承或赠与',
        '其他'
      ],
      h19List: [
        '200元以下',
        '200-499元',
        '500-999元',
        '1000-1999元',
        '2000-2999元',
        '3000-3999元',
        '4000-4999元',
        '6000-5999元',
        '6000-7999元',
        '8000-8999元',
        '9000-9999元',
        '10000元以上'
      ],
      h20List: [
        '不满10万元',
        '10万元以上，不满20万元',
        '20万元以上，不满30万元',
        '30万元以上，不满50万元',
        '50万元以上，不满100万元',
        '100万元以上',
        '没有汽车'
      ],
      isShowH19: true,
      showPickerH10: false,
      showPickerH11: false,
      showPickerH12: false,
      showPickerH13: false,
      showPickerH14: false,
      showPickerH15: false,
      showPickerH16: false,
      showPickerH17: false,
      showPickerH18: false,
      showPickerH19: false,
      showPickerH20: false,
      houseHold: {
        h1: '',//户别
        h2Live: '0',//本户应登记人数,2020年10月31日晚居住本户的人数 _____人
        h2NoLive: '0',//户口在本户，2020年10月31日晚未住本户的人数 _____人
        h3Man: '0',//本户2019年11月1日至2020年10月31日期间的出生人口,男人
        h3Woman: '0',//本户2019年11月1日至2020年10月31日期间的出生人口,女人
        h4Man: '0',//本户2019年11月1日至2020年10月31日期间的死亡人口,男人
        h4Woman: '0',//本户2019年11月1日至2020年10月31日期间的死亡人口,女人
        h5: '',//住房类型,h5List,（选择3-6的，跳至个人项目。）
        h6: '',//本户住房建筑面积
        h7: '1',//本户住房间数
        h8: '',//您家中是否有6个月及以上的孕妇（是/否）
        h9: '',//被访人类型：住户，租户,
        h10: '',
        h11: '',
        h12: '',
        h13: '',
        h14: '',
        h15: '',
        h16: '',
        h17: '',
        h18: '',
        h19: '',
        h20: ''
      },
      activePersonInfoList: [],
      currentPersonInfoIndex: null,
      d13List: [
        '乡',
        '镇的村委会',
        '镇的居委会',
        '街道'
      ],
      d14List: [
        '有',
        '无'
      ],
      d15List: [
        '本县（市、区、旗）',
        '本省其他县（市、区、旗）',
        '省外'
      ],
      d16List: [
        '本县（市、区）',
        '其他地区（请填报具体地址）'
      ],
      d17List: [
        '未上过学',
        '学前教育',
        '小学',
        '初中',
        '高中',
        '大学专科',
        '大学本科',
        '硕士研究生',
        '博士研究生'
      ],
      d18List: [
        '在校',
        '毕业',
        '肄业',
        '辍学',
        '其他'
      ],
      d19List: [
        '是',
        '否'
      ],
      d20List: [
        '是',
        '在职休假、在职学习培训、临时停工（保留工资）',
        '未做任何工作'
      ],
      d21List: [
        '企业、事业、机关或社会团体等法人单位',
        '个体经营户',
        '经营农村家庭承包地（家庭农林牧渔生产经营活动）',
        '自由职业/灵活就业'
      ],
      d24List: [
        '在校学习',
        '离退休',
        '料理家务',
        '丧失工作能力',
        '其他'
      ],
      d25List: [
        '劳动收入',
        '离退休金/养老金',
        '最低生活保障',
        '失业保险金',
        '财产性收入',
        '家庭其他成员供养',
        '其他'
      ],
      d26List: [
        '未婚',
        '有配偶',
        '离婚',
        '丧偶'
      ],
      d28List: [
        '未生育',
        '有生育'
      ],
      d29List: [
        '一年内未生育',
        '一年内有生育'
      ],
      d30List: [
        '与配偶和子女同住',
        '与配偶同住',
        '与子女同住',
        '独居（有保姆）',
        '独居（无保姆）',
        '养老机构',
        '其他'
      ],
      d31List: [
        '健康',
        '基本健康',
        '不健康，但生活能自理',
        '生活不能自理'
      ],
      genderList: [
        '男',
        '女'
      ],
      personInfo: {
        isShowD13: true,
        isShowD15Other: false,
        isShowD16Other: false,
        isShowD18: true,
        isShowD20Other: false,
        isShowD21: true,
        isShowD22: true,
        isShowD23: true,
        isShowD24: true,
        isShowD27: true,
        isShowD28: true,
        isShowD29: true,
        isShowD28Other:false,
        isShowD29Other:false,
        showPickerD13: false,
        showPickerD14: false,
        showPickerD15: false,
        showPickerD16: false,
        showPickerD17: false,
        showPickerD18: false,
        showPickerD19: false,
        showPickerD20: false,
        showPickerD21: false,
        showPickerD24: false,
        showPickerD25: false,
        showPickerD26: false,
        showPickerD28: false,
        showPickerD29: false,
        showPickerD29FirstGender: false,
        showPickerD29SecondGender: false,
        showPickerD30: false,
        showPickerD31: false,
        d1: '',//姓名
        d2: '',//与户主关系
        d3: '',//公民身份号码
        d4: '',//性别
        d5: '',//出生年月
        d6: '',//民族
        d7: '',//普查时点居住地
        d7Index: '',//普查时点居住地
        d7Province: '',//普查时点居住地不在本地的省
        d7City: '',//普查时点居住地不在本地的市
        d7County: '',//普查时点居住地不在本地的县
        d8: '',//户口登记地
        d8Province: '',//户口登记地不在本地的省
        d8City: '',//户口登记地不在本地的市
        d8County: '',//户口登记地不在本地的县
        d9: '',//离开户口登记地时间
        d9List: [],//离开户口登记地时间
        d10: '',//离开户口登记地原因
        d11: '',//是否识字
        d12: '',//受教育程度
        d13: '',
        d14: '',
        d15: '',
        d15Province: '',
        d16: '',
        d16Province: '',
        d16City: '',
        d16County: '',
        d18: '',
        d20: '',
        d20Hour: '',
        d21: '',
        d22Name: '',
        d22Business: '',
        d23: '',
        d24: '',
        d25: '',
        d26: '',
        d27Year: '',
        d27Month: '',
        d28: '',
        d28BirthMan:'',
        d28BirthWoman:'',
        d28LiveMan:'',
        d28LiveWoman:'',
        d29: '',
        d29FirstBirth: '',
        d29FirstGender: '',
        d29SecondBirth: '',
        d29SecondGender: '',
        d30: '',
        d31: ''
      },
      personInfoList: []
    }
  },
  created() {
    this.dealLinkParam(this.$route.query)
    this.personInfoList = []
  },
  computed: {
    ...mapGetters(['examinePersonName'])
  },
  mounted() {
  },

  methods: {
    getCensusSurveyById() {
      if (!this.roomAddressId) {
        this.$toast.fail('请输入唯一编码')
        return
      }
      let param = { id: this.roomAddressId }
      let _this = this
      censusSurvey_getById(param)
        .then((res) => {
          if (res.code == 200) {
            if (res.data) {
              _this.personInfoList = []
              Object.assign(_this.roomAddress, res.data.roomAddress)
              Object.assign(_this.houseHold, res.data.houseHold)
              let tempPersonInfoList = res.data.personInfoList
              for (let i = 0; i < tempPersonInfoList.length; i++) {
                Object.assign(_this.personInfo, tempPersonInfoList[i])
                if (_this.personInfo.d8 == '户口待定' || _this.personInfo.d9 == '没有离开户口登记地') {
                  _this.personInfo.isShowD13 = false
                }
                if (_this.personInfo.d11 == '未上过学' || _this.personInfo.d11 == '学前教育') {
                  _this.personInfo.isShowD18 = false
                }
                _this.personInfoList.push(Object.assign({}, _this.personInfo))
              }
              let length = _this.personInfoList.length
              for (let i = 0; i < length; i++) {
                _this.activePersonInfoList.splice(0,0,i)
              }
            } else {
              this.$toast.fail('没查到对应的问卷，请检查唯一编码是否正确')
            }
          }
        })
        .catch(() => {
        })
    },
    onSubmit(values) {
      this.roomAddress.personCount = this.personInfoList.length
      let censusSurvey = {
        roomAddress: this.roomAddress,
        houseHold: this.houseHold,
        personInfoList: this.personInfoList
      }
      console.log(censusSurvey)
      this.$dialog.confirm({
        title: '人口普查问卷',
        message: '确认提交吗'
      })
        .then(() => {
          // on confirm
          this.activeNames = []
          this.activePersonInfoList = []
          this.submitButtonDisable = true
          this.submitButtonText = '提交中'
          // this.submitButtonText = '提交'
          // this.isAgain = true
          // this.$toast.success('提交成功，衷心感谢您的参与！')

          censusSurvey_update(censusSurvey)
            .then((res) => {
              if (res.code == 200) {
                this.submitButtonText = '提交'
                this.isAgain = true
                this.submitButtonDisable = false

                this.$toast.success('提交成功，衷心感谢您的参与！')
              } else {
                console.log(res)
                this.submitButtonText = '提交'
                this.submitButtonDisable = false
                this.$toast.fail('提交失败')
              }
            })
            .catch(() => {
              this.submitButtonText = '提交'
              this.submitButtonDisable = false
            })
        })
        .catch(() => {
          // on cancel
          console.log('cancel')
        })
    },
    onFailed(errorInfo) {
      console.log('onFailed', errorInfo)
      this.$toast.fail('请填写所有的必填项，注意输入框字体是红色的！')
    },
    againFill() {
      this.$store.dispatch('setExaminePersonName', this.roomAddress.examinePersonName)
      this.reload()
    },
    dealLinkParam(query) {
      if (query && query.hasOwnProperty('id')) {
        let param = { id: query.id }
        community_getById(param)
          .then((res) => {
            if (res.code == 200) {
              this.roomAddress.chargePersonId = res.data.chargePersonId
              this.roomAddress.chargePersonName = res.data.chargePersonName
              this.roomAddress.communityId = res.data.id
              this.roomAddress.town = res.data.town
              this.roomAddress.village = res.data.village
              this.roomAddress.community = res.data.name
            }
          })
          .catch(() => {
          })
      }
    },
    formatter(type, val) {
      if (type === 'year') {
        return `${val}年`
      } else if (type === 'month') {
        return `${val}月`
      }
      return val
    },
    choiceH10(value, index) {
      this.houseHold.h10 = value
      this.showPickerH10 = false
    },
    choiceH11(value, index) {
      this.houseHold.h11 = value
      this.showPickerH11 = false
    },
    choiceH12(value, index) {
      this.houseHold.h12 = value
      this.showPickerH12 = false
    },
    choiceH13(value, index) {
      this.houseHold.h13 = value
      this.showPickerH13 = false
    },
    choiceH14(value, index) {
      this.houseHold.h14 = value
      this.showPickerH14 = false
    },
    choiceH15(value, index) {
      this.houseHold.h15 = value
      this.showPickerH15 = false
    },
    choiceH16(value, index) {
      this.houseHold.h16 = value
      this.showPickerH16 = false
    },
    choiceH17(value, index) {
      this.houseHold.h17 = value
      this.showPickerH17 = false
    },
    choiceH18(value, index) {
      this.houseHold.h18 = value
      this.showPickerH18 = false
      if (index >= 2) {
        this.isShowH19 = false
      } else {
        this.isShowH19 = true
      }
    },
    choiceH19(value, index) {
      this.houseHold.h19 = value
      this.showPickerH19 = false
    },
    choiceH20(value, index) {
      this.houseHold.h20 = value
      this.showPickerH20 = false
    },

    recordCurrentPersonInfoIndex(index, showPicker) {
      this.personInfoList[index][showPicker] = true
      this.currentPersonInfoIndex = index
    },

    choiceD13(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d13 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD13 = false
    },
    choiceD14(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d14 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD14 = false
    },
    choiceD15(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d15 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD15 = false
      if (index == 2) {
        this.personInfoList[this.currentPersonInfoIndex].isShowD15Other = true
      } else {
        this.personInfoList[this.currentPersonInfoIndex].isShowD15Other = false
        this.personInfoList[this.currentPersonInfoIndex].d15Province = ''
      }
    },
    choiceD16(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d16 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD16 = false
      if (index == 1) {
        this.personInfoList[this.currentPersonInfoIndex].isShowD16Other = true
      } else {
        this.personInfoList[this.currentPersonInfoIndex].isShowD16Other = false
        this.personInfoList[this.currentPersonInfoIndex].d16Province = ''
        this.personInfoList[this.currentPersonInfoIndex].d16City = ''
        this.personInfoList[this.currentPersonInfoIndex].d16County = ''
      }
    },
    choiceD18(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d18 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD18 = false
    },
    choiceD20(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d20 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD20 = false
      if (index == 0) {
        this.personInfoList[this.currentPersonInfoIndex].isShowD20Other = true
      } else {
        this.personInfoList[this.currentPersonInfoIndex].isShowD20Other = false
        this.personInfoList[this.currentPersonInfoIndex].d20Hour=''
      }
      if (index == 2) {
        this.personInfoList[this.currentPersonInfoIndex].isShowD21 = false
        this.personInfoList[this.currentPersonInfoIndex].isShowD22 = false
        this.personInfoList[this.currentPersonInfoIndex].isShowD23 = false
        this.personInfoList[this.currentPersonInfoIndex].d21 = ''
        this.personInfoList[this.currentPersonInfoIndex].d22 = ''
        this.personInfoList[this.currentPersonInfoIndex].d23 = ''
      } else {
        this.personInfoList[this.currentPersonInfoIndex].isShowD21 = true
        this.personInfoList[this.currentPersonInfoIndex].isShowD22 = true
        this.personInfoList[this.currentPersonInfoIndex].isShowD23 = true
      }
    },
    choiceD21(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d21 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD21 = false
    },
    choiceD23(index) {
      let value = this.personInfoList[index].d23
      if(value){
        this.personInfoList[index].isShowD24 = false
        this.personInfoList[index].d24 = ''
      }else {
        this.personInfoList[index].isShowD24 = true
      }
    },
    choiceD24(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d24 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD24 = false
    },
    choiceD25(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d25 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD25 = false
    },
    choiceD26(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d26 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD26 = false
      if (index == 0) {
        this.personInfoList[this.currentPersonInfoIndex].isShowD27 = false
        this.personInfoList[this.currentPersonInfoIndex].isShowD28 = false
        this.personInfoList[this.currentPersonInfoIndex].isShowD29 = false
        this.personInfoList[this.currentPersonInfoIndex].d27Year = ''
        this.personInfoList[this.currentPersonInfoIndex].d27Month = ''
        this.personInfoList[this.currentPersonInfoIndex].d28 = ''
        this.personInfoList[this.currentPersonInfoIndex].d28BirthMan = ''
        this.personInfoList[this.currentPersonInfoIndex].d28BirthWoman = ''
        this.personInfoList[this.currentPersonInfoIndex].d28LiveMan = ''
        this.personInfoList[this.currentPersonInfoIndex].d28LiveWoman = ''
        this.personInfoList[this.currentPersonInfoIndex].d29 = ''
        this.personInfoList[this.currentPersonInfoIndex].d29FirstBirth = ''
        this.personInfoList[this.currentPersonInfoIndex].d29FirstGender = ''
        this.personInfoList[this.currentPersonInfoIndex].d29SecondBirth = ''
        this.personInfoList[this.currentPersonInfoIndex].d29SecondGender = ''
      } else {
        this.personInfoList[this.currentPersonInfoIndex].isShowD27 = true
        this.personInfoList[this.currentPersonInfoIndex].isShowD28 = true
        this.personInfoList[this.currentPersonInfoIndex].isShowD29 = true
      }
    },
    choiceD28(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d28 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD28 = false
      if (index == 0) {
        this.personInfoList[this.currentPersonInfoIndex].isShowD29 = false
        this.personInfoList[this.currentPersonInfoIndex].d29 = ''
        this.personInfoList[this.currentPersonInfoIndex].d29FirstBirth = ''
        this.personInfoList[this.currentPersonInfoIndex].d29FirstGender = ''
        this.personInfoList[this.currentPersonInfoIndex].d29SecondBirth = ''
        this.personInfoList[this.currentPersonInfoIndex].d29SecondGender = ''
      } else {
        this.personInfoList[this.currentPersonInfoIndex].isShowD29 = true
        this.personInfoList[this.currentPersonInfoIndex].isShowD28Other = false

      }
    },
    choiceD29(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d29 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD29 = false
      if (index == 0) {
        this.personInfoList[this.currentPersonInfoIndex].isShowD29Other = false
        this.personInfoList[this.currentPersonInfoIndex].d29FirstBirth = ''
        this.personInfoList[this.currentPersonInfoIndex].d29FirstGender = ''
        this.personInfoList[this.currentPersonInfoIndex].d29SecondBirth = ''
        this.personInfoList[this.currentPersonInfoIndex].d29SecondGender = ''
      } else {
        this.personInfoList[this.currentPersonInfoIndex].isShowD29Other = true
      }
    },
    choiceD29FirstGender(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d29FirstGender = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD29FirstGender = false
    },
    choiceD29SecondGender(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d29SecondGender = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD29SecondGender = false
    },
    choiceD30(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d30 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD30 = false
    },
    choiceD31(value, index) {
      this.personInfoList[this.currentPersonInfoIndex].d31 = value
      this.personInfoList[this.currentPersonInfoIndex].showPickerD31 = false
    },

    clickVillage() {
      if (!this.roomAddress.town) {
        this.$toast.fail('请先选择乡(镇、街道)')
        return
      }
      this.showPickerVillage = true
    },
    clickCommunity() {
      if (!this.roomAddress.town) {
        this.$toast.fail('请先选择乡(镇、街道)')
        return
      }
      if (!this.roomAddress.village) {
        this.$toast.fail('请先选择普查小区')
        return
      }
      this.showPickerCommunity = true
    },
    clearVillage() {
      this.roomAddress.village = ''
      this.villageList = []
    },
    clearCommunity() {
      this.roomAddress.community = ''
      this.communityList = []
    }
  }
}
</script>
<style lang="scss" scoped>


.page {
  height: 100%;
}

.van-form {
  width: 100%;
  height: 100%;
  //display: flex;
  //flex-direction: column;
  height: 100%;
}

.div_top {
  background-color: #1989fa;
  height: 40px;

  color: white;
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
}

.div_top_text {
  font-size: 1.6em;
}

.div_content {
  //flex: 1;
  //overflow: scroll;
}

.div_foot {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
}

.submit_button_text {
  font-size: 1.4em;
  font-weight: bold;
}

.div_button {
  width: 30%;
  padding: 5px 0px;
}

.question_tip {
  font-family: Times, TimesNR, 'New Century Schoolbook', Georgia, 'New York', serif !important;
  color: red;
  font-size: 0.9em;
  padding: 0;
  margin: 0;
  font-weight: bold !important;
  font-style: normal !important;
}

.question_tip_field {
  padding-left: 1em;
}

.houseHold_title_tip {
  padding-bottom: 0px;
  margin-bottom: 5px;
}

.big_title > > > .van-collapse-item__wrapper {
  background-color: red !important;
}

/deep/ .big_title > .van-cell--large {
  background-color: #ebedf0;
}

</style>
