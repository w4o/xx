<template>
  <div class="dynamic-form-container layout-pd">
    <el-card shadow="hover">
      <el-form ref="dialogFormRef" :model="state.ruleForm" :rules="state.rules" label-width="90px" size="default">
        <el-form-item label="标题" prop="title">
          <el-input v-model="state.ruleForm.title" clearable placeholder="请输入分类名称"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="state.ruleForm.status"
                     clearable
                     placeholder="请选择状态"
                     style="width: 100%;">
            <el-option :value="1" label="草稿"></el-option>
            <el-option :value="2" label="发布"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分类" prop="categoryIds">
          <el-select v-model="state.ruleForm.categoryIds"
                     clearable
                     multiple
                     placeholder="请选择分类"
                     style="width: 100%;">
            <el-option v-for="item in categoryList" :key="item.id" :label="item.name"
                       :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标签" prop="tagIds">
          <el-select v-model="state.ruleForm.tags"
                     :loading="tagLoading"
                     :remote-method="searchTag"
                     allow-create
                     clearable
                     default-first-option
                     filterable
                     multiple
                     placeholder="请选择标签"
                     remote
                     reserve-keyword
                     style="width: 100%;">
            <el-option v-for="item in tagList" :key="item.id" :label="item.name"
                       :value="item.name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="特色图片" prop="thumbnail">
          <div
              style="border: 1px solid #dedfe0; border-radius: 5px; display: flex; justify-content: center; align-items: center; width: 55px; height: 55px"
              @click="mediaRef.show()">
            <img v-if="state.ruleForm.thumbnail" :src="state.ruleForm.thumbnail" alt="" height="55"
                 style="border-radius: 5px"
                 width="55">
            <el-icon v-else color="#dedfe0" size="35">
              <ele-Plus/>
            </el-icon>
          </div>
        </el-form-item>
        <el-form-item label="摘要" prop="summary">
          <el-input v-model="state.ruleForm.summary" :autosize="{ minRows: 1}" placeholder="请输入摘要"
                    type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <Editor v-model:get-html="state.ruleForm.content"/>
        </el-form-item>
      </el-form>
      <div class="mt20" style="display: flex; justify-content: end;">
        <el-button style="width: 100px" type="primary" @click="onSubmit()">提交</el-button>
      </div>
    </el-card>

    <MediaLibraryDialog ref="mediaRef" v-model:value="mediaData"/>
  </div>
</template>

<script lang="ts" name="cmsPostEdit" setup>

import {defineAsyncComponent, nextTick, onMounted, reactive, ref, watch} from "vue";
import {useTagApi} from "/@/api/cms/tag";
import {useCategoryApi} from "/@/api/cms/category";
import {usePostApi} from "/@/api/cms/post";
import {useRoute, useRouter} from 'vue-router'
import {ElLoading} from "element-plus";

const router = useRouter();
const route = useRoute()

const MediaLibraryDialog = defineAsyncComponent(() => import('/@/components/mediaLibrary/mediaLibraryDialog.vue'))
const Editor = defineAsyncComponent(() => import('/@/components/editor/index.vue'));

const tagApi = useTagApi();
const categoryApi = useCategoryApi();
const postApi = usePostApi();

const state = reactive({
  type: '',
  ruleForm: {
    postId: '',
    title: '',
    categoryIds: [],
    tags: [],
    content: '',
    summary: '',
    thumbnail: '',
    status: 1,
  },
  rules: {
    title: [
      {required: true, message: '请输入标题', trigger: 'blur'},
    ]
  }
})

const dialogFormRef = ref()
const mediaRef = ref()

const mediaData = ref({} as any)
const categoryList = ref([] as any)
const tagList = ref([] as any)
const tagLoading = ref(false)

// 打开弹窗
const openDialog = (type: string, row: any) => {
  if (type === 'edit') {
    nextTick(() => {
      state.ruleForm = {...row};
      dialogFormRef.value.clearValidate();
    });
  } else {
    // 清空表单，此项需加表单验证才能使用
    nextTick(() => {
      dialogFormRef.value.resetFields();
    });
  }
  state.type = type;
  mediaData.value = {}
};

// 提交
const onSubmit = () => {
  dialogFormRef.value.validate(async (valid: any) => {
    if (!valid) return;

    const data = {...state.ruleForm};

    if (state.type === 'add') {
      // 新增
      await postApi.add(data)
    } else {
      // 修改
      await postApi.update(data)
    }
    router.back()
  });
};

const searchTag = async (value: string) => {
  if (!value) {
    tagList.value = [];
    return;
  }
  tagLoading.value = true;
  tagList.value = await tagApi.getTag(value).finally(() => {
    tagLoading.value = false;
  });
}

watch(() => mediaData.value, (val) => {
  state.ruleForm.thumbnail = val.url
})

onMounted(async () => {
  categoryList.value = await categoryApi.findTree()
  state.type = route.query.type as string
  if (state.type === 'edit') {
    const loadingInstance = ElLoading.service({background: 'rgba(0, 0, 0, 0.3)'})
    state.ruleForm = await postApi.getPost(route.query.postId as string).then().finally(() => {
      loadingInstance.close()
    })
  }
})

// 暴露变量
defineExpose({
  openDialog,
});
</script>

<style lang="scss" scoped>

</style>
