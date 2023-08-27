<template>
  <div class="dynamic-form-container layout-pd">
    <el-card shadow="hover">
      <template #header>
        <span style="font-weight: bold">
          {{ adInfo.title }}
        </span>
      </template>
      <div style="display: flex; flex-direction: row">
        <div style="width: 100%;">
          <div class="mb15">
            <el-button size="default" type="success" @click="addAd">
              <el-icon>
                <ele-FolderAdd/>
              </el-icon>
              新增图片
            </el-button>
          </div>

          <div style="display: flex; flex-direction: row; flex-wrap: wrap; width: 100%">
            <div v-for="(item,index) in adData.records" :key="index"
                 style="position: relative; width: 200px; height: 250px; margin-bottom: 5px; margin-right: 5px; border-radius: 6px; border: 1px solid var(--el-border-color); overflow: hidden;">
              <div v-if="form.bannerId === item.bannerId" class="angle_mark">
                <span>✓</span>
              </div>
              <img :alt="item.description" :src="item.url"
                   height="160" style="border-radius: 6px 6px 0 0;"
                   width="198" @click="handleClickAd(item)">
              <div style="padding: 5px; display: flex; flex-direction: column">
                <span>{{ item.title }}</span>
                <span>{{ item.path }}</span>
                <div style="display: flex; flex-direction: row; justify-content: space-between; align-items: center">
                  <el-button link size="small" type="primary" @click="handleDelete(item)">删除</el-button>
                  <el-switch v-model="item.visible"
                             active-color="#13ce66" inactive-color="#ff4949"
                             style="margin-top: 5px" @click="handleClickSwitch(item)"></el-switch>
                </div>
              </div>
            </div>
          </div>

          <el-pagination
              v-model:current-page="params.pageNo"
              v-model:page-size="params.pageSize"
              :page-sizes="[10, 20, 30]"
              :pager-count="5"
              :total="adData.total"
              background
              class="mt15"
              layout="total, sizes, prev, pager, next, jumper"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
          >
          </el-pagination>
        </div>
        <div v-if="showEdit"
             style="min-width: 300px; width: 300px; height: 100%; background: #f7f7f7; border-radius: 5px; padding: 10px">
          <el-form ref="formRef" :model="form" :rules="rules" label-width="70px" size="default">
            <el-form-item label-width='0' prop="url">
              <img v-if="form.url" :alt="form.description" :src="form.url" style="width: 100%">
              <div v-else style="width: 100%; display: flex; justify-content: center; padding: 10px 0 10px 0"
                   @click="mediaRef.show">
                <el-icon color="#dedfe0" size="55">
                  <ele-Plus/>
                </el-icon>
              </div>
            </el-form-item>
            <el-form-item label="标题" prop="title">
              <el-input v-model="form.title" clearable placeholder="请输入标题"></el-input>
            </el-form-item>
            <el-form-item label="描述" prop="description">
              <el-input v-model="form.description" clearable placeholder="请输入描述"></el-input>
            </el-form-item>
            <el-form-item label="跳转类型" prop="path">
              <el-select v-model="form.type" clearable placeholder="请选择跳转类型" style="width: 100%;">
                <el-option v-for="item in typeList" :key="item.value" :label="item.label"
                           :value="item.value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="跳转页" prop="path">
              <el-input v-model="form.path" clearable placeholder="请输入跳转页"></el-input>
            </el-form-item>
            <el-form-item label="排序" prop="sort">
              <el-input-number v-model="form.sort" clearable min="0" placeholder="请输入排序"
                               style="width: 100%;"></el-input-number>
            </el-form-item>
            <el-form-item label="开始时间" prop="startTime">
              <el-date-picker v-model="form.startTime"
                              clearable
                              placeholder="请选择开始时间"
                              style="width: 100%"
                              type="datetime"
                              value-format="YYYY-MM-DD HH:mm:ss"></el-date-picker>
            </el-form-item>
            <el-form-item label="结束时间" prop="endTime">
              <el-date-picker v-model="form.endTime"
                              :default-time="new Date(2000, 1, 1, 23, 59, 59)"
                              clearable
                              placeholder="请选择结束时间" style="width: 100%"
                              type="datetime"
                              value-format="YYYY-MM-DD HH:mm:ss"></el-date-picker>
            </el-form-item>
            <el-form-item label="是否显示" prop="visible">
              <el-switch v-model="form.visible" active-color="#13ce66"
                         inactive-color="#ff4949"></el-switch>
            </el-form-item>
          </el-form>
          <el-button style="width: 100%; margin-top: 30px" type="primary" @click="onSubmit()">提交</el-button>
        </div>
      </div>
    </el-card>
    <MediaLibraryDialog ref="mediaRef" v-model:value="mediaData"/>
  </div>
