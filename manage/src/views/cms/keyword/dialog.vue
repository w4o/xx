<template>
  <div class="cms-category-dialog-container">
    <el-dialog v-model="state.dialog.isShowDialog" :title="state.dialog.title">
      <el-form ref="dialogFormRef" :model="state.ruleForm" :rules="state.rules" label-width="90px" size="default">
        <el-form-item label="关键字" prop="keyword">
          <el-input v-model="state.ruleForm.keyword	" clearable placeholder="请输入关键字名称"></el-input>
        </el-form-item>
        <el-form-item label="文章" prop="postId">
          <el-select v-model="state.ruleForm.postId"
                     :loading="loading"
                     :remote-method="searchPost"
                     clearable
                     default-first-option
                     filterable
                     placeholder="输入文章标题后选择文章"
                     remote
                     reserve-keyword
                     style="width: 100%;">
            <el-option v-for="item in postList" :key="item.postId" :label="item.title"
                       :value="item.postId"></el-option>
          </el-select>
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
<script lang="ts" name="cmsKeywordDialog" setup>

import {nextTick, reactive, ref} from "vue";
import {useKeywordApi} from "/@/api/cms/keyword";
import {usePostApi} from "/@/api/cms/post";

const keywordApi = useKeywordApi();
const postApi = usePostApi();

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
    keywordId: '',
    keyword: '',
    postId: '',
    postTitle: ''
  },
  rules: {
    keyword: [
      {required: true, message: '请输入关键字', trigger: 'blur'},
    ],
    postId: [
      {required: true, message: '请选择文章', trigger: 'blur'},
    ],
  }
})

const loading = ref(false);
const postList = ref([]);

// 打开弹窗
const openDialog = (type: string, row) => {
  if (type === 'edit') {
    state.dialog.title = '修改关键字';
    state.dialog.submitTxt = '修 改';
    nextTick(() => {
      state.ruleForm = {...row};
      dialogFormRef.value.clearValidate();
      getPost(state.ruleForm.postTitle)
    });
  } else {
    state.dialog.title = '新增关键字';
    state.dialog.submitTxt = '新 增';
    // 清空表单，此项需加表单验证才能使用
    nextTick(() => {
      dialogFormRef.value.resetFields();
      getPost('')
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
      await keywordApi.add(data)
    } else {
      // 修改
      await keywordApi.update(data.keywordId, data)
    }
    closeDialog();
    emit('refresh');
  });
};

const getPost = async (value: string) => {
  const params = {
    pageNo: 1,
    pageSize: 10,
  }
  if (value) {
    params['search'] = value
  }
  const {records} = await postApi.findPage(params);
  postList.value = records
}

const searchPost = (value: string) => {
  getPost(value)
}

// 暴露变量
defineExpose({
  openDialog,
});
</script>
