<template>
  <div class="system-dic-container layout-padding">
    <el-card class="layout-padding-auto" shadow="hover">
      <div class="cms-tag-search mb15">
        <el-input v-model="state.tableData.param.search" clearable placeholder="请输入标签名称或描述"
                  size="default" style="max-width: 180px"></el-input>
        <el-button class="ml10" size="default" type="primary" @click="onSearch">
          <el-icon>
            <ele-Search/>
          </el-icon>
          查询
        </el-button>
        <el-button class="ml10" size="default" type="success" @click="onOpenAddTag('add')">
          <el-icon>
            <ele-FolderAdd/>
          </el-icon>
          新增标签
        </el-button>
      </div>
      <el-table v-loading="state.tableData.loading" :data="state.tableData.data" style="width: 100%">
        <el-table-column label="序号" type="index" width="60"/>
        <el-table-column label="特色图片" prop="thumbnail" show-overflow-tooltip>
          <template #default="scope">
            <el-image :src="scope.row.thumbnail" fit="cover" style="width: 50px; height: 50px">
              <template #error>
                <div class="image-slot">
                  <el-icon>
                    <icon-picture/>
                  </el-icon>
                </div>
              </template>
            </el-image>
          </template>
        </el-table-column>
        <el-table-column label="标签名称" prop="name" show-overflow-tooltip></el-table-column>
        <el-table-column label="标签描述" prop="description" show-overflow-tooltip></el-table-column>
        <el-table-column label="文章数量" prop="postCount" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="100">
          <template #default="scope">
            <el-button size="small" text type="primary" @click="onOpenEditTag('edit', scope.row)">修改</el-button>
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
    <TagDialog ref="tagDialogRef" @refresh="getTableData()"/>
  </div>
</template>

<script lang="ts" name="cmsTag" setup>

import {defineAsyncComponent, onMounted, reactive, ref} from "vue";
import {useTagApi} from "/@/api/cms/tag";
import {ElMessage, ElMessageBox} from "element-plus";
import {Picture as IconPicture} from '@element-plus/icons-vue'

const tagApi = useTagApi()
// 引入组件
const TagDialog = defineAsyncComponent(() => import('./dialog.vue'))

// 定义变量内容
const tagDialogRef = ref();
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
  const {records, total} = await tagApi.findPage(state.tableData.param).finally(() => {
    state.tableData.loading = false;
  });
  state.tableData.data = records;
  state.tableData.total = total;
}
const onSearch = () => {
  state.tableData.param.pageNo = 1;
  getTableData();
};
// 打开新增标签弹窗
const onOpenAddTag = (type: string) => {
  tagDialogRef.value?.openDialog(type)
}
// 打开编辑标签弹窗
const onOpenEditTag = (type: string, row) => {
  tagDialogRef.value?.openDialog(type, row)
}
// 删除标签
const onRowDel = (row) => {
  ElMessageBox.confirm(`此操作将永久删除标签：“${row.name}”，是否继续?`, '提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning',
  }).then(async () => {
    await tagApi.delete(row.tagId);
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
