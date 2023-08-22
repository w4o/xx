<template>
  <div class="system-dic-container layout-padding">
    <el-card class="layout-padding-auto" shadow="hover">
      <div class="mb15">
        <el-input v-model="state.tableData.param.search" clearable placeholder="请输入"
                  size="default" style="max-width: 180px"></el-input>
        <el-button class="ml10" size="default" type="primary" @click="onSearch">
          <el-icon>
            <ele-Search/>
          </el-icon>
          查询
        </el-button>
        <el-button class="ml10" size="default" type="success" @click="onOpenAddWord('add')">
          <el-icon>
            <ele-FolderAdd/>
          </el-icon>
          新增一句话
        </el-button>
      </div>
      <el-table v-loading="state.tableData.loading" :data="state.tableData.data" style="width: 100%">
        <el-table-column label="序号" type="index" width="60"/>
        <el-table-column label="一句话" prop="content" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="100">
          <template #default="scope">
            <el-button size="small" text type="primary" @click="onOpenEditWord('edit', scope.row)">修改</el-button>
            <el-button size="small" text type="primary" @click="onRowDel(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          v-model:current-page="state.tableData.param.pageNo"
          v-model:page-size="state.tableData.param.pageSize"
          :page-sizes="[10, 20, 30]"
          :pager-count="5"
          :total="state.tableData.total"
          background
          class="mt15"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="onHandleSizeChange"
          @current-change="onHandleCurrentChange"
      >
      </el-pagination>
    </el-card>
    <WordDialog ref="wordDialogRef" @refresh="getTableData()"/>
  </div>
</template>

<script lang="ts" name="cmsWord" setup>

import {defineAsyncComponent, onMounted, reactive, ref} from "vue";
import {useWordApi} from "/@/api/cms/word";
import {ElMessage, ElMessageBox} from "element-plus";

const wordApi = useWordApi()
// 引入组件
const WordDialog = defineAsyncComponent(() => import('./dialog.vue'))

// 定义变量内容
const wordDialogRef = ref();
const state = reactive({
  tableData: {
    param: {
      pageNo: 1,
      pageSize: 10,
      search: ''
    },
    data: [],
    total: 0,
    loading: false
  }
})

// 初始化表格数据
const getTableData = async () => {
  state.tableData.loading = true;
  const {records, total} = await wordApi.findPage(state.tableData.param).finally(() => {
    state.tableData.loading = false;
  });
  state.tableData.data = records;
  state.tableData.total = total;
}
const onSearch = () => {
  state.tableData.param.pageNo = 1;
  getTableData();
};
// 打开新增一句话弹窗
const onOpenAddWord = (type: string) => {
  wordDialogRef.value?.openDialog(type)
}
// 打开编辑一句话弹窗
const onOpenEditWord = (type: string, row) => {
  wordDialogRef.value?.openDialog(type, row)
}
// 删除一句话
const onRowDel = (row) => {
  ElMessageBox.confirm(`此操作将永久删除一句话：“${row.content}”，是否继续?`, '提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(async () => {
    await wordApi.delete(row.wordId);
    await getTableData();
    ElMessage.success('删除成功');
  }).catch(() => {
  });
}

// 分页改变
const onHandleSizeChange = (val: number) => {
  state.tableData.param.pageSize = val;
  getTableData();
};
// 分页改变
const onHandleCurrentChange = (val: number) => {
  state.tableData.param.pageNo = val;
  getTableData();
};
// 页面加载时
onMounted(() => {
  getTableData();
});
</script>

<style lang="scss" scoped>
.image-slot {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: var(--el-fill-color-light);
  color: var(--el-text-color-secondary);
  font-size: 30px;
}
</style>
