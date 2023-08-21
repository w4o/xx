<template>
  <div class="cms-category-dialog-container">
    <el-dialog v-model="state.dialog.isShowDialog" :title="state.dialog.title">
      <el-form ref="dialogFormRef" :model="state.ruleForm" :rules="state.rules" label-width="90px" size="default">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="state.ruleForm.name" clearable placeholder="请输入分类名称"></el-input>
        </el-form-item>
        <el-form-item label="分类描述" prop="description">
          <el-input v-model="state.ruleForm.description" clearable placeholder="请输入分类描述"
                    type="textarea"></el-input>
        </el-form-item>
        <el-form-item label="特色图片" prop="thumbnail">
          <div
              style="border: 1px solid #dedfe0; border-radius: 5px; display: flex; justify-content: center; align-items: center; width: 55px; height: 55px"
              @click="mediaRef.show()">
            <img v-if="mediaData?.mediaId" :alt="mediaData.description" :src="mediaData.url" height="55"
                 style="border-radius: 5px"
                 width="55">
            <el-icon v-else color="#dedfe0" size="35">
              <ele-Plus/>
            </el-icon>
          </div>
        </el-form-item>
      </el-form>
      <template #footer>
				<span class="dialog-footer">
					<el-button size="default" @click="onCancel">取 消</el-button>
					<el-button size="default" type="primary" @click="onSubmit">{{ state.dialog.submitTxt }}</el-button>
				</span>
      </template>
    </el-dialog>
  </div>

  <MediaLibraryDialog ref="mediaRef" v-model:value="mediaData"/>
</template>
<script lang="ts" name="cmsCategoryDialog" setup>

import {defineAsyncComponent, nextTick, reactive, ref} from "vue";
import {useCategoryApi} from "/@/api/cms/category";

const MediaLibraryDialog = defineAsyncComponent(() => import('/@/components/mediaLibrary/mediaLibraryDialog.vue'))

const categoryApi = useCategoryApi();
// 定义子组件向父组件传值/事件
const emit = defineEmits(['refresh']);
const dialogFormRef = ref()
const state = reactive({
  dialog: {
    isShowDialog: false,
    title: '',
    submitTxt: '',
    type: '',
  },
  ruleForm: {
    categoryId: '',
    name: '',
    description: '',
    thumbnail: '',
  },
  rules: {
    name: [
      {required: true, message: '请输入分类名称', trigger: 'blur'},
      {min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur'},
    ],
    description: [
      {required: true, message: '请输入分类描述', trigger: 'blur'},
      {min: 2, max: 200, message: '长度在 2 到 200 个字符', trigger: 'blur'},
    ]
  }
})

const mediaData = ref({} as any)
const mediaRef = ref()

// 打开弹窗
const openDialog = (type: string, row: RowCategoryType) => {
  if (type === 'edit') {
    state.dialog.title = '修改分类';
    state.dialog.submitTxt = '修 改';
    nextTick(() => {
      state.ruleForm = {...row};
      dialogFormRef.value.clearValidate();
    });
  } else {
    state.dialog.title = '新增分类';
    state.dialog.submitTxt = '新 增';
    // 清空表单，此项需加表单验证才能使用
    nextTick(() => {
      dialogFormRef.value.resetFields();
    });
  }
  state.dialog.type = type;
  state.dialog.isShowDialog = true;
  mediaData.value = {}
};
// 关闭弹窗
const closeDialog = () => {
  state.dialog.isShowDialog = false;
};
// 取消
const onCancel = () => {
  closeDialog();
};
// 提交
const onSubmit = () => {
  dialogFormRef.value.validate(async (valid: any) => {
    if (!valid) return;

    const data = {...state.ruleForm};
    data.thumbnail = mediaData.value.url

    if (state.dialog.type === 'add') {
      // 新增
      await categoryApi.add(data)
    } else {
      // 修改
      await categoryApi.update(data.categoryId, data)
    }
    closeDialog();
    emit('refresh');
  });
};
// 暴露变量
defineExpose({
  openDialog,
});
</script>