</template>

<script lang="ts" name="appletAdConfig" setup>
import {useRoute} from 'vue-router'
import {defineAsyncComponent, onMounted, ref, watch} from "vue";
import {useAppletBannerApi} from "/@/api/applet/banner";
import {ElLoading, ElMessage, ElMessageBox} from "element-plus";

const route = useRoute()
const mediaRef = ref()
const formRef = ref()
const MediaLibraryDialog = defineAsyncComponent(() => import('/@/components/mediaLibrary/mediaLibraryDialog.vue'))
const appletBannerApi = useAppletBannerApi()

const adInfo = route.query
const adData = ref({} as any)
const form = ref({
  bannerId: '',
  url: '',
  path: '',
  title: '',
  description: '',
  sort: 0,
  type: '',
  visible: true,
  position: adInfo.position,
  startTime: '',
  endTime: ''
})
const showEdit = ref(false)
const mediaData = ref({} as any)
const typeList = [
  {
    label: 'tabBar 页面',
    value: 1
  },
  {
    label: '保留当前页面，跳转到其他页面 ',
    value: 2
  },
  {
    label: '关闭当前页面，跳转到其他页面',
    value: 3
  },
  {
    label: '打开放大',
    value: 4
  }
]

const params = {
  pageNo: 1,
  pageSize: 10,
  search: '',
  position: adInfo.position,
}

const rules = {
  url: [
    {required: true, message: '请选择图片', trigger: 'blur'}
  ]
}

const onSearch = () => {
  params.pageNo = 1;
  getData()
}

const getData = async () => {
  const loading = ElLoading.service({background: 'rgba(0, 0, 0, 0.3)'})
  adData.value = await appletBannerApi.findPage(params).finally(() => {
    loading.close()
  })
}

const addAd = () => {
  initForm()
  showEdit.value = true
}

const onSubmit = () => {
  formRef.value.validate(async (valid: any) => {
    if (!valid) return;

    const data = {...form.value};
    data.position = adInfo.position
    if (form.value.bannerId) {
      await appletBannerApi.update(data)
    } else {
      await appletBannerApi.add(data)
    }
    getData()
  })
}

const handleClickAd = (item: any) => {
  if (form.value.bannerId === item.bannerId) {
    initForm()
    showEdit.value = false
    return
  }
  form.value = {...item}
  showEdit.value = true
}

const handleClickSwitch = async (item: any) => {
  if (item.visible) {
    await appletBannerApi.visible(item.bannerId)
  } else {
    await appletBannerApi.invisible(item.bannerId)
  }
  if (item.bannerId === form.value.bannerId) {
    form.value.visible = item.visible
  }
}

const initForm = () => {
  form.value = {
    bannerId: '',
    url: '',
    path: '',
    title: '',
    description: '',
    sort: 0,
    type: '',
    visible: true,
    position: adInfo.position,
    startTime: '',
    endTime: ''
  }
}

const handleDelete = (item) => {
  ElMessageBox.confirm(`此操作将删除图片：“${item.title}”，是否继续?`, '提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(async () => {
    await appletBannerApi.delete(item.bannerId);
    await getData();
    ElMessage.success('删除成功');
  }).catch(() => {
  });
}

// 分页改变
const handleSizeChange = (val: number) => {
  params.pageSize = val;
  getData();
};
// 分页改变
const handleCurrentChange = (val: number) => {
  params.pageNo = val;
  getData();
};

watch(() => mediaData.value, (val) => {
  form.value.url = val.url
  form.value.description = val.description
})

onMounted(() => {
  getData()
})
</script>

<style lang="scss" scoped>
.angle_mark {
  position: absolute;
  top: -25px;
  right: -25px;
  background-color: #529b2e;
  width: 50px;
  height: 50px;
  transform: rotate(45deg);
  // 角标文字
  span {
    position: absolute;
    display: inline-block;
    color: #fff;
    width: 100%;
    bottom: 0;
    left: 0;
    text-align: center;
    transform: rotate(-45deg);
  }
}
</style>
