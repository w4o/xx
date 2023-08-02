<template>
  <div class="cms-category-dialog-container">
    <el-dialog :title="state.dialog.title" v-model="state.dialog.isShowDialog">
      <el-form ref="dialogFormRef" :model="state.ruleForm" size="default" label-width="90px" :rules="state.rules">
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="state.ruleForm.name" placeholder="请输入分类名称" clearable></el-input>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="state.ruleForm.sort" :min="0" :max="999" controls-position="right"
                           placeholder="请输入排序"/>
        </el-form-item>
        <el-form-item label="分类描述" prop="description">
          <el-input type="textarea" v-model="state.ruleForm.description" placeholder="请输入分类描述" clearable></el-input>
        </el-form-item>
        <el-form-item label="特色图片" prop="thumbnail">
          <el-upload v-model="state.ruleForm.thumbnail" ></el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
				<span class="dialog-footer">
					<el-button @click="onCancel" size="default">取 消</el-button>
					<el-button type="primary" @click="onSubmit" size="default">{{ state.dialog.submitTxt }}</el-button>
				</span>
      </template>
    </el-dialog>
  </div>
</template>
<script setup lang="ts" name="cmsCategoryDialog">

import {nextTick, reactive, ref} from "vue";
import {useCategoryApi} from "/@/api/cms/category";

const categoryApi = useCategoryApi();
// 定义子组件向父组件传值/事件
const emit = defineEmits(['refresh']);
const dialogFormRef = ref()
const state = reactive({
  dialog: {
    isShowDialog: false,
    title: '',
    submitTxt: '',
    type:'',
  },
  ruleForm: {
    categoryId: '',
    name:'',
    description: '',
    thumbnail: '',
    sort: '',
  },
  rules: {

  }
})

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
  dialogFormRef.value.validate(async (valid:any) => {
    if (!valid) return;

    if (state.dialog.type === 'add') {
      // 新增
      await categoryApi.add(state.ruleForm)
    } else {
      // 修改
      await categoryApi.update(state.ruleForm.categoryId, state.ruleForm)
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
