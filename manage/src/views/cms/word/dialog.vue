<template>
  <div class="cms-category-dialog-container">
    <el-dialog v-model="state.dialog.isShowDialog" :title="state.dialog.title">
      <el-form ref="dialogFormRef" :model="state.ruleForm" :rules="state.rules" label-width="90px" size="default">
        <el-form-item label="一句话" prop="content">
          <el-input v-model="state.ruleForm.content	" clearable placeholder="请输入一句话名称"></el-input>
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
</template>
<script lang="ts" name="cmsWordDialog" setup>

import {nextTick, reactive, ref} from "vue";
import {useWordApi} from "/@/api/cms/word";

const wordApi = useWordApi();
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
    wordId: '',
    content: '',
  },
  rules: {
    content: [
      {required: true, message: '请输入一句话', trigger: 'blur'},
    ]
  }
})

// 打开弹窗
const openDialog = (type: string, row) => {
  if (type === 'edit') {
    state.dialog.title = '修改一句话';
    state.dialog.submitTxt = '修 改';
    nextTick(() => {
      state.ruleForm = {...row};
      dialogFormRef.value.clearValidate();
    });
  } else {
    state.dialog.title = '新增一句话';
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
  dialogFormRef.value.validate(async (valid: any) => {
    if (!valid) return;

    const data = {...state.ruleForm};

    if (state.dialog.type === 'add') {
      // 新增
      await wordApi.add(data)
    } else {
      // 修改
      await wordApi.update(data.wordId, data)
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
